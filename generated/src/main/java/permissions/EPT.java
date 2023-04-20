package permissions;

import runtime.Permission;
import runtime.RuntimeEpt;

public class EPT {
  private static EPT instance = new EPT();
  private RuntimeEpt runtimeEpt;

  private EPT() {
    this.runtimeEpt = new RuntimeEpt();
    this.runtimeEpt.addPermission(new Permission("Manufacturer", "EVcrManufacturer", true));
    this.runtimeEpt.addPermission(new Permission("Customer", "EVcrManufacturer", false));
    this.runtimeEpt.addPermission(new Permission("Carrier", "EVcrManufacturer", false));
    this.runtimeEpt.addPermission(new Permission("Manufacturer", "EVendManufacturer", true));
    this.runtimeEpt.addPermission(new Permission("Customer", "EVendManufacturer", false));
    this.runtimeEpt.addPermission(new Permission("Carrier", "EVendManufacturer", false));
    this.runtimeEpt.addPermission(new Permission("Manufacturer", "EVcrCustomer", true));
    this.runtimeEpt.addPermission(new Permission("Customer", "EVcrCustomer", false));
    this.runtimeEpt.addPermission(new Permission("Carrier", "EVcrCustomer", false));
    this.runtimeEpt.addPermission(new Permission("Manufacturer", "EVendCustomer", true));
    this.runtimeEpt.addPermission(new Permission("Customer", "EVendCustomer", true));
    this.runtimeEpt.addPermission(new Permission("Carrier", "EVendCustomer", false));
    this.runtimeEpt.addPermission(new Permission("Manufacturer", "EVcrCarrier", true));
    this.runtimeEpt.addPermission(new Permission("Customer", "EVcrCarrier", false));
    this.runtimeEpt.addPermission(new Permission("Carrier", "EVcrCarrier", false));
    this.runtimeEpt.addPermission(new Permission("Manufacturer", "EVendCarrier", true));
    this.runtimeEpt.addPermission(new Permission("Customer", "EVendCarrier", false));
    this.runtimeEpt.addPermission(new Permission("Carrier", "EVendCarrier", false));
    this.runtimeEpt.addPermission(new Permission("Manufacturer", "EVplaceOrder", false));
    this.runtimeEpt.addPermission(new Permission("Customer", "EVplaceOrder", true));
    this.runtimeEpt.addPermission(new Permission("Carrier", "EVplaceOrder", false));
    this.runtimeEpt.addPermission(new Permission("Manufacturer", "EVorderReady", true));
    this.runtimeEpt.addPermission(new Permission("Customer", "EVorderReady", false));
    this.runtimeEpt.addPermission(new Permission("Carrier", "EVorderReady", false));
    this.runtimeEpt.addPermission(new Permission("Manufacturer", "EVinvoiceOrder", true));
    this.runtimeEpt.addPermission(new Permission("Customer", "EVinvoiceOrder", false));
    this.runtimeEpt.addPermission(new Permission("Carrier", "EVinvoiceOrder", false));
    this.runtimeEpt.addPermission(new Permission("Manufacturer", "EVrejectOrder", true));
    this.runtimeEpt.addPermission(new Permission("Customer", "EVrejectOrder", false));
    this.runtimeEpt.addPermission(new Permission("Carrier", "EVrejectOrder", false));
    this.runtimeEpt.addPermission(new Permission("Manufacturer", "EVrequestShipment", true));
    this.runtimeEpt.addPermission(new Permission("Customer", "EVrequestShipment", false));
    this.runtimeEpt.addPermission(new Permission("Carrier", "EVrequestShipment", false));
    this.runtimeEpt.addPermission(new Permission("Manufacturer", "EVacceptShipment", false));
    this.runtimeEpt.addPermission(new Permission("Customer", "EVacceptShipment", false));
    this.runtimeEpt.addPermission(new Permission("Carrier", "EVacceptShipment", true));
    this.runtimeEpt.addPermission(new Permission("Manufacturer", "EVconfirmDelivery", false));
    this.runtimeEpt.addPermission(new Permission("Customer", "EVconfirmDelivery", true));
    this.runtimeEpt.addPermission(new Permission("Carrier", "EVconfirmDelivery", false));
    this.runtimeEpt.addPermission(new Permission("Manufacturer", "EVpickupShipment", false));
    this.runtimeEpt.addPermission(new Permission("Customer", "EVpickupShipment", false));
    this.runtimeEpt.addPermission(new Permission("Carrier", "EVpickupShipment", true));
    this.runtimeEpt.addPermission(new Permission("Manufacturer", "EVshipmentDelivered", false));
    this.runtimeEpt.addPermission(new Permission("Customer", "EVshipmentDelivered", false));
    this.runtimeEpt.addPermission(new Permission("Carrier", "EVshipmentDelivered", true));
    this.runtimeEpt.addPermission(new Permission("Manufacturer", "EVinvoiceShipment", false));
    this.runtimeEpt.addPermission(new Permission("Customer", "EVinvoiceShipment", false));
    this.runtimeEpt.addPermission(new Permission("Carrier", "EVinvoiceShipment", true));
    this.runtimeEpt.addPermission(new Permission("Manufacturer", "EVrejectShipment", false));
    this.runtimeEpt.addPermission(new Permission("Customer", "EVrejectShipment", false));
    this.runtimeEpt.addPermission(new Permission("Carrier", "EVrejectShipment", true));
    this.runtimeEpt.addPermission(new Permission("Manufacturer", "EVpayShipmentInvoice", true));
    this.runtimeEpt.addPermission(new Permission("Customer", "EVpayShipmentInvoice", false));
    this.runtimeEpt.addPermission(new Permission("Carrier", "EVpayShipmentInvoice", false));
    this.runtimeEpt.addPermission(new Permission("Manufacturer", "EVpayOrderInvoice", false));
    this.runtimeEpt.addPermission(new Permission("Customer", "EVpayOrderInvoice", true));
    this.runtimeEpt.addPermission(new Permission("Carrier", "EVpayOrderInvoice", false));
  }

  public static RuntimeEpt getInstance() {
    return EPT.instance.runtimeEpt;
  }
}