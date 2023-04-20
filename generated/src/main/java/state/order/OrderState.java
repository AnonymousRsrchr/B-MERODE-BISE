package state.order;

import entity.Order;

import runtime.State;
import runtime.exception.FailedEventHandlingException;

import org.hyperledger.fabric.contract.Context;

public abstract class OrderState extends State {
  //========== Constructor ===========
  public OrderState(String name, StateType stateType) {
    super(name, stateType);
  }

  //========= Event Handling =========
  public void handle_EVrejectOrder(Order object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVrejectOrder(Order, Context)]: Transition not allowed from the current state"); }
  public void handle_EVinvoiceOrder(Order object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVinvoiceOrder(Order, Context)]: Transition not allowed from the current state"); }
  public void handle_EVshipmentDelivered(Order object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVshipmentDelivered(Order, Context)]: Transition not allowed from the current state"); }
  public void handle_EVorderReady(Order object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVorderReady(Order, Context)]: Transition not allowed from the current state"); }
  public void handle_EVpayOrderInvoice(Order object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVpayOrderInvoice(Order, Context)]: Transition not allowed from the current state"); }
  public void handle_EVplaceOrder(Order object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVplaceOrder(Order, Context)]: Transition not allowed from the current state"); }
  public void handle_EVconfirmDelivery(Order object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVconfirmDelivery(Order, Context)]: Transition not allowed from the current state"); }
  public void handle_EVinvoiceShipment(Order object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVinvoiceShipment(Order, Context)]: Transition not allowed from the current state"); }
  public void handle_EVpayShipmentInvoice(Order object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVpayShipmentInvoice(Order, Context)]: Transition not allowed from the current state"); }
  public void handle_EVacceptShipment(Order object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVacceptShipment(Order, Context)]: Transition not allowed from the current state"); }
  public void handle_EVrequestShipment(Order object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVrequestShipment(Order, Context)]: Transition not allowed from the current state"); }
  public void handle_EVpickupShipment(Order object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVpickupShipment(Order, Context)]: Transition not allowed from the current state"); }
  public void handle_EVrejectShipment(Order object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVrejectShipment(Order, Context)]: Transition not allowed from the current state"); }
}