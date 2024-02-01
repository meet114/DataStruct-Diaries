//Meetkumar Saspara
//115971301
//R:01

/**
 * Represents a Student with a name and an amount of money.
 */
public class Student {

    private String name;
    private double money;

    /**
     * Constructs a Student object with the specified name and money.
     *
     * @param name  the name of the student
     * @param money the amount of money the student has
     */
    public Student(String name, double money) {
        this.name = name;
        this.money = money;
    }

    /**
     * Gets the name of the student.
     *
     * @return the name of the student
     */

    public String getName() {
        return name;
    }

    /**
     * Sets the name of the student.
     *
     * @param name the name of the student
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the amount of money the student has.
     *
     * @return the amount of money the student has
     */

    public double getMoney() {
        return money;
    }

    /**
     * Sets the amount of money the student has.
     *
     * @param money the amount of money the student has
     */
    public void setMoney(double money) {
        this.money = money;
    }

    /**
     * Clones the student object.
     *
     * @return a new Student object with the same name and money as this student
     */
    public Student clone() {
        return new Student(this.name, this.money);
    }

    /**
     * Checks if this student is equal to another object.
     *
     * @param obj the object to compare with this student
     * @return true if the object is a student with the same name and money, otherwise false
     */
    public boolean equals(Object obj) {
        if (obj instanceof Student) {
            Student student = (Student) obj;
            if (this.name.equals(student.name) && this.money == student.money) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * Returns a string representation of the student.
     *
     * @return a string representation of the student
     */
    public String toString() {
        String s = this.name + " has $" + this.money;
        return s;
    }
}
