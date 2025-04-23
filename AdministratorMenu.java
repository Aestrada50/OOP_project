import java.util.Scanner;

/**
 * AdministratorMenu class implements the UserMenu interface and provides a menu for administrators 
 * to manage users in the system.
 * It allows the user to create, manage, and delete users.
 * @author Noel Lozano
 */
public class AdministratorMenu implements UserMenu {
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
            System.out.println("\n--- Administrator Menu ---");
            System.out.println("1. Create User");
            System.out.println("2. Manage User");
            System.out.println("3. Delete User");
            System.out.println("4. Back");
            System.out.print("Select an option: ");
            int choice = getInput();
            switch (choice) {
                case 1:
                    SystemLog.log("Administrator created a new user.");
                    System.out.println("[Stub] Creating user...");
                    break;
                case 2:
                    SystemLog.log("Administrator managed a user.");
                    System.out.println("[Stub] Managing user...");
                    break;
                case 3:
                    SystemLog.log("Administrator deleted a user.");
                    System.out.println("[Stub] Deleting user...");
                    break;
                case 4:
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
