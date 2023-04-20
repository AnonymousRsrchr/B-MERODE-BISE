package runtime;

import org.hyperledger.fabric.shim.ChaincodeException;

import permissions.EPT;
import runtime.BusinessObject;
import runtime.CollaborationSetup;
import runtime.JsonConverter;
import runtime.Util;
import runtime.exception.FailedEventHandlingException;
import org.hyperledger.fabric.contract.Context;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class BusinessEvent {

  public enum BusinessEventType {
    CREATE,
    MODIFY,
    END
  }

  private String name;
  private Class<? extends BusinessObject> ownerBOT;
  private BusinessEventType ownerEffectType;

  public BusinessEvent(String name, BusinessEventType typeForOwner, Class<? extends BusinessObject> ownerBOT) {
    this.name = name;
    this.ownerEffectType = typeForOwner;
    this.ownerBOT = ownerBOT;
  }

  public BusinessObject handle(Context ctx, String jsonPayload) throws FailedEventHandlingException {
    switch(this.getEffectType()) {
      case CREATE: {
        System.out.println("=====> Handling Creating Event");
        return this.handleCreatingEvent(ctx, jsonPayload);
      }
      case MODIFY: {
        System.out.println("=====> Handling a Modifying Event");
        return this.handleModifyingEvent(ctx, jsonPayload);
      }
      case END: {
        System.out.println("=====> Handling an Ending Event");
        return this.handleEndingEvent(ctx, jsonPayload);
      }
      default: return null;
    }
  }

  private BusinessObject handleCreatingEvent(Context ctx, String jsonPayload) throws FailedEventHandlingException {
    BusinessObject builtFromJson = this.buildObjectFromJson(jsonPayload);
    CollaborationSetup setup = CollaborationSetup.load(ctx);
    int nextIdNr = setup.getLastId(builtFromJson.getClass().getSimpleName()) + 1;
    builtFromJson.setId(builtFromJson.getClass().getSimpleName() + "#" + nextIdNr);
    rejectIfUnallowedInSetupPhase(ctx, builtFromJson);
    PermissionsHandler.validateEPTPermissions(ctx, this.name, EPT.getInstance());
    System.out.println("=====> Object Build from JSON: " + builtFromJson.toJsonString());
    Method handlingMethod = this.getHandlingMethod(builtFromJson);
    System.out.println("=====> Method to Execute Retrieved: " + handlingMethod.getName());
    try {
      handlingMethod.invoke(builtFromJson.getCurrentState(), builtFromJson, ctx);
      setup.nextId(builtFromJson.getClass().getSimpleName());
      setup.save(ctx);
    } catch (IllegalAccessException e) {
      System.out.println("=====> Failed to Execute Method " + handlingMethod.getName() + "; Reason: \n " + e.getMessage());
      throw new FailedEventHandlingException("[BusinessEvent.handleCreatingEvent(Context, String)]: Could not invoke event handling method: \n" + e.getMessage());
    } catch(InvocationTargetException e) {
      String msg = "Failed to Create new " + this.ownerBOT.getSimpleName() + " using event " + this.name + ": " + e.getCause().getMessage();
      throw new FailedEventHandlingException(msg);
    }

    System.out.println("=====> Method " + handlingMethod.getName() + " Successfully Executed");
    return builtFromJson;
  }

  private BusinessObject handleEndingEvent(Context ctx, String jsonPayload)  {
    //To retrieve: IF of the existing object
    HashMap<String, Object> map = (HashMap<String, Object>)Util.getJsonObject(jsonPayload);
    String id = (String)map.get("id");

    //Check if the object exists
    String objectJson = ctx.getStub().getStringState(id);
    if(objectJson == null || objectJson.length() == 0)
      throw new FailedEventHandlingException("Business Object with ID " + id + " does not exist");

    BusinessObject builtFromJson = this.buildObjectFromJson(JsonConverter.fromRecordJsonToFullJson(id,objectJson));
    rejectIfUnallowedInSetupPhase(ctx, builtFromJson);
    PermissionsHandler.validateEPTPermissions(ctx, this.name, EPT.getInstance());

    System.out.println("----> Object to Remove: " + builtFromJson.toJsonString());

    Method handlingMethod = this.getHandlingMethod(builtFromJson);
    System.out.println("=====> Method to Execute Retrieved: " + handlingMethod.getName());
    try {
      handlingMethod.invoke(builtFromJson.getCurrentState(), builtFromJson, ctx);
    } catch (IllegalAccessException e) {
      System.out.println("=====> Failed to Execute Method " + handlingMethod.getName() + " on BO: " + builtFromJson.toJsonString() + "; Reason: \n " + e.getMessage());
      throw new FailedEventHandlingException("[BusinessEvent.handleEndingEvent(Context, String)]: Could not invoke event handling method: \n" + e.getMessage());
    } catch(InvocationTargetException e) {
      String msg = "Failed to invoke event handling method " + handlingMethod.getName() + " on the BO: " + builtFromJson.toJsonString() + "(reason: " + e.getCause().getMessage() + ")";
      throw new FailedEventHandlingException(msg);
    }

    System.out.println("=====> Method " + handlingMethod.getName() + " Successfully Executed");
    return builtFromJson;
  }

  private BusinessObject handleModifyingEvent(Context ctx, String jsonPayload) throws FailedEventHandlingException {
    //To retrieve: IF of the existing object
    HashMap<String, Object> map = (HashMap<String, Object>)Util.getJsonObject(jsonPayload);
    String id = (String)map.get("id");

    //Check if the object exists
    String objectJson = ctx.getStub().getStringState(id);
    if(objectJson == null || objectJson.length() == 0)
      throw new FailedEventHandlingException("Business Object with ID " + id + " does not exist");

    BusinessObject builtFromJson = this.buildObjectFromJson(JsonConverter.fromRecordJsonToFullJson(id, objectJson));
    rejectIfUnallowedInSetupPhase(ctx, builtFromJson);
    PermissionsHandler.validateEPTPermissions(ctx, this.name, EPT.getInstance());

    Method handlingMethod;
    try {
      handlingMethod = this.getHandlingMethod(builtFromJson);
    } catch(FailedEventHandlingException e) {
      throw new ChaincodeException("Transition " + "ME_" + this.name + " is not available from current state. Object on which method is invoked (current state): " + builtFromJson.toJsonString());
    }
    System.out.println("=====> Method to Execute Retrieved: " + handlingMethod.getName());
    try {
      handlingMethod.invoke(builtFromJson.getCurrentState(), builtFromJson, ctx);
    } catch (IllegalAccessException e) {
      throw new FailedEventHandlingException(e.getMessage());
    } catch(InvocationTargetException e) {
      String msg = "Failed to invoke event handling method " + handlingMethod.getName() + " on the BO: " + builtFromJson.toJsonString() + "(reason: " + e.getCause().getMessage() + ")";
      throw new FailedEventHandlingException(msg);
    }

    System.out.println("=====> Method " + handlingMethod.getName() + " Successfully Executed");
    return builtFromJson;
  }

  private Method getHandlingMethod(BusinessObject bo) throws FailedEventHandlingException {
    String methodName = "handle_" + this.name;
    try {
      return bo.getCurrentState().getClass().getDeclaredMethod(methodName, this.ownerBOT, Context.class);
    } catch (NoSuchMethodException e) {
      throw new FailedEventHandlingException("Transition " + this.name + " not available for BOT in its current state: " + bo.toJsonString());
    }
  }

  private BusinessObject buildObjectFromJson(String json) throws FailedEventHandlingException {
    Method fromJson = null;
    try {
      fromJson = this.ownerBOT.getMethod("fromJson", String.class);
    } catch(NoSuchMethodException e) {
      throw new FailedEventHandlingException("[BusinessEvent.buildObjectFromJson(String)]: Internal Issue: Can't find method to build BusinessObject from JSON");
    }

    BusinessObject builtFromJson = null;
    try {
      builtFromJson = (BusinessObject)fromJson.invoke(null, json);
    } catch (IllegalAccessException | InvocationTargetException e) {
      throw new FailedEventHandlingException("[BusinessEvent.buildObjectFromJson(String)]: Can't invoke fromJson method (" + e.getCause().getMessage() + ")");
    }

    if(builtFromJson == null)
      throw new FailedEventHandlingException("[BusinessEvent.buildObjectFromJson(String)]: Error Deserializing JSON String into " + this.getOwnerBOT().getSimpleName());

    return builtFromJson;
  }

  /**
   * @throws FailedEventHandlingException if we currently are in the setup phase and either:
   * - The client initiating the call is not the ParticipantsHandler
   * - The executed operation is not about creating/modifying a Participant (i.e. BO for which participant is true)
   */
  private void rejectIfUnallowedInSetupPhase(Context ctx, BusinessObject loadedBO) throws FailedEventHandlingException {
    CollaborationSetup setup = CollaborationSetup.fromJson(ctx.getStub().getStringState("BMERODE.COLLABORATION_SETUP"));

    if(!setup.getSetupFinalized()) {
      if(!PermissionsHandler.setupAllowed(ctx))
        throw new FailedEventHandlingException("Event sent by another entity than the ParticipantsHandler during setup");
      if(!loadedBO.isParticipant())
        throw new FailedEventHandlingException("During setup, only participant BO can be created or modified");
    }
  }

  public String getName() { return this.name; }
  public Class<? extends BusinessObject> getOwnerBOT() { return this.ownerBOT; }
  public BusinessEventType  getEffectType() { return this.ownerEffectType; }
}