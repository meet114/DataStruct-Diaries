//Meetkumar Saspara
//115971301
//R:01
//HW3

/**
 * Custom exception class for indicating that a stack is full when trying to perform an operation.
 */
public class FullStackException extends Exception {

  /**
   * Constructs a new FullStackException with no detail message.
   */
  FullStackException() {
    super();
  }

  /**
   * Constructs a new FullStackException with the specified detail message.
   *
   * @param message The detail message (which is saved for later retrieval by the getMessage() method).
   */
  FullStackException(String message) {
    super(message);
  }
}
