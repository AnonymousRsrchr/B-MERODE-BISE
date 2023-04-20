package state.manufacturer;

import entity.Manufacturer;

import runtime.StubHelper;
import runtime.BusinessObject;
import runtime.exception.FailedEventHandlingException;
import runtime.exception.BusinessObjectNotFoundException;
import org.bouncycastle.util.encoders.Base64;


import org.hyperledger.fabric.contract.Context;

import java.util.function.Supplier;

public class ManufacturerRegisteredState extends ManufacturerState {
  //========== Constructor ===========
  public ManufacturerRegisteredState() {
    super("registered", StateType.ONGOING);
  }

  //========= Event Handling =========
  //--- Creating Events ---
  //--- Modifying Events ---
  @Override
  public void handle_EVrejectShipment(Manufacturer object, Context ctx) throws FailedEventHandlingException {

    //Changes the state of the current Manufacturer (object)
    ManufacturerRegisteredState newState = new ManufacturerRegisteredState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
  }
  @Override
  public void handle_EVpayOrderInvoice(Manufacturer object, Context ctx) throws FailedEventHandlingException {

    //Changes the state of the current Manufacturer (object)
    ManufacturerRegisteredState newState = new ManufacturerRegisteredState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
  }
  @Override
  public void handle_EVinvoiceShipment(Manufacturer object, Context ctx) throws FailedEventHandlingException {

    //Changes the state of the current Manufacturer (object)
    ManufacturerInvoicedState newState = new ManufacturerInvoicedState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
  }
  @Override
  public void handle_EVacceptShipment(Manufacturer object, Context ctx) throws FailedEventHandlingException {

    //Changes the state of the current Manufacturer (object)
    ManufacturerRegisteredState newState = new ManufacturerRegisteredState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
  }
  @Override
  public void handle_EVplaceOrder(Manufacturer object, Context ctx) throws FailedEventHandlingException {

    //Changes the state of the current Manufacturer (object)
    ManufacturerRegisteredState newState = new ManufacturerRegisteredState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
  }
  @Override
  public void handle_EVrequestShipment(Manufacturer object, Context ctx) throws FailedEventHandlingException {

    //Changes the state of the current Manufacturer (object)
    ManufacturerRegisteredState newState = new ManufacturerRegisteredState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
  }
  @Override
  public void handle_EVconfirmDelivery(Manufacturer object, Context ctx) throws FailedEventHandlingException {

    //Changes the state of the current Manufacturer (object)
    ManufacturerRegisteredState newState = new ManufacturerRegisteredState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
  }
  @Override
  public void handle_EVrejectOrder(Manufacturer object, Context ctx) throws FailedEventHandlingException {

    //Changes the state of the current Manufacturer (object)
    ManufacturerRegisteredState newState = new ManufacturerRegisteredState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
  }
  @Override
  public void handle_EVpickupShipment(Manufacturer object, Context ctx) throws FailedEventHandlingException {

    //Changes the state of the current Manufacturer (object)
    ManufacturerRegisteredState newState = new ManufacturerRegisteredState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
  }
  @Override
  public void handle_EVorderReady(Manufacturer object, Context ctx) throws FailedEventHandlingException {

    //Changes the state of the current Manufacturer (object)
    ManufacturerRegisteredState newState = new ManufacturerRegisteredState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
  }
  @Override
  public void handle_EVshipmentDelivered(Manufacturer object, Context ctx) throws FailedEventHandlingException {

    //Changes the state of the current Manufacturer (object)
    ManufacturerRegisteredState newState = new ManufacturerRegisteredState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
  }
  @Override
  public void handle_EVinvoiceOrder(Manufacturer object, Context ctx) throws FailedEventHandlingException {

    //Changes the state of the current Manufacturer (object)
    ManufacturerRegisteredState newState = new ManufacturerRegisteredState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
  }

  //--- Ending Events ---
  @Override
  public void handle_EVendManufacturer(Manufacturer object, Context ctx) throws FailedEventHandlingException {

    //Check if there are living  dependents before ending the object
    if(StubHelper.hasLivingDependents(ctx, object))
      throw new FailedEventHandlingException("Manufacturer (" + object.getId() + ") has living dependents and therefore cannot be ended");

    //Changes the state of the current Manufacturer (object)
    ManufacturerRemovedState newState = new ManufacturerRemovedState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
  }
}
