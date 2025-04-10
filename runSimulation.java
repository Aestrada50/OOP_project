import java.util.Scanner;

public class runSimulation {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            int userType = displayMainMenu();
            switch (userType) {
                case 1:
                    handleScientistMenu();
                    break;
                case 2:
                    handleSpaceAgencyMenu();
                    break;
                case 3:
                    handlePolicymakerMenu();
                    break;
                case 4:
                    handleAdministratorMenu();
                    break;
                case 5:
                    System.out.println("Exiting system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private static int displayMainMenu() {
        System.out.println("\n=== Select User Type ===");
        System.out.println("1. Scientist");
        System.out.println("2. Space Agency Representative");
        System.out.println("3. Policymaker");
        System.out.println("4. Administrator");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
        return getUserInput();
    }

    private static void handleScientistMenu() {
        while (true) {
            System.out.println("\n--- Scientist Menu ---");
            System.out.println("1. Track Objects in Space");
            System.out.println("2. Assess Orbit Status");
            System.out.println("3. Back");
            System.out.print("Select an option: ");
            int choice = getUserInput();
            switch (choice) {
                case 1:
                    System.out.println("[Stub] Tracking objects in space...");
                    break;
                case 2:
                    System.out.println("[Stub] Assessing orbit status...");
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void handleSpaceAgencyMenu() {
        while (true) {
            System.out.println("\n--- Space Agency Representative Menu ---");
            System.out.println("1. Analyze Long-term Impact");
            System.out.println("2. Generate Density Reports");
            System.out.println("3. Back");
            System.out.print("Select an option: ");
            int choice = getUserInput();
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

    private static void handlePolicymakerMenu() {
        while (true) {
            System.out.println("\n--- Policymaker Menu ---");
            System.out.println("1. Review Reports on Debris Impact");
            System.out.println("2. Assess Risk Levels for Future Space Missions");
            System.out.println("3. Back");
            System.out.print("Select an option: ");
            int choice = getUserInput();
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

    private static void handleAdministratorMenu() {
        while (true) {
            System.out.println("\n--- Administrator Menu ---");
            System.out.println("1. Create User");
            System.out.println("2. Manage User");
            System.out.println("3. Delete User");
            System.out.println("4. Back");
            System.out.print("Select an option: ");
            int choice = getUserInput();
            switch (choice) {
                case 1:
                    System.out.println("[Stub] Creating user...");
                    break;
                case 2:
                    System.out.println("[Stub] Managing user...");
                    break;
                case 3:
                    System.out.println("[Stub] Deleting user...");
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static int getUserInput() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1; // Invalid input
        }
    }
}

