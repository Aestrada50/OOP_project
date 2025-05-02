import java.io.*;
import java.util.*;
import factory.SpaceObjectFactory;
import model.SpaceObject;

/**
 * The {@code TrackingSystem} class is responsible for managing and tracking space objects.
 * It provides functionality to load space object data from a CSV file, retrieve objects
 * by type or ID, and store them in appropriate data structures.
 */
public class TrackingSystem {

    private Map<String, List<SpaceObject>> objectTypeMap;
    private Map<String, SpaceObject> objectById;

    /**
     * Constructs a new {@code TrackingSystem} instance.
     * Initializes the data structures for storing space objects.
     */
    public TrackingSystem() {
        objectTypeMap = new HashMap<>();
        objectById = new HashMap<>();
    }

    /**
     * Loads space object data from a CSV file and populates the internal data structures.
     *
     * @param filename The name of the CSV file containing space object data.
     */
    // Updated loadObjectsFromCSV() in TrackingSystem
public void loadObjectsFromCSV(String filename) {
    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
        String[] headers = br.readLine().split(",");
        Map<String, Integer> headerMap = new HashMap<>();
        for (int i = 0; i < headers.length; i++) {
            headerMap.put(headers[i].trim().toLowerCase(), i);
        }

        String line;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",", -1);
            Map<String, String> row = new HashMap<>();
            for (Map.Entry<String, Integer> entry : headerMap.entrySet()) {
                row.put(entry.getKey(), values[entry.getValue()]);
            }

            String objectType = row.get("object_type");
            if (objectType == null) continue;

            SpaceObject obj = SpaceObjectFactory.getFactory(objectType).create(row);
            objectTypeMap.computeIfAbsent(objectType.toUpperCase(), k -> new ArrayList<>()).add(obj);
            objectById.put(obj.getRecordId(), obj);
        }

        System.out.println("Loaded space object data successfully.");
    } catch (IOException e) {
        System.err.println("Error reading file: " + e.getMessage());
    }
}


    /**
     * Retrieves a list of space objects by their type.
     *
     * @param type The type of space objects to retrieve (e.g., "DEBRIS", "PAYLOAD").
     * @return A list of space objects of the specified type, or an empty list if none exist.
     */
    public List<SpaceObject> getObjectsByType(String type) {
        return objectTypeMap.getOrDefault(type.toUpperCase(), new ArrayList<>());
    }

    /**
     * Retrieves a space object by its unique record ID.
     *
     * @param recordId The unique record ID of the space object.
     * @return The space object with the specified record ID, or {@code null} if not found.
     */
    public SpaceObject getObjectById(String recordId) {
        return objectById.get(recordId);
    }

    /**
     * Safely parses a string into an integer.
     *
     * @param val The string to parse.
     * @return The parsed integer, or 0 if parsing fails.
     */
    private int parseIntSafe(String val) {
        try {
            return Integer.parseInt(val.trim());
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Safely parses a string into a long.
     *
     * @param val The string to parse.
     * @return The parsed long, or 0L if parsing fails.
     */
    private long parseLongSafe(String val) {
        try {
            return Long.parseLong(val.trim());
        } catch (Exception e) {
            return 0L;
        }
    }

    /**
     * Safely parses a string into a double.
     *
     * @param val The string to parse.
     * @return The parsed double, or 0.0 if parsing fails.
     */
    private double parseDoubleSafe(String val) {
        try {
            return Double.parseDouble(val.trim());
        } catch (Exception e) {
            return 0.0;
        }
    }
}
