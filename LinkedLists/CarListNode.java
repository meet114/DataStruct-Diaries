//Meetkumar Saspara
//115971301
//R:01
//HW2

/**
 * Represents a node in a doubly linked list of cars.
 */

public class CarListNode {

  /**
   * Constructs a new CarListNode with the specified data.
   *
   * @param initData the data to be stored in the node
   * @throws IllegalArgumentException if initData is null
   */

  // Data (car information) stored in this node
  private Car data;
  // Reference to the next node in the list
  private CarListNode next;
  // Reference to the previous node in the list
  private CarListNode prev;

  /**
   * Constructs a new CarListNode with the specified data.
   *
   * @param initData the data to be stored in the node
   * @throws IllegalArgumentException if initData is null
   */

  public CarListNode(Car initData) throws IllegalArgumentException {
    if (initData == null) {
      throw new IllegalArgumentException("Change this LA _Nd line 9");
    }
    this.next = null;
    this.prev = null;
    this.data = initData;
  }

  // Getters and setters for data, next, and prev
  /**
   * Gets the previous node in the list.
   *
   * @return the previous node in the list
   */

  public CarListNode getPrev() {
    return this.prev;
  }

  /**
   * Sets the previous node in the list.
   *
   * @param prev the previous node in the list
   */
  public void setPrev(CarListNode prev) {
    this.prev = prev;
  }

  /**
   * Gets the next node in the list.
   *
   * @return the next node in the list
   */
  public CarListNode getNext() {
    return this.next;
  }

  /**
   * Sets the next node in the list.
   *
   * @param next the next node in the list
   */
  public void setNext(CarListNode next) {
    this.next = next;
  }

  /**
   * Gets the data stored in the node.
   *
   * @return the data stored in the node
   */

  public Car getData() {
    return this.data;
  }

  /**
   * Sets the data stored in the node.
   *
   * @param data the data to be stored in the node
   */
  public void setData(Car data) {
    this.data = data;
  }
}
