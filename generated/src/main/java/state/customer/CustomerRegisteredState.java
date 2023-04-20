package state.customer;

import entity.Customer;

import runtime.StubHelper;
import runtime.BusinessObject;
import runtime.exception.FailedEventHandlingException;
import runtime.exception.BusinessObjectNotFoundException;
import org.bouncycastle.util.encoders.Base64;


import org.hyperledger.fabric.contract.Context;

import java.util.function.Supplier;

public class CustomerRegisteredState extends CustomerState {
  //========== Constructor ===========
  public CustomerRegisteredState() {
    super("registered", StateType.ONGOING);
  }

  //========= Event Handling =========
  //--- Creating Events ---
  //--- Modifying Events ---
  @Override
  public void handle_EVinvoiceOrder(Customer object, Context ctx) throws FailedEventHandlingException {

    //Changes the state of the current Customer (object)
    CustomerInvoicedState newState = new CustomerInvoicedState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
  }
  @Override
  public void handle_EVrejectShipment(Customer object, Context ctx) throws FailedEventHandlingException {

    //Changes the state of the current Customer (object)
    CustomerRegisteredState newState = new CustomerRegisteredState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
  }
  @Override
  public void handle_EVacceptShipment(Customer object, Context ctx) throws FailedEventHandlingException {

    //Changes the state of the current Customer (object)
    CustomerRegisteredState newState = new CustomerRegisteredState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
  }
  @Override
  public void handle_EVinvoiceShipment(Customer object, Context ctx) throws FailedEventHandlingException {

    //Changes the state of the current Customer (object)
    CustomerRegisteredState newState = new CustomerRegisteredState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
  }
  @Override
  public void handle_EVplaceOrder(Customer object, Context ctx) throws FailedEventHandlingException {

    //Changes the state of the current Customer (object)
    CustomerRegisteredState newState = new CustomerRegisteredState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
  }
  @Override
  public void handle_EVrequestShipment(Customer object, Context ctx) throws FailedEventHandlingException {

    //Changes the state of the current Customer (object)
    CustomerRegisteredState newState = new CustomerRegisteredState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
  }
  @Override
  public void handle_EVconfirmDelivery(Customer object, Context ctx) throws FailedEventHandlingException {

    //Changes the state of the current Customer (object)
    CustomerRegisteredState newState = new CustomerRegisteredState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
  }
  @Override
  public void handle_EVrejectOrder(Customer object, Context ctx) throws FailedEventHandlingException {

    //Changes the state of the current Customer (object)
    CustomerRegisteredState newState = new CustomerRegisteredState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
  }
  @Override
  public void handle_EVpickupShipment(Customer object, Context ctx) throws FailedEventHandlingException {

    //Changes the state of the current Customer (object)
    CustomerRegisteredState newState = new CustomerRegisteredState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
  }
  @Override
  public void handle_EVorderReady(Customer object, Context ctx) throws FailedEventHandlingException {

    //Changes the state of the current Customer (object)
    CustomerRegisteredState newState = new CustomerRegisteredState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
  }
  @Override
  public void handle_EVpayShipmentInvoice(Customer object, Context ctx) throws FailedEventHandlingException {

    //Changes the state of the current Customer (object)
    CustomerRegisteredState newState = new CustomerRegisteredState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
  }
  @Override
  public void handle_EVshipmentDelivered(Customer object, Context ctx) throws FailedEventHandlingException {

    //Changes the state of the current Customer (object)
    CustomerRegisteredState newState = new CustomerRegisteredState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
  }

  //--- Ending Events ---
  @Override
  public void handle_EVendCustomer(Customer object, Context ctx) throws FailedEventHandlingException {

    //Check if there are living  dependents before ending the object
    if(StubHelper.hasLivingDependents(ctx, object))
      throw new FailedEventHandlingException("Customer (" + object.getId() + ") has living dependents and therefore cannot be ended");

    //Changes the state of the current Customer (object)
    CustomerRemovedState newState = new CustomerRemovedState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
  }
}
