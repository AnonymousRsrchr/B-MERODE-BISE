package runtime.exception;

public class FailedEventHandlingException extends RuntimeException {
  public FailedEventHandlingException() { super(); }
  public FailedEventHandlingException(String msg) { super(msg); }
}
