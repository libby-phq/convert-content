import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);

    System.out.print("What kind of content would you like to update?\n-cutscene\n-acts\n");
    String contentTypeToUpdate = scan.nextLine();

    if (contentTypeToUpdate.compareTo("cutscene") == 0) {
      Cutscene cutsceneContent = new Cutscene();

      System.out.print("Enter your current array content and then press enter (without []):");
      String currentCopy = getMultilineInput(scan);
      System.out.print("Enter your new copy straight from trix:");
      String newCopy = getMultilineInput(scan);

      String[] objects = currentCopy.split("},");
      cutsceneContent.currentObjects =  Arrays.copyOf(objects, objects.length - 1);
      cutsceneContent.newObjects = newCopy.split("\n");

      if (cutsceneContent.currentObjects.length != cutsceneContent.newObjects.length) {
        System.out.print("Number of items in current content doesn't match number in new content");
      }
      else {
        cutsceneContent.replaceContent();
        copyContentToClipboard(cutsceneContent.newJsonString);
      }
    }
    else {
      System.out.print("Sorry this feature hasn't been added yet");
    }

  }

  public static String getMultilineInput(Scanner scan) {
    StringBuilder input = new StringBuilder();

    while (true) {
      String line = scan.nextLine();
      if (line.isEmpty()) {
        break; // Exit on empty line
      }
      input.append(line).append("\n");
    }

    return input.toString();
  }

  public static void copyContentToClipboard(String newJsonString) {
    StringSelection newJsonStringSelection = new StringSelection(newJsonString);
    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    clipboard.setContents(newJsonStringSelection, null);
    System.out.print("New content copied to clipboard!");
  }
}