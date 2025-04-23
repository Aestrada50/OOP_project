import java.util.Scanner;

/**
 * SpaceAgencyMenu class implements the UserMenu interface and provides a menu for space agency representatives 
 * to analyze long-term impacts and generate density reports.
 * It allows the user to analyze long-term impact or generate density reports.
 * @author Noel Lozano
 */
public class SpaceAgencyMenu implements UserMenu {
    /**
     * Scanner instance for user input.
     */
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * shows the menu for the space agency representative and handles user input.
     * It provides options to analyze long-term impact or generate density reports.
     * @author Noel Lozano
     */
    @Override
    public void showMenu() {
        while (true) {
            System.out.println("\n--- Space Agency Representative Menu ---");
            System.out.println("1. Analyze Long-term Impact");
            System.out.println("2. Generate Density Reports");
            System.out.println("3. Back");
            System.out.print("Select an option: ");
            int choice = getInput();
            switch (choice) {
                case 1:
                    System.out.println("[Stub] Analyzing long-term impact...");
                    break;
                case 2:
                    System.out.println("[Stub] Generating density reports...");
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    /**
     * Gets user input and returns it as an integer.
     * If the input is invalid, it returns -1.
     * @return The user input as an integer or -1 if invalid.
     * @author Noel Lozano
     */
    private int getInput() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (Exception e) {
            return -1;
        }
    }
}
