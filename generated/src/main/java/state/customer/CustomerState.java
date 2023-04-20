package state.customer;

import entity.Customer;

import runtime.State;
import runtime.exception.FailedEventHandlingException;

import org.hyperledger.fabric.contract.Context;

public abstract class CustomerState extends State {
  //========== Constructor ===========
  public CustomerState(String name, StateType stateType) {
    super(name, stateType);
  }

  //========= Event Handling =========
  public void handle_EVrejectOrder(Customer object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVrejectOrder(Customer, Context)]: Transition not allowed from the current state"); }
  public void handle_EVinvoiceOrder(Customer object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVinvoiceOrder(Customer, Context)]: Transition not allowed from the current state"); }
  public void handle_EVshipmentDelivered(Customer object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVshipmentDelivered(Customer, Context)]: Transition not allowed from the current state"); }
  public void handle_EVorderReady(Customer object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVorderReady(Customer, Context)]: Transition not allowed from the current state"); }
  public void handle_EVcrCustomer(Customer object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVcrCustomer(Customer, Context)]: Transition not allowed from the current state"); }
  public void handle_EVpayOrderInvoice(Customer object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVpayOrderInvoice(Customer, Context)]: Transition not allowed from the current state"); }
  public void handle_EVendCustomer(Customer object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVendCustomer(Customer, Context)]: Transition not allowed from the current state"); }
  public void handle_EVplaceOrder(Customer object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVplaceOrder(Customer, Context)]: Transition not allowed from the current state"); }
  public void handle_EVconfirmDelivery(Customer object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVconfirmDelivery(Customer, Context)]: Transition not allowed from the current state"); }
  public void handle_EVinvoiceShipment(Customer object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVinvoiceShipment(Customer, Context)]: Transition not allowed from the current state"); }
  public void handle_EVpayShipmentInvoice(Customer object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVpayShipmentInvoice(Customer, Context)]: Transition not allowed from the current state"); }
  public void handle_EVacceptShipment(Customer object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVacceptShipment(Customer, Context)]: Transition not allowed from the current state"); }
  public void handle_EVrequestShipment(Customer object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVrequestShipment(Customer, Context)]: Transition not allowed from the current state"); }
  public void handle_EVpickupShipment(Customer object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVpickupShipment(Customer, Context)]: Transition not allowed from the current state"); }
  public void handle_EVrejectShipment(Customer object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVrejectShipment(Customer, Context)]: Transition not allowed from the current state"); }
}