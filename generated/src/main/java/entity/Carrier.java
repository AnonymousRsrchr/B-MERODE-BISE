package entity;


import com.owlike.genson.annotation.JsonCreator;
import com.owlike.genson.annotation.JsonProperty;

import runtime.BusinessObject;

import state.carrier.CarrierState;
import state.carrier.CarrierAllocatedState;

public class Carrier extends BusinessObject {
   //=========== Attributes ===========
  private final boolean isParticipant = true;
  private String publicKey;
  private CarrierState currentState;
  private String legalName;

  //========== Constructors ==========
  /**
   * Creates a new Carrier with a Generated ID and Default State: Allocated
   */
  @JsonCreator
  public Carrier(@JsonProperty("legalName") String legalName, @JsonProperty("publicKey") String publicKey) {
    super();
    this.currentState = new CarrierAllocatedState();
    this.legalName = legalName;
    this.publicKey = publicKey;
  }

  public Carrier(@JsonProperty("id") String id, @JsonProperty("currentState") CarrierState currentState, @JsonProperty("legalName") String legalName, @JsonProperty("publicKey") String publicKey) {
    super(id);
    this.currentState = currentState;
    this.legalName = legalName;
    this.publicKey = publicKey;
  }

  //============ Getters =============
  @Override
  public boolean isParticipant() { return this.isParticipant; }
  @Override
  public String getPublicKey() { return this.publicKey; }
  public CarrierState getCurrentState() { return this.currentState; }
  public String getLegalName() { return this.legalName; }

  //============ Setters =============
  public void setCurrentState(CarrierState currentState) { this.currentState = currentState; }
  public void setLegalName(String legalName) { this.legalName = legalName; }
  public void setPublicKey(String publicKey) { this.publicKey = publicKey; }

  //===== JSON (De)serialization =====
  public String toJsonString() { return genson.serialize(this); }
  public static Carrier fromJson(String json) { return genson.deserialize(json, Carrier.class); }
}