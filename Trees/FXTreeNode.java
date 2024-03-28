//Meetkumar Saspara
//115971301
//R:01
//HW5

/**
 * Represents a node in a GUI component tree.
 */
public class FXTreeNode {

  private String text;
  private ComponentType type;
  private FXTreeNode parent;
  private FXTreeNode[] children;
  final int maxChildren = 10;

  /**
   * Constructs a FXTreeNode with the specified type, text, and parent.
   *
   * @param type   The type of the GUI component
   * @param text   The text associated with the node
   * @param parent The parent node of this node
   */

  public FXTreeNode(ComponentType type, String text, FXTreeNode parent) {
    this.text = text;
    this.type = type;
    this.parent = parent;
    this.children = new FXTreeNode[maxChildren];
  }

  /**
   * Returns the text associated with this node.
   *
   * @return The text associated with this node
   */
  public String getText() {
    return text;
  }

  /**
   * Sets the text associated with this node.
   *
   * @param text The text to be set
   */
  public void setText(String text) {
    this.text = text;
  }

  /**
   * Returns the type of the GUI component associated with this node.
   *
   * @return The type of the GUI component
   */
  public ComponentType getType() {
    return type;
  }

  /**
   * Sets the type of the GUI component associated with this node.
   *
   * @param type The type of the GUI component to be set
   */
  public void setType(ComponentType type) {
    this.type = type;
  }

  /**
   * Returns the parent node of this node.
   *
   * @return The parent node
   */
  public FXTreeNode getParent() {
    return this.parent;
  }

  /**
   * Sets the parent node of this node.
   *
   * @param parent The parent node to be set
   */
  public void setParent(FXTreeNode parent) {
    this.parent = parent;
  }

  /**
   * Returns the array of child nodes of this node.
   *
   * @return The array of child nodes
   */
  public FXTreeNode[] getChildren() {
    return this.children;
  }

  /**
   * Sets the array of child nodes of this node.
   *
   * @param children The array of child nodes to be set
   */
  public void setChildren(FXTreeNode[] children) {
    this.children = children;
  }

  /**
   * Returns the maximum number of children a node can have.
   *
   * @return The maximum number of children
   */
  public int getMaxChildren() {
    return this.maxChildren;
  }
}
