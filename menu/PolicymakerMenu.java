package menu;
/**
 * PolicymakerMenu class provides a menu for policymakers to review debris reports
 * and assess risk levels for future space missions. Extends BaseMenu for shared functionality.
 * 
 * @author Noel Lozano
 */
public class PolicymakerMenu extends BaseMenu {

    /**
     * Displays the policymaker's menu options and handles user input.
     */
    @Override
    public void showMenu() {
        String[] options = {"Review Reports on Debris Impact", "Assess Risk Levels for Future Space Missions", "Back"};
        while (true) {
            printOptions("Policymaker Menu", options);
            int choice = getInput();
            switch (choice) {
                case 1 -> reviewReports();
                case 2 -> assessRiskLevels();
                case 3 -> { return; }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    /**
     * Simulates reviewing reports on debris impact.
     */
    private void reviewReports() {
        System.out.println("[Stub] Reviewing reports on debris impact...");
    }

    /**
     * Simulates assessing risk levels for future missions.
     */
    private void assessRiskLevels() {
        System.out.println("[Stub] Assessing risk levels for future missions...");
    }
}
