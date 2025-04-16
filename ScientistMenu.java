import java.util.Scanner;

public class ScientistMenu implements UserMenu {
    private static final Scanner scanner = new Scanner(System.in);

    @Override
    public void showMenu() {
        while (true) {
            System.out.println("\n--- Scientist Menu ---");
            System.out.println("1. Track Objects in Space");
            System.out.println("2. Assess Orbit Status");
            System.out.println("3. Back");
            System.out.print("Select an option: ");
            int choice = getInput();
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

    private int getInput() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (Exception e) {
            return -1;
        }
    }
}
