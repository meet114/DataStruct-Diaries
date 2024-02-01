//Meetkumar Saspara
//115971301
//R:01

/**
 * Represents an exception thrown when an invalid argument is encountered.
 */
public class InvalidArgumentException extends Exception {

  /**
   * Constructs a new InvalidArgumentException with no detail message.
   */
  InvalidArgumentException() {
    super();
  }

  /**
   * Constructs a new InvalidArgumentException with the specified detail message.
   *
   * @param message the detail message
   */
  InvalidArgumentException(String message) {
    super(message);
  }
}
