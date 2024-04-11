//Meetkumar Saspara
//115971301
//R:01
//HW06

import java.io.Serializable;
import java.util.LinkedList;

/**
 * The {@code Student} class represents a student entity.
 * It implements the {@code Serializable} interface to support object serialization.
 */
public class Student implements Serializable {

  private String webID; // Unique identifier for the student
  private LinkedList<Course> courses; // List of courses associated with the student

  /**
   * Constructs a new {@code Student} object with the specified webID.
   *
   * @param webID the unique identifier for the student
   */
  public Student(String webID) {
    this.webID = webID;
    this.courses = new LinkedList<>(); // Initialize the courses list
  }

  /**
   * Returns the webID of the student.
   *
   * @return the webID of the student
   */
  public String getWebID() {
    return webID;
  }

  /**
   * Sets the webID of the student.
   *
   * @param webID the new webID to set for the student
   */
  public void setWebID(String webID) {
    this.webID = webID;
  }

  /**
   * Returns the list of courses associated with the student.
   *
   * @return the list of courses associated with the student
   */
  public LinkedList<Course> getCourses() {
    return courses;
  }

  /**
   * Sets the list of courses associated with the student.
   *
   * @param courses the new list of courses to set for the student
   */
  public void setCourses(LinkedList<Course> courses) {
    this.courses = courses;
  }
}
