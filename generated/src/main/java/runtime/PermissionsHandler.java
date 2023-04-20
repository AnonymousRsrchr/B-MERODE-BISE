package runtime;

import runtime.exception.BusinessEventNotFoundException;
import runtime.exception.FailedEventHandlingException;
import org.bouncycastle.util.encoders.Base64;
import org.hyperledger.fabric.contract.Context;
import runtime.exception.CollaborationSetupException;

public class PermissionsHandler {
  public static boolean setupAllowed(Context ctx) {
    CollaborationSetup setup = null;
    try {
      setup = CollaborationSetup.load(ctx);
    } catch(CollaborationSetupException e) {
      System.out.println(e);
      return false;
    }

    byte[] encodedPK = ctx.getClientIdentity().getX509Certificate().getPublicKey().getEncoded();
    String plainPK = new String(Base64.encode(encodedPK));

    return setup.getParticipantsHandlerPK().equals(plainPK);
  }

  public static void validateEPTPermissions(Context ctx, String businessEventName, RuntimeEpt runtimeEpt) throws FailedEventHandlingException {
    CollaborationSetup setup = CollaborationSetup.fromJson(ctx.getStub().getStringState("BMERODE.COLLABORATION_SETUP"));
    if(!setup.getSetupFinalized())
      return;

    //Convert sender PK To String
    byte[] encodedPK = ctx.getClientIdentity().getX509Certificate().getPublicKey().getEncoded();
    String senderPlainPK = new String(Base64.encode(encodedPK));
    //Fetch the BO corresponding to the sender and throw exception if not found
    BusinessObject sender = null;
    try {
      sender = StubHelper.findParticipant(ctx, senderPlainPK);
    } catch(BusinessEventNotFoundException e) {
      throw new FailedEventHandlingException("There is no participant registered with public key " + senderPlainPK + " in the BMERODE collaboration: operation denied");
    }
    //Get permission from EPT
    Permission permission = runtimeEpt.getPermission(businessEventName, sender.getClass().getSimpleName());
    System.out.println("Authorization for " + sender.getClass().getSimpleName() + " to fire business event " + businessEventName + ": " + permission.isAllowed());

    //If permission is denied, throw an exception
    if(permission.isAllowed()) {
      System.out.println("Authorization for " + sender.getClass().getSimpleName() + " to fire business event " + businessEventName + " granted");
    } else {
      throw new FailedEventHandlingException("Authorization for " + sender.getClass().getSimpleName() + " to fire business event " + businessEventName + " denied");
    }

  }
}
