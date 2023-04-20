package entity;


import com.owlike.genson.annotation.JsonCreator;
import com.owlike.genson.annotation.JsonProperty;

import runtime.BusinessObject;

import state.manufacturer.ManufacturerState;
import state.manufacturer.ManufacturerAllocatedState;

public class Manufacturer extends BusinessObject {
   //=========== Attributes ===========
  private final boolean isParticipant = true;
  private String publicKey;
  private ManufacturerState currentState;
  private String legalName;

  //========== Constructors ==========
  /**
   * Creates a new Manufacturer with a Generated ID and Default State: Allocated
   */
  @JsonCreator
  public Manufacturer(@JsonProperty("legalName") String legalName, @JsonProperty("publicKey") String publicKey) {
    super();
    this.currentState = new ManufacturerAllocatedState();
    this.legalName = legalName;
    this.publicKey = publicKey;
  }

  public Manufacturer(@JsonProperty("id") String id, @JsonProperty("currentState") ManufacturerState currentState, @JsonProperty("legalName") String legalName, @JsonProperty("publicKey") String publicKey) {
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
  public ManufacturerState getCurrentState() { return this.currentState; }
  public String getLegalName() { return this.legalName; }

  //============ Setters =============
  public void setCurrentState(ManufacturerState currentState) { this.currentState = currentState; }
  public void setLegalName(String legalName) { this.legalName = legalName; }
  public void setPublicKey(String publicKey) { this.publicKey = publicKey; }

  //===== JSON (De)serialization =====
  public String toJsonString() { return genson.serialize(this); }
  public static Manufacturer fromJson(String json) { return genson.deserialize(json, Manufacturer.class); }
}