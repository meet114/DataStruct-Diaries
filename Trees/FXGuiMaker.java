//Meetkumar Saspara
//115971301
//R:01
//HW5

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Provides a command-line interface for interacting with FXComponentTree.
 */
public class FXGuiMaker {

  private static FXComponentTree tree;

  /**
   * Main method to run the FXGuiMaker application.
   *
   * @param args Command-line arguments (not used)
   */
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    boolean quit = true;
    System.out.println("Welcome to counterfeit SceneBuilder.");
    String choice;
    while (quit) {
      System.out.println();

      System.out.println(
        "Menu:\n" +
        "\n" +
        "    L) Load from file\n" +
        "    P) Print tree\n" +
        "    C) Move cursor to a child node\n" +
        "    R) Move cursor to root\n" +
        "    A) Add a child\n" +
        "    U) Cursor up (to parent)\n" +
        "    E) Edit text of cursor\n" +
        "    D) Delete child\n" +
        "    S) Save to file\n" +
        "    X) Export FXML //Works the same as save, extra credit\n" +
        "    Q) Quit"
      );
      System.out.println("Please select an option: ");
      choice = input.nextLine().toUpperCase();
      switch (choice.toUpperCase().charAt(0)) {
        case 'L':
          loadFromFile(input);
          break;
        case 'P':
          tree.printTree(tree);
          break;
        case 'C':
          System.out.print("Please enter number of child (starting with 1):");
          int index = input.nextInt();
          input.nextLine();
          System.out.println();
          tree.cursorToChild(index - 1);
          if (
            tree.getCursor().getType() == ComponentType.Label ||
            tree.getCursor().getType() == ComponentType.TextArea ||
            tree.getCursor().getType() == ComponentType.Button
          ) {
            System.out.println(
              "Cursor Moved to " +
              tree.getCursor().getType() +
              ": " +
              tree.getCursor().getText() +
              "."
            );
          } else {
            System.out.println(
              "Cursor Moved to " + tree.getCursor().getType() + "."
            );
          }

          break;
        case 'R':
          tree.cursorToRoot();
          System.out.println("Cursor is at root.");
          break;
        case 'A':
          System.out.print(
            "Select component type (H - HBox, V - VBox, T - TextArea, B - Button, L - Label):"
          );
          char typeChar = input.nextLine().toUpperCase().charAt(0);
          ComponentType newType;
          switch (typeChar) {
            case 'H':
              newType = ComponentType.HBox;
              break;
            case 'V':
              newType = ComponentType.VBox;
              break;
            case 'T':
              newType = ComponentType.TextArea;
              break;
            case 'B':
              newType = ComponentType.Button;
              break;
            case 'L':
              newType = ComponentType.Label;
              break;
            default:
              newType = ComponentType.Label;
              System.out.println(
                "Please select a valid componentType. from (H - HBox, V - VBox, T - TextArea, B - Button, L - Label) "
              );
          }
          if (tree.getCursor().getParent().getType() == newType) {
            System.out.println("Invalid");
            break;
          }
          System.out.println();
          System.out.println("Please enter text: ");
          String newText = input.nextLine();
          System.out.println("Please enter an index: ");
          int newIndex = input.nextInt();
          input.nextLine();
          FXTreeNode newNode = new FXTreeNode(
            newType,
            newText,
            tree.getCursor()
          );
          tree.addChild(newIndex - 1, newNode);
          tree.cursorToChild(newIndex - 1);
          System.out.println("Added");

          break;
        case 'U':
          tree.cursorToParent();
          System.out.println("Cursor Moved to " + tree.getCursor().getType());
          break;
        case 'E':
          System.out.println("Please enter new text: ");
          String updateText = input.nextLine();
          if (
            tree.getCursor() == null ||
            tree.getCursor().getType() == ComponentType.HBox ||
            tree.getCursor().getType() == ComponentType.VBox ||
            tree.getCursor().getType() == ComponentType.AnchorPane
          ) {
            System.out.println("Cannot edit text");
          } else {
            tree.getCursor().setText(updateText);
            System.out.println("Text Edited.");
          }

          break;
        case 'D':
          System.out.print("Please enter number of child (starting with 1):");
          index = input.nextInt();
          input.nextLine();
          System.out.println();
          tree.deleteChild(index - 1);
          break;
        case 'S':
          System.out.print("Please enter a filename: ");
          String fileName = input.nextLine();
          System.out.println();

          tree.writeToFile(tree, fileName);

          break;
        case 'X':
          break;
        case 'Q':
          quit = false;
          System.out.println("Make like a tree and leave!");
          break;
      }
    }
    input.close();
  }

  /**
   * Loads FXComponentTree from a file.
   *
   * @param input Scanner object for user input
   */
  private static void loadFromFile(Scanner input) {
    System.out.println("Please enter filename: ");
    String name = input.nextLine();

    try {
      tree = FXComponentTree.readFromFile(name);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
