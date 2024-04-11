//Meetkumar Saspara
//115971301
//R:01
//HW06

/**
 * Custom exception class for invalid student data.
 */

public class invalidStudentException extends Exception {

  /**
   * Constructs an {@code InvalidStudentException} with no detail message.
   */
  invalidStudentException() {
    super();
  }

  /**
   * Constructs an {@code InvalidStudentException} with the specified detail message.
   *
   * @param message the detail message (which is saved for later retrieval by the {@link #getMessage()} method)
   */
  invalidStudentException(String message) {
    super(message);
  }
}
