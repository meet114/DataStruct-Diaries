//Meetkumar Saspara
//115971301
//R:01
//HW3

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Scanner;

/**
 * Manages the operations of a mailroom including delivering, retrieving, and managing packages.
 */
public class MailroomManager {

  public int[] twoSum(int[] nums, int target) {
    int curr = 1;
    int prev = 0;
    for (int i = 0; i < nums.length - 1; i++) {
      if (nums[curr] + nums[prev] == target) {
        System.out.println("[" + prev + "," + curr + "]");
        break;
      }
    }
    return nums;
  }

  static String[] nameBegins = { "A-G", "H-J", "K-M", "N-R", "S-Z" };

  /**
   * Main method to run the mailroom manager application.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    PackageStack[] stacks = new PackageStack[6];
    for (int i = 0; i < 6; i++) {
      stacks[i] = new PackageStack();
    }

    Scanner input = new Scanner(System.in);
    int day = 0;
    System.out.println(
      "Welcome to the Irving Mailroom Manager. You can try to make it better, but the odds are stacked against you. It is day " +
      day
    );

    while (true) {
      System.out.print(
        "Menu:\n" +
        "     D) Deliver a package\n" +
        "     G) Get someone's package\n" +
        "     T) Make it tomorrow\n" +
        "     P) Print the stacks\n" +
        "     M) Move a package from one stack to another\n" +
        "     F) Find packages in the wrong stack and move to floor\n" +
        "     L) List all packages awaiting a user\n" +
        "     E) Empty the floor.\n" +
        "     Q) Quit\n" +
        "Please select an option: "
      );
      String option = input.next().toUpperCase();
      switch (option) {
        case "D":
          System.out.print("Please enter the recipient name: ");
          String recipient = input.next();
          System.out.print("Please enter the weight (lbs): ");
          double weight = input.nextDouble();
          try {
            deliverPackage(stacks, recipient, weight, day);
          } catch (FullStackException e) {
            System.out.println(e.getMessage());
          }

          break;
        case "G":
          System.out.print("Please enter the recipient name: ");
          recipient = input.next();
          try {
            getPackage(stacks, recipient);
          } catch (Exception e) {
            System.out.println(e.getMessage());
          }

          break;
        case "T":
          day++;
          System.out.println("It is now day " + day + ".");

          break;
        case "P":
          printStack(stacks, nameBegins);
          break;
        case "M":
          System.out.println(
            "Please enter the source stack (enter 0 for floor): "
          );
          int indexSource = input.nextInt();
          if (indexSource != 0) {
            indexSource = indexSource - 1;
          }
          if (indexSource == 0) {
            indexSource = 5;
          }
          boolean emp = false;
          if (stacks[indexSource].peek() == null) {
            System.out.println("Source stack is empty. Cannot move a package.");
            emp = true;
          }
          System.out.println("Please enter the destination stack: ");
          int indexDestination = input.nextInt();
          if (indexDestination != 0) {
            indexDestination = indexDestination - 1;
          }

          if (indexDestination == 0) {
            indexDestination = 5;
          }

          if (emp == false) {
            moveStacks(
              stacks[indexSource],
              stacks[indexDestination],
              indexSource,
              indexDestination
            );
          }
          break;
        case "F":
          WrongPackages(stacks);

          break;
        case "L":
          System.out.print("Please enter the recipient name: ");
          recipient = input.next();
          listPackages(recipient, stacks);
          break;
        case "E":
          clearFloor(stacks);
          break;
        case "Q":
          System.out.println("Use Amazon Locker next time.");
          System.out.println("(A-G, H-J, K-M, N-R, S-Z)");
          return;
      }
    }
  }

  /**
   * Moves packages that are in the wrong stack to the floor stack.
   *
   * @param stacks An array of PackageStack objects representing different stacks.
   */
  private static void WrongPackages(PackageStack[] stacks) {
    for (int i = 0; i < stacks.length; i++) {
      ArrayList<Package> elementToMove = new ArrayList<>();
      for (int j = 0; j < stacks[i].getList().size(); j++) {
        Package element = stacks[i].getList().get(j);
        char firstChar = element.getRecipient().toUpperCase().charAt(0);
        int stacksIndex = 0;

        if (firstChar >= 'A' && firstChar <= 'G') {
          stacksIndex = 0;
        } else if (firstChar >= 'H' && firstChar <= 'J') {
          stacksIndex = 1;
        } else if (firstChar >= 'K' && firstChar <= 'M') {
          stacksIndex = 2;
        } else if (firstChar >= 'N' && firstChar <= 'R') {
          stacksIndex = 3;
        } else if (firstChar >= 'S' && firstChar <= 'Z') {
          stacksIndex = 4;
        }
        if (stacksIndex != i) {
          elementToMove.add(element);
        }
      }

      for (int k = 0; k < elementToMove.size(); k++) {
        Package element = elementToMove.get(k);
        try {
          stacks[i].getList().remove(element);
          stacks[5].push(element);
        } catch (FullStackException e) {
          System.out.println(e.getMessage());
        }
      }
    }

    System.out.println("Misplaced packages moved to floor.");
  }

