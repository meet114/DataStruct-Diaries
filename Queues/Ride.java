//Meetkumar Saspara
//115971301
//R:01
//HW4

import java.util.LinkedList;

/**
 * The Ride class represents a ride in the theme park.
 */
public class Ride {

  /**
   * The duration of the ride.
   */
  private int duration;
  /**
   * The time left for the ride.
   */
  private int timeLeft;
  /**
   * The name of the ride.
   */
  private String Name;
  /**
   * The virtual line for the ride.
   */
  private VirtualLine virtualLine;
  /**
   * The holding queue for the ride.
   */
  private HoldingQueue holdingQueue;
  /**
   * The list of people on the ride.
   */
  private LinkedList<Person> peopleOnRide;
  /**
   * The maximum capacity of the ride.
   */
  private int rideMax;
  /**
   * The number of completed rides.
   */
  private int rideCompleted;

  /**
   * Constructs a new Ride object with default values.
   */
  public Ride() {
    duration = 0;
    timeLeft = 0;
    Name = "";
    virtualLine = new VirtualLine();
    holdingQueue = new HoldingQueue();
    peopleOnRide = new LinkedList<>();
    rideMax = 0;
    rideCompleted = 0;
  }

  /**
   * Gets the number of completed rides for this ride.
   *
   * @return The number of completed rides.
   */

  public int getRideCompleted() {
    return rideCompleted;
  }

  /**
   * Sets the number of completed rides for this ride.
   *
   * @param rideCompleted The number of completed rides to set.
   */
  public void setRideCompleted(int rideCompleted) {
    this.rideCompleted += rideCompleted;
  }

  /**
   * Gets the maximum capacity of the ride.
   *
   * @return The maximum capacity of the ride.
   */
  public int getRideMax() {
    return rideMax;
  }

  /**
   * Updates the time left for the ride based on the current time.
   *
   * @param currentTime The current time.
   */
  public void timeUpdateLeft(int currentTime) {
    int time = timeLeft - 1;
    this.timeLeft = time;
  }

  /**
   * Resets the time left for the ride to its duration.
   */
  public void restTimeLeft() {
    this.timeLeft = this.duration;
  }

  /**
   * Sets the maximum capacity of the ride.
   *
   * @param rideMax The maximum capacity of the ride.
   */
  public void setRideMax(int rideMax) {
    this.rideMax = rideMax;
  }

  /**
   * Gets the duration of the ride.
   *
   * @return The duration of the ride.
   */
  public int getDuration() {
    return duration;
  }

  /**
   * Sets the duration of the ride.
   *
   * @param duration The duration to set.
   */
  public void setDuration(int duration) {
    this.duration = duration;
    this.timeLeft = duration;
  }

  /**
   * Gets the time left for the ride.
   *
   * @return The time left for the ride.
   */
  public int getTimeLeft() {
    return timeLeft;
  }

  /**
   * Sets the time left for the ride.
   *
   * @param timeLeft The time left to set.
   */
  public void setTimeLeft(int timeLeft) {
    this.timeLeft = timeLeft;
  }

  /**
   * Gets the name of the ride.
   *
   * @return The name of the ride.
   */
  public String getName() {
    return Name;
  }

  /**
   * Sets the name of the ride.
   *
   * @param name The name to set.
   */
  public void setName(String name) {
    this.Name = name;
  }

  /**
   * Gets the virtual line of the ride.
   *
   * @return The virtual line of the ride.
   */
  public VirtualLine getVirtualLine() {
    return virtualLine;
  }

  /**
   * Sets the virtual line of the ride.
   *
   * @param virtualLine The virtual line to set.
   */
  public void setVirtualLine(VirtualLine virtualLine) {
    this.virtualLine = virtualLine;
  }

  /**
   * Gets the holding queue of the ride.
   *
   * @return The holding queue of the ride.
   */
  public HoldingQueue getHoldingQueue() {
    return this.holdingQueue;
  }

  /**
   * Sets the holding queue of the ride.
   *
   * @param holdingQueue The holding queue to set.
   */
  public void setHoldingQueue(HoldingQueue holdingQueue) {
    this.holdingQueue = holdingQueue;
  }

  /**
   * Gets the list of people on the ride.
   *
   * @return The list of people on the ride.
   */
  public LinkedList<Person> getPeopleOnRide() {
    return peopleOnRide;
  }

  /**
   * Sets the list of people on the ride.
   *
   * @param peopleOnRide The list of people to set.
   */
  public void setPeopleOnRide(LinkedList<Person> peopleOnRide) {
    this.peopleOnRide = peopleOnRide;
  }

  /**
   * Prints the status of people in the list.
   *
   * @param peopleInList The list of people.
   */
  public void rideQueue(LinkedList<Person> peopleInList) {
    for (Person person : peopleInList) {
      System.out.print(person.getStatus());
    }
  }

  /**
   * Prints the list of people on the ride, in the holding queue, and in the virtual queue.
   */
  public void printList() {
    System.out.print("On Ride: ");
    for (Person tempPerson : peopleOnRide) {
      System.out.print(
        tempPerson.getCustomerType() + " " + tempPerson.getNumber() + ", "
      );
    }
    System.out.println();
    System.out.print("Holding Queue: ");
    for (Person tempPerson : holdingQueue) {
      System.out.print(
        tempPerson.getCustomerType() + " " + tempPerson.getNumber() + ", "
      );
    }
    System.out.println();
    System.out.print("Virtual Queue: ");
    for (Person tempPerson : virtualLine) {
      System.out.print(
        tempPerson.getCustomerType() + " " + tempPerson.getNumber() + ", "
      );
    }
    System.out.println();
  }
}
