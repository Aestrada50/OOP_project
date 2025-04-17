import java.io.*;
import java.util.*;

public class TrackingSystem {

    private Map<String, List<SpaceObject>> objectTypeMap;
    private Map<String, SpaceObject> objectById;
    private File file = new File("rso_metrics.csv");

    public TrackingSystem() {
        objectTypeMap = new HashMap<>();
        objectById = new HashMap<>();
        loadObjectsFromCSV(file);
    }

    public void loadObjectsFromCSV(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", -1); // -1 keeps empty values

                if (parts.length < 20) continue; // Skip malformed rows

                String objectType = parts[5].trim().toUpperCase(); // "DEBRIS", "PAYLOAD", etc.
                String recordId = parts[0].trim();
                String satelliteName = parts[2].trim();
                String country = parts[3].trim();
                String orbitType = parts[4].trim();
                int launchYear = parseIntSafe(parts[6]);
                String launchSite = parts[7].trim();
                double longitude = parseDoubleSafe(parts[8]);
                double avgLongitude = parseDoubleSafe(parts[9]);
                String geohash = parts[10].trim();
                int daysOld = parseIntSafe(parts[18]);
                long conjunctionCount = parseLongSafe(parts[19]);

                SpaceObject obj;

                switch (objectType) {
                    case "DEBRIS":
                        obj = new Debris(recordId, satelliteName, country, orbitType,
                                launchYear, launchSite, longitude, avgLongitude, geohash, daysOld, conjunctionCount);
                        break;
                    case "PAYLOAD":
                        obj = new Payload(recordId, satelliteName, country, orbitType,
                                launchYear, launchSite, longitude, avgLongitude, geohash, daysOld, conjunctionCount);
                        break;
                    case "ROCKET BODY":
                        obj = new RocketBody(recordId, satelliteName, country, orbitType,
                                launchYear, launchSite, longitude, avgLongitude, geohash, daysOld, conjunctionCount);
                        break;
                    default:
                        obj = new Unknown(recordId, satelliteName, country, orbitType,
                                launchYear, launchSite, longitude, avgLongitude, geohash, daysOld, conjunctionCount);
                        break;
                }

                objectTypeMap.computeIfAbsent(objectType, k -> new ArrayList<>()).add(obj);
                objectById.put(recordId, obj);
            }
            System.out.println("Loaded space object data successfully.");
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    // Getter
    public List<SpaceObject> getObjectsByType(String type) {
        return objectTypeMap.getOrDefault(type.toUpperCase(), new ArrayList<>());
    }

    public SpaceObject getObjectById(String recordId) {
        return objectById.get(recordId);
    }

    // Helper functions
    private int parseIntSafe(String val) {
        try {
            return Integer.parseInt(val.trim());
        } catch (Exception e) {
            return 0;
        }
    }

    private long parseLongSafe(String val) {
        try {
            return Long.parseLong(val.trim());
        } catch (Exception e) {
            return 0L;
        }
    }

    private double parseDoubleSafe(String val) {
        try {
            return Double.parseDouble(val.trim());
        } catch (Exception e) {
            return 0.0;
        }
    }
}