  /**
   * Lists all packages awaiting a specific recipient.
   *
   * @param recipient The name of the recipient whose packages are to be listed.
   * @param stacks An array of PackageStack objects representing different stacks.
   */

  private static void listPackages(String recipient, PackageStack[] stacks) {
    ArrayList<ArrayList<Package>> tempLists = new ArrayList<>();
    int count = 0;
    for (int i = 0; i < 6; i++) {
      tempLists.add(stacks[i].userPackages(recipient));
      if (tempLists.get(i).size() > 0) {
        count += tempLists.get(i).size();
      }
    }
    if (count == 0) {
      System.out.println("No packages found for " + recipient + ".");
    }
    System.out.println(recipient + " has " + count + " package total.");
    for (int i = 0; i < tempLists.size(); i++) {
      ArrayList<Package> stackPackages = tempLists.get(i);
      for (int j = 0; j < stackPackages.size(); j++) {
        Package diffPackages = stackPackages.get(j);
        int stackNumber = i + 1;
        int packageInStack = j + 1;
        int getWeight = (int) diffPackages.getWeight();
        System.out.println(
          "Package " +
          packageInStack +
          " is in Stack " +
          stackNumber +
          ", it was delivered on day " +
          diffPackages.getArrivalDate() +
          ", and weighs " +
          getWeight +
          " lbs"
        );
      }
    }
  }

  /**
   * Delivers a package to the appropriate stack based on the recipient's name.
   *
   * @param stacks An array of PackageStack objects representing different stacks.
   * @param recipient The name of the recipient.
   * @param weight The weight of the package in pounds.
   * @param arrivalDate The arrival date of the package.
   * @throws FullStackException If the stack is full and cannot accept more packages.
   */
  public static void deliverPackage(
    PackageStack[] stacks,
    String recipient,
    double weight,
    int arrivalDate
  ) throws FullStackException {
    char firstChar = recipient.toUpperCase().charAt(0);
    int stacksArray = 0;
    if (firstChar >= 'A' && firstChar <= 'G') {
      stacksArray = 0;
    } else if (firstChar >= 'H' && firstChar <= 'J') {
      stacksArray = 1;
    } else if (firstChar >= 'K' && firstChar <= 'M') {
      stacksArray = 2;
    } else if (firstChar >= 'N' && firstChar <= 'R') {
      stacksArray = 3;
    } else if (firstChar >= 'S' && firstChar <= 'Z') {
      stacksArray = 4;
    }
    if (stacks[stacksArray].isFull()) {
      if (stacksArray == 4) {
        stacksArray--;
      } else {
        stacksArray++;
        stacks[stacksArray].push(new Package(recipient, arrivalDate, weight));
        int getWeight = (int) stacks[stacksArray].peek().getWeight();
        System.out.println(
          "A " +
          getWeight +
          " lb package is awaiting pickup by " +
          stacks[stacksArray].peek().getRecipient() +
          "."
        );
      }
      return;
    } else {
      stacks[stacksArray].push(new Package(recipient, arrivalDate, weight));
      int getWeight = (int) stacks[stacksArray].peek().getWeight();
      System.out.println(
        "A " +
        getWeight +
        " lb package is awaiting pickup by " +
        stacks[stacksArray].peek().getRecipient() +
        "."
      );
    }
  }

  /**
   * Retrieves a package for the specified recipient.
   *
   * @param stacks An array of PackageStack objects representing different stacks.
   * @param recipient The name of the recipient.
   * @throws FullStackException If the stack is full and cannot accept more packages.
   * @throws EmptyStackException If the stack is empty and cannot retrieve any packages.
   */
  public static void getPackage(PackageStack[] stacks, String recipient)
    throws FullStackException, EmptyStackException {
    int floorElement = 0;
    char firstChar = recipient.toUpperCase().charAt(0);
    int stacksArray = 0;

    if (firstChar >= 'A' && firstChar <= 'G') {
      stacksArray = 0;
    } else if (firstChar >= 'H' && firstChar <= 'J') {
      stacksArray = 1;
    } else if (firstChar >= 'K' && firstChar <= 'M') {
      stacksArray = 2;
    } else if (firstChar >= 'N' && firstChar <= 'R') {
      stacksArray = 3;
    } else if (firstChar >= 'S' && firstChar <= 'Z') {
      stacksArray = 4;
    }
    // if (stacks[stacksArray].isFull()) {
    //   if (stacksArray == 4) {
    //     stacksArray--;
    //   } else {
    //     stacksArray++;
    //   }
    //   if (stacks[stacksArray].peek() == null) {
    //     throw new EmptyStackException(
    //       "Empty Stack therefore cannot get packages."
    //     );
    //   }
    // }
    // if (stacks[stacksArray].peek() == null) {
    //   throw new EmptyStackException(
    //     "Empty Stack therefore cannot get packages."
    //   );
    // }
    int NumStack = stacksArray + 1;
    while (!(stacks[stacksArray].peek().getRecipient().equals(recipient))) {
      Package element = stacks[stacksArray].pop();
      stacks[5].push(element);
      floorElement++;
    }
    System.out.println(
      "Move " + floorElement + " packages from Stack " + NumStack + " to floor."
    );
    printStack(stacks, nameBegins);
    System.out.println();
    int getWeight = (int) stacks[stacksArray].peek().getWeight();
    System.out.println(
      "Give " +
      recipient +
      " " +
      getWeight +
      "lb package delivered on day " +
      stacks[stacksArray].peek().getArrivalDate() +
      "."
    );
    stacks[stacksArray].pop();

    while (floorElement > 0) {
      Package element = stacks[5].pop();
      stacks[stacksArray].push(element);
      floorElement--;
    }
    System.out.println(
      "Return " +
      NumStack +
      " packages to Stack " +
      floorElement +
      " from floor."
    );
    printStack(stacks, nameBegins);
  }

  /**
   * Prints the current state of the package stacks.
   *
   * @param stacks An array of PackageStack objects representing different stacks.
   * @param nameBegins An array of strings representing the names beginning each stack.
   */
  public static void printStack(PackageStack[] stacks, String[] nameBegins) {
    System.out.println("Current Packages:");
    System.out.println();
    System.out.println("--------------------------------");
    for (int i = 0; i < 6; i++) {
      int stackNum = i + 1;
      if (i == 5) {
        System.out.print("Floor: |");
        if (stacks[5].peek() == null) {
          System.out.print("empty.");
          System.out.println();
          return;
        }
        stacks[5].printList();
        return;
      } else if (stacks[i].peek() == null) {
        System.out.print(
          "Stack " + stackNum + " (" + nameBegins[i] + ")" + ":|"
        );
        System.out.print("empty.");
      } else {
        System.out.print(
          "Stack " + stackNum + " (" + nameBegins[i] + ")" + ":|"
        );
        stacks[i].printList();
      }
      System.out.println();
    }
  }

  /**
   * Moves packages from one stack to another.
   *
   * @param indexSource The source PackageStack from which packages are moved.
   * @param indexDestination The destination PackageStack to which packages are moved.
   * @param indexSource2 The index of the source stack.
   * @param indexDestination2 The index of the destination stack.
   */
  public static void moveStacks(
    PackageStack indexSource,
    PackageStack indexDestination,
    int indexSource2,
    int indexDestination2
  ) {
    try {
      Package eleSource = indexSource.pop();
      indexDestination.push(eleSource);
      indexSource2++;
      if (indexSource2 == 6) {
        indexSource2 = 0;
      }
      indexDestination2++;
      if (indexDestination2 == 6) {
        indexDestination2 = 0;
      }
      System.out.println(
        "Package moved successfully from stack " +
        indexSource2 +
        " to stack " +
        indexDestination2
      );
      indexSource2--;
      indexDestination2--;
    } catch (FullStackException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Clears the floor stack by removing all packages.
   *
   * @param stacks An array of PackageStack objects representing different stacks.
   */
  public static void clearFloor(PackageStack[] stacks) {
    while (!(stacks[5].isEmpty())) {
      stacks[5].pop();
    }
    System.out.println(
      "The floor has been emptied. Mr. Trash Can is no longer hungry."
    );
  }
}
