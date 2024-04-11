//Meetkumar Saspara
//115971301
//R:01
//HW06
import java.io.Serializable;
import java.util.Comparator;

/**
 * The {@code SemesterComparator} class implements the {@code Comparator} interface
 * to compare {@code Course} objects based on their semester.
 * It also implements the {@code Serializable} interface to support object serialization.
 */
public class SemesterComparator implements Comparator<Course>, Serializable {

  /**
   * Compares two {@code Course} objects based on their semester.
   * The comparison is done first by year and then by semester (Fall, Winter, Spring).
   *
   * @param left the first {@code Course} to compare
   * @param right the second {@code Course} to compare
   * @return a negative integer, zero, or a positive integer as the first {@code Course} is less than, equal to, or greater than the second {@code Course}
   */
  @Override
  public int compare(Course left, Course right) {
    String leftSem = left.getSemester().substring(0, 1);
    String rightSem = right.getSemester().substring(0, 1);
    int leftYear = Integer.parseInt(left.getSemester().substring(1));
    int rightYear = Integer.parseInt(right.getSemester().substring(1));

    if (leftYear < rightYear) {
      return -1;
    } else if (leftYear > rightYear) {
      return 1;
    } else {
      if (leftSem.equals("F") && rightSem.equals("S")) {
        return 1;
      } else if (leftSem.equals("S") && rightSem.equals("F")) {
        return -1;
      } else if (leftSem.equals("F") && rightSem.equals("W")) {
        return -1;
      } else if (leftSem.equals("W") && rightSem.equals("F")) {
        return 1;
      } else if (leftSem.equals("W") && rightSem.equals("S")) {
        return 1;
      } else if (leftSem.equals("S") && rightSem.equals("W")) {
        return -1;
      } else {
        return 0;
      }
    }
  }
}
