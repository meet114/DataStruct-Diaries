//Meetkumar Saspara
//115971301
//R:01
//HW3

import java.util.ArrayList;
import java.util.EmptyStackException;

/**
 * Represents a stack of packages.
 */

public class PackageStack {

  private final int CAPACITY = 7;

  private ArrayList<Package> list = new ArrayList<Package>();

  /**
   * Checks if the package stack is empty.
   *
   * @return true if the stack is empty, false otherwise
   */
  public boolean isEmpty() {
    return this.list.size() == 0;
  }

  /**
   * Checks if the package stack is full.
   *
   * @return true if the stack is full, false otherwise
   */
  public boolean isFull() {
    return list.size() == CAPACITY;
  }

  /**
   * Pushes a package onto the stack.
   *
   * @param x the package to push onto the stack
   * @throws FullStackException if the stack is full
   */
  public void push(Package x) throws FullStackException {
    if (list.size() == CAPACITY) {
      throw new FullStackException("Change line 20");
    }
    list.add(x);
  }

  /**
   * Pops a package from the stack.
   *
   * @return the package popped from the stack
   * @throws EmptyStackException if the stack is empty
   */
  public Package pop() throws EmptyStackException {
    if (list.isEmpty()) {
      System.out.println("Source stack is empty. Cannot move a package.");
      throw new EmptyStackException();
    }

    Package top = list.get(list.size() - 1);
    list.remove(list.size() - 1);
    return top;
  }

  /**
   * Prints the list of packages in the stack.
   */
  public void printList() {
    for (int i = 0; i < list.size(); i++) {
      System.out.print(
        "[" +
        list.get(i).getRecipient() +
        " " +
        list.get(i).getArrivalDate() +
        "]"
      );
    }
  }

  /**
   * Retrieves a list of packages for a specific recipient.
   *
   * @param recipient the recipient to filter packages by
   * @return a list of packages for the specified recipient
   */
  public ArrayList<Package> userPackages(String recipient) {
    ArrayList<Package> tempList = new ArrayList<Package>();

    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getRecipient().equals(recipient)) {
        Package x = new Package(
          recipient,
          list.get(i).getArrivalDate(),
          list.get(i).getWeight()
        );
        tempList.add(x);
      }
    }
    return tempList;
  }

  /**
   * Removes a specified package from the stack.
   *
   * @param pkg the package to remove
   */
  public void remove(Package pkg) {
    list.remove(pkg);
  }

  /**
   * Retrieves the list of packages in the stack.
   *
   * @return the list of packages in the stack
   */
  public ArrayList<Package> getList() {
    return list;
  }

  /**
   * Retrieves the package at the top of the stack without removing it.
   *
   * @return the package at the top of the stack
   */
  public Package peek() {
    if (isEmpty()) {
      return null;
    }
    return list.get(list.size() - 1);
  }
}
