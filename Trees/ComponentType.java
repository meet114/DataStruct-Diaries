//Meetkumar Saspara
//115971301
//R:01
//HW5
/**
 * Enum representing different types of GUI components.
 */
public enum ComponentType {
  Button,
  Label,
  TextArea,
  HBox,
  VBox,
  AnchorPane;

  /**
   * Returns the string representation of the enum constant.
   *
   * @return String representation of the enum constant
   */
  @Override
  public String toString() {
    return this.name();
  }
}
