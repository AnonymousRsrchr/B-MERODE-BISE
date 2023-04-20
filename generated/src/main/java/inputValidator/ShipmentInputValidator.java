package inputValidator;

import com.owlike.genson.Genson;

import java.util.HashMap;
import java.util.Map;
import java.time.LocalDateTime;


public class ShipmentInputValidator {
  private static final Genson genson = new Genson();

  @SuppressWarnings("unchecked")
  public static boolean isValidForCreation(String json) {
    HashMap<String, Object> jsonObject = (HashMap<String, Object>)genson.deserialize(json, Map.class);

    if(!jsonObject.containsKey("weight"))
       return false;

    if(jsonObject.get("weight") == null)
       return false;

    if(!(jsonObject.get("weight") instanceof Float))
       return false;

    if(!jsonObject.containsKey("pickupLocation"))
       return false;

    if(jsonObject.get("pickupLocation") == null)
       return false;

    if(!(jsonObject.get("pickupLocation") instanceof String))
       return false;

    if(!jsonObject.containsKey("transportationCost"))
       return false;

    if(jsonObject.get("transportationCost") == null)
       return false;

    if(!(jsonObject.get("transportationCost") instanceof Float))
       return false;

    if(!jsonObject.containsKey("effectiveDeliveryDate"))
       return false;

    if(jsonObject.get("effectiveDeliveryDate") == null)
       return false;

    if(!(jsonObject.get("effectiveDeliveryDate") instanceof String))
       return false;

    if(!jsonObject.containsKey("shippingInvoiceAmount"))
       return false;

    if(jsonObject.get("shippingInvoiceAmount") == null)
       return false;

    if(!(jsonObject.get("shippingInvoiceAmount") instanceof String))
       return false;

    if(!jsonObject.containsKey("orderId_Order_Shipment"))
       return false;

    if(jsonObject.get("orderId_Order_Shipment") == null)
       return false;

    if(!(jsonObject.get("orderId_Order_Shipment") instanceof String))
       return false;

    if(!jsonObject.containsKey("carrierId_Carrier_Shipment"))
       return false;

    if(jsonObject.get("carrierId_Carrier_Shipment") == null)
       return false;

    if(!(jsonObject.get("carrierId_Carrier_Shipment") instanceof String))
       return false;

    return true;
  }

}