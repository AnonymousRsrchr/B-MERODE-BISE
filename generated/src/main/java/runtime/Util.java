package runtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.owlike.genson.Genson;

import java.util.Map;

public class Util {
  private static final Genson genson = new Genson();
  public static final Logger logger = LoggerFactory.getLogger(Util.class);

  @SuppressWarnings("unchecked")
  public static Map<String, Object> getJsonObject(String json) {
    return genson.deserialize(json, Map.class);
  }
}

