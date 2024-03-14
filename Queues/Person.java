//Meetkumar Saspara
//115971301
//R:01
//HW4

import java.util.LinkedList;

/**
 * The Person class represents a customer in the theme park.
 */
public class Person {

  /** The unique identifier of the person. */
  private int number;

  /** The maximum number of lines the person can be on. */
  private int maxLines;

  /** The list of rides the person is currently on. */
  private LinkedList<Ride> lines;

  /** The status of the person (e.g., available, on ride, holding). */
  private Status status;

  /** The type of customer (e.g., gold, silver, regular). */
  private CustomerType customerType;

  /**
   * Gets the type of customer.
   *
   * @return The customer type.
   */

  public CustomerType getCustomerType() {
    return customerType;
  }

  /**
   * Sets the type of customer.
   *
   * @param customerType The customer type to set.
   */
  public void setCustomerType(CustomerType customerType) {
    this.customerType = customerType;
  }

  /**
   * Gets the unique identifier of the person.
   *
   * @return The unique identifier of the person.
   */
  public int getNumber() {
    return number;
  }

  /**
   * Sets the unique identifier of the person.
   *
   * @param number The unique identifier of the person to set.
   */
  public void setNumber(int number) {
    this.number = number;
  }

  /**
   * Gets the maximum number of lines the person can be on.
   *
   * @return The maximum number of lines.
   */
  public int getMaxLines() {
    return maxLines;
  }

  /**
   * Sets the maximum number of lines the person can be on.
   *
   * @param maxLines The maximum number of lines to set.
   */
  public void setMaxLines(int maxLines) {
    this.maxLines = maxLines;
  }

  /**
   * Gets the list of rides the person is currently on.
   *
   * @return The list of rides.
   */
  public LinkedList<Ride> getLines() {
    return lines;
  }

  /**
   * Sets the ride the person is currently on.
   *
   * @param lines The ride to set.
   */
  public void setLines(Ride lines) {
    this.lines.add(lines);
  }

  /**
   * Gets the status of the person.
   *
   * @return The status of the person.
   */
  public Status getStatus() {
    return status;
  }

  /**
   * Sets the status of the person.
   *
   * @param status The status to set.
   */
  public void setStatus(Status status) {
    this.status = status;
  }

  /**
   * Constructs a new Person object with the given number.
   *
   * @param number The unique identifier of the person.
   * @throws IllegalArgumentException If the number is not positive.
   */
  public Person(int number) throws IllegalArgumentException {
    if (number <= 0) {
      throw new IllegalArgumentException(
        "Wrong number generated: Number must be positive. LINE:122:Person_ Constructor"
      );
    }
    this.number = number;
    this.maxLines = 1;
    this.lines = new LinkedList<>();
    this.status = Status.Available;
  }

  /**
   * Sets the customer type based on the ticket status.
   *
   * @param ticketStatus The ticket status.
   * @return The updated person object.
   */
  public Person setCustomerInfo(CustomerType ticketStatus) {
    Person customer = new Person(number);

    if (ticketStatus.equals(CustomerType.Gold)) {
      customer.setMaxLines(3);
    } else if (ticketStatus.equals(CustomerType.Silver)) {
      customer.setMaxLines(2);
    } else {
      customer.setMaxLines(1);
    }
    return customer;
  }

  /**
   * Returns a string representation of the rides the person is on.
   *
   * @return A string representation of the rides.
   */
  public String printRides() {
    String res = "";
    for (Ride ride : lines) {
      res += ride.getName() + " ";
    }
    return res;
  }
}
