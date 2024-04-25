/**
 * Javadoc for Node class.
 * This class represents a node in a graph.
 */

import java.util.HashSet;
import java.util.LinkedList;

public class Node {

  private String name;
  private HashSet<Edge> edges;
  private boolean visited;
  private LinkedList<String> path;
  private int distance;

  /**
   * Constructs a Node object with the specified parameters.
   *
   * @param name     the name of the node
   * @param edges    the set of edges connected to the node
   * @param visited  indicates whether the node has been visited in traversal
   * @param path     the shortest path to the node from the source node
   * @param distance the distance from the source node to this node
   */
  public Node(
    String name,
    HashSet<Edge> edges,
    boolean visited,
    LinkedList<String> path,
    int distance
  ) {
    this.name = name;
    this.distance = Integer.MAX_VALUE;
    this.path = new LinkedList<>();
    this.edges = new HashSet<>();
    this.visited = false;
  }

  /**
   * Gets the name of the node.
   *
   * @return the name of the node
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the node.
   *
   * @param name the name of the node to be set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the set of edges connected to the node.
   *
   * @return the set of edges connected to the node
   */
  public HashSet<Edge> getEdges() {
    return edges;
  }

  /**
   * Sets the set of edges connected to the node.
   *
   * @param edges the set of edges to be set
   */
  public void setEdges(HashSet<Edge> edges) {
    this.edges = edges;
  }

  /**
   * Checks if the node has been visited in traversal.
   *
   * @return true if the node has been visited, otherwise false
   */
  public boolean isVisited() {
    return visited;
  }

  /**
   * Sets the visited status of the node.
   *
   * @param visited the visited status of the node
   */
  public void setVisited(boolean visited) {
    this.visited = visited;
  }

  /**
   * Gets the shortest path to the node from the source node.
   *
   * @return the shortest path to the node
   */
  public LinkedList<String> getPath() {
    return path;
  }

  /**
   * Sets the shortest path to the node from the source node.
   *
   * @param path the shortest path to be set
   */
  public void setPath(LinkedList<String> path) {
    this.path = path;
  }

  /**
   * Gets the distance from the source node to this node.
   *
   * @return the distance from the source node to this node
   */
  public int getDistance() {
    return distance;
  }

  /**
   * Sets the distance from the source node to this node.
   *
   * @param distance the distance to be set
   */
  public void setDistance(int distance) {
    this.distance = distance;
  }
}
