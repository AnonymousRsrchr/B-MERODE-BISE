package inputValidator;

import com.owlike.genson.Genson;

import java.util.HashMap;
import java.util.Map;
import java.time.LocalDateTime;


public class OrderInputValidator {
  private static final Genson genson = new Genson();

  @SuppressWarnings("unchecked")
  public static boolean isValidForCreation(String json) {
    HashMap<String, Object> jsonObject = (HashMap<String, Object>)genson.deserialize(json, Map.class);

    if(!jsonObject.containsKey("productRef"))
       return false;

    if(jsonObject.get("productRef") == null)
       return false;

    if(!(jsonObject.get("productRef") instanceof String))
       return false;

    if(!jsonObject.containsKey("quantity"))
       return false;

    if(jsonObject.get("quantity") == null)
       return false;

    if(!(jsonObject.get("quantity") instanceof Float))
       return false;

    if(!jsonObject.containsKey("initialOrderPrice"))
       return false;

    if(jsonObject.get("initialOrderPrice") == null)
       return false;

    if(!(jsonObject.get("initialOrderPrice") instanceof Float))
       return false;

    if(!jsonObject.containsKey("expectedDeliveryDate"))
       return false;

    if(jsonObject.get("expectedDeliveryDate") == null)
       return false;

    if(!(jsonObject.get("expectedDeliveryDate") instanceof String))
       return false;

    if(!jsonObject.containsKey("deliveryLocation"))
       return false;

    if(jsonObject.get("deliveryLocation") == null)
       return false;

    if(!(jsonObject.get("deliveryLocation") instanceof String))
       return false;

    if(!jsonObject.containsKey("orderInvoiceAmount"))
       return false;

    if(jsonObject.get("orderInvoiceAmount") == null)
       return false;

    if(!(jsonObject.get("orderInvoiceAmount") instanceof Float))
       return false;

    if(!jsonObject.containsKey("effectiveDeliveryDate"))
       return false;

    if(jsonObject.get("effectiveDeliveryDate") == null)
       return false;

    if(!(jsonObject.get("effectiveDeliveryDate") instanceof String))
       return false;

    if(!jsonObject.containsKey("orderReadyDate"))
       return false;

    if(jsonObject.get("orderReadyDate") == null)
       return false;

    if(!(jsonObject.get("orderReadyDate") instanceof String))
       return false;

    if(!jsonObject.containsKey("manufacturerId_Manufacturer_Order"))
       return false;

    if(jsonObject.get("manufacturerId_Manufacturer_Order") == null)
       return false;

    if(!(jsonObject.get("manufacturerId_Manufacturer_Order") instanceof String))
       return false;

    if(!jsonObject.containsKey("customerId_Customer_Order"))
       return false;

    if(jsonObject.get("customerId_Customer_Order") == null)
       return false;

    if(!(jsonObject.get("customerId_Customer_Order") instanceof String))
       return false;

    return true;
  }

}