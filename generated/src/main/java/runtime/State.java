package runtime;


public abstract class State {

  public enum StateType {
    INITIAL,
    ONGOING,
    FINAL
  }

  private String name;
  private StateType stateType;

  public State(String name, StateType stateType) {
    this.name = name;
    this.stateType = stateType;
  }

  public String getName() { return this.name; }
  public StateType getStateType() { return this.stateType; }

  public void setName(String name) { this.name = name; }
  public void setStateType(StateType stateType) { this.stateType = stateType; }
}
