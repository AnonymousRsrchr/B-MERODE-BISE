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

public class ShipmentInvoicingState extends ShipmentState {
  //========== Constructor ===========
  public ShipmentInvoicingState() {
    super("invoicing", StateType.ONGOING);
  }

  //========= Event Handling =========
  //--- Creating Events ---
  //--- Modifying Events ---
  @Override
  public void handle_EVinvoiceShipment(Shipment object, Context ctx) throws FailedEventHandlingException {

    //The Shipment object will be saved with its current properties
    //Before changing state and (possibly) attributes, we check there was no change to the masters
    Shipment currentLedgerObject = (Shipment)StubHelper.findBusinessObject(ctx, object.getId());
    if(!object.getOrderId_Order_Shipment().equals(currentLedgerObject.getOrderId_Order_Shipment()))
      throw new FailedEventHandlingException("The master of a Business Object (" + object.getId() + ") cannot be changed");
    if(!object.getCarrierId_Carrier_Shipment().equals(currentLedgerObject.getCarrierId_Carrier_Shipment()))
      throw new FailedEventHandlingException("The master of a Business Object (" + object.getId() + ") cannot be changed");
    //Changes the state of the current Shipment (object)
    ShipmentInvoicedState newState = new ShipmentInvoicedState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
    //Trigger Event Handling for Each Master
    //Order
    Order order_Order_Shipment = (Order)StubHelper.findBusinessObject(ctx, object.getOrderId_Order_Shipment());
    order_Order_Shipment.getCurrentState().handle_EVinvoiceShipment(order_Order_Shipment, ctx);
    //Carrier
    Carrier carrier_Carrier_Shipment = (Carrier)StubHelper.findBusinessObject(ctx, object.getCarrierId_Carrier_Shipment());
    carrier_Carrier_Shipment.getCurrentState().handle_EVinvoiceShipment(carrier_Carrier_Shipment, ctx);
  }

  //--- Ending Events ---
}
