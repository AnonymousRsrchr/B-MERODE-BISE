package state.carrier;

import entity.Carrier;

import runtime.State;
import runtime.exception.FailedEventHandlingException;

import org.hyperledger.fabric.contract.Context;

public abstract class CarrierState extends State {
  //========== Constructor ===========
  public CarrierState(String name, StateType stateType) {
    super(name, stateType);
  }

  //========= Event Handling =========
  public void handle_EVconfirmDelivery(Carrier object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVconfirmDelivery(Carrier, Context)]: Transition not allowed from the current state"); }
  public void handle_EVcrCarrier(Carrier object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVcrCarrier(Carrier, Context)]: Transition not allowed from the current state"); }
  public void handle_EVendCarrier(Carrier object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVendCarrier(Carrier, Context)]: Transition not allowed from the current state"); }
  public void handle_EVinvoiceShipment(Carrier object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVinvoiceShipment(Carrier, Context)]: Transition not allowed from the current state"); }
  public void handle_EVpayShipmentInvoice(Carrier object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVpayShipmentInvoice(Carrier, Context)]: Transition not allowed from the current state"); }
  public void handle_EVshipmentDelivered(Carrier object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVshipmentDelivered(Carrier, Context)]: Transition not allowed from the current state"); }
  public void handle_EVacceptShipment(Carrier object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVacceptShipment(Carrier, Context)]: Transition not allowed from the current state"); }
  public void handle_EVrequestShipment(Carrier object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVrequestShipment(Carrier, Context)]: Transition not allowed from the current state"); }
  public void handle_EVpickupShipment(Carrier object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVpickupShipment(Carrier, Context)]: Transition not allowed from the current state"); }
  public void handle_EVrejectShipment(Carrier object, Context ctx) throws FailedEventHandlingException { throw new FailedEventHandlingException("["+this.getClass().getSimpleName()+".handle_EVrejectShipment(Carrier, Context)]: Transition not allowed from the current state"); }
}