//Meetkumar Saspara
//115971301
//R:01
//HW2

/**
 * Represents a doubly linked list of cars.
 */

public class CarList {

  // Head of the linked list
  private CarListNode head;
  // Tail of the linked list
  private CarListNode tail;
  // Cursor pointing to a specific node in the list
  private CarListNode cursor;
  // Total number of cars in the list
  public static int numCars = 0;

  /**
   * Constructs a new empty CarList.
   */

  public CarList() {
    this.head = null;
    this.tail = null;
    this.cursor = null;
  }

  /**
   * Returns the number of cars in the list.
   *
   * @return the number of cars in the list
   */
  public int numCars() {
    return numCars;
  }

  /**
   * Gets the car currently pointed to by the cursor.
   *
   * @return the car pointed to by the cursor, or null if the cursor is null
   */
  public Car getCursorCar() {
    if (this.cursor == null) {
      return null;
    }
    return this.cursor.getData();
  }

  /**
   * Resets the cursor to the head of the list.
   */

  public void resetCursorToHead() {
    if (this.head == null) {
      this.cursor = null;
    }
    this.cursor = this.head;
  }

  /**
   * Resets the cursor to the tail of the list.
   */
  public void resetCursorToTail() {
    CarListNode temp = this.head;
    while (temp.getNext() != null) {
      temp = temp.getNext();
    }
    this.cursor = temp;
  }

  /**
   * Moves the cursor forward one position in the list.
   *
   * @throws EndOfListException if the cursor is at the tail of the list
   */
  public void cursorForward() throws EndOfListException {
    if (this.cursor == null || this.cursor.getNext() == null) {
      throw new EndOfListException("The cursor is at the tail of the list!");
    }
    this.cursor = this.cursor.getNext();
  }

  /**
   * Moves the cursor backward one position in the list.
   *
   * @throws EndOfListException if the cursor is at the head of the list
   */
  public void cursorBackward() throws EndOfListException {
    if (this.cursor == null || this.cursor.getPrev() == null) {
      throw new EndOfListException("The cursor is at the tail of the list!");
    }
    this.cursor = this.cursor.getPrev();
  }

  /**
   * Inserts a new car before the cursor.
   *
   * @param newCar the car to insert
   * @throws IllegalArgumentException if newCar is null
   */
  public void insertBeforeCursor(Car newCar) throws IllegalArgumentException {
    if (newCar == null) {
      throw new IllegalArgumentException("Nothing to paste.");
    }

    if (this.cursor == null) {
      this.head = new CarListNode(newCar);
      this.tail = this.head;
      this.cursor = this.head;
    } else if (this.cursor.getPrev() == null) {
      CarListNode newCarNode = new CarListNode(newCar);
      newCarNode.setNext(this.cursor);
      this.cursor.setPrev(newCarNode);
      this.head = newCarNode;
    } else {
      CarListNode newCarNode = new CarListNode(newCar);
      newCarNode.setNext(this.cursor);
      newCarNode.setPrev(this.cursor.getPrev());
      this.cursor.getPrev().setNext(newCarNode);
      this.cursor.setPrev(newCarNode);
    }
    numCars++;
  }

  /**
   * Appends a new car to the tail of the list.
   *
   * @param newCar the car to append
   * @throws IllegalArgumentException if newCar is null
   */
  public void appendToTail(Car newCar) throws IllegalArgumentException {
    if (newCar == null) {
      throw new IllegalArgumentException("Nothing to paste.");
    }

    CarListNode newCarNode = new CarListNode(newCar);
    if (this.tail == null) {
      this.head = this.tail = this.cursor = newCarNode;
    } else {
      this.tail.setNext(newCarNode);
      newCarNode.setPrev(this.tail);
      this.tail = newCarNode;
    }
    numCars++;
  }

