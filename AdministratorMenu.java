import java.util.Scanner;

public class AdministratorMenu implements UserMenu {
    private static final Scanner scanner = new Scanner(System.in);

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

    private int getInput() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (Exception e) {
            return -1;
        }
    }
}
