//Meetkumar Saspara
//115971301
//R:01

import java.util.Scanner;

/**
 * Simulates a lunch line scenario where students can be added, removed, and served lunch, among other actions.
 */

public class LunchLineSimulator {

    /**
     * The two realities of the lunch line simulation.
     */
    private static StudentLine realityA = new StudentLine();
    private static StudentLine realityB = new StudentLine();
    static boolean ASelected = true;

    public static void main(String[] args) {
        realityA = new StudentLine();
        realityB = new StudentLine();
        Scanner input = new Scanner(System.in);
        String choice;
        // Handle user choice based on input
        while (true) {
            System.out.print(
                "Menu:\n" +
                "     A) Add a student to the line at the end\n" +
                "     C) Have a new student cut a friend\n" +
                "     T) Have two students trade places\n" +
                "     B) Have the bully remove a student\n" +
                "     U) Update a student's money amount\n" +
                "     S) Serve a student\n" +
                "     P) Print the current reality's lunch line\n" +
                "     O) Switch to the other reality\n" +
                "     E) Check if the realities are equal\n" +
                "     D) Duplicate this reality into the other reality\n" +
                "     Q) Quit middle school and move on to real life.\n" +
                "Please select an option: "
            );

            choice = input.nextLine().toUpperCase();
            // Add a student to the line
            if (choice.charAt(0) == 'A') {
                System.out.print("Please enter student name:");
                String name = input.nextLine();
                System.out.print("Please enter money amount:");
                Double money = input.nextDouble();
                input.nextLine();
                try {
                    Student stu = new Student(name, money);
                    if (ASelected) {
                        realityA.addStudent(realityA.numStudents(), stu);
                    } else {
                        realityB.addStudent(realityB.numStudents(), stu);
                    }
                    System.out.println(
                        name +
                        " has been added to the line in position " +
                        realityA.numStudents() +
                        ". " +
                        name +
                        " has $" +
                        money +
                        "."
                    );
                } catch (Exception e) {
                    System.out.println("\n" + e.getMessage());
                }
            }
            // Have a new student cut a friend
            else if (choice.charAt(0) == 'C') {
                System.out.print("Please enter student name: ");
                String name = input.nextLine();
                System.out.print("Please enter money amount: ");
                Double amount = input.nextDouble();
                System.out.print("Please enter position: ");
                int idx = input.nextInt();
                input.nextLine();
                idx = idx - 1;
                try {
                    Student prev_s;
                    Student s = new Student(name, amount);
                    if (ASelected) {
                        prev_s = realityA.getStudent(idx);
                        realityA.cutStudent(idx, s);
                    } else {
                        prev_s = realityB.getStudent(idx);
                        realityB.cutStudent(idx, s);
                    }
                    System.out.println(
                        name +
                        "has cut " +
                        prev_s.getName() +
                        "and is now in position " +
                        idx +
                        ". " +
                        name +
                        "has " +
                        amount +
                        "."
                    );
                } catch (Exception e) {
                    System.out.println("\n" + e.getMessage());
                }
            }
            // Have two students trade places
            else if (choice.charAt(0) == 'T') {
                System.out.print("Please enter student1 index:");
                int idx = input.nextInt();
                idx = idx - 1;
                System.out.print("Please enter student2 index:");
                int idx_2 = input.nextInt();
                idx_2 = idx_2 - 1;
                try {
                    if (ASelected) {
                        realityA.swapStudents(idx, idx_2);
                    } else {
                        realityB.swapStudents(idx, idx_2);
                    }
                } catch (Exception e) {
                    System.out.println("\n" + e.getMessage());
                }
                input.nextLine();
            }
            // Have the bully remove a student
            else if (choice.charAt(0) == 'B') {
                System.out.println("Please enter student index:");
                int idx = input.nextInt();
                idx = idx - 1;
                try {
                    Student removedStudent;
                    if (ASelected) {
                        removedStudent = realityA.bullyStudent(idx);
                    } else {
                        removedStudent = realityB.bullyStudent(idx);
                    }
                    System.out.println(
                        "The bully has stolen " +
                        removedStudent.getName() +
                        "'s lunch money,and" +
                        removedStudent.getName() +
                        " has left, feeling hangry."
                    );
                } catch (Exception e) {
                    System.out.println("\n" + e.getMessage());
                }
                input.nextLine();
            }
            // Update a student's money amount
            else if (choice.charAt(0) == 'U') {
                System.out.print("Please enter student index:");
                int idx = input.nextInt();

                idx = idx - 1;
                System.out.print("Please enter new money amount:");
                Double amount = input.nextDouble();
                input.nextLine();
                try {
                    if (amount < 0) {
                        System.out.println(
                            "You can't have debt in middle school. The lunch line was not updated."
                        );
                    } else {
                        Student studentToUpdate;
                        if (ASelected) {
                            studentToUpdate = realityA.getStudent(idx);

                            studentToUpdate.setMoney(amount);
                        } else {
                            studentToUpdate = realityB.getStudent(idx);

                            studentToUpdate.setMoney(amount);
                        }
                        if (amount == 0) {
                            if (ASelected) {
                                realityA.removeStudent(idx);
                            } else {
                                realityB.removeStudent(idx);
                            }
                            System.out.println(
                                "As a result of a shady transaction with the floor," +
                                studentToUpdate.getName() +
                                " now has $" +
                                studentToUpdate.getMoney()
                            );
                        }
                    }
                } catch (Exception e) {
                    System.out.println("\n" + e.getMessage());
                }
            }
            // Serve a student and removes from line
            else if (choice.charAt(0) == 'S') {
                try {
                    Student servedStudent;
                    if (ASelected) {
                        servedStudent = realityA.ServeStudent(0);
                    } else {
                        servedStudent = realityB.ServeStudent(0);
                    }
                    System.out.println(
                        servedStudent.getName() +
                        " has been served today's special: Bouncy \"Chicken?\" Nuggets. We hope he lives to see another day!"
                    );
                } catch (Exception e) {
                    System.out.println("\n" + e.getMessage());
                }
            }
            // Print the current reality's lunch line
            else if (choice.charAt(0) == 'P') {
                try {
                    if (ASelected) {
                        System.out.println("Lunch Line in Reality A:");
                        System.out.println(realityA.toString());
                    } else {
                        System.out.println("Lunch Line in Reality B:");
                        System.out.println(realityB.toString());
                    }
                } catch (Exception e) {
                    System.out.println("\n" + e.getMessage());
                }
            }
            // Switch to the other reality
            else if (choice.charAt(0) == 'O') {
                ASelected = !ASelected;
                System.out.println(
                    "You are in " +
                    (ASelected ? "Reality A" : "Reality B") +
                    ". I reject your reality and substitute my own."
                );
            }
            // Check if the realities are equal
            else if (choice.charAt(0) == 'E') {
                boolean realitiesEqual = realityA.equals(realityB);
                if (realitiesEqual) {
                    System.out.println("The realities are equal.");
                } else {
                    System.out.println("The realities are not equal.");
                }
            }
            // Duplicate this reality into the other reality
            else if (choice.charAt(0) == 'D') {
                try {
                    if (ASelected) {
                        realityB = (StudentLine) realityA.clone();
                    } else {
                        realityA = (StudentLine) realityB.clone();
                    }
                    System.out.println(
                        "Reality A has been copied into Reality B."
                    );
                } catch (Exception e) {
                    System.out.println("\n" + e.getMessage());
                }
            }
            // Quit the program
            else if (choice.charAt(0) == 'Q') {
                System.out.println(
                    "You are now leaving the Middle School Lunch Line Simulator. We congratulate you on your decision to do something more productive with your time"
                );
                break;
            }
        }
    }
}
