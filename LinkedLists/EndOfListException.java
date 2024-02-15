//Meetkumar Saspara
//115971301
//R:01
//HW2

/**
 * Represents an exception that is thrown when attempting to perform an operation
 * beyond the end of a car list.
 */
public class EndOfListException extends Exception {

  /**
   * Constructs a new EndOfListException with no detail message.
   */
  EndOfListException() {
    super();
  }

  /**
   * Constructs a new EndOfListException with the specified detail message.
   *
   * @param message the detail message (which is saved for later retrieval by the getMessage() method)
   */
  EndOfListException(String message) {
    super(message);
  }
}
