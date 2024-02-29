//Meetkumar Saspara
//115971301
//R:01
//HW3

/**
 * Represents a package with recipient, arrival date, and weight.
 */
public class Package {

  /**
   * The recipient of the package.
   */
  private String recipient;
  /**
   * The arrival date of the package.
   */

  private int arrivalDate = 0;
  /**
   * The weight of the package.
   */
  private double weight;

  /**
   * Constructs a Package object with the given recipient, arrival date, and weight.
   *
   * @param recipient   the recipient of the package
   * @param arrivalDate the arrival date of the package
   * @param weight      the weight of the package
   */
  public Package(String recipient, int arrivalDate, double weight) {
    this.recipient = recipient;
    this.arrivalDate = arrivalDate;
    this.weight = weight;
  }

  /**
   * Returns the weight of the package.
   *
   * @return the weight of the package
   */
  public double getWeight() {
    return weight;
  }

  /**
   * Sets the weight of the package.
   *
   * @param weight the weight to set
   */
  public void setWeight(double weight) {
    this.weight = weight;
  }

  /**
   * Returns the arrival date of the package.
   *
   * @return the arrival date of the package
   */
  public int getArrivalDate() {
    return arrivalDate;
  }/**
   * Sets the arrival date of the package.
   *
   * @param arrivalDate the arrival date to set
   */

  public void setArrivalDate(int arrivalDate) {
    this.arrivalDate = arrivalDate;
  }

  /**
   * Returns the recipient of the package.
   *
   * @return the recipient of the package
   */
  public String getRecipient() {
    return recipient;
  }

  /**
   * Sets the recipient of the package.
   *
   * @param recipient the recipient to set
   */
  public void setRecipient(String recipient) {
    this.recipient = recipient;
  }
}
