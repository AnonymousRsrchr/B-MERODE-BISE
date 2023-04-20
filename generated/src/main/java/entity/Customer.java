package entity;


import com.owlike.genson.annotation.JsonCreator;
import com.owlike.genson.annotation.JsonProperty;

import runtime.BusinessObject;

import state.customer.CustomerState;
import state.customer.CustomerAllocatedState;

public class Customer extends BusinessObject {
   //=========== Attributes ===========
  private final boolean isParticipant = true;
  private String publicKey;
  private CustomerState currentState;
  private String legalName;

  //========== Constructors ==========
  /**
   * Creates a new Customer with a Generated ID and Default State: Allocated
   */
  @JsonCreator
  public Customer(@JsonProperty("legalName") String legalName, @JsonProperty("publicKey") String publicKey) {
    super();
    this.currentState = new CustomerAllocatedState();
    this.legalName = legalName;
    this.publicKey = publicKey;
  }

  public Customer(@JsonProperty("id") String id, @JsonProperty("currentState") CustomerState currentState, @JsonProperty("legalName") String legalName, @JsonProperty("publicKey") String publicKey) {
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
  public CustomerState getCurrentState() { return this.currentState; }
  public String getLegalName() { return this.legalName; }

  //============ Setters =============
  public void setCurrentState(CustomerState currentState) { this.currentState = currentState; }
  public void setLegalName(String legalName) { this.legalName = legalName; }
  public void setPublicKey(String publicKey) { this.publicKey = publicKey; }

  //===== JSON (De)serialization =====
  public String toJsonString() { return genson.serialize(this); }
  public static Customer fromJson(String json) { return genson.deserialize(json, Customer.class); }
}