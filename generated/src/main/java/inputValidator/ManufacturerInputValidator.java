package inputValidator;

import com.owlike.genson.Genson;

import java.util.HashMap;
import java.util.Map;
import java.time.LocalDateTime;


public class ManufacturerInputValidator {
  private static final Genson genson = new Genson();

  @SuppressWarnings("unchecked")
  public static boolean isValidForCreation(String json) {
    HashMap<String, Object> jsonObject = (HashMap<String, Object>)genson.deserialize(json, Map.class);

    if(!jsonObject.containsKey("legalName"))
       return false;

    if(jsonObject.get("legalName") == null)
       return false;

    if(!(jsonObject.get("legalName") instanceof String))
       return false;

    if(!jsonObject.containsKey("publicKey"))
       return false;

    if(jsonObject.get("publicKey") == null)
       return false;

    if(!(jsonObject.get("publicKey") instanceof String))
       return false;

    return true;
  }

}