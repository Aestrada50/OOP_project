import java.util.*;
import model.Debris;
import model.TrackingSystem;
import model.SpaceObject;
import java.io.*;

/**
 * ScientistMenu class provides a menu for scientists to track objects in space
 * and assess the orbit status of debris. Extends BaseMenu for common functionality.
 * 
 * @author  Noel Lozano
 */
public class ScientistMenu extends BaseMenu {
    private TrackingSystem trackingSystem;

    /**
     * Constructs a ScientistMenu with the given TrackingSystem instance.
     * @param trackingSystem the tracking system to interact with.
     */
    public ScientistMenu(TrackingSystem trackingSystem) {
        this.trackingSystem = trackingSystem;
    }

    /**
     * Displays the scientist's menu options and handles user input.
     */
    @Override
    public void showMenu() {
        String[] options = {"Track Objects in Space", "Assess Orbit Status", "Back"};
        while (true) {
            printOptions("Scientist Menu", options);
            int choice = getInput();
            switch (choice) {
                case 1 -> trackObjectsMenu();
                case 2 -> assessOrbitStatusMenu();
                case 3 -> { System.out.println("Exiting Scientist Menu."); return; }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    /**
     * Displays sub-menu for tracking different types of space objects.
     */
    private void trackObjectsMenu() {
        while (true) {
            String[] types = {"Rocket Body", "Debris", "Payload", "Unknown", "Back"};
            printOptions("Track Objects in Space", types);
            int choice = getInput();
            if (choice >= 1 && choice <= 4) {
                String type = types[choice - 1].toUpperCase();
                List<SpaceObject> objects = trackingSystem.getObjectsByType(type);
                displayObjects(objects);
                SystemLog.log("Scientist tracked " + type.toLowerCase() + " in space.");
            } else if (choice == 5) {
                return;
            } else {
                System.out.println("Invalid option. Try again.");
            }
        }
    }

    /**
     * Displays sub-menu for assessing orbit status.
     */
    private void assessOrbitStatusMenu() {
        while (true) {
            String[] options = {"Track Objects in LEO", "Assess if debris is still in orbit", "Back"};
            printOptions("Assess Orbit Status", options);
            int choice = getInput();
            switch (choice) {
                case 1 -> trackLEOObjects();
                case 2 -> assessDebrisOrbitStatus();
                case 3 -> { return; }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    /**
     * Tracks and displays all objects currently in Low Earth Orbit (LEO).
     */
    private void trackLEOObjects() {
        List<SpaceObject> leoObjects = new ArrayList<>();
        for (String type : new String[]{"DEBRIS", "PAYLOAD", "ROCKET BODY", "UNKNOWN"}) {
            for (SpaceObject obj : trackingSystem.getObjectsByType(type)) {
                if ("LEO".equalsIgnoreCase(obj.getOrbitType())) leoObjects.add(obj);
            }
        }
        displayObjects(leoObjects);
        SystemLog.log("Scientist assessed objects in LEO.");
    }

    /**
     * Assesses the orbit status of debris, generates CSV and TXT reports.
     */
    private void assessDebrisOrbitStatus() {
        List<SpaceObject> debrisList = trackingSystem.getObjectsByType("DEBRIS");
        int inOrbit = 0, exited = 0;
        List<SpaceObject> exitedDebris = new ArrayList<>();

        try (PrintWriter csvOut = new PrintWriter("assessed_debris.csv");
             PrintWriter txtOut = new PrintWriter("exited_debris_summary.txt")) {

            csvOut.println("RecordID,SatelliteName,Country,OrbitType,LaunchYear,LaunchSite,Longitude,AvgLongitude,Geohash,DaysOld,ConjunctionCount,StillInOrbit,RiskLevel");

            for (SpaceObject obj : debrisList) {
                Debris d = (Debris) obj;
                boolean stillInOrbit = d.getOrbitType() != null && !d.getOrbitType().isEmpty()
                        && d.getLongitude() != 0 && d.getDaysOld() < 15000 && d.getConjunctionCount() >= 1;
                d.setStillInOrbit(stillInOrbit);
                if (stillInOrbit) inOrbit++;
                else {
                    exited++;
                    exitedDebris.add(d);
                }

                double drift = Math.abs(d.getLongitude() - d.getAvgLongitude());
                String risk = drift > 50 ? "High" : drift > 10 ? "Moderate" : "Low";
                d.setRiskLevel(risk);

                csvOut.printf("%s,%s,%s,%s,%d,%s,%.2f,%.2f,%s,%d,%d,%b,%s%n",
                        d.getRecordId(), d.getSatelliteName(), d.getCountry(), d.getOrbitType(),
                        d.getLaunchYear(), d.getLaunchSite(), d.getLongitude(), d.getAvgLongitude(),
                        d.getGeohash(), d.getDaysOld(), d.getConjunctionCount(), d.isStillInOrbit(), d.getRiskLevel());
            }

            txtOut.printf("In-Orbit Debris Count: %d%n", inOrbit);
            txtOut.printf("Exited Debris Count: %d%n%n", exited);
            txtOut.println("Exited Debris Information:");
            for (SpaceObject d : exitedDebris) {
                txtOut.printf("ID: %s | Name: %s | Country: %s | Orbit: %s | Year: %d | Site: %s | Long: %.2f | AvgLong: %.2f | Geo: %s | DaysOld: %d%n",
                        d.getRecordId(), d.getSatelliteName(), d.getCountry(), d.getOrbitType(),
                        d.getLaunchYear(), d.getLaunchSite(), d.getLongitude(), d.getAvgLongitude(),
                        d.getGeohash(), d.getDaysOld());
            }

            System.out.println("Orbit assessment completed. Files generated.");
            SystemLog.log("Scientist assessed Debris orbit status.");
        } catch (IOException e) {
            System.err.println("Failed to write output: " + e.getMessage());
        }
    }

    /**
     * Displays the list of space objects.
     * @param list List of space objects to display.
     */
    private void displayObjects(List<SpaceObject> list) {
        for (SpaceObject obj : list) {
            System.out.printf("ID: %s | Name: %s | Country: %s | Orbit: %s | Year: %d | Site: %s | Long: %.2f | AvgLong: %.2f | Geo: %s | DaysOld: %d%n",
                    obj.getRecordId(), obj.getSatelliteName(), obj.getCountry(), obj.getOrbitType(),
                    obj.getLaunchYear(), obj.getLaunchSite(), obj.getLongitude(), obj.getAvgLongitude(),
                    obj.getGeohash(), obj.getDaysOld());
        }
    }
}
