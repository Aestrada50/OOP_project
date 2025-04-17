import java.util.*;
import java.io.*;

public class RunSimulation {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        TrackingSystem trackingSystem = new TrackingSystem();
        trackingSystem.loadObjectsFromCSV("rso_metrics.csv"); // Load your input CSV file

        while (true) {
            String input = displayMainMenu();

            if (input.equalsIgnoreCase("EXIT")) {
                System.out.println("Saving final debris tracking report and log...");
                saveFinalDebrisReport(trackingSystem);
                saveLog();
                System.out.println("Exiting system. Goodbye!");
                break;
            }

            int userType;
            try {
                userType = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number or 'EXIT'.");
                continue;
            }

            UserMenu menu = null;
            switch (userType) {
                case 1:
                    menu = new ScientistMenu(trackingSystem);
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
                default:
                    System.out.println("Invalid input. Try again.");
            }

            if (menu != null) {
                menu.showMenu();
            }
        }
    }

    private static String displayMainMenu() {
        System.out.println("\n=== Select User Type ===");
        System.out.println("1. Scientist");
        System.out.println("2. Space Agency Representative");
        System.out.println("3. Policymaker");
        System.out.println("4. Administrator");
        System.out.println("Type 'EXIT' to exit the system.");
        System.out.print("Enter your choice: ");
        return scanner.nextLine().trim();
    }

    private static void saveFinalDebrisReport(TrackingSystem trackingSystem) {
        try (PrintWriter out = new PrintWriter("debris_tracking_report.csv")) {
            out.println("RecordID,SatelliteName,Country,OrbitType,LaunchYear,LaunchSite,Longitude,AvgLongitude,Geohash,DaysOld,ConjunctionCount,StillInOrbit,RiskLevel");

            for (SpaceObject obj : trackingSystem.getObjectsByType("DEBRIS")) {
                if (obj instanceof Debris d) {
                    out.printf("%s,%s,%s,%s,%d,%s,%.2f,%.2f,%s,%d,%d,%b,%s%n",
                            d.getRecordId(), d.getSatelliteName(), d.getCountry(), d.getOrbitType(),
                            d.getLaunchYear(), d.getLaunchSite(), d.getLongitude(), d.getAvgLongitude(),
                            d.getGeohash(), d.getDaysOld(), d.getConjunctionCount(), d.isStillInOrbit(), d.getRiskLevel());
                }
            }
            System.out.println("Debris tracking report saved as 'debris_tracking_report.csv'.");
        } catch (IOException e) {
            System.err.println("Error writing debris tracking report: " + e.getMessage());
        }
    }

    private static void saveLog() {
        try (PrintWriter log = new PrintWriter("latest_log.txt")) {
            log.printf("Program exited at %s%n", new java.util.Date());
            // Add additional logging details here if needed
            System.out.println("Log saved as 'latest_log.txt'.");
        } catch (IOException e) {
            System.err.println("Failed to save log: " + e.getMessage());
        }
    }
}
