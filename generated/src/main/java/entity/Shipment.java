package entity;


import com.owlike.genson.annotation.JsonCreator;
import com.owlike.genson.annotation.JsonProperty;

import runtime.BusinessObject;

import state.shipment.ShipmentState;
import state.shipment.ShipmentAllocatedState;

public class Shipment extends BusinessObject {
   //=========== Attributes ===========
  private final boolean isParticipant = false;
  private ShipmentState currentState;
  private Float weight;
  private String pickupLocation;
  private Float transportationCost;
  private String effectiveDeliveryDate;
  private String shippingInvoiceAmount;
  private final String orderId_Order_Shipment;
  private final String carrierId_Carrier_Shipment;

  //========== Constructors ==========
  /**
   * Creates a new Shipment with a Generated ID and Default State: Allocated
   */
  @JsonCreator
  public Shipment(@JsonProperty("weight") Float weight, @JsonProperty("pickupLocation") String pickupLocation, @JsonProperty("transportationCost") Float transportationCost, @JsonProperty("effectiveDeliveryDate") String effectiveDeliveryDate, @JsonProperty("shippingInvoiceAmount") String shippingInvoiceAmount, @JsonProperty("orderId_Order_Shipment") String orderId_Order_Shipment, @JsonProperty("carrierId_Carrier_Shipment") String carrierId_Carrier_Shipment) {
    super();
    this.currentState = new ShipmentAllocatedState();
    this.weight = weight;
    this.pickupLocation = pickupLocation;
    this.transportationCost = transportationCost;
    this.effectiveDeliveryDate = effectiveDeliveryDate;
    this.shippingInvoiceAmount = shippingInvoiceAmount;
    this.orderId_Order_Shipment = orderId_Order_Shipment;
    this.carrierId_Carrier_Shipment = carrierId_Carrier_Shipment;
  }

  public Shipment(@JsonProperty("id") String id, @JsonProperty("currentState") ShipmentState currentState, @JsonProperty("weight") Float weight, @JsonProperty("pickupLocation") String pickupLocation, @JsonProperty("transportationCost") Float transportationCost, @JsonProperty("effectiveDeliveryDate") String effectiveDeliveryDate, @JsonProperty("shippingInvoiceAmount") String shippingInvoiceAmount, @JsonProperty("orderId_Order_Shipment") String orderId_Order_Shipment, @JsonProperty("carrierId_Carrier_Shipment") String carrierId_Carrier_Shipment) {
    super(id);
    this.currentState = currentState;
    this.weight = weight;
    this.pickupLocation = pickupLocation;
    this.transportationCost = transportationCost;
    this.effectiveDeliveryDate = effectiveDeliveryDate;
    this.shippingInvoiceAmount = shippingInvoiceAmount;
    this.orderId_Order_Shipment = orderId_Order_Shipment;
    this.carrierId_Carrier_Shipment = carrierId_Carrier_Shipment;
  }

  //============ Getters =============
  @Override
  public boolean isParticipant() { return this.isParticipant; }
  @Override
  public String getPublicKey() { return null; }
  public ShipmentState getCurrentState() { return this.currentState; }
  public Float getWeight() { return this.weight; }
  public String getPickupLocation() { return this.pickupLocation; }
  public Float getTransportationCost() { return this.transportationCost; }
  public String getEffectiveDeliveryDate() { return this.effectiveDeliveryDate; }
  public String getShippingInvoiceAmount() { return this.shippingInvoiceAmount; }
  public String getOrderId_Order_Shipment() { return this.orderId_Order_Shipment; }
  public String getCarrierId_Carrier_Shipment() { return this.carrierId_Carrier_Shipment; }

  //============ Setters =============
  public void setCurrentState(ShipmentState currentState) { this.currentState = currentState; }
  public void setWeight(Float weight) { this.weight = weight; }
  public void setPickupLocation(String pickupLocation) { this.pickupLocation = pickupLocation; }
  public void setTransportationCost(Float transportationCost) { this.transportationCost = transportationCost; }
  public void setEffectiveDeliveryDate(String effectiveDeliveryDate) { this.effectiveDeliveryDate = effectiveDeliveryDate; }
  public void setShippingInvoiceAmount(String shippingInvoiceAmount) { this.shippingInvoiceAmount = shippingInvoiceAmount; }

  //===== JSON (De)serialization =====
  public String toJsonString() { return genson.serialize(this); }
  public static Shipment fromJson(String json) { return genson.deserialize(json, Shipment.class); }
}