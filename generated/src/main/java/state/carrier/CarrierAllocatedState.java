package state.carrier;

import entity.Carrier;

import runtime.StubHelper;
import runtime.BusinessObject;
import runtime.exception.FailedEventHandlingException;
import runtime.exception.BusinessObjectNotFoundException;
import org.bouncycastle.util.encoders.Base64;


import org.hyperledger.fabric.contract.Context;

import java.util.function.Supplier;

public class CarrierAllocatedState extends CarrierState {
  //========== Constructor ===========
  public CarrierAllocatedState() {
    super("allocated", StateType.INITIAL);
  }

  //========= Event Handling =========
  //--- Creating Events ---
  @Override
  public void handle_EVcrCarrier(Carrier object, Context ctx) throws FailedEventHandlingException {

    //Changes the state of the current Carrier (object)
    CarrierExistsState newState = new CarrierExistsState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
  }
  //--- Modifying Events ---

  //--- Ending Events ---
}
