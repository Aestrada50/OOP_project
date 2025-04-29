import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

/**
 * SpaceAgencyMenu class implements the UserMenu interface and provides a menu for space agency representatives 
 * to analyze long-term impacts and generate density reports.
 */
public class SpaceAgencyMenu implements UserMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String CSV_FILE = "debris.csv"; // Path to your CSV file

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
                    analyzeLongTermImpact();
                    break;
                case 2:
                    generateDensityReports();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

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

    private int getInput() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (Exception e) {
            return -1;
        }
    }

    private double getDoubleInput() {
        try {
            return Double.parseDouble(scanner.nextLine().trim());
        } catch (Exception e) {
            return 0;
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