  /**
   * Removes the car pointed to by the cursor from the list.
   *
   * @return the removed car
   * @throws EndOfListException if the cursor is null
   */
  public Car removeCursor() throws EndOfListException {
    if (this.cursor == null) {
      throw new EndOfListException("The cursor is at the tail of the list!");
    }
    CarListNode removedNode = this.cursor;
    if (this.cursor == this.head) {
      this.head = this.head.getNext();
      if (this.head != null) {
        this.head.setPrev(null);
      }
      this.cursor = this.head;
    } else if (this.cursor == this.tail) {
      this.tail = this.tail.getPrev();
      if (this.tail != null) {
        this.tail.setNext(null);
      }
      this.cursor = this.tail;
    } else {
      this.cursor.getPrev().setNext(this.cursor.getNext());
      this.cursor.getNext().setPrev(this.cursor.getPrev());
      this.cursor = this.cursor.getPrev();
    }
    numCars--;
    return removedNode.getData();
  }

  /**
   * Merges two CarLists.
   *
   * @param list_1 the first CarList to merge
   * @param list_2 the second CarList to merge
   * @return the merged CarList
   */
  public CarList mergeLists(CarList list_1, CarList list_2) {
    CarListNode current1 = list_1.head;
    CarListNode current2 = list_2.head;
    CarListNode tempCurrent1 = null;
    CarListNode tempCurrent2 = null;

    while (current1 != null && current2 != null) {
      CarListNode next1 = current1.getNext();

      CarListNode next2 = current2.getNext();

      current1.setNext(current2);
      current2.setNext(next1);

      if (next1 == null) {
        tempCurrent1 = current2;
      } else if (next2 == null) {
        tempCurrent2 = next1;
      }

      current1 = next1;
      current2 = next2;
    }

    if (current2 != null) {
      tempCurrent1.setNext(current2);
    }

    return list_1;
  }

  /**
   * Prints the list of cars to the console.
   */
  public void printList() {
    CarListNode current = this.head;
    System.out.println("Make        Owner");
    System.out.println("----------------------");
    if (this.head == null) {
      System.out.println("[Empty]");
      return;
    }

    while (current != null) {
      if (current == this.cursor) {
        System.out.print("->");
      } else {
        System.out.print(" ");
      }

      System.out.println(
        current.getData().getMake() + "        " + current.getData().getOwner()
      );
      current = current.getNext();
    }
  }

  /**
   * Checks if the given CarList is null.
   *
   * @param list the CarList to check
   * @return true if the CarList is null, false otherwise
   */
  public static boolean isCutNull(CarList list) {
    if (list.head == null || list.tail == null || list.cursor == null) {
      return true;
    }
    return false;
  }

  /**
   * Empties the given CarList.
   *
   * @param list the CarList to empty
   */

  public void emptyList(CarList list) {
    this.cursor = null;
    this.head = null;
    this.tail = null;
  }

  /**
   * Sets the cursor to null.
   */
  public void nullCursor() {
    this.cursor = null;
  }

  /**
   * Sorts the list of cars in non-decreasing order by make and then by owner.
   */
  public void sortList() {
    if (this.head == null || this.head.getNext() == null) {
      return;
    }

    boolean swapped;
    CarListNode current;

    do {
      swapped = false;
      current = head;

      while (current.getNext() != null) {
        int compareCar = current
          .getData()
          .getMake()
          .compareTo(current.getNext().getData().getMake());
        if (
          compareCar > 0 ||
          (
            compareCar == 0 &&
            current
              .getData()
              .getOwner()
              .compareTo(current.getNext().getData().getOwner()) >
            0
          )
        ) {
          boolean isCursor = false;
          if (current == this.cursor) {
            isCursor = true;
          }
          Car temp = current.getData();

          current.setData(current.getNext().getData());
          current.getNext().setData(temp);
          if (isCursor) {
            this.cursor = current.getNext();
          }

          swapped = true;
        }
        current = current.getNext();
      }
    } while (swapped);
  }
}
