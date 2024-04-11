//Meetkumar Saspara
//115971301
//R:01
//HW06
import java.io.Serializable;

/**
 * The {@code Course} class represents a course entity.
 * It implements the {@code Serializable} interface to support object serialization.
 */
public class Course implements Serializable {

  private String department; // Department of the course
  private int number; // Number of the course
  private String semester; // Semester in which the course is offered

  /**
   * Constructs a new {@code Course} object with the specified department, number, and semester.
   *
   * @param department the department of the course
   * @param number the number of the course
   * @param semester the semester in which the course is offered
   */
  public Course(String department, int number, String semester) {
    this.department = department;
    this.number = number;
    this.semester = semester;
  }

  /**
   * Returns the department of the course.
   *
   * @return the department of the course
   */
  public String getDepartment() {
    return department;
  }

  /**
   * Sets the department of the course.
   *
   * @param department the new department to set for the course
   */
  public void setDepartment(String department) {
    this.department = department;
  }

  /**
   * Returns the number of the course.
   *
   * @return the number of the course
   */
  public int getNumber() {
    return number;
  }

  /**
   * Sets the number of the course.
   *
   * @param number the new number to set for the course
   */
  public void setNumber(int number) {
    this.number = number;
  }

  /**
   * Returns the semester in which the course is offered.
   *
   * @return the semester in which the course is offered
   */
  public String getSemester() {
    return semester;
  }

  /**
   * Sets the semester in which the course is offered.
   *
   * @param semester the new semester to set for the course
   */
  public void setSemester(String semester) {
    this.semester = semester;
  }
}
