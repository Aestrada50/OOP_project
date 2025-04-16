import java.util.Scanner;

public class PolicymakerMenu implements UserMenu {
    private static final Scanner scanner = new Scanner(System.in);

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

    private int getInput() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (Exception e) {
            return -1;
        }
    }
}
