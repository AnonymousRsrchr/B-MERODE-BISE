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

public class OrderOrderPaidState extends OrderState {
  //========== Constructor ===========
  public OrderOrderPaidState() {
    super("orderPaid", StateType.FINAL);
  }

  //========= Event Handling =========
  //--- Creating Events ---
  //--- Modifying Events ---

  //--- Ending Events ---
}
