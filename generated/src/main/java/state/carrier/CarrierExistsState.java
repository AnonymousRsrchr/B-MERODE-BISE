package state.carrier;

import entity.Carrier;

import runtime.StubHelper;
import runtime.BusinessObject;
import runtime.exception.FailedEventHandlingException;
import runtime.exception.BusinessObjectNotFoundException;
import org.bouncycastle.util.encoders.Base64;


import org.hyperledger.fabric.contract.Context;

import java.util.function.Supplier;

public class CarrierExistsState extends CarrierState {
  //========== Constructor ===========
  public CarrierExistsState() {
    super("exists", StateType.ONGOING);
  }

  //========= Event Handling =========
  //--- Creating Events ---
  //--- Modifying Events ---
  @Override
  public void handle_EVrejectShipment(Carrier object, Context ctx) throws FailedEventHandlingException {

    //Changes the state of the current Carrier (object)
    CarrierExistsState newState = new CarrierExistsState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
  }
  @Override
  public void handle_EVshipmentDelivered(Carrier object, Context ctx) throws FailedEventHandlingException {

    //Changes the state of the current Carrier (object)
    CarrierExistsState newState = new CarrierExistsState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
  }
  @Override
  public void handle_EVconfirmDelivery(Carrier object, Context ctx) throws FailedEventHandlingException {

    //Changes the state of the current Carrier (object)
    CarrierExistsState newState = new CarrierExistsState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
  }
  @Override
  public void handle_EVacceptShipment(Carrier object, Context ctx) throws FailedEventHandlingException {

    //Changes the state of the current Carrier (object)
    CarrierExistsState newState = new CarrierExistsState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
  }
  @Override
  public void handle_EVpayShipmentInvoice(Carrier object, Context ctx) throws FailedEventHandlingException {

    //Changes the state of the current Carrier (object)
    CarrierExistsState newState = new CarrierExistsState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
  }
  @Override
  public void handle_EVrequestShipment(Carrier object, Context ctx) throws FailedEventHandlingException {

    //Changes the state of the current Carrier (object)
    CarrierExistsState newState = new CarrierExistsState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
  }
  @Override
  public void handle_EVpickupShipment(Carrier object, Context ctx) throws FailedEventHandlingException {

    //Changes the state of the current Carrier (object)
    CarrierExistsState newState = new CarrierExistsState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
  }
  @Override
  public void handle_EVinvoiceShipment(Carrier object, Context ctx) throws FailedEventHandlingException {

    //Changes the state of the current Carrier (object)
    CarrierExistsState newState = new CarrierExistsState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
  }

  //--- Ending Events ---
  @Override
  public void handle_EVendCarrier(Carrier object, Context ctx) throws FailedEventHandlingException {

    //Check if there are living  dependents before ending the object
    if(StubHelper.hasLivingDependents(ctx, object))
      throw new FailedEventHandlingException("Carrier (" + object.getId() + ") has living dependents and therefore cannot be ended");

    //Changes the state of the current Carrier (object)
    CarrierEndedState newState = new CarrierEndedState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
  }
}
