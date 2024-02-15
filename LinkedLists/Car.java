//Meetkumar Saspara
//115971301
//R:01
//HW2

/**
 * Represents a car with a make and an owner.
 */
public class Car {

  // Enum representing the make of the car
  private Make make;
  // Owner's name
  private String owner;

  /**
   * Constructs a new Car instance with the specified make and owner.
   *
   * @param make  the make of the car
   * @param owner the owner of the car
   */
  public Car(Make make, String owner) {
    this.make = make;
    this.owner = owner;
  }

  /**
   * Gets the make of the car.
   *
   * @return the make of the car
   */

  public Make getMake() {
    return this.make;
  }

  /**
   * Sets the make of the car.
   *
   * @param make the make of the car
   */

  public void setMake(Make make) {
    this.make = make;
  }

  /**
   * Gets the owner of the car.
   *
   * @return the owner of the car
   */

  public String getOwner() {
    return this.owner;
  }

  /**
   * Sets the owner of the car.
   *
   * @param owner the owner of the car
   */

  public void setOwner(String owner) {
    this.owner = owner;
  }

  /**
   * Returns a string representation of the car.
   *
   * @return a string representation of the car
   */

  public String toString() {
    String toString_Car = "Car [make=" + make + ", owner=" + owner + "]";
    return toString_Car;
  }
}
