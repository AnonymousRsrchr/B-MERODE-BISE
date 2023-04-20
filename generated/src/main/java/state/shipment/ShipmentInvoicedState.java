package state.shipment;

import entity.Shipment;
import entity.Order;
import entity.Carrier;

import runtime.StubHelper;
import runtime.BusinessObject;
import runtime.exception.FailedEventHandlingException;
import runtime.exception.BusinessObjectNotFoundException;
import org.bouncycastle.util.encoders.Base64;


import org.hyperledger.fabric.contract.Context;

import java.util.function.Supplier;

public class ShipmentInvoicedState extends ShipmentState {
  //========== Constructor ===========
  public ShipmentInvoicedState() {
    super("invoiced", StateType.ONGOING);
  }

  //========= Event Handling =========
  //--- Creating Events ---
  //--- Modifying Events ---

  //--- Ending Events ---
  @Override
  public void handle_EVpayShipmentInvoice(Shipment object, Context ctx) throws FailedEventHandlingException {

    //Check if there are living  dependents before ending the object
    if(StubHelper.hasLivingDependents(ctx, object))
      throw new FailedEventHandlingException("Shipment (" + object.getId() + ") has living dependents and therefore cannot be ended");

    //Changes the state of the current Shipment (object)
    ShipmentPaidState newState = new ShipmentPaidState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
    //Trigger Event Handling for Each Master
    //Order
    Order order_Order_Shipment = (Order)StubHelper.findBusinessObject(ctx, object.getOrderId_Order_Shipment());
    order_Order_Shipment.getCurrentState().handle_EVpayShipmentInvoice(order_Order_Shipment, ctx);
    //Carrier
    Carrier carrier_Carrier_Shipment = (Carrier)StubHelper.findBusinessObject(ctx, object.getCarrierId_Carrier_Shipment());
    carrier_Carrier_Shipment.getCurrentState().handle_EVpayShipmentInvoice(carrier_Carrier_Shipment, ctx);
  }
}
