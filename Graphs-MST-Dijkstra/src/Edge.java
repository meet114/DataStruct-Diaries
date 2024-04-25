//Meetkumar Saspara
//115971301
//R:01
//HW7
/**
 * Javadoc for Edge class.
 * This class represents an edge in a graph.
 */

public class Edge implements Comparable<Edge> {

  private Node A;
  private Node B;
  private int cost;

  /**
   * Empty constructor for Edge class.
   */
  public Edge() {}

  /**
   * Constructs an Edge object with the specified nodes and cost.
   *
   * @param a    the first node of the edge
   * @param b    the second node of the edge
   * @param cost the cost of the edge
   */
  public Edge(Node a, Node b, int cost) {
    A = a;
    B = b;
    this.cost = cost;
  }

  /**
   * Returns a string representation of the edge.
   *
   * @return a string representation of the edge
   */
  public String toString() {
    return null;
  }

  /**
   * Gets the first node of the edge.
   *
   * @return the first node of the edge
   */
  public Node getA() {
    return A;
  }

  /**
   * Sets the first node of the edge.
   *
   * @param a the first node of the edge to be set
   */
  public void setA(Node a) {
    A = a;
  }

  /**
   * Gets the second node of the edge.
   *
   * @return the second node of the edge
   */
  public Node getB() {
    return B;
  }

  /**
   * Sets the second node of the edge.
   *
   * @param b the second node of the edge to be set
   */
  public void setB(Node b) {
    B = b;
  }

  /**
   * Gets the cost of the edge.
   *
   * @return the cost of the edge
   */
  public int getCost() {
    return cost;
  }

  /**
   * Sets the cost of the edge.
   *
   * @param cost the cost of the edge to be set
   */
  public void setCost(int cost) {
    this.cost = cost;
  }

  /**
   * Compares this edge with the specified edge based on their costs.
   *
   * @param otherEdge the edge to be compared
   * @return a negative integer, zero, or a positive integer as this edge is less than, equal to, or greater than the specified edge
   */
  public int compareTo(Edge otherEdge) {
    if (this.getCost() > otherEdge.getCost()) {
      return 1;
    } else if (this.getCost() < otherEdge.getCost()) {
      return -1;
    } else {
      return 0;
    }
  }
}
