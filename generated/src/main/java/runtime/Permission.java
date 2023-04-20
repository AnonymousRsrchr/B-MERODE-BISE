package runtime;

import java.util.Objects;

public class Permission {
  private final String botName;
  private final String eventName;
  private final boolean allowed;

  public Permission(String botName, String eventName, boolean allowed) {
    this.botName = botName;
    this.eventName = eventName;
    this.allowed = allowed;
  }

  public String getBusinessObjectName() { return this.botName; }
  public String getBusinessEventName() { return this.eventName; }
  public boolean isAllowed() { return this.allowed; }

  @Override
  public boolean equals(Object o) {
    if(!(o instanceof Permission))
      return false;

    return this.equals((Permission)o);
  }

  public boolean equals(Permission p) {
    if(!p.getBusinessEventName().equals(this.getBusinessEventName()))
      return false;
    if(!p.getBusinessObjectName().equals(this.getBusinessObjectName()))
      return false;
    if(p.isAllowed() != this.isAllowed())
      return false;

    return true;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getBusinessObjectName(), this.getBusinessEventName(), this.isAllowed());
  }

  @Override
  public String toString() {
    return "Auth: { event: " + this.eventName + ", ptcpType: " + this.botName + ": " + (this.allowed ? "allowed" : "denied");
  }
}
