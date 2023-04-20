package state.manufacturer;

import entity.Manufacturer;

import runtime.State;
import runtime.exception.FailedEventHandlingException;

import org.hyperledger.fabric.contract.Context;

public abstract class ManufacturerState extends State {
  //========== Constructor ===========
  public ManufacturerState(String name, StateType stateType) {
    super(name, stateType);
  }

  //========= Event Handling =========
  public void handle_EVendManufacturer(Manufacturer object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVendManufacturer(Manufacturer, Context)]: Transition not allowed from the current state"); }
  public void handle_EVcrManufacturer(Manufacturer object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVcrManufacturer(Manufacturer, Context)]: Transition not allowed from the current state"); }
  public void handle_EVrejectOrder(Manufacturer object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVrejectOrder(Manufacturer, Context)]: Transition not allowed from the current state"); }
  public void handle_EVinvoiceOrder(Manufacturer object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVinvoiceOrder(Manufacturer, Context)]: Transition not allowed from the current state"); }
  public void handle_EVshipmentDelivered(Manufacturer object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVshipmentDelivered(Manufacturer, Context)]: Transition not allowed from the current state"); }
  public void handle_EVorderReady(Manufacturer object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVorderReady(Manufacturer, Context)]: Transition not allowed from the current state"); }
  public void handle_EVpayOrderInvoice(Manufacturer object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVpayOrderInvoice(Manufacturer, Context)]: Transition not allowed from the current state"); }
  public void handle_EVplaceOrder(Manufacturer object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVplaceOrder(Manufacturer, Context)]: Transition not allowed from the current state"); }
  public void handle_EVconfirmDelivery(Manufacturer object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVconfirmDelivery(Manufacturer, Context)]: Transition not allowed from the current state"); }
  public void handle_EVinvoiceShipment(Manufacturer object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVinvoiceShipment(Manufacturer, Context)]: Transition not allowed from the current state"); }
  public void handle_EVpayShipmentInvoice(Manufacturer object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVpayShipmentInvoice(Manufacturer, Context)]: Transition not allowed from the current state"); }
  public void handle_EVacceptShipment(Manufacturer object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVacceptShipment(Manufacturer, Context)]: Transition not allowed from the current state"); }
  public void handle_EVrequestShipment(Manufacturer object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVrequestShipment(Manufacturer, Context)]: Transition not allowed from the current state"); }
  public void handle_EVpickupShipment(Manufacturer object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVpickupShipment(Manufacturer, Context)]: Transition not allowed from the current state"); }
  public void handle_EVrejectShipment(Manufacturer object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVrejectShipment(Manufacturer, Context)]: Transition not allowed from the current state"); }
}