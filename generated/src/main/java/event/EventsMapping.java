package event;

import entity.Manufacturer;
import entity.Customer;
import entity.Carrier;
import entity.Order;
import entity.Shipment;

import runtime.BusinessEvent;
import runtime.exception.BusinessEventNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class EventsMapping {
  private static final EventsMapping instance = new EventsMapping();
  private Map<String, BusinessEvent> events;

  private EventsMapping() {
     this.events = new HashMap<String, BusinessEvent>();
     this.loadEvents();
  }

  public boolean eventExists(String eventName) {
return this.events.containsKey(eventName);
}

  public BusinessEvent getBusinessEvent(String name) throws BusinessEventNotFoundException {
    if(!this.eventExists(name))
      throw new BusinessEventNotFoundException("EventsMapping.getBusinessEvent(String): event with name" + name + " not found");

    return this.events.get(name);
  }

  private void loadEvents() {
    BusinessEvent EVcrManufacturer = new BusinessEvent("EVcrManufacturer", BusinessEvent.BusinessEventType.CREATE, Manufacturer.class);
    this.events.put(EVcrManufacturer.getName(), EVcrManufacturer);

    BusinessEvent EVendManufacturer = new BusinessEvent("EVendManufacturer", BusinessEvent.BusinessEventType.END, Manufacturer.class);
    this.events.put(EVendManufacturer.getName(), EVendManufacturer);

    BusinessEvent EVcrCustomer = new BusinessEvent("EVcrCustomer", BusinessEvent.BusinessEventType.CREATE, Customer.class);
    this.events.put(EVcrCustomer.getName(), EVcrCustomer);

    BusinessEvent EVendCustomer = new BusinessEvent("EVendCustomer", BusinessEvent.BusinessEventType.END, Customer.class);
    this.events.put(EVendCustomer.getName(), EVendCustomer);

    BusinessEvent EVcrCarrier = new BusinessEvent("EVcrCarrier", BusinessEvent.BusinessEventType.CREATE, Carrier.class);
    this.events.put(EVcrCarrier.getName(), EVcrCarrier);

    BusinessEvent EVendCarrier = new BusinessEvent("EVendCarrier", BusinessEvent.BusinessEventType.END, Carrier.class);
    this.events.put(EVendCarrier.getName(), EVendCarrier);

    BusinessEvent EVplaceOrder = new BusinessEvent("EVplaceOrder", BusinessEvent.BusinessEventType.CREATE, Order.class);
    this.events.put(EVplaceOrder.getName(), EVplaceOrder);

    BusinessEvent EVrejectOrder = new BusinessEvent("EVrejectOrder", BusinessEvent.BusinessEventType.END, Order.class);
    this.events.put(EVrejectOrder.getName(), EVrejectOrder);

    BusinessEvent EVrequestShipment = new BusinessEvent("EVrequestShipment", BusinessEvent.BusinessEventType.CREATE, Shipment.class);
    this.events.put(EVrequestShipment.getName(), EVrequestShipment);

    BusinessEvent EVrejectShipment = new BusinessEvent("EVrejectShipment", BusinessEvent.BusinessEventType.END, Shipment.class);
    this.events.put(EVrejectShipment.getName(), EVrejectShipment);

    BusinessEvent EVacceptShipment = new BusinessEvent("EVacceptShipment", BusinessEvent.BusinessEventType.MODIFY, Shipment.class);
    this.events.put(EVacceptShipment.getName(), EVacceptShipment);

    BusinessEvent EVorderReady = new BusinessEvent("EVorderReady", BusinessEvent.BusinessEventType.MODIFY, Order.class);
    this.events.put(EVorderReady.getName(), EVorderReady);

    BusinessEvent EVpickupShipment = new BusinessEvent("EVpickupShipment", BusinessEvent.BusinessEventType.MODIFY, Shipment.class);
    this.events.put(EVpickupShipment.getName(), EVpickupShipment);

    BusinessEvent EVshipmentDelivered = new BusinessEvent("EVshipmentDelivered", BusinessEvent.BusinessEventType.MODIFY, Shipment.class);
    this.events.put(EVshipmentDelivered.getName(), EVshipmentDelivered);

    BusinessEvent EVinvoiceShipment = new BusinessEvent("EVinvoiceShipment", BusinessEvent.BusinessEventType.MODIFY, Shipment.class);
    this.events.put(EVinvoiceShipment.getName(), EVinvoiceShipment);

    BusinessEvent EVconfirmDelivery = new BusinessEvent("EVconfirmDelivery", BusinessEvent.BusinessEventType.MODIFY, Shipment.class);
    this.events.put(EVconfirmDelivery.getName(), EVconfirmDelivery);

    BusinessEvent EVinvoiceOrder = new BusinessEvent("EVinvoiceOrder", BusinessEvent.BusinessEventType.MODIFY, Order.class);
    this.events.put(EVinvoiceOrder.getName(), EVinvoiceOrder);

    BusinessEvent EVpayShipmentInvoice = new BusinessEvent("EVpayShipmentInvoice", BusinessEvent.BusinessEventType.END, Shipment.class);
    this.events.put(EVpayShipmentInvoice.getName(), EVpayShipmentInvoice);

    BusinessEvent EVpayOrderInvoice = new BusinessEvent("EVpayOrderInvoice", BusinessEvent.BusinessEventType.END, Order.class);
    this.events.put(EVpayOrderInvoice.getName(), EVpayOrderInvoice);

  }

public static EventsMapping instance() { return instance; }

}