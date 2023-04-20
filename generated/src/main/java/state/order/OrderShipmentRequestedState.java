package state.order;

import entity.Order;
import entity.Customer;
import entity.Manufacturer;

import runtime.StubHelper;
import runtime.BusinessObject;
import runtime.exception.FailedEventHandlingException;
import runtime.exception.BusinessObjectNotFoundException;
import org.bouncycastle.util.encoders.Base64;


import org.hyperledger.fabric.contract.Context;

import java.util.function.Supplier;

public class OrderShipmentRequestedState extends OrderState {
  //========== Constructor ===========
  public OrderShipmentRequestedState() {
    super("shipmentRequested", StateType.ONGOING);
  }

  //========= Event Handling =========
  //--- Creating Events ---
  //--- Modifying Events ---
  @Override
  public void handle_EVacceptShipment(Order object, Context ctx) throws FailedEventHandlingException {

    //The Order object will be saved with its current properties
    //Before changing state and (possibly) attributes, we check there was no change to the masters
    Order currentLedgerObject = (Order)StubHelper.findBusinessObject(ctx, object.getId());
    if(!object.getManufacturerId_Manufacturer_Order().equals(currentLedgerObject.getManufacturerId_Manufacturer_Order()))
      throw new FailedEventHandlingException("The master of a Business Object (" + object.getId() + ") cannot be changed");
    if(!object.getCustomerId_Customer_Order().equals(currentLedgerObject.getCustomerId_Customer_Order()))
      throw new FailedEventHandlingException("The master of a Business Object (" + object.getId() + ") cannot be changed");
    //Changes the state of the current Order (object)
    OrderPreparationState newState = new OrderPreparationState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
    //Trigger Event Handling for Each Master
    //Manufacturer
    Manufacturer manufacturer_Manufacturer_Order = (Manufacturer)StubHelper.findBusinessObject(ctx, object.getManufacturerId_Manufacturer_Order());
    manufacturer_Manufacturer_Order.getCurrentState().handle_EVacceptShipment(manufacturer_Manufacturer_Order, ctx);
    //Customer
    Customer customer_Customer_Order = (Customer)StubHelper.findBusinessObject(ctx, object.getCustomerId_Customer_Order());
    customer_Customer_Order.getCurrentState().handle_EVacceptShipment(customer_Customer_Order, ctx);
  }

  //--- Ending Events ---
  @Override
  public void handle_EVrejectShipment(Order object, Context ctx) throws FailedEventHandlingException {

    //Check if there are living  dependents before ending the object
    if(StubHelper.hasLivingDependents(ctx, object))
      throw new FailedEventHandlingException("Order (" + object.getId() + ") has living dependents and therefore cannot be ended");

    //Changes the state of the current Order (object)
    OrderRejectedState newState = new OrderRejectedState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
    //Trigger Event Handling for Each Master
    //Manufacturer
    Manufacturer manufacturer_Manufacturer_Order = (Manufacturer)StubHelper.findBusinessObject(ctx, object.getManufacturerId_Manufacturer_Order());
    manufacturer_Manufacturer_Order.getCurrentState().handle_EVrejectShipment(manufacturer_Manufacturer_Order, ctx);
    //Customer
    Customer customer_Customer_Order = (Customer)StubHelper.findBusinessObject(ctx, object.getCustomerId_Customer_Order());
    customer_Customer_Order.getCurrentState().handle_EVrejectShipment(customer_Customer_Order, ctx);
  }
}
