import java.util.Scanner;

public class SpaceAgencyMenu implements UserMenu {
    private static final Scanner scanner = new Scanner(System.in);

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

    private int getInput() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (Exception e) {
            return -1;
        }
    }
}
