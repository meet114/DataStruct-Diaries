//Meetkumar Saspara
//115971301
//R:01
//HW7
import big.data.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Javadoc for RoadCalculator class.
 * This class provides methods to build a graph from a given URL, find the Minimum Spanning Tree (MST) of the graph,
 * and calculate the shortest path between two cities using Dijkstra's algorithm.
 */
public class RoadCalculator {

  private static LinkedList<Edge> mst;
  private static HashMap<String, Node> graph;

  /**
   * Main method to run the RoadCalculator program.
   *
   * @param args command-line arguments (not used in this program)
   */
  public static void main(String[] args) {
    graph = new HashMap<String, Node>();
    Scanner input = new Scanner(System.in);
    System.out.println("Please enter graph URL");
    String url = input.nextLine();
    buildGraph(url);
    mst = buildMST(graph);
    printTree();

    while (true) {
      System.out.println(
        "Enter a starting point for shortest path or Q to quit:"
      );
      String choice = input.nextLine();
      if (choice.toUpperCase().equals("Q")) {
        System.out.println(
          "Goodbye; PSA, there's a cop on the right in 3 miles!"
        );
        return;
      } else {
        if (!graph.containsKey(choice)) {
          System.out.println("Cannot find the given city.");
          return;
        }
        String dest = input.nextLine();
        if (!graph.containsKey(dest)) {
          System.out.println("Cannot find the given city.");
          return;
        }

        int cost = Djikstra(graph, choice, dest);
        System.out.println("Distance: " + cost);
        System.out.println("Path:");
        System.out.println(String.join(", ", graph.get(dest).getPath()));
      }
    }
  }

  /**
   * Builds the graph from the XML file located at the given URL.
   *
   * @param location the URL of the XML file containing graph data
   * @return a HashMap representing the graph
   */
  public static HashMap<String, Node> buildGraph(String location) {
    // connectXML part was copied from HW7.HTML Big Data Sample Program
    DataSource ds = DataSource.connectXML(location);
    ds.load();
    String cityNamesStr = ds.fetchString("cities");
    String[] cityNames = cityNamesStr
      .substring(1, cityNamesStr.length() - 1)
      .replace("\"", "")
      .split(",");
    Arrays.sort(cityNames);
    String roadNamesStr = ds.fetchString("roads");
    String[] roadNames = roadNamesStr
      .substring(1, roadNamesStr.length() - 1)
      .split("\",\"");
    roadNames[0] = roadNames[0].substring(1);
    roadNames[roadNames.length - 1] =
      roadNames[roadNames.length - 1].substring(
          0,
          roadNames[roadNames.length - 1].length() - 1
        );
    System.out.println("Loading Map...");
    System.out.println();
    System.out.println("Cities:");
    for (String city : cityNames) {
      Node newCity = new Node(city, null, false, null, Integer.MAX_VALUE);
      graph.put(city, newCity);
      System.out.println(city);
    }
    System.out.println();
    System.out.println("Roads:");
    Arrays.sort(roadNames);
    for (String edgeInfo : roadNames) {
      String[] parts = edgeInfo.split(",", 3);
      int cost = Integer.parseInt(parts[2]);
      Edge newRoadEdge = new Edge(
        graph.get(parts[0]),
        graph.get(parts[1]),
        cost
      );
      graph.get(parts[0]).getEdges().add(newRoadEdge);
      graph.get(parts[1]).getEdges().add(newRoadEdge);

      System.out.println(
        graph.get(parts[0]).getName() +
        " to " +
        graph.get(parts[1]).getName() +
        " " +
        cost
      );
    }
    return graph;
  }

  /**
   * Creates a copy of the edges in the graph.
   *
   * @return a LinkedList of Edge objects representing the copied edges
   */
  public static LinkedList<Edge> copyEdges() {
    LinkedList<Edge> copiedEdges = new LinkedList<>();

    for (Node node : graph.values()) {
      for (Edge nodeEdge : node.getEdges()) {
        copiedEdges.add(
          new Edge(nodeEdge.getA(), nodeEdge.getB(), nodeEdge.getCost())
        );
      }
    }
    return copiedEdges;
  }

  /**
   * Builds the Minimum Spanning Tree (MST) of the graph using Prim's algorithm.
   *
   * @param graph the graph represented as a HashMap
   * @return a LinkedList of Edge objects representing the MST
   */
  public static LinkedList<Edge> buildMST(HashMap<String, Node> graph) {
    mst = new LinkedList<>();
    LinkedList<Edge> newList = copyEdges();
    Node iterateNode = graph.values().iterator().next();
    iterateNode.setVisited(true);

    while (mst.size() < graph.size() - 1) {
      Edge ptrEdge = null;
      int infiniteCost = Integer.MAX_VALUE;

      for (Edge edge : newList) {
        Node fromA = edge.getA();
        Node toB = edge.getB();

        if (
          (fromA.isVisited() && !toB.isVisited()) ||
          (!fromA.isVisited() && toB.isVisited())
        ) {
          if (edge.getCost() < infiniteCost) {
            ptrEdge = edge;
            infiniteCost = edge.getCost();
          }
        }
      }

      if (ptrEdge != null) {
        mst.add(ptrEdge);
        if (!ptrEdge.getA().isVisited()) {
          ptrEdge.getA().setVisited(true);
        } else {
          ptrEdge.getB().setVisited(true);
        }
      } else {
        break;
      }
    }
    return mst;
  }

  /**
   * Prints the Minimum Spanning Tree (MST) of the graph.
   */
  public static void printTree() {
    System.out.println();
    System.out.println("Minimum Spanning Tree:");

    mst.sort(new EdgeComparator());

    for (Edge edge : mst) {
      String from = edge.getA().getName();
      String to = edge.getB().getName();
      int cost = edge.getCost();
      System.out.println(from + " to " + to + " " + cost);
    }
  }

  /**
   * Finds the shortest path between two cities using Dijkstra's algorithm.
   *
   * @param graph  the graph represented as a HashMap
   * @param source the starting city
   * @param dest   the destination city
   * @return the distance of the shortest path between the source and destination cities
   */
  public static int Djikstra(
    HashMap<String, Node> graph,
    String source,
    String dest
  ) {
    Node sourceNode = graph.get(source);
    sourceNode.setDistance(0);

    LinkedList<Node> visited = new LinkedList<>();

    while (!visited.contains(graph.get(dest))) {
      Node ptrNode = null;
      int cost = Integer.MAX_VALUE;

      for (Node node : graph.values()) {
        if (!visited.contains(node) && node.getDistance() < cost) {
          ptrNode = node;
          cost = node.getDistance();
        }
      }
      visited.add(ptrNode);

      for (Edge ptrEdge : ptrNode.getEdges()) {
        Node nextNode;
        if (ptrEdge.getA() == ptrNode) {
          nextNode = ptrEdge.getB();
        } else {
          nextNode = ptrEdge.getA();
        }
        if (!visited.contains(nextNode)) {
          int newCost = ptrNode.getDistance() + ptrEdge.getCost();
          if (newCost < nextNode.getDistance()) {
            nextNode.setDistance(newCost);
            LinkedList<String> newPath = new LinkedList<>(ptrNode.getPath());
            newPath.add(ptrNode.getName());
            nextNode.setPath(newPath);
          }
        }
      }
    }
    LinkedList<String> finalPart = graph.get(dest).getPath();
    finalPart.add(dest);
    int cost = graph.get(dest).getDistance();

    return cost;
  }
}
