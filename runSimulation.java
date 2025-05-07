// ✅ 3. RunSimulation.java (with handled AuthenticationException)
import java.io.*;
import java.util.*;

import auth.UserAuthenticator;
import auth.AuthenticationException;
import model.TrackingSystem;
import model.Debris;
import model.SpaceObject;
import menu.AdministratorMenu;
import menu.PolicymakerMenu;
import menu.ScientistMenu;
import menu.SpaceAgencyMenu;
import log.SystemLog;

public class RunSimulation {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        TrackingSystem trackingSystem = new TrackingSystem();
        trackingSystem.loadObjectsFromCSV("rso_metrics.csv");

        while (true) {
            String input = displayMainMenu();

            if (input.equalsIgnoreCase("EXIT")) {
                System.out.println("Saving final debris tracking report and log...");
                saveFinalDebrisReport(trackingSystem);
                saveLog();
                System.out.println("Exiting system. Goodbye!");
                break;
            }

            try {
                int choice = Integer.parseInt(input);
                switch (choice) {
                    case 1 -> {
                        try {
                            if (UserAuthenticator.login("Administrator"))
                                new AdministratorMenu().showMenu();
                        } catch (AuthenticationException e) {
                            System.out.println("[Login Failed] " + e.getMessage());
                        }
                    }
                    case 2 -> {
                        try {
                            if (UserAuthenticator.login("Scientist"))
                                new ScientistMenu(trackingSystem).showMenu();
                        } catch (AuthenticationException e) {
                            System.out.println("[Login Failed] " + e.getMessage());
                        }
                    }
                    case 3 -> {
                        try {
                            if (UserAuthenticator.login("Space Agency Representative"))
                                new SpaceAgencyMenu().showMenu();
                        } catch (AuthenticationException e) {
                            System.out.println("[Login Failed] " + e.getMessage());
                        }
                    }
                    case 4 -> {
                        try {
                            if (UserAuthenticator.login("Policymaker"))
                                new PolicymakerMenu().showMenu();
                        } catch (AuthenticationException e) {
                            System.out.println("[Login Failed] " + e.getMessage());
                        }
                    }
                    default -> System.out.println("Invalid selection. Please choose a valid option.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number (1-4) or 'EXIT'.");
            }
        }
    }

    private static String displayMainMenu() {
        System.out.println("\n=== Select User Type ===");
        System.out.println("1. Administrator");
        System.out.println("2. Scientist");
        System.out.println("3. Space Agency Representative");
        System.out.println("4. Policymaker");
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
        try (BufferedWriter log = new BufferedWriter(new FileWriter("latest_log.txt"))) {
            for (String entry : SystemLog.getLogs()) {
                log.write(entry);
                log.newLine();
            }
            log.write("[Session ended at " + new java.util.Date() + "]");
            log.newLine();
            System.out.println("Log saved as 'latest_log.txt'.");
        } catch (IOException e) {
            System.err.println("Failed to save log: " + e.getMessage());
        }
    }
}
