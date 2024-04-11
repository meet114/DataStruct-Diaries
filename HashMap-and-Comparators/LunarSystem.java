//Meetkumar Saspara
//115971301
//R:01
//HW06

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * The {@code LunarSystem} class represents a course registration system for managing student records.
 * It allows students and the registrar to perform various operations such as login, registration, course enrollment, and saving/loading data.
 */
public class LunarSystem {

  private static HashMap<String, Student> database;

  /**
   * The main method of the Lunar System.
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    database = new HashMap<String, Student>();

    System.out.println(
      "Welcome to the Lunar System, a second place course registration system for second rate courses at a second class school."
    );

    loadDb();

    while (true) {
      System.out.println(
        "Menu:\n" +
        " \n" +
        "L)Login\n" +
        " \n" +
        "X)Save state and quit\n" +
        " \n" +
        "Q)Quit without saving state.\n" +
        " \n" +
        "Please select an option: \n"
      );
      String choice = input.nextLine().toUpperCase();
      switch (choice.charAt(0)) {
        case 'L':
          loginPage(input);
          break;
        case 'X':
          try {
            saveDb();
          } catch (FileException e) {
            e.printStackTrace();
          }
          System.out.println(
            "System state saved, system shut down for maintenance."
          );
          return;
        case 'Q':
          System.out.println("Good bye, please pick the right SUNY next time!");
          return;
        default:
          System.out.println("Invalid choice. Please select a try again");
      }
    }
  }

  /**
   * Displays the login page and prompts the user to enter their webID.
   *
   * @param input the Scanner object for user input
   */
  private static void loginPage(Scanner input) {
    System.out.print("Please enter webid: ");
    String webID = input.nextLine();
    if (webID.toUpperCase().equals("REGISTRAR")) {
      registrarOption(input);
    } else if (!database.containsKey(webID)) {
      System.out.println("Could not find User");
    } else {
      studentOption(input, webID);
    }
  }

  /**
   * Displays options for the registrar and performs actions based on user input.
   *
   * @param input the Scanner object for user input
   */
  private static void registrarOption(Scanner input) {
    System.out.println(
      "Welcome Registrar.\n" +
      "Options:\n  " +
      "   R) Register a student\n  " +
      "   D) De-register a student\n  " +
      "   E) View course enrollment\n  " +
      "   L) Logout"
    );

    while (true) {
      System.out.println("Please select an option: ");
      String choice = input.nextLine().toUpperCase();
      switch (choice.charAt(0)) {
        case 'R':
          register(input);
          break;
        case 'D':
          deregister(input);
          break;
        case 'E':
          viewAndDropCourse(input, null, true);
          break;
        case 'L':
          System.out.println("Registrar logged out.");
          return;
        default:
          System.out.println("Invalid choice. Please select a try again");
      }
    }
  }

  /**
   * Displays options for the student and performs actions based on user input.
   *
   * @param input the Scanner object for user input
   * @param webID the webID of the logged-in student
   */
  private static void studentOption(Scanner input, String webID) {
    System.out.println(
      "Welcome " +
      webID +
      "\n" +
      "Options:\n" +
      "    A) Add a class\n" +
      "    D) Drop a class\n" +
      "    C) View your classes sorted by course name/department\n" +
      "    S) View your courses sorted by semester\n" +
      "    L) Logout"
    );

    while (true) {
      System.out.println("Please select an option: ");
      String choice = input.nextLine().toUpperCase();
      switch (choice.charAt(0)) {
        case 'A':
          addStudentClass(input, webID);
          break;
        case 'D':
          viewAndDropCourse(input, webID, false);
          break;
        case 'C':
          sortedNameDepartment(input, webID);
          break;
        case 'S':
          sortedsemester(input, webID);
          break;
        case 'L':
          System.out.println(webID + " logged out");
          return;
        default:
          System.out.println("Invalid choice. Please select a try again");
      }
    }
  }

  /**
   * Displays courses sorted by semester for a specific student.
   *
   * @param input the Scanner object for user input
   * @param webID the webID of the logged-in student
   */
  private static void sortedsemester(Scanner input, String webID) {
    LinkedList<Course> courses = new LinkedList<>();

    for (Student student : database.values()) {
      if (student.getWebID().equals(webID)) {
        courses = student.getCourses();
      }
    }
    courses.sort(new SemesterComparator());
    for (Course course : courses) {
      System.out.println(
        course.getDepartment() +
        "\t" +
        course.getNumber() +
        "\t" +
        course.getSemester()
      );
    }
  }

