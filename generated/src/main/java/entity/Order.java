package entity;


import com.owlike.genson.annotation.JsonCreator;
import com.owlike.genson.annotation.JsonProperty;

import runtime.BusinessObject;

import state.order.OrderState;
import state.order.OrderAllocatedState;

public class Order extends BusinessObject {
   //=========== Attributes ===========
  private final boolean isParticipant = false;
  private OrderState currentState;
  private String productRef;
  private Float quantity;
  private Float initialOrderPrice;
  private String expectedDeliveryDate;
  private String deliveryLocation;
  private Float orderInvoiceAmount;
  private String effectiveDeliveryDate;
  private String orderReadyDate;
  private final String manufacturerId_Manufacturer_Order;
  private final String customerId_Customer_Order;

  //========== Constructors ==========
  /**
   * Creates a new Order with a Generated ID and Default State: Allocated
   */
  @JsonCreator
  public Order(@JsonProperty("productRef") String productRef, @JsonProperty("quantity") Float quantity, @JsonProperty("initialOrderPrice") Float initialOrderPrice, @JsonProperty("expectedDeliveryDate") String expectedDeliveryDate, @JsonProperty("deliveryLocation") String deliveryLocation, @JsonProperty("orderInvoiceAmount") Float orderInvoiceAmount, @JsonProperty("effectiveDeliveryDate") String effectiveDeliveryDate, @JsonProperty("orderReadyDate") String orderReadyDate, @JsonProperty("manufacturerId_Manufacturer_Order") String manufacturerId_Manufacturer_Order, @JsonProperty("customerId_Customer_Order") String customerId_Customer_Order) {
    super();
    this.currentState = new OrderAllocatedState();
    this.productRef = productRef;
    this.quantity = quantity;
    this.initialOrderPrice = initialOrderPrice;
    this.expectedDeliveryDate = expectedDeliveryDate;
    this.deliveryLocation = deliveryLocation;
    this.orderInvoiceAmount = orderInvoiceAmount;
    this.effectiveDeliveryDate = effectiveDeliveryDate;
    this.orderReadyDate = orderReadyDate;
    this.manufacturerId_Manufacturer_Order = manufacturerId_Manufacturer_Order;
    this.customerId_Customer_Order = customerId_Customer_Order;
  }

  public Order(@JsonProperty("id") String id, @JsonProperty("currentState") OrderState currentState, @JsonProperty("productRef") String productRef, @JsonProperty("quantity") Float quantity, @JsonProperty("initialOrderPrice") Float initialOrderPrice, @JsonProperty("expectedDeliveryDate") String expectedDeliveryDate, @JsonProperty("deliveryLocation") String deliveryLocation, @JsonProperty("orderInvoiceAmount") Float orderInvoiceAmount, @JsonProperty("effectiveDeliveryDate") String effectiveDeliveryDate, @JsonProperty("orderReadyDate") String orderReadyDate, @JsonProperty("manufacturerId_Manufacturer_Order") String manufacturerId_Manufacturer_Order, @JsonProperty("customerId_Customer_Order") String customerId_Customer_Order) {
    super(id);
    this.currentState = currentState;
    this.productRef = productRef;
    this.quantity = quantity;
    this.initialOrderPrice = initialOrderPrice;
    this.expectedDeliveryDate = expectedDeliveryDate;
    this.deliveryLocation = deliveryLocation;
    this.orderInvoiceAmount = orderInvoiceAmount;
    this.effectiveDeliveryDate = effectiveDeliveryDate;
    this.orderReadyDate = orderReadyDate;
    this.manufacturerId_Manufacturer_Order = manufacturerId_Manufacturer_Order;
    this.customerId_Customer_Order = customerId_Customer_Order;
  }

  //============ Getters =============
  @Override
  public boolean isParticipant() { return this.isParticipant; }
  @Override
  public String getPublicKey() { return null; }
  public OrderState getCurrentState() { return this.currentState; }
  public String getProductRef() { return this.productRef; }
  public Float getQuantity() { return this.quantity; }
  public Float getInitialOrderPrice() { return this.initialOrderPrice; }
  public String getExpectedDeliveryDate() { return this.expectedDeliveryDate; }
  public String getDeliveryLocation() { return this.deliveryLocation; }
  public Float getOrderInvoiceAmount() { return this.orderInvoiceAmount; }
  public String getEffectiveDeliveryDate() { return this.effectiveDeliveryDate; }
  public String getOrderReadyDate() { return this.orderReadyDate; }
  public String getManufacturerId_Manufacturer_Order() { return this.manufacturerId_Manufacturer_Order; }
  public String getCustomerId_Customer_Order() { return this.customerId_Customer_Order; }

  //============ Setters =============
  public void setCurrentState(OrderState currentState) { this.currentState = currentState; }
  public void setProductRef(String productRef) { this.productRef = productRef; }
  public void setQuantity(Float quantity) { this.quantity = quantity; }
  public void setInitialOrderPrice(Float initialOrderPrice) { this.initialOrderPrice = initialOrderPrice; }
  public void setExpectedDeliveryDate(String expectedDeliveryDate) { this.expectedDeliveryDate = expectedDeliveryDate; }
  public void setDeliveryLocation(String deliveryLocation) { this.deliveryLocation = deliveryLocation; }
  public void setOrderInvoiceAmount(Float orderInvoiceAmount) { this.orderInvoiceAmount = orderInvoiceAmount; }
  public void setEffectiveDeliveryDate(String effectiveDeliveryDate) { this.effectiveDeliveryDate = effectiveDeliveryDate; }
  public void setOrderReadyDate(String orderReadyDate) { this.orderReadyDate = orderReadyDate; }

  //===== JSON (De)serialization =====
  public String toJsonString() { return genson.serialize(this); }
  public static Order fromJson(String json) { return genson.deserialize(json, Order.class); }
}