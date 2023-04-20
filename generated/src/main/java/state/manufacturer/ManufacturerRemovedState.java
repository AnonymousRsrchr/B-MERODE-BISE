package state.manufacturer;

import entity.Manufacturer;

import runtime.StubHelper;
import runtime.BusinessObject;
import runtime.exception.FailedEventHandlingException;
import runtime.exception.BusinessObjectNotFoundException;
import org.bouncycastle.util.encoders.Base64;


import org.hyperledger.fabric.contract.Context;

import java.util.function.Supplier;

public class ManufacturerRemovedState extends ManufacturerState {
  //========== Constructor ===========
  public ManufacturerRemovedState() {
    super("removed", StateType.FINAL);
  }

  //========= Event Handling =========
  //--- Creating Events ---
  //--- Modifying Events ---

  //--- Ending Events ---
}
