import java.util.Scanner;

public class runSimulation {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            int userType = displayMainMenu();
            UserMenu menu = null;

            switch (userType) {
                case 1:
                    menu = new ScientistMenu(null);
                    break;
                case 2:
                    menu = new SpaceAgencyMenu();
                    break;
                case 3:
                    menu = new PolicymakerMenu();
                    break;
                case 4:
                    menu = new AdministratorMenu();
                    break;
                case 5:
                    System.out.println("Exiting system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid input. Please try again.");
            }

            if (menu != null) {
                menu.showMenu();
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
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (Exception e) {
            return -1;
        }
    }
}
