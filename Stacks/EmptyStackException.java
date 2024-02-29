//Meetkumar Saspara
//115971301
//R:01
//HW3

/**
 * Custom exception class for indicating that a stack is empty when trying to perform an operation.
 */
public class EmptyStackException extends Exception {

  /**
   * Constructs a new EmptyStackException with no detail message.
   */
  EmptyStackException() {
    super();
  }

  /**
   * Constructs a new EmptyStackException with the specified detail message.
   *
   * @param message The detail message (which is saved for later retrieval by the getMessage() method).
   */
  EmptyStackException(String message) {
    super(message);
  }
}
