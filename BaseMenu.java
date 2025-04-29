import java.util.Scanner;

/**
 * BaseMenu class provides shared utilities for user menu classes,
 * such as standardized input handling and option printing.
 * All user menu classes should extend this base class.
 * 
 * @author Noel lozano
 */
public abstract class BaseMenu implements UserMenu {
    protected static final Scanner scanner = new Scanner(System.in);

    /**
     * Reads and parses an integer input from the user.
     * @return Parsed integer, or -1 if parsing fails.
     */
    protected int getInput() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * Reads and parses a double input from the user.
     * @return Parsed double, or 0 if parsing fails.
     */
    protected double getDoubleInput() {
        try {
            return Double.parseDouble(scanner.nextLine().trim());
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Prints a numbered list of options with a given title.
     * @param title The title for the options menu.
     * @param options An array of option strings to display.
     */
    protected void printOptions(String title, String[] options) {
        System.out.println("\n--- " + title + " ---");
        for (int i = 0; i < options.length; i++) {
            System.out.printf("%d. %s%n", i + 1, options[i]);
        }
    }
}
