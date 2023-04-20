package state.shipment;

import entity.Shipment;

import runtime.State;
import runtime.exception.FailedEventHandlingException;

import org.hyperledger.fabric.contract.Context;

public abstract class ShipmentState extends State {
  //========== Constructor ===========
  public ShipmentState(String name, StateType stateType) {
    super(name, stateType);
  }

  //========= Event Handling =========
  public void handle_EVconfirmDelivery(Shipment object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVconfirmDelivery(Shipment, Context)]: Transition not allowed from the current state"); }
  public void handle_EVinvoiceShipment(Shipment object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVinvoiceShipment(Shipment, Context)]: Transition not allowed from the current state"); }
  public void handle_EVpayShipmentInvoice(Shipment object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVpayShipmentInvoice(Shipment, Context)]: Transition not allowed from the current state"); }
  public void handle_EVshipmentDelivered(Shipment object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVshipmentDelivered(Shipment, Context)]: Transition not allowed from the current state"); }
  public void handle_EVacceptShipment(Shipment object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVacceptShipment(Shipment, Context)]: Transition not allowed from the current state"); }
  public void handle_EVrequestShipment(Shipment object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVrequestShipment(Shipment, Context)]: Transition not allowed from the current state"); }
  public void handle_EVpickupShipment(Shipment object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVpickupShipment(Shipment, Context)]: Transition not allowed from the current state"); }
  public void handle_EVrejectShipment(Shipment object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVrejectShipment(Shipment, Context)]: Transition not allowed from the current state"); }
}