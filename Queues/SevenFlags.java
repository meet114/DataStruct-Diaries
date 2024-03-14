//Meetkumar Saspara
//115971301
//R:01
//HW4

import java.util.LinkedList;
import java.util.Scanner;

/**
 * The SevenFlags class simulates a theme park scenario with different types of customers and rides.
 */
public class SevenFlags {

  /** The list of rides available in the theme park. */
  private static LinkedList<Ride> rides;
  /** The short names of the rides. */
  static String[] ridesNameShort = { "BSOD", "KK", "ToT", "GF" };
  /** The full names of the rides. */
  static String[] ridesName = {
    "Blue Scream of Death ",
    "Kingda Knuth ",
    "i386 Tower of Terror ",
    "GeForce ",
  };
  /** The list of gold customers in the theme park. */
  private static LinkedList<Person> goldMap = new LinkedList<>();
  /** The list of silver customers in the theme park. */
  private static LinkedList<Person> silverMap = new LinkedList<>();
  /** The list of regular customers in the theme park. */
  private static LinkedList<Person> regularMap = new LinkedList<>();

  /** Temporary map for gold customers for assigning second rides. */
  private static LinkedList<Person> tempGoldMapTwo = new LinkedList<>();
  /** Temporary map for silver customers for assigning rides. */
  private static LinkedList<Person> tempSilverMap = new LinkedList<>();
  /** Temporary map for gold customers for assigning third rides. */
  private static LinkedList<Person> tempGoldMap = new LinkedList<>();

  /** The list of customers waiting to transfer to another ride. */
  private static LinkedList<Person> transferRide = new LinkedList<>();
  /** The list of customers waiting to transfer to another ride again. */
  private static LinkedList<Person> transferRideTwo = new LinkedList<>();
  /** The probabilities of customers selecting each ride. */
  static double[] probabilities = new double[4];
  /** The count of gold customers. */
  private static int goldCount = 0;
  /** The count of silver customers. */
  private static int silverCount = 0;
  /** The count of regular customers. */
  private static int regularCount = 0;

  /**
   * The main method to start the simulation.
   * @param args Command-line arguments.
   */
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    rides = new LinkedList<>();
    System.out.println("Welcome to Seven Flags!");
    System.out.print("Please enter the number of regular customers: ");
    int regular = input.nextInt();

    System.out.print("Please enter the number of silver customers: ");
    int silverMember = input.nextInt();

    System.out.print("Please enter the number of gold customers: ");
    int goldMember = input.nextInt();

    System.out.print("Please enter simulation length: ");
    int simulationLength = input.nextInt();

    for (int i = 0; i < 4; i++) {
      Ride ride = new Ride();
      ride.setName(ridesNameShort[i]);
      System.out.print(
        "Please enter the duration of " + ridesName[i] + "(minutes): "
      );
      ride.setDuration(input.nextInt());

      System.out.print("Please enter the capacity of " + ridesName[i] + ": ");
      int capacity = input.nextInt();
      ride.setRideMax(capacity);

      System.out.print(
        "Please enter the holding queue size for " + ridesName[i] + ": "
      );
      int holdCapacity = input.nextInt();
      ride.getHoldingQueue().setMaxSize(holdCapacity);

      rides.add(ride);
    }

    System.out.println("Sum of all Probability should be equals to 1.");
    System.out.println();
    for (int i = 0; i < 4; i++) {
      System.out.print(
        "Please enter the probability for " +
        ridesName[i] +
        "from (0, .1 ) to (.9 ,1) : "
      );
      probabilities[i] = input.nextDouble();
    }

