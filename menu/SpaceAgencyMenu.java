package menu;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import log.SystemLog;

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
            String header = br.readLine();
            if (header == null) {
                System.out.println("CSV file is empty.");
                return;
            }

            String[] headers = header.split(",", -1);
            Map<String, Integer> indexMap = new HashMap<>();
            for (int i = 0; i < headers.length; i++) {
                String cleanName = headers[i].trim().toLowerCase()
                    .replace("?", "")
                    .replace("\"", "")
                    .replace(" ", "_")
                    .replaceAll("[^a-z0-9_]", "");
                indexMap.put(cleanName, i);
            }

            // Confirm some basic required columns exist
            String[] basicFields = {"record_id", "satellite_name", "country", "approximate_orbit_type", "object_type"};
            for (String key : basicFields) {
                if (!indexMap.containsKey(key)) {
                    System.out.println("Missing required column: " + key);
                    return;
                }
            }

            String line;
            boolean found = false;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length <= 20) continue; // ensure safe access to parts[19] and parts[20]

                // Use confirmed indexes
                String rawDaysOld = parts[19];              // days_old
                String rawConjunctionCount = parts[20];     // conjunction_count
                String orbitType = parts[indexMap.get("approximate_orbit_type")].trim();

                int daysOld = parseIntSafe(rawDaysOld);
                int conjunctionCount = parseIntSafe(rawConjunctionCount);

                // Debug print
                System.out.printf("DEBUG: Orbit=%s, DaysOld=%d, Conjunctions=%d%n", orbitType, daysOld, conjunctionCount);

                if ("LEO".equalsIgnoreCase(orbitType) && daysOld > 200 && conjunctionCount > 0) {
                    String recordId = parts[indexMap.get("record_id")].trim();
                    String satelliteName = parts[indexMap.get("satellite_name")].trim();
                    String country = parts[indexMap.get("country")].trim();
                    String objectType = parts[indexMap.get("object_type")].trim();

                    System.out.printf(
                        "Record ID: %s, Satellite Name: %s, Country: %s, Orbit: %s, Object Type: %s, Days Old: %d, Conjunctions: %d%n",
                        recordId, satelliteName, country, orbitType, objectType, daysOld, conjunctionCount
                    );
                    found = true;
                }
            }

            if (!found) {
                System.out.println("No matching LEO debris objects found with daysOld > 200 and conjunctionCount > 0.");
            } else {
                SystemLog.log("Long-term impact analysis completed by Space Agent.");
            }

        } catch (Exception e) {
            System.out.println("Error reading CSV: " + e.getMessage());
            e.printStackTrace();
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
            String header = br.readLine();
            if (header == null) {
                System.out.println("CSV file is empty.");
                return;
            }

            String[] headers = header.split(",", -1);
            Map<String, Integer> indexMap = new HashMap<>();
            for (int i = 0; i < headers.length; i++) {
                String cleanName = headers[i].trim().toLowerCase().replaceAll("[^a-z0-9_]", "");
                indexMap.put(cleanName, i);
            }

            String[] required = {
                "longitude", "record_id", "satellite_name", "country",
                "approximate_orbit_type", "launch_year", "object_type"
            };
            for (String key : required) {
                if (!indexMap.containsKey(key)) {
                    System.out.println("Missing column: " + key);
                    return;
                }
            }

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length < headers.length) continue;

                double longitude = parseDoubleSafe(parts[indexMap.get("longitude")]);

                if (longitude >= minLongitude && longitude <= maxLongitude) {
                    count++;
                    String recordId = parts[indexMap.get("record_id")].trim();
                    String satelliteName = parts[indexMap.get("satellite_name")].trim();
                    String country = parts[indexMap.get("country")].trim();
                    String orbitType = parts[indexMap.get("approximate_orbit_type")].trim();
                    int launchYear = parseIntSafe(parts[indexMap.get("launch_year")]);
                    String objectType = parts[indexMap.get("object_type")].trim();

                    System.out.printf(
                        "Record ID: %s, Satellite Name: %s, Country: %s, Orbit: %s, Launch Year: %d, Object Type: %s%n",
                        recordId, satelliteName, country, orbitType, launchYear, objectType
                    );
                }
            }

            System.out.println("\nTotal Objects in Range: " + count);
            SystemLog.log("Density Report Generated by Space Agent.");

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
