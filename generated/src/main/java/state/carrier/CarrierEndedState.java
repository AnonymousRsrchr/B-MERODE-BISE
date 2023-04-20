package state.carrier;

import entity.Carrier;

import runtime.StubHelper;
import runtime.BusinessObject;
import runtime.exception.FailedEventHandlingException;
import runtime.exception.BusinessObjectNotFoundException;
import org.bouncycastle.util.encoders.Base64;


import org.hyperledger.fabric.contract.Context;

import java.util.function.Supplier;

public class CarrierEndedState extends CarrierState {
  //========== Constructor ===========
  public CarrierEndedState() {
    super("ended", StateType.FINAL);
  }

  //========= Event Handling =========
  //--- Creating Events ---
  //--- Modifying Events ---

  //--- Ending Events ---
}
