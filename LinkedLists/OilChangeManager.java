//Meetkumar Saspara
//115971301
//R:01
//HW2

import java.util.Scanner;

/**
 * Manages oil change job lists for Joe and Donny, including editing, merging, printing, and sorting.
 */
public class OilChangeManager {

  /**
   * Main method to interact with the user and manage oil change job lists.
   *
   * @param args command-line arguments (not used)
   */
  public static void main(String[] args) {
    // Scanner object for user input
    Scanner input = new Scanner(System.in);
    // CarList for Joe's job list
    CarList joeList = new CarList();
    // CarList for Donny's job list
    CarList donnyList = new CarList();
    // CarList for finished job list
    CarList finishList = new CarList();
    // Flag to control program loop
    boolean quit = false;
    // Temporary variable to store cut car
    Car cutNode = null;
    String choice;

    while (!quit) {
      System.out.print(
        "Menu\n" +
        "     L) Edit Job Lists for Joe and Donny\n" +
        "     M) Merge Job Lists\n" +
        "     P) Print Job Lists\n" +
        "     F) Paste car to end of finished car list\n" +
        "     S) Sort Job Lists\n" +
        "     Q) Quit.\n" +
        "Please select an option:"
      );
      choice = input.nextLine().toUpperCase();
      switch (choice.charAt(0)) {
        case 'L':
          System.out.println("Please select a list - Joe (J) or Donny (D): ");
          String empList = input.nextLine().toUpperCase();
          CarList currentList = empList.charAt(0) == 'J' ? joeList : donnyList;

          System.out.print(
            "Options:\n" +
            "     A) Add a car to the end of the list\n" +
            "     F) Cursor Forward\n" +
            "     H) Cursor to Head\n" +
            "     T) Cursor to Tail\n" +
            "     B) Cursor Backward\n" +
            "     I) Insert car before cursor\n" +
            "     X) Cut car at cursor\n" +
            "     V) Paste before cursor\n" +
            "     R) Remove cursor\n" +
            "Please select an action: "
          );
          String empChoice = input.nextLine().toUpperCase();
          switch (empChoice.charAt(0)) {
            case 'A':
              System.out.println(
                "Please enter vehicle make (Ford, GMC, Chevy, Jeep, Dodge, Chrysler, and Lincoln): "
              );
              String makeName = input.nextLine().toUpperCase();

              try {
                Make makeEnum = Make.valueOf(makeName);
                System.out.println("Please enter owner's name:");
                String ownerName = input.nextLine();

                currentList.appendToTail(new Car(makeEnum, ownerName));
                System.out.println(
                  ownerName +
                  "'s " +
                  makeName +
                  " has been scheduled for an oil change with " +
                  (empList.charAt(0) == 'J' ? "Joe" : "Donny") +
                  " and has been added to his list."
                );
              } catch (IllegalArgumentException e) {
                System.out.println("We don't service " + makeName + "!");
              }

              break;
            case 'F':
              try {
                currentList.cursorForward();
                System.out.println(
                  "Cursor Moved Forward in " +
                  (empList.charAt(0) == 'J' ? "Joe's" : "Donny's") +
                  " List."
                );
              } catch (EndOfListException e) {
                System.out.println(e.getMessage());
              }
              break;
            case 'H':
              currentList.resetCursorToHead();
              System.out.println(
                "Cursor Moved To Head in " +
                (empList.charAt(0) == 'J' ? "Joe's" : "Donny's") +
                " List."
              );
              break;
            case 'T':
              currentList.resetCursorToTail();
              System.out.println(
                "Cursor Moved To Tail in " +
                (empList.charAt(0) == 'J' ? "Joe's" : "Donny's") +
                " List."
              );
              break;
            case 'B':
              try {
                currentList.cursorBackward();
                System.out.println(
                  "Cursor Moved Backward in " +
                  (empList.charAt(0) == 'J' ? "Joe's" : "Donny's") +
                  " List."
                );
              } catch (EndOfListException e) {
                System.out.println(e.getMessage());
              }
              break;
            case 'I':
              System.out.println(
                "Please enter vehicle make (Ford, GMC, Chevy, Jeep, Dodge, Chrysler, and Lincoln): "
              );
              String makeBefore = input.nextLine().toUpperCase();
              try {
                Make makeEnum = Make.valueOf(makeBefore);
                System.out.println("Please enter owner's name:");
                String ownerBefore = input.nextLine();

                currentList.insertBeforeCursor(new Car(makeEnum, ownerBefore));
                System.out.println(
                  ownerBefore +
                  "'s " +
                  makeBefore +
                  " has been scheduled for an oil change with " +
                  (empList.charAt(0) == 'J' ? "Joe" : "Donny") +
                  " and has been added to his list."
                );
              } catch (IllegalArgumentException e) {
                System.out.println("We don't service " + makeBefore + "!");
              }

              break;
            case 'X':
              try {
                cutNode = currentList.removeCursor();
                System.out.println(
                  "Cursor cut in " +
                  (empList.charAt(0) == 'J' ? "Joe's" : "Donny's") +
                  " List."
                );
              } catch (Exception e) {
                System.out.println("\n" + e.getMessage());
              }
              break;
            case 'V':
              try {
                currentList.insertBeforeCursor(cutNode);
                System.out.println(
                  "Cursor pasted in " +
                  (empList.charAt(0) == 'J' ? "Joe's" : "Donny's") +
                  " List."
                );
                cutNode = null;
              } catch (IllegalArgumentException e) {
                System.out.println("\n" + e.getMessage());
              }
              break;
            case 'R':
              try {
                Car removedCar = currentList.removeCursor();
                System.out.println(
                  "Cursor removed in " +
                  (empList.charAt(0) == 'J' ? "Joe's" : "Donny's") +
                  " List: " +
                  removedCar
                );
              } catch (EndOfListException e) {
                System.out.println("The cursor is at the tail of the list");
              }
              break;
          }
          break;
        case 'M':
          System.out.println("Merge into which list? Joe (J) or Donny (D):");
          String destination = input.nextLine().toUpperCase();

          switch (destination.charAt(0)) {
            case 'J':
              joeList = joeList.mergeLists(joeList, donnyList);
              donnyList.emptyList(donnyList);
              System.out.println("Joe's list merged into Donny's.");

              break;
            case 'D':
              donnyList = donnyList.mergeLists(donnyList, joeList);
              joeList.emptyList(joeList);
              System.out.println("Donny's list merged into Joe's.");

              break;
            default:
              System.out.println("Invalid choice. Try again");

              break;
          }
          break;
        case 'P':
          System.out.println("Joe's List:");
          joeList.printList();
          System.out.println("Donny's List:");
          donnyList.printList();
          System.out.println("Finished List:");
          finishList.nullCursor();
          finishList.printList();

          break;
        case 'F':
          try {
            finishList.appendToTail(cutNode);
          } catch (IllegalArgumentException e) {
            System.out.println("\n" + e.getMessage());
          }
          cutNode = null;

          break;
        case 'S':
          joeList.sortList();
          donnyList.sortList();
          finishList.sortList();
          break;
        case 'Q':
          quit = true;
          System.out.println("Enjoy your retirement!");
          break;
      }
    }
  }
}
