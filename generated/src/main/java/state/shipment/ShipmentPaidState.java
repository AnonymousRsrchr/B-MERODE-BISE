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

public class ShipmentPaidState extends ShipmentState {
  //========== Constructor ===========
  public ShipmentPaidState() {
    super("paid", StateType.FINAL);
  }

  //========= Event Handling =========
  //--- Creating Events ---
  //--- Modifying Events ---

  //--- Ending Events ---
}
