package runtime;

import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import com.owlike.genson.annotation.JsonCreator;
import com.owlike.genson.annotation.JsonIgnore;
import com.owlike.genson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

import org.hyperledger.fabric.contract.Context;
import runtime.exception.CollaborationSetupException;

public class CollaborationSetup {
    private boolean setupFinalized;
    private String participantsHandlerPK;
    @JsonProperty("lastIds")
    private Map<String, Integer> lastIds;

    @JsonIgnore
    protected final static Genson genson = new GensonBuilder().create();

    @JsonCreator
    public CollaborationSetup(@JsonProperty("setupFinalized") boolean setupFinalized, @JsonProperty("participantsHandlerPK") String participantsHandlerPK) {
        this.setupFinalized = setupFinalized;
        this.participantsHandlerPK = participantsHandlerPK;
        this.lastIds = new HashMap<String, Integer>();

        this.lastIds.put("Manufacturer", -1);
        this.lastIds.put("Customer", -1);
        this.lastIds.put("Carrier", -1);
        this.lastIds.put("Order", -1);
        this.lastIds.put("Shipment", -1);
    }

    public boolean getSetupFinalized() { return this.setupFinalized; }
    public String getParticipantsHandlerPK() { return this.participantsHandlerPK; }
    public int getLastId(String BOTname) {
        if(!this.lastIds.containsKey(BOTname))
            throw new RuntimeException("BusinessObjectType " + BOTname + " is not defined in the invoked chaincode");

        return this.lastIds.get(BOTname);
    }

    public int nextId(String botName) {
        if(!this.lastIds.containsKey(botName))
            throw new RuntimeException("BusinessObjectType " + botName + " is not defined in the invoked chaincode");

        int nextId = getLastId(botName)+1;
        this.lastIds.put(botName, nextId);
        return nextId;
    }

    public void save(Context ctx) {
        ctx.getStub().putStringState("BMERODE.COLLABORATION_SETUP", this.toJsonString());
    }

    public void markAsReady() { this.setupFinalized = true; }

    public String toJsonString() { return genson.serialize(this); }
    public static CollaborationSetup fromJson(String json) { return genson.deserialize(json, CollaborationSetup.class); }
    public static CollaborationSetup load(Context ctx) throws CollaborationSetupException {
    String currentSetup = ctx.getStub().getStringState("BMERODE.COLLABORATION_SETUP");
        if(currentSetup == null || currentSetup.length() == 0)
            throw new CollaborationSetupException("CollaboationSetup.load(Context): Collaboration needs to be initialized first");

        return CollaborationSetup.fromJson(currentSetup);
    }
}