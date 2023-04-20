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

public class OrderAllocatedState extends OrderState {
  //========== Constructor ===========
  public OrderAllocatedState() {
    super("allocated", StateType.INITIAL);
  }

  //========= Event Handling =========
  //--- Creating Events ---
  @Override
  public void handle_EVplaceOrder(Order object, Context ctx) throws FailedEventHandlingException {
    //Check if the referenced masters exist
    //Manufacturer
    if(!StubHelper.exists(ctx, object.getManufacturerId_Manufacturer_Order()))
      throw new FailedEventHandlingException("Master with id " + object.getManufacturerId_Manufacturer_Order() + " does not exist");

    //Retrieve Master (manufacturer_Manufacturer_Order)
    Manufacturer manufacturer_Manufacturer_Order = (Manufacturer)StubHelper.findBusinessObject(ctx, object.getManufacturerId_Manufacturer_Order());

    //Customer
    if(!StubHelper.exists(ctx, object.getCustomerId_Customer_Order()))
      throw new FailedEventHandlingException("Master with id " + object.getCustomerId_Customer_Order() + " does not exist");

    //Retrieve Master (customer_Customer_Order)
    Customer customer_Customer_Order = (Customer)StubHelper.findBusinessObject(ctx, object.getCustomerId_Customer_Order());


    //Changes the state of the current Order (object)
    OrderPlacedState newState = new OrderPlacedState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
    //Trigger Event Handling for Each Master
    //Manufacturer
    manufacturer_Manufacturer_Order.getCurrentState().handle_EVplaceOrder(manufacturer_Manufacturer_Order, ctx);

    //Customer
    customer_Customer_Order.getCurrentState().handle_EVplaceOrder(customer_Customer_Order, ctx);

  }
  //--- Modifying Events ---

  //--- Ending Events ---
}
