//Meetkumar Saspara
//115971301
//R:01
//HW5
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents a tree structure of GUI components.
 */
public class FXComponentTree {

  private FXTreeNode root;
  private FXTreeNode cursor;

  /**
   * Constructs a new FXComponentTree with root and cursor set to null.
   */
  FXComponentTree() {
    this.root = null;
    this.cursor = null;
  }

  /**
   * Returns the cursor node of the tree.
   *
   * @return The cursor node
   */
  public FXTreeNode getCursor() {
    return this.cursor;
  }

  /**
   * Sets the cursor node of the tree.
   *
   * @param cursor The cursor node to set
   */
  public void setCursor(FXTreeNode cursor) {
    this.cursor = cursor;
  }

  /**
   * Returns the root node of the tree.
   *
   * @return The root node
   */
  public FXTreeNode getRoot() {
    return this.root;
  }

  /**
   * Sets the root node of the tree.
   *
   * @param root The root node to set
   */
  public void setRoot(FXTreeNode root) {
    this.root = root;
  }

  /**
   * Moves the cursor to the root of the tree.
   */
  public void cursorToRoot() {
    this.cursor = root;
  }

  /**
   * Deletes a child node at the specified index from the cursor node.
   *
   * @param index The index of the child node to delete
   */
  public void deleteChild(int index) {
    FXTreeNode[] child = cursor.getChildren();
    if (child[index] != null) {
      FXTreeNode temp = child[index];
      child[index] = null;
      System.out.println(temp.getType() + " removed");
    } else {
      System.out.println("IndexOutOfBounds");
    }
  }

  /**
   * Adds a child node at the specified index to the cursor node.
   *
   * @param index The index to add the child node
   * @param node  The child node to add
   */
  public void addChild(int index, FXTreeNode node) {
    FXTreeNode[] children = this.cursor.getChildren();
    if (children[index] != null) {
      for (int i = children.length - 1; i > index; i--) {
        children[i] = children[i - 1];
      }
    }
    children[index] = node;
  }

  /**
   * Sets the text of the cursor node.
   *
   * @param text The text to set
   */
  public void setTextAtCursor(String text) {
    this.cursor.setText(text);
  }

  /**
   * Moves the cursor to the child node at the specified index.
   *
   * @param index The index of the child node to move to
   */
  public void cursorToChild(int index) {
    this.cursor = cursor.getChildren()[index];
  }

  /**
   * Moves the cursor to the parent node.
   */
  public void cursorToParent() {
    if (cursor != root) {
      cursor = cursor.getParent();
    } else {
      System.out.println(" cursor at ROOT.");
    }
  }

  /**
   * Reads the FXComponentTree structure from a file and returns the tree.
   *
   * @param filename The name of the file to read from
   * @return The FXComponentTree read from the file
   * @throws FileNotFoundException if the file does not exist
   */
  public static FXComponentTree readFromFile(String filename)
    throws FileNotFoundException {
    try {
      File file = new File(filename);
      Scanner input = new Scanner(file);
      FXComponentTree tree = new FXComponentTree();
      while (input.hasNextLine()) {
        String nextLine = input.nextLine();
        String[] splits = nextLine.split(" ", 3);

        String strPosition = splits[0];
        String strType = splits[1];
        String text = splits.length > 2 ? splits[2] : null;

        String[] splitPosition = strPosition.split("-");
        int[] position = new int[splitPosition.length];
        for (int i = 0; i < splitPosition.length; i++) {
          position[i] = Integer.parseInt(splitPosition[i]);
        }

        ComponentType type = ComponentType.valueOf(strType);

        if (tree.getRoot() == null) {
          tree.setRoot(new FXTreeNode(type, text, null));
          tree.setCursor(tree.getRoot());
        } else {
          tree.cursorToRoot();

          for (int i = 1; i < position.length - 1; i++) {
            tree.cursorToChild(position[i]);
          }
          tree.addChild(
            position[position.length - 1],
            new FXTreeNode(type, text, tree.getCursor())
          );
        }
      }
      System.out.println(filename + " loaded");
      tree.cursorToRoot();
      input.close();
      return tree;
    } catch (FileNotFoundException e) {
      System.out.println(filename + " not found");
    }
    return null;
  }

  /**
   * Writes the FXComponentTree structure to a file.
   *
   * @param tree     The FXComponentTree to write to the file
   * @param filename The name of the file to write to
   */
  public void writeToFile(FXComponentTree tree, String filename) {
    try {
      FileWriter newFileName = new FileWriter(filename);
      if (getRoot() != null) {
        saveNodeToFile(getRoot(), "0", newFileName);
      }
      System.out.println(filename + " saved to computer");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Recursively saves the structure of the FXComponentTree starting from the given node to a file.
   *
   * @param node        The node to start saving from
   * @param index       The index of the current node
   * @param newFileName The FileWriter object to write to the file
   * @throws IOException if an I/O error occurs while writing to the file
   */
  private static void saveNodeToFile(
    FXTreeNode node,
    String index,
    FileWriter newFileName
  ) throws IOException {
    if (node == null) {
      return;
    }

    String nodeText = (node.getText() != null) ? node.getText() : "";
    newFileName.write(index + " " + node.getType() + " " + nodeText + "\n");

    FXTreeNode[] children = node.getChildren();
    for (int i = 0; i < children.length; i++) {
      saveNodeToFile(children[i], index + "-" + i, newFileName);
    }
  }

  public void exportToFXML(FXComponentTree tree, String filename) {}

  /**
   * Prints the structure of the FXComponentTree.
   *
   * @param tree The FXComponentTree to print
   */
  public void printTree(FXComponentTree tree) {
    this.printNode(tree.getRoot(), "");
  }

  /**
   * Recursively prints the structure of the FXComponentTree starting from the given node.
   *
   * @param node      The node to start printing from
   * @param nodeFront The indentation string for the current node
   */
  private void printNode(FXTreeNode node, String nodeFront) {
    if (node == null) {
      return;
    }

    String nodeText = (node.getText() != null) ? node.getText() : "";
    String arrow = "";
    if (node == this.root && node != this.cursor) {
      arrow = "\t";
    } else if (node == this.cursor) {
      arrow = "==>";
    } else {
      arrow = "+--";
    }
    if (
      node.getType() == ComponentType.Label ||
      node.getType() == ComponentType.TextArea ||
      node.getType() == ComponentType.Button
    ) {
      System.out.println(nodeFront + arrow + node.getType() + ": " + nodeText);
    } else if (node.getType() == ComponentType.AnchorPane) {
      System.out.println(nodeFront + arrow + "Anchor Pane");
    } else {
      System.out.println(nodeFront + arrow + node.getType());
    }

    FXTreeNode[] children = node.getChildren();
    for (int i = 0; i < children.length; i++) {
      String childNodeFront = nodeFront + "";

      if (i == children.length - 2) {
        childNodeFront = nodeFront + "+-- ";
      } else {
        childNodeFront = nodeFront + "\t";
      }
      printNode(children[i], childNodeFront);
    }
  }
}
