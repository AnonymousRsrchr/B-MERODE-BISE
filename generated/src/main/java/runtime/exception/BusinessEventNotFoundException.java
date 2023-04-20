package runtime.exception;

public class BusinessEventNotFoundException extends RuntimeException {
  public BusinessEventNotFoundException() { super(); }
  public BusinessEventNotFoundException(String msg) { super(msg); }
}