    System.out.println(
      "------------------------------------------------------------------------------------------"
    );
    parkSimulator(simulationLength, regular, silverMember, goldMember);
  }

  /**
   * Assigns rides to customers based on their membership types.
   * @param simulationLength The length of the simulation.
   * @param regular The number of regular customers.
   * @param silverMember The number of silver customers.
   * @param goldMember The number of gold customers.
   */
  private static void assigningRide(
    int simulationLength,
    int regular,
    int silverMember,
    int goldMember
  ) {
    int tempGoldMember = goldMember;
    int tempSilverMember = silverMember;
    int tempRegular = regular;
    int tryCount = regular + silverMember + goldMember;
    for (int i = 0; i < tryCount; i++) {
      if (tempGoldMember != 0) {
        Person goldPerson = new Person(i + 1);
        goldPerson.setCustomerType(CustomerType.Gold);
        Ride selectedRide = RandomGenerator.selectRide(rides, probabilities);

        CustomerType type = CustomerType.Gold;
        randomRides(selectedRide, goldPerson, goldMap, type, ridesNameShort);
        tempGoldMember--;
      }
      if (tempSilverMember != 0) {
        Person silverPerson = new Person(i + 1);
        silverPerson.setCustomerType(CustomerType.Silver);
        Ride selectedRide = RandomGenerator.selectRide(rides, probabilities);
        CustomerType type = CustomerType.Silver;
        randomRides(
          selectedRide,
          silverPerson,
          silverMap,
          type,
          ridesNameShort
        );

        tempSilverMember--;
      }
      if (tempRegular != 0) {
        Person regularPerson = new Person(i + 1);
        regularPerson.setCustomerType(CustomerType.Regular);
        Ride selectedRide = RandomGenerator.selectRide(rides, probabilities);

        CustomerType type = CustomerType.Regular;
        randomRides(
          selectedRide,
          regularPerson,
          regularMap,
          type,
          ridesNameShort
        );
        tempRegular--;
      }
    }
    // tempGoldMember = goldMember;
    // tempSilverMember = silverMember;
    int tryCountTwo = silverMember + goldMember;
    lineAssigningTwo(tryCountTwo, silverMap, goldMap);

    lineAssigningThree(tempGoldMap);
  }

  /**
   * Assigns rides for gold members who are waiting in a special queue.
   * @param tempGoldMap The list of gold members waiting in the special queue.
   */
  private static void lineAssigningThree(LinkedList<Person> tempGoldMap) {
    int goldCount = tempGoldMap.size();
    for (int i = 0; i < goldCount; i++) {
      Person tempPerson = tempGoldMap.get(i);
      Ride selectedRide = RandomGenerator.selectRide(rides, probabilities);
      //tempPerson.setRideNameShortThree(ridesNameShort[rideRandom]);
      // tempPerson.setLines(rides.get(rideRandom));
      randomRidesForVIP(selectedRide, tempPerson, tempGoldMapTwo);
    }
  }

  /**
   * Assigns rides for gold and silver members who are waiting in lines.
   * @param tryCountTwo The total number of gold and silver members.
   * @param silverLine The queue for silver members.
   * @param goldLine The queue for gold members.
   */
  private static void lineAssigningTwo(
    int tryCountTwo,
    LinkedList<Person> silverLine,
    LinkedList<Person> goldLine
  ) {
    int goldCount = goldLine.size();
    int silverCount = silverLine.size();
    for (int i = 0; i < goldCount; i++) {
      Person tempPerson = goldLine.get(i);
      Ride selectedRide = RandomGenerator.selectRide(rides, probabilities);
      // tempPerson.setRideNameShortTwo(ridesNameShort[rideRandom]);
      //tempPerson.setLines(rides.get(rideRandom));
      randomRidesForVIP(selectedRide, tempPerson, tempGoldMap);
    }
    for (int i = 0; i < silverCount; i++) {
      Person tempPerson = silverLine.get(i);
      Ride selectedRide = RandomGenerator.selectRide(rides, probabilities);
      // tempPerson.setRideNameShortTwo(ridesNameShort[rideRandom]);
      //tempPerson.setLines(rides.get(rideRandom));
      randomRidesForVIP(selectedRide, tempPerson, tempSilverMap);
    }
  }

  /**
   * Assigns customers to rides for VIP customers (Gold and Silver) based on their status and ride availability.
   *
   * @param selectedRide The ride selected for the customer.
   * @param personType   The type of person (Gold, Silver).
   * @param mapType      The map to which the person will be added (tempGoldMap, tempSilverMap, tempGoldMapTwo).
   */
  private static void randomRidesForVIP(
    Ride selectedRide,
    Person personType,
    LinkedList<Person> mapType
  ) {
    if (
      personType.getStatus().equals(Status.OnRide) ||
      personType.getStatus().equals(Status.Holding)
    ) {
      selectedRide.getVirtualLine().enqueue(personType);
      personType.setStatus(personType.getStatus());
    } else {
      Status status = personType.getStatus();
      if (
        selectedRide.getHoldingQueue().isEmpty() &&
        selectedRide.getPeopleOnRide().size() < selectedRide.getRideMax()
      ) {
        selectedRide.getPeopleOnRide().add(personType);

        status = Status.OnRide;
      } else if (
        selectedRide.getPeopleOnRide().size() == selectedRide.getRideMax() &&
        (
          selectedRide.getHoldingQueue().size() <
          selectedRide.getHoldingQueue().getMaxSize()
        )
      ) {
        selectedRide.getHoldingQueue().enqueue(personType);

        status = Status.Holding;
      } else {
        selectedRide.getVirtualLine().enqueue(personType);
        personType.setStatus(personType.getStatus());
      }
      personType.setStatus(status);
    }
    // personType.setLines(rides.get(rideRandom));
    personType.getLines().add(selectedRide);
    mapType.add(personType);
  }

  /**
   * Assigns customers to rides based on their status and ride availability.
   *
   * @param selectedRide The ride selected for the customer.
   * @param personType   The type of person (Gold, Silver, Regular).
   * @param mapType      The map to which the person will be added (goldMap, silverMap, regularMap).
   */
  private static void randomRides(
    Ride selectedRide,
    Person personType,
    LinkedList<Person> mapType,
    CustomerType type,
    String[] ridesNames
  ) {
    Status status;
    if (
      selectedRide.getHoldingQueue().isEmpty() &&
      selectedRide.getPeopleOnRide().size() < selectedRide.getRideMax()
    ) {
      selectedRide.getPeopleOnRide().add(personType);
      personType.setCustomerInfo(type);
      status = Status.OnRide;
    } else if (
      selectedRide.getPeopleOnRide().size() == selectedRide.getRideMax() &&
      (
        selectedRide.getHoldingQueue().size() <
        selectedRide.getHoldingQueue().getMaxSize()
      )
    ) {
      selectedRide.getHoldingQueue().enqueue(personType);
      personType.setCustomerInfo(type);
      status = Status.Holding;
    } else {
      selectedRide.getVirtualLine().enqueue(personType);
      personType.setCustomerInfo(type);
      status = Status.Available;
    }
    // personType.setRideNameShort(ridesNames[rideRandom]);
    personType.getLines().add(selectedRide);
    personType.setStatus(status);
    mapType.add(personType);
  }

  /**
   * Simulates the park by iterating over time and updating ride statuses.
   *
   * @param simulationLength Length of the simulation in minutes.
   * @param regular          Number of regular customers.
   * @param silverMember     Number of silver customers.
   * @param goldMember       Number of gold customers.
   */
  private static void parkSimulator(
    int simulationLength,
    int regular,
    int silverMember,
    int goldMember
  ) {
    assigningRide(simulationLength, regular, silverMember, goldMember);
    System.out.println("At Time 0:");
    for (int i = 0; i < 4; i++) {
      System.out.println(
        ridesName[i] +
        " - Time remaining: " +
        rides.get(i).getTimeLeft() +
        " min"
      );

      rides.get(i).printList();
      System.out.println("----------------");
    }

    customerMap(silverMap, goldMap, regularMap);

    for (int i = 1; i < simulationLength; i++) {
      System.out.println();
      System.out.println("At Time " + i + ":");
      System.out.println();

      nextSimulation();

      for (int j = 0; j < 4; j++) {
        System.out.println(
          ridesName[j] +
          " - Time remaining: " +
          rides.get(j).getTimeLeft() +
          " min"
        );

        rides.get(j).printList();
        System.out.println("----------------");
      }
      customerMap(silverMap, goldMap, regularMap);
    }
    System.out.println("..........At the end of the simulation:......");
    System.out.println();
    System.out.println(printAverages());
    System.out.println();
    for (int j = 0; j < 4; j++) {
      System.out.println(
        rides.get(j).getName() +
        " has completed rides for " +
        rides.get(j).getRideCompleted() +
        " people."
      );
      System.out.println();
    }
  }

  /**
   * Transfers persons from a ride's holding queue to its virtual line or other rides based on availability.
   *
   * @param transferRide The list of persons to be transferred.
   * @param ridenum      The index of the ride from which persons are being transferred.
   */
  private static void transferPerson(
    LinkedList<Person> transferRide,
    int ridenum
  ) {
    int transferRideCount = transferRide.size();
    for (int i = 0; i < transferRideCount; i++) {
      Person temPerson = transferRide.get(i);
      temPerson.getLines().remove(rides.get(i));
      Ride selectedRide = RandomGenerator.selectRide(rides, probabilities);
      if (
        temPerson.getStatus().equals(Status.OnRide) ||
        temPerson.getStatus().equals(Status.Holding)
      ) {
        selectedRide.getVirtualLine().enqueue(temPerson);
        temPerson.getLines().add(selectedRide);
        temPerson.setStatus(Status.Available);
      }
    }
    for (Person person : transferRide) {
      if (person.getCustomerType().equals(CustomerType.Gold)) {
        goldCount++;
      } else if (person.getCustomerType().equals(CustomerType.Silver)) {
        silverCount++;
      } else {
        regularCount++;
      }
    }
    clearList(transferRide);
  }

  /**
   * Clears a given linked list.
   *
   * @param linkedListClear The linked list to be cleared.
   */
  private static void clearList(LinkedList<Person> linkedListClear) {
    linkedListClear.removeAll(linkedListClear);
  }

  /**
   * Updates the simulation to the next time step and processes ride and customer statuses.
   */

  private static void nextSimulation() {
    for (int i = 0; i < 4; i++) {
      rides.get(i).timeUpdateLeft(rides.get(i).getTimeLeft());
      if (rides.get(i).getTimeLeft() == 0 || rides.get(i).getTimeLeft() < 0) {
        rides.get(i).restTimeLeft();
        for (int j = 0; j < rides.get(i).getPeopleOnRide().size(); j++) {
          Person tempPerson = rides.get(i).getPeopleOnRide().get(j);
          transferRide.add(tempPerson);
        }
        rides.get(i).setRideCompleted(transferRide.size());
        clearList(rides.get(i).getPeopleOnRide());

        for (int k = 0; k < rides.get(i).getRideMax(); k++) {
          if (
            rides.get(i).getPeopleOnRide().size() < rides.get(i).getRideMax() &&
            !(rides.get(i).getHoldingQueue().isEmpty())
          ) {
            rides.get(i).getHoldingQueue().getFirst().setStatus(Status.OnRide);
            rides
              .get(i)
              .getPeopleOnRide()
              .add(rides.get(i).getHoldingQueue().getFirst());
            rides.get(i).getHoldingQueue().removeFirst();
          }
        }

        transferPerson(transferRide, i);

        for (int l = 0; l < rides.get(i).getHoldingQueue().getMaxSize(); l++) {
          if (!(rides.get(i).getVirtualLine().isEmpty())) {
            if (
              rides
                .get(i)
                .getVirtualLine()
                .getFirst()
                .getStatus()
                .equals(Status.OnRide) ||
              rides
                .get(i)
                .getVirtualLine()
                .getFirst()
                .getStatus()
                .equals(Status.Holding)
            ) {
              transferRideTwo.add(rides.get(i).getVirtualLine().getFirst());
              rides.get(i).getVirtualLine().removeFirst();
            } else if (
              rides.get(i).getHoldingQueue().size() <
              rides.get(i).getHoldingQueue().getMaxSize()
            ) {
              rides
                .get(i)
                .getVirtualLine()
                .getFirst()
                .setStatus(Status.Holding);
              rides
                .get(i)
                .getHoldingQueue()
                .enqueue(rides.get(i).getVirtualLine().getFirst());
              rides.get(i).getVirtualLine().removeFirst();
              for (int k = 0; k < rides.get(i).getRideMax(); k++) {
                if (
                  rides.get(i).getPeopleOnRide().size() <
                  rides.get(i).getRideMax() &&
                  !(rides.get(i).getHoldingQueue().isEmpty())
                ) {
                  rides
                    .get(i)
                    .getHoldingQueue()
                    .getFirst()
                    .setStatus(Status.OnRide);
                  rides
                    .get(i)
                    .getPeopleOnRide()
                    .add(rides.get(i).getHoldingQueue().getFirst());
                  rides.get(i).getHoldingQueue().removeFirst();
                }
              }
            }
          }
        }
        for (int m = 0; m < transferRideTwo.size(); m++) {
          rides.get(i).getVirtualLine().add(transferRideTwo.get(m));
        }
        clearList(transferRideTwo);
      }
    }
  }

  /**
   * Displays the current status of customers in different lines.
   *
   * @param silverMap  The list of silver customers.
   * @param goldMap    The list of gold customers.
   * @param regularMap The list of regular customers.
   */
  private static void customerMap(
    LinkedList<Person> silverMap,
    LinkedList<Person> goldMap,
    LinkedList<Person> regularMap
  ) {
    System.out.println();
    System.out.println("Regular Customers:");
    System.out.println("Num  Line   Status");
    System.out.println("----------------");
    int num = 1;
    for (Person person : regularMap) {
      System.out.println(
        num + "." + " " + person.printRides() + " " + person.getStatus()
      );
      num++;
    }
    System.out.println();
    System.out.println("Silver Customers:");
    System.out.println("Num  Line 1  Line 2  Status");
    System.out.println("--------------------------");
    num = 1;
    //LinkedList<Person> silverMapTwo = tempSilverMap;
    for (Person person : silverMap) {
      System.out.println(
        num + "." + " " + person.printRides() + " " + person.getStatus()
      );
      num++;
    }
    num = 1;
    System.out.println();
    System.out.println("Gold Customers:");
    System.out.println("Num  Line 1  Line 2  Line 3   Status");
    System.out.println("-------------------------------------");
    for (Person person : goldMap) {
      System.out.println(
        num + "." + " " + person.printRides() + " " + person.getStatus()
      );
      num++;
    }
  }

  /**
   * Displays the averages of rides taken by customers of different types.
   *
   * @return A string containing the averages of rides taken by Gold, Silver, and Regular customers.
   */
  private static String printAverages() {
    String res = "";

    float goldAverage = goldCount / goldMap.size();
    float silverAverage = silverCount / silverMap.size();
    float regularAverage = silverCount / silverMap.size();

    res +=
      "On average, Gold customers have taken " +
      goldAverage +
      " rides.\n" +
      "On average, Silver customers have taken " +
      silverAverage +
      " rides.\n" +
      "On average, regular customers have taken " +
      regularAverage +
      " rides.";
    return res;
  }
}
