//Meetkumar Saspara
//115971301
//R:01
//HW4

/**
 * The HoldingQueue class represents a queue for holding customers waiting for a ride.
 */
public class HoldingQueue extends VirtualLine {

  /** The maximum size of the holding queue. */
  private int maxSize = 0;

  /** Constructs a new HoldingQueue object. */
  public HoldingQueue() {}

  /**
   * Gets the maximum size of the holding queue.
   *
   * @return The maximum size of the holding queue.
   */
  public int getMaxSize() {
    return maxSize;
  }

  /**
   * Sets the maximum size of the holding queue.
   *
   * @param maxSize The maximum size of the holding queue to set.
   */
  public void setMaxSize(int maxSize) {
    this.maxSize = maxSize;
  }

  /**
   * Adds a person to the holding queue.
   *
   * @param person The person to be added to the holding queue.
   */
  public void enqueue(Person person) {
    super.enqueue(person);
  }

  /**
   * Removes and returns the person at the front of the holding queue.
   *
   * @return The person at the front of the holding queue.
   */
  public Person dequeue() {
    return super.dequeue();
  }

  /**
   * Returns the person at the front of the holding queue without removing it.
   *
   * @return The person at the front of the holding queue.
   */
  public Person peek() {
    return super.peek();
  }

  /**
   * Checks if the holding queue is empty.
   *
   * @return true if the holding queue is empty, false otherwise.
   */
  public boolean isEmpty() {
    return super.isEmpty();
  }
}
