package state.manufacturer;

import entity.Manufacturer;

import runtime.StubHelper;
import runtime.BusinessObject;
import runtime.exception.FailedEventHandlingException;
import runtime.exception.BusinessObjectNotFoundException;
import org.bouncycastle.util.encoders.Base64;


import org.hyperledger.fabric.contract.Context;

import java.util.function.Supplier;

public class ManufacturerAllocatedState extends ManufacturerState {
  //========== Constructor ===========
  public ManufacturerAllocatedState() {
    super("allocated", StateType.INITIAL);
  }

  //========= Event Handling =========
  //--- Creating Events ---
  @Override
  public void handle_EVcrManufacturer(Manufacturer object, Context ctx) throws FailedEventHandlingException {

    //Changes the state of the current Manufacturer (object)
    ManufacturerRegisteredState newState = new ManufacturerRegisteredState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
  }
  //--- Modifying Events ---

  //--- Ending Events ---
}
