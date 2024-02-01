//Meetkumar Saspara
//115971301
//R:01

/**
 * Exception thrown when attempting to perform an operation on an empty student line.
 */
public class EmptyLineException extends Exception {

  /**
   * Constructs an EmptyLineException with no specified detail message.
   */
  EmptyLineException() {
    super();
  }

  /**
   * Constructs an EmptyLineException with the specified detail message.
   *
   * @param message the detail message (which is saved for later retrieval by the getMessage() method)
   */
  EmptyLineException(String message) {
    super(message);
  }
}
