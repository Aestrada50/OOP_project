import java.util.*;
import java.io.*;

public class ScientistMenu implements UserMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private TrackingSystem trackingSystem;

    public ScientistMenu(TrackingSystem trackingSystem) {
        this.trackingSystem = trackingSystem;
    }

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
                    trackObjectsMenu();
                    break;
                case 2:
                    assessOrbitStatusMenu();
                    break;
                case 3:
                    System.out.println("Exiting Scientist Menu.");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void trackObjectsMenu() {
        while (true) {
            System.out.println("\n--- Track Objects in Space ---");
            System.out.println("1. Rocket Body");
            System.out.println("2. Debris");
            System.out.println("3. Payload");
            System.out.println("4. Unknown");
            System.out.println("5. Back");
            System.out.print("Select an option: ");
            int choice = getInput();
            String[] types = {"ROCKET BODY", "DEBRIS", "PAYLOAD", "UNKNOWN"};
            if (choice >= 1 && choice <= 4) {
                List<SpaceObject> objects = trackingSystem.getObjectsByType(types[choice - 1]);
                displayObjects(objects);
            } else if (choice == 5) return;
            else System.out.println("Invalid option. Try again.");
        }
    }

    private void assessOrbitStatusMenu() {
        while (true) {
            System.out.println("\n--- Assess Orbit Status ---");
            System.out.println("1. Track Objects in LEO");
            System.out.println("2. Assess if debris is still in orbit");
            System.out.println("3. Back");
            System.out.print("Select an option: ");
            int choice = getInput();
            switch (choice) {
                case 1:
                    List<SpaceObject> leoObjects = new ArrayList<>();
                    for (String type : new String[]{"DEBRIS", "PAYLOAD", "ROCKET BODY", "UNKNOWN"}) {
                        for (SpaceObject obj : trackingSystem.getObjectsByType(type)) {
                            if ("LEO".equalsIgnoreCase(obj.getOrbitType())) leoObjects.add(obj);
                        }
                    }
                    displayObjects(leoObjects);
                    break;
                case 2:
                    assessDebrisOrbitStatus();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

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
        } catch (IOException e) {
            System.err.println("Failed to write output: " + e.getMessage());
        }
    }

    private void displayObjects(List<SpaceObject> list) {
        for (SpaceObject obj : list) {
            System.out.printf("ID: %s | Name: %s | Country: %s | Orbit: %s | Year: %d | Site: %s | Long: %.2f | AvgLong: %.2f | Geo: %s | DaysOld: %d%n",
                    obj.getRecordId(), obj.getSatelliteName(), obj.getCountry(), obj.getOrbitType(),
                    obj.getLaunchYear(), obj.getLaunchSite(), obj.getLongitude(), obj.getAvgLongitude(),
                    obj.getGeohash(), obj.getDaysOld());
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
