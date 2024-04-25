//Meetkumar Saspara
//115971301
//R:01
//HW7
/**
 * Javadoc for EdgeComparator class.
 * This class implements the Comparator interface to compare Edge objects based on their vertices' names.
 */
import java.util.Comparator;

/**
 * Compares two Edge objects based on the names of their vertices.
 *
 * @param e1 the first Edge object to be compared
 * @param e2 the second Edge object to be compared
 * @return a negative integer, zero, or a positive integer as the first Edge is less than, equal to, or greater than the second Edge
 */
public class EdgeComparator implements Comparator<Edge> {

  public int compare(Edge e1, Edge e2) {
    String from1 = e1.getA().getName();
    String from2 = e2.getA().getName();
    int comparison = from1.compareTo(from2);
    if (comparison == 0) {
      String to1 = e1.getB().getName();
      String to2 = e2.getB().getName();
      return to1.compareTo(to2);
    }
    return comparison;
  }
}
