//Meetkumar Saspara
//115971301
//R:01
//HW4

import java.util.LinkedList;

/**
 * The VirtualLine class represents a virtual line for customers waiting for a ride.
 */
public class VirtualLine extends LinkedList<Person> {

  /** Constructs a new VirtualLine object. */
  public VirtualLine() {}

  /**
   * Retrieves, but does not remove, the person at the front of the virtual line.
   *
   * @return The person at the front of the virtual line.
   */
  public Person peek() {
    return this.peekFirst();
  }

  /**
   * Adds a person to the end of the virtual line.
   *
   * @param person The person to be added to the virtual line.
   */
  public void enqueue(Person person) {
    this.addLast(person);
  }

  /**
   * Retrieves and removes the person at the end of the virtual line.
   *
   * @return The person at the end of the virtual line.
   */
  public Person dequeue() {
    return this.pollLast();
  }
}
