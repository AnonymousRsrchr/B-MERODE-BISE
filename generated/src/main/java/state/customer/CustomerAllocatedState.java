package state.customer;

import entity.Customer;

import runtime.StubHelper;
import runtime.BusinessObject;
import runtime.exception.FailedEventHandlingException;
import runtime.exception.BusinessObjectNotFoundException;
import org.bouncycastle.util.encoders.Base64;


import org.hyperledger.fabric.contract.Context;

import java.util.function.Supplier;

public class CustomerAllocatedState extends CustomerState {
  //========== Constructor ===========
  public CustomerAllocatedState() {
    super("allocated", StateType.INITIAL);
  }

  //========= Event Handling =========
  //--- Creating Events ---
  @Override
  public void handle_EVcrCustomer(Customer object, Context ctx) throws FailedEventHandlingException {

    //Changes the state of the current Customer (object)
    CustomerRegisteredState newState = new CustomerRegisteredState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
  }
  //--- Modifying Events ---

  //--- Ending Events ---
}
