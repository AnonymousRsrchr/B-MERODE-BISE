package state.manufacturer;

import entity.Manufacturer;

import runtime.StubHelper;
import runtime.BusinessObject;
import runtime.exception.FailedEventHandlingException;
import runtime.exception.BusinessObjectNotFoundException;
import org.bouncycastle.util.encoders.Base64;


import org.hyperledger.fabric.contract.Context;

import java.util.function.Supplier;

public class ManufacturerInvoicedState extends ManufacturerState {
  //========== Constructor ===========
  public ManufacturerInvoicedState() {
    super("invoiced", StateType.ONGOING);
  }

  //========= Event Handling =========
  //--- Creating Events ---
  //--- Modifying Events ---
  @Override
  public void handle_EVorderReady(Manufacturer object, Context ctx) throws FailedEventHandlingException {

    //Changes the state of the current Manufacturer (object)
    ManufacturerInvoicedState newState = new ManufacturerInvoicedState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
  }
  @Override
  public void handle_EVinvoiceOrder(Manufacturer object, Context ctx) throws FailedEventHandlingException {

    //Changes the state of the current Manufacturer (object)
    ManufacturerInvoicedState newState = new ManufacturerInvoicedState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
  }
  @Override
  public void handle_EVrejectShipment(Manufacturer object, Context ctx) throws FailedEventHandlingException {

    //Changes the state of the current Manufacturer (object)
    ManufacturerInvoicedState newState = new ManufacturerInvoicedState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
  }
  @Override
  public void handle_EVpayOrderInvoice(Manufacturer object, Context ctx) throws FailedEventHandlingException {

    //Changes the state of the current Manufacturer (object)
    ManufacturerInvoicedState newState = new ManufacturerInvoicedState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
  }
  @Override
  public void handle_EVshipmentDelivered(Manufacturer object, Context ctx) throws FailedEventHandlingException {

    //Changes the state of the current Manufacturer (object)
    ManufacturerInvoicedState newState = new ManufacturerInvoicedState();
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
  public void handle_EVpayShipmentInvoice(Manufacturer object, Context ctx) throws FailedEventHandlingException {

    //Changes the state of the current Manufacturer (object)
    ManufacturerRegisteredState newState = new ManufacturerRegisteredState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
  }
  @Override
  public void handle_EVplaceOrder(Manufacturer object, Context ctx) throws FailedEventHandlingException {

    //Changes the state of the current Manufacturer (object)
    ManufacturerInvoicedState newState = new ManufacturerInvoicedState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
  }
  @Override
  public void handle_EVpickupShipment(Manufacturer object, Context ctx) throws FailedEventHandlingException {

    //Changes the state of the current Manufacturer (object)
    ManufacturerInvoicedState newState = new ManufacturerInvoicedState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
  }
  @Override
  public void handle_EVrejectOrder(Manufacturer object, Context ctx) throws FailedEventHandlingException {

    //Changes the state of the current Manufacturer (object)
    ManufacturerInvoicedState newState = new ManufacturerInvoicedState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
  }
  @Override
  public void handle_EVacceptShipment(Manufacturer object, Context ctx) throws FailedEventHandlingException {

    //Changes the state of the current Manufacturer (object)
    ManufacturerInvoicedState newState = new ManufacturerInvoicedState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
  }
  @Override
  public void handle_EVconfirmDelivery(Manufacturer object, Context ctx) throws FailedEventHandlingException {

    //Changes the state of the current Manufacturer (object)
    ManufacturerInvoicedState newState = new ManufacturerInvoicedState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
  }

  //--- Ending Events ---
}
