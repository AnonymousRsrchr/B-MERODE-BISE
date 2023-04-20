package runtime;

import com.owlike.genson.Genson;

import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.shim.ledger.KeyModification;
import org.hyperledger.fabric.shim.ledger.KeyValue;
import org.hyperledger.fabric.shim.ledger.QueryResultsIterator;
import runtime.exception.BusinessObjectNotFoundException;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

public class StubHelper {
  public static boolean exists(Context ctx, String key) {
    byte[] buffer = ctx.getStub().getState(key);

    return buffer != null && buffer.length != 0;
  }


  public static ArrayList<String> findBusinessObjectHistory(Context ctx, String key) throws BusinessObjectNotFoundException {
    if(!exists(ctx, key))
      throw new BusinessObjectNotFoundException("[StubHelper.findBusinessObjectHistory(Context, String)]: Business Object with ID " + key + " was not Found in the Ledger");

    Genson g = new Genson();
    QueryResultsIterator<KeyModification> historyForKey = ctx.getStub().getHistoryForKey(key);
    ArrayList<String> history = new ArrayList<>();
    for(KeyModification modif: historyForKey) {
      String fullState = JsonConverter.fromRecordJsonToFullJson(key, modif.getStringValue());
      BusinessObject boToFetch = null;
      Method fromJsonMethod = null;

      try {
        fromJsonMethod = Class.forName("entity." + key.substring(0, key.indexOf("#"))).getDeclaredMethod("fromJson", String.class);
      } catch (NoSuchMethodException e) {
        e.printStackTrace();
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }

      try {
        boToFetch = (BusinessObject) fromJsonMethod.invoke(null, fullState);
      } catch(Exception e) {
        e.printStackTrace();
        throw new RuntimeException("[StubHelper.findBusinessObject(Context, String)]: Could not load Business Object (" + key + ") from JSON");
      }

      try {
        String dataJson = JsonConverter.toRecordJsonData(boToFetch);
        Map<String, Object> jsonObject = Util.getJsonObject(dataJson);
        jsonObject.put("transactionId", modif.getTxId());
        jsonObject.put("transactionTimestamp", modif.getTimestamp());
        history.add(g.serialize(jsonObject));
      } catch(Exception e) {
        throw new RuntimeException("StubHelper.findBusinessObjectHistory: Could not load particular history version of the business object with key: " + key);
      }

    }

    return history;
  }

  public static BusinessObject findBusinessObject(Context ctx, String key) throws BusinessObjectNotFoundException {
    if(!exists(ctx, key))
      throw new BusinessObjectNotFoundException("[StubHelper.findBusinessObject(Context, String)]: Business Object with ID " + key + " was not Found in the Ledger");

    String boState = ctx.getStub().getStringState(key);
    System.out.println("Record Json: " + boState);
    String fullBoState = JsonConverter.fromRecordJsonToFullJson(key, boState);
    System.out.println("Full BO State: " + fullBoState);

    BusinessObject boToReturn = null;
    Method fromJsonMethod = null;
    try {
      fromJsonMethod = Class.forName("entity." + key.substring(0, key.indexOf("#"))).getDeclaredMethod("fromJson", String.class);
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    try {
      boToReturn = (BusinessObject) fromJsonMethod.invoke(null, fullBoState);
      System.out.println("BO to Return: " + boToReturn.toJsonString());
    } catch(Exception e) {
      e.printStackTrace();
      throw new RuntimeException("[StubHelper.findBusinessObject(Context, String)]: Could not load Business Object (" + key + ") from JSON");
    }

    return boToReturn;
  }

  public static boolean hasLivingDependents(Context ctx, BusinessObject bo) {
    String classAsRef = bo.getClass().getSimpleName().substring(0, 1).toLowerCase() + bo.getClass().getSimpleName().substring(1) + "Id";
    String query = "{\"selector\":{\""+classAsRef+"\":\""+bo.getId()+"\", \"currentState\": {\"stateType\": \"ONGOING\"}}}";

    QueryResultsIterator<KeyValue> results = ctx.getStub().getQueryResult(query);
    return results.iterator().hasNext();
  }

  public static void save(Context ctx, BusinessObject bo) {
    ctx.getStub().putStringState(bo.getId(), JsonConverter.toRecordJsonData(bo));
  }

  public static boolean hasLivingDependentsOfType(Context ctx, BusinessObject master, BusinessObject dependent) {
    String classAsRef = master.getClass().getSimpleName().substring(0, 1).toLowerCase() + master.getClass().getSimpleName().substring(1) + "Id";
    String query = "{\"selector\":{\"@class\":\""+dependent.getClass().getName()+"\", \""+classAsRef+"\":\""+master.getId()+"\", \"currentState\": {\"stateType\": \"ONGOING\"}}}";

    QueryResultsIterator<KeyValue> results = ctx.getStub().getQueryResult(query);
    return results.iterator().hasNext();
  }

  public static BusinessObject findParticipant(Context ctx, String publicKey) {
    String query = "{\"selector\": {\"participant\": true, \"publicKey\": \"" + publicKey + "\"}}";

    QueryResultsIterator<KeyValue> results = ctx.getStub().getQueryResult(query);
    if(!results.iterator().hasNext())
      throw new BusinessObjectNotFoundException("StubHelper.findParticipant: no participant business object with public key " + publicKey);

    KeyValue recordKV = results.iterator().next();
    String participantRecord = recordKV.getStringValue();
    String enrichedParticipantRecord = JsonConverter.fromRecordJsonToFullJson(recordKV.getKey(), participantRecord);

    BusinessObject participant = null;
    try {
      participant = BusinessObject.fromJson(enrichedParticipantRecord);
    } catch(Exception e) {
      throw new RuntimeException("[StubHelper.findParticipant(Context, String)]: Could not load Business Object participant from JSON");
    }

    return participant;
  }
}
