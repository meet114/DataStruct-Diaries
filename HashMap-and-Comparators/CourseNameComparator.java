//Meetkumar Saspara
//115971301
//R:01
//HW06
import java.io.Serializable;
import java.util.Comparator;

/**
 * The {@code CourseNameComparator} class implements the {@code Comparator} interface
 * to compare {@code Course} objects based on their department, number, and semester.
 * It also implements the {@code Serializable} interface to support object serialization.
 */
public class CourseNameComparator implements Comparator<Course>, Serializable {

  /**
   * Compares two {@code Course} objects based on their department, number, and semester.
   *
   * @param left the first {@code Course} to compare
   * @param right the second {@code Course} to compare
   * @return a negative integer, zero, or a positive integer as the first {@code Course} is less than, equal to, or greater than the second {@code Course}
   */
  public int compare(Course left, Course right) {
    int departmentComparison = left
      .getDepartment()
      .compareTo(right.getDepartment());
    if (departmentComparison != 0) {
      return departmentComparison;
    } else {
      if (left.getNumber() < right.getNumber()) {
        return -1;
      } else if (left.getNumber() > right.getNumber()) {
        return 1;
      } else {
        return left.getSemester().compareTo(right.getSemester());
      }
    }
  }
}
