//Meetkumar Saspara
//115971301
//R:01

/**
 * Custom exception class representing an exception thrown by the Dean.
 * This exception is used in scenarios where the Dean intervenes in the lunch line simulation.
 */
public class DeanException extends Exception {

  /**
   * Constructs a new DeanException with no specified detail message.
   */
  DeanException() {
    super();
  }

  /**
   * Constructs a new DeanException with the specified detail message.
   *
   * @param message the detail message (which is saved for later retrieval by the getMessage() method)
   */
  DeanException(String message) {
    super(message);
  }
}
