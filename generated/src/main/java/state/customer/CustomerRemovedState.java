package state.customer;

import entity.Customer;

import runtime.StubHelper;
import runtime.BusinessObject;
import runtime.exception.FailedEventHandlingException;
import runtime.exception.BusinessObjectNotFoundException;
import org.bouncycastle.util.encoders.Base64;


import org.hyperledger.fabric.contract.Context;

import java.util.function.Supplier;

public class CustomerRemovedState extends CustomerState {
  //========== Constructor ===========
  public CustomerRemovedState() {
    super("removed", StateType.FINAL);
  }

  //========= Event Handling =========
  //--- Creating Events ---
  //--- Modifying Events ---

  //--- Ending Events ---
}
