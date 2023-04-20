/*
 * SPDX-License-Identifier: Apache-2.0
 */

import com.owlike.genson.Genson;
import org.hyperledger.fabric.shim.ChaincodeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import runtime.CollaborationSetup;
import runtime.exception.CollaborationSetupException;
import runtime.exception.FailedEventHandlingException;
import runtime.*;
import event.EventsMapping;
import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.Contract;
import org.hyperledger.fabric.contract.annotation.Default;
import org.hyperledger.fabric.contract.annotation.Transaction;
import org.hyperledger.fabric.contract.annotation.Contact;
import org.hyperledger.fabric.contract.annotation.Info;
import org.hyperledger.fabric.contract.annotation.License;
import runtime.BusinessObject;
import runtime.JsonConverter;
import runtime.StubHelper;
import runtime.exception.BusinessEventNotFoundException;
import org.bouncycastle.util.encoders.Base64;


import java.util.ArrayList;

import static org.hyperledger.fabric.shim.ResponseUtils.newErrorResponse;

@Contract(name = "BMerodeCollaboration",
        info = @Info(title = "BMerodeCollaboration",
                description = "Runs a BMerode Collaboration",
                version = "0.1",
                license =
                @License(name = "Apache-2.0",
                        url = ""),
                contact =  @Contact(email = "xyz@gmail.com",
                        name = "xyz",
                        url = "https://xyz.me")))
@Default
public class CollaborationContract implements ContractInterface {
  public  CollaborationContract() {}

  private Logger logger = LoggerFactory.getLogger(CollaborationContract.class);

  @Transaction(intent = Transaction.TYPE.EVALUATE)
  public String getSenderPk(Context ctx) {
    byte[] encodedPK = ctx.getClientIdentity().getX509Certificate().getPublicKey().getEncoded();
    String plainPK = new String(Base64.encode(encodedPK));

    return plainPK;
  }

  private void logCaller(Context ctx) {
    byte[] encodedPK = ctx.getClientIdentity().getX509Certificate().getPublicKey().getEncoded();
    String plainPK = new String(Base64.encode(encodedPK));

    logger.info("Sender PK: {}", plainPK);
  }

  @Transaction(intent = Transaction.TYPE.SUBMIT)
  public void initWithSenderAsParticipantsHandler(Context ctx) {
    init(ctx, getSenderPk(ctx));
  }

  @Transaction(intent = Transaction.TYPE.SUBMIT)
  public void init(Context ctx, String participantsHandlerPK) {
    if(participantsHandlerPK == null || participantsHandlerPK.equals(""))
      throw new ChaincodeException("The Public Key of the Participants Handler must be Provided to Setup the Collaboration");
    String currentSetup = ctx.getStub().getStringState("BMERODE.COLLABORATION_SETUP");
    if(currentSetup != null && currentSetup.length() != 0)
      throw new ChaincodeException("[CollaborationContract.init(String)]: Initial Setup already Exists");

    CollaborationSetup setup = new CollaborationSetup(false, participantsHandlerPK);
    ctx.getStub().putStringState("BMERODE.COLLABORATION_SETUP", setup.toJsonString());
  }

  @Transaction(intent = Transaction.TYPE.EVALUATE)
  public boolean isInitialized(Context ctx) {
    String currentSetup = ctx.getStub().getStringState("BMERODE.COLLABORATION_SETUP");
    return (currentSetup != null && currentSetup.length() != 0);
  }

  @Transaction(intent = Transaction.TYPE.SUBMIT)
  public void markCollaborationAsReady(Context ctx) {
    if(!PermissionsHandler.setupAllowed(ctx))
      throw new ChaincodeException("CollaborationContract.markCollaborationAsReady(Context): Only the ParticipantsHandler can mark the collaboration as ready");


    CollaborationSetup setup = CollaborationSetup.load(ctx);
    setup.markAsReady();
    ctx.getStub().putStringState("BMERODE.COLLABORATION_SETUP", setup.toJsonString());
  }

  @Transaction(intent = Transaction.TYPE.EVALUATE)
  public String getBusinessObject(Context ctx, String id) {
    //logCaller(ctx);
    BusinessObject bo = null;
    try {
      bo = StubHelper.findBusinessObject(ctx, id);
    } catch(Exception e) {
      throw new ChaincodeException("Failed to Retrieve BO: " + e.getClass().getSimpleName() + ": " + e.getMessage());
    }

    return JsonConverter.toRecordJson(bo);
  }

  @Transaction(intent = Transaction.TYPE.EVALUATE)
  public String getBusinessObjectHistory(Context ctx, String id) {
    logCaller(ctx);
    ArrayList<String> boVersions = null;
    try {
      StubHelper.findBusinessObjectHistory(ctx, id);
    } catch(Exception e) {
      throw new ChaincodeException(e.getClass().getSimpleName() + ": " + e.getMessage());
    }
    Genson g = new Genson();
    return g.serialize(boVersions);
  }

  @Transaction(intent = Transaction.TYPE.SUBMIT)
  public String handleEvent(Context ctx, String eventName, String payloadJson) {
    logCaller(ctx);
    //Based on event name, possible to retrieve all the  methods that it will invoke
    //It is also to find which BO is the owner
    //So in the end, all we need is the eventName and the payload

    logger.info("=====> Receiving Request to Handle Event: " + eventName);
    BusinessEvent event = null;
    try {
      event = EventsMapping.instance().getBusinessEvent(eventName);
    } catch (Exception e) {
      throw new ChaincodeException("Can't handle event " + eventName + " (event not found)");
    }
    logger.info("=====> Event Identifier: " + eventName + "(ownerType: " + event.getOwnerBOT().getSimpleName() + ")");

    BusinessObject boToReturn;
    try {
      boToReturn = event.handle(ctx, payloadJson);
    } catch (FailedEventHandlingException e) {
      logger.error(e.getMessage());
      throw new ChaincodeException(e.getClass().getSimpleName() + ": " + e.getMessage());
      //throw new RuntimeException("--> Could Not Handle Event " + eventName + " (" + e + ")");
    }

    logger.info("=====> Event " + eventName + " Successfully Handled");

    return boToReturn.toJsonString();
  }
}