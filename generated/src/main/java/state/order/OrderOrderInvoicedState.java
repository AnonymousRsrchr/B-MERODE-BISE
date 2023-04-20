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

public class OrderOrderInvoicedState extends OrderState {
  //========== Constructor ===========
  public OrderOrderInvoicedState() {
    super("orderInvoiced", StateType.ONGOING);
  }

  //========= Event Handling =========
  //--- Creating Events ---
  //--- Modifying Events ---

  //--- Ending Events ---
  @Override
  public void handle_EVpayOrderInvoice(Order object, Context ctx) throws FailedEventHandlingException {

    //Check if there are living  dependents before ending the object
    if(StubHelper.hasLivingDependents(ctx, object))
      throw new FailedEventHandlingException("Order (" + object.getId() + ") has living dependents and therefore cannot be ended");

    //Changes the state of the current Order (object)
    OrderOrderPaidState newState = new OrderOrderPaidState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
    //Trigger Event Handling for Each Master
    //Manufacturer
    Manufacturer manufacturer_Manufacturer_Order = (Manufacturer)StubHelper.findBusinessObject(ctx, object.getManufacturerId_Manufacturer_Order());
    manufacturer_Manufacturer_Order.getCurrentState().handle_EVpayOrderInvoice(manufacturer_Manufacturer_Order, ctx);
    //Customer
    Customer customer_Customer_Order = (Customer)StubHelper.findBusinessObject(ctx, object.getCustomerId_Customer_Order());
    customer_Customer_Order.getCurrentState().handle_EVpayOrderInvoice(customer_Customer_Order, ctx);
  }
}
