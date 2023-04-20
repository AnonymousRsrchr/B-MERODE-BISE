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

public class ShipmentAllocatedState extends ShipmentState {
  //========== Constructor ===========
  public ShipmentAllocatedState() {
    super("allocated", StateType.INITIAL);
  }

  //========= Event Handling =========
  //--- Creating Events ---
  @Override
  public void handle_EVrequestShipment(Shipment object, Context ctx) throws FailedEventHandlingException {
    //Check if the referenced masters exist
    //Order
    if(!StubHelper.exists(ctx, object.getOrderId_Order_Shipment()))
      throw new FailedEventHandlingException("Master with id " + object.getOrderId_Order_Shipment() + " does not exist");

    //Retrieve Master (order_Order_Shipment)
    Order order_Order_Shipment = (Order)StubHelper.findBusinessObject(ctx, object.getOrderId_Order_Shipment());

    //Master order can only have one dependent of type Shipment. Checking if one already exists
    if(StubHelper.hasLivingDependentsOfType(ctx, order_Order_Shipment, object))
      throw new FailedEventHandlingException("Master with id " + object.getOrderId_Order_Shipment() + " already has a living dependent of type Shipment (limited to one)");
    //Carrier
    if(!StubHelper.exists(ctx, object.getCarrierId_Carrier_Shipment()))
      throw new FailedEventHandlingException("Master with id " + object.getCarrierId_Carrier_Shipment() + " does not exist");

    //Retrieve Master (carrier_Carrier_Shipment)
    Carrier carrier_Carrier_Shipment = (Carrier)StubHelper.findBusinessObject(ctx, object.getCarrierId_Carrier_Shipment());


    //Changes the state of the current Shipment (object)
    ShipmentRequestedState newState = new ShipmentRequestedState();
    object.setCurrentState(newState);
    StubHelper.save(ctx, object);
    //Trigger Event Handling for Each Master
    //Order
    order_Order_Shipment.getCurrentState().handle_EVrequestShipment(order_Order_Shipment, ctx);

    //Carrier
    carrier_Carrier_Shipment.getCurrentState().handle_EVrequestShipment(carrier_Carrier_Shipment, ctx);

  }
  //--- Modifying Events ---

  //--- Ending Events ---
}
