//Meetkumar Saspara
//115971301
//R:01
//HW06

/**
 * The {@code FileException} class represents an exception that occurs during file operations.
 * It extends the {@code Exception} class, indicating that it is a checked exception and must be caught
 * or declared to be thrown in method signatures.
 */
public class FileException extends Exception {

  /**
   * Constructs a new {@code FileException} with no specified detail message.
   * The detail message is initialized to {@code null}.
   */
  FileException() {
    super();
  }

  /**
   * Constructs a new {@code FileException} with the specified detail message.
   * The detail message is a {@code String} that describes the specific cause of the exception.
   *
   * @param message the detail message (which is saved for later retrieval by the {@link Throwable#getMessage()} method).
   */
  FileException(String message) {
    super(message);
  }
}
