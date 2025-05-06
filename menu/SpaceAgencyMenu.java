package menu;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * SpaceAgencyMenu class provides a menu for space agency representatives to
 * analyze long-term impacts and generate density reports from a CSV database.
 * Extends BaseMenu for shared functionality.
 * 
 * @author Noel Lozano
 */
public class SpaceAgencyMenu extends BaseMenu {
    private static final String CSV_FILE = "rso_metrics.csv"; // Path to your CSV file

    /**
     * Displays the space agency representative's menu and handles user input.
     */
    @Override
    public void showMenu() {
        String[] options = {"Analyze Long-term Impact", "Generate Density Reports", "Back"};
        while (true) {
            printOptions("Space Agency Representative Menu", options);
            int choice = getInput();
            switch (choice) {
                case 1 -> analyzeLongTermImpact();
                case 2 -> generateDensityReports();
                case 3 -> { return; }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    /**
     * Analyzes the long-term impact of space debris based on CSV data.
     */
    private void analyzeLongTermImpact() {
        System.out.println("\n--- Analyzing Long-term Impact ---");
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length < 20) continue;

                String recordId = parts[0].trim();
                String satelliteName = parts[2].trim();
                String country = parts[3].trim();
                String orbitType = parts[4].trim();
                String objectType = parts[5].trim();
                int daysOld = parseIntSafe(parts[18]);
                int conjunctionCount = parseIntSafe(parts[19]);

                if ("LEO".equalsIgnoreCase(orbitType) && daysOld > 200 && conjunctionCount > 0) {
                    System.out.printf("Record ID: %s, Satellite Name: %s, Country: %s, Orbit: %s, Object Type: %s, Days Old: %d, Conjunctions: %d%n",
                            recordId, satelliteName, country, orbitType, objectType, daysOld, conjunctionCount);
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading CSV: " + e.getMessage());
        }
    }

    /**
     * Generates a density report based on user-provided longitude range.
     */
    private void generateDensityReports() {
        System.out.println("\n--- Generating Density Report ---");
        System.out.print("Enter minimum longitude: ");
        double minLongitude = getDoubleInput();
        System.out.print("Enter maximum longitude: ");
        double maxLongitude = getDoubleInput();

        int count = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length < 20) continue;

                double longitude = parseDoubleSafe(parts[10]);
                if (longitude >= minLongitude && longitude <= maxLongitude) {
                    count++;
                    String recordId = parts[0].trim();
                    String satelliteName = parts[2].trim();
                    String country = parts[3].trim();
                    String orbitType = parts[4].trim();
                    int launchYear = parseIntSafe(parts[6]);
                    String objectType = parts[5].trim();

                    System.out.printf("Record ID: %s, Satellite Name: %s, Country: %s, Orbit: %s, Launch Year: %d, Object Type: %s%n",
                            recordId, satelliteName, country, orbitType, launchYear, objectType);
                }
            }
            System.out.println("\nTotal Objects in Range: " + count);
        } catch (Exception e) {
            System.out.println("Error reading CSV: " + e.getMessage());
        }
    }

    private int parseIntSafe(String s) {
        try {
            return Integer.parseInt(s.trim());
        } catch (Exception e) {
            return 0;
        }
    }

    private double parseDoubleSafe(String s) {
        try {
            return Double.parseDouble(s.trim());
        } catch (Exception e) {
            return 0;
        }
    }
}
