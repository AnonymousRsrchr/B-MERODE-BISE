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

public class OrderPreparationState extends OrderState {
  //========== Constructor ===========
  public OrderPreparationState() {
    super("preparation", StateType.ONGOING);
  }

  //========= Event Handling =========
  //--- Creating Events ---
  //--- Modifying Events ---
  @Override
  public void handle_EVorderReady(Order object, Context ctx) throws FailedEventHandlingException {

    //The Order object will be saved with its current properties
    //Before changing state and (possibly) attributes, we check there was no change to the masters
    Order currentLedgerObject = (Order)StubHelper.findBusinessObject(ctx, object.getId());
    if(!object.getManufacturerId_Manufacturer_Order().equals(currentLedgerObject.getManufacturerId_Manufacturer_Order()))
      throw new FailedEventHandlingException("The master of a Business Object (" + object.getId() + ") cannot be changed");
    if(!object.getCustomerId_Customer_Order().equals(currentLedgerObject.getCustomerId_Customer_Order()))
      throw new FailedEventHandlingException("The master of a Business Object (" + object.getId() + ") cannot be changed");
    //Changes the state of the current Order (object)
    OrderDeliveryState newState = new OrderDeliveryState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
    //Trigger Event Handling for Each Master
    //Manufacturer
    Manufacturer manufacturer_Manufacturer_Order = (Manufacturer)StubHelper.findBusinessObject(ctx, object.getManufacturerId_Manufacturer_Order());
    manufacturer_Manufacturer_Order.getCurrentState().handle_EVorderReady(manufacturer_Manufacturer_Order, ctx);
    //Customer
    Customer customer_Customer_Order = (Customer)StubHelper.findBusinessObject(ctx, object.getCustomerId_Customer_Order());
    customer_Customer_Order.getCurrentState().handle_EVorderReady(customer_Customer_Order, ctx);
  }

  //--- Ending Events ---
}
