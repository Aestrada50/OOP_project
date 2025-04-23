import java.util.Scanner;

/**
 * AdministratorMenu class implements the UserMenu interface and provides a menu for administrators 
 * to manage users in the system.
 * It allows the user to create, manage, and delete users.
 * @author Noel Lozano
 */
public class PolicymakerMenu implements UserMenu {
    /**
     * Scanner instance for user input.
     */
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * shows the menu for the administrator and handles user input.
     * It provides options to create, manage, or delete users.
     * @author Noel Lozano
     */
    @Override
    public void showMenu() {
        while (true) {
            System.out.println("\n--- Policymaker Menu ---");
            System.out.println("1. Review Reports on Debris Impact");
            System.out.println("2. Assess Risk Levels for Future Space Missions");
            System.out.println("3. Back");
            System.out.print("Select an option: ");
            int choice = getInput();
            switch (choice) {
                case 1:
                    System.out.println("[Stub] Reviewing reports on debris impact...");
                    break;
                case 2:
                    System.out.println("[Stub] Assessing risk levels for future missions...");
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