  /**
   * Displays courses sorted by course name/department for a specific student.
   *
   * @param input the Scanner object for user input
   * @param webID the webID of the logged-in student
   */
  private static void sortedNameDepartment(Scanner input, String webID) {
    LinkedList<Course> courses = new LinkedList<>();

    for (Student student : database.values()) {
      if (student.getWebID().equals(webID)) {
        courses = student.getCourses();
        break;
      }
    }
    courses.sort(new CourseNameComparator());
    for (Course course : courses) {
      System.out.println(
        course.getDepartment() +
        "\t" +
        course.getNumber() +
        "\t" +
        course.getSemester()
      );
    }
  }

  /**
   * Adds a new course to the courses list of a specific student.
   *
   * @param input the Scanner object for user input
   * @param webID the webID of the logged-in student
   */
  private static void addStudentClass(Scanner input, String webID) {
    System.out.print("Please enter the course name : ");
    String courseName = input.nextLine();

    System.out.println("Please enter the course number : ");
    int courseNumber = input.nextInt();
    input.nextLine();

    System.out.println("Please select a semester: ");
    String courseSem = input.nextLine();
    courseSem.toUpperCase();
    String semesterName = " ";
    if (courseSem.startsWith("S")) {
      semesterName = "Spring";
    } else if (courseSem.startsWith("F")) {
      semesterName = "Fall";
    } else if (courseSem.startsWith("W")) {
      semesterName = "Winter";
    }
    Course newCourse = new Course(
      courseName,
      courseNumber,
      courseSem.toUpperCase()
    );
    database.get(webID).getCourses().add(newCourse);
    System.out.println(
      "Added course " +
      courseName +
      " " +
      courseNumber +
      " for semester " +
      semesterName +
      " " +
      courseSem.substring(1)
    );
  }

  /**
   * Registers a new student.
   *
   * @param input the Scanner object for user input
   */
  private static void register(Scanner input) {
    System.out.println("Please enter a webid for the new student:");
    String webId = input.nextLine();
    if (database.containsKey(webId)) {
      System.out.println("Student already registered. ");
    } else {
      Student newStudent = new Student(webId);
      database.put(webId, newStudent);
      System.out.println(webId + " registered.");
    }
  }

  /**
   * Deregisters a student.
   *
   * @param input the Scanner object for user input
   */
  private static void deregister(Scanner input) {
    System.out.println(
      "Please enter a webid for the student to be deregistered: "
    );
    String webId = input.nextLine();
    if (database.containsKey(webId)) {
      database.remove(webId);
      System.out.println(webId + "deregistered");
    } else {
      System.out.println("Error: Could not find student in database");
    }
  }

  /**
   * Displays or drops a course for a specific student.
   *
   * @param input the Scanner object for user input
   * @param webID the webID of the logged-in student (null if the user is the registrar)
   * @param printOrDrop true if the method should display courses, false if it should drop a course
   */
  private static void viewAndDropCourse(
    Scanner input,
    String webID,
    boolean printOrDrop
  ) {
    System.out.println("Please enter course: ");
    String courseName = input.nextLine();
    System.out.println("Please enter course Number: ");
    int courseNumber = input.nextInt();
    input.nextLine(); // Consume newline character
    if (printOrDrop) {
      System.out.println("Student   Semester  ");
      System.out.println("--------------------");
    }

    for (Student student : database.values()) {
      for (Course course : student.getCourses()) {
        if (
          course.getDepartment().equals(courseName) &&
          course.getNumber() == courseNumber
        ) {
          if (printOrDrop) {
            System.out.println(
              student.getWebID() + "\t" + course.getSemester()
            );
          } else {
            LinkedList<Course> courses = student.getCourses();
            int defaultSize = courses.size();
            courses.remove(course);

            int newSize = defaultSize - courses.size();
            System.out.println(
              "Removed " +
              newSize +
              " course(s) from student " +
              student.getWebID()
            );
            return;
          }
        }
      }
    }
    if (!printOrDrop) {
      System.out.println("Removed " + 0 + " course(s) from student " + webID);
    }
  }

  /**
   * Saves the database to a file.
   *
   * @throws FileException if an error occurs while saving the database
   */
  private static void saveDb() throws FileException {
    try {
      FileOutputStream fileStream = new FileOutputStream("Lunar.sav");
      ObjectOutputStream objStream = new ObjectOutputStream(fileStream);
      objStream.writeObject(database);
      objStream.close();
      fileStream.close();
    } catch (IOException e) {
      throw new FileException("Error saving database: " + e.getMessage());
    }
  }

  /**
   * Loads the database from a file.
   */
  private static void loadDb() {
    try {
      FileInputStream fileStream = new FileInputStream("Lunar.sav");
      ObjectInputStream objStream = new ObjectInputStream(fileStream);
      database = (HashMap<String, Student>) objStream.readObject();
      objStream.close();
      fileStream.close();
      System.out.println("Previous data loaded.");
    } catch (IOException | ClassNotFoundException e) {
      System.out.println("No previous data found.");
    }
  }
}
