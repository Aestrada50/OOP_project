package model;

import java.io.*;
import java.util.*;
import factory.SpaceObjectFactory;

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
     * Handles arbitrary column order using header mapping.
     *
     * @param filename The name of the CSV file containing space object data.
     */
    public void loadObjectsFromCSV(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String[] headers = br.readLine().split(",");
            Map<String, Integer> headerMap = new HashMap<>();

            // 🧼 Normalize headers (remove ?, quotes, normalize underscores)
            for (int i = 0; i < headers.length; i++) {
                String cleanKey = headers[i].trim().toLowerCase()
                    .replace("?", "")
                    .replace("\"", "")
                    .replace(" ", "_")
                    .replaceAll("[^a-z0-9_]", "");
                headerMap.put(cleanKey, i);
            }

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",", -1);
                Map<String, String> row = new HashMap<>();

                for (Map.Entry<String, Integer> entry : headerMap.entrySet()) {
                    int index = entry.getValue();
                    String value = (index < values.length) ? values[index].trim() : "";
                    row.put(entry.getKey(), value);
                }

                String objectType = row.get("object_type");
                if (objectType == null || objectType.isEmpty()) {
                    System.out.println("Skipping row: missing object_type");
                    continue;
                }

                try {
                    SpaceObject obj = SpaceObjectFactory.getFactory(objectType).create(row);
                    objectTypeMap.computeIfAbsent(objectType.toUpperCase(), k -> new ArrayList<>()).add(obj);
                    objectById.put(obj.getRecordId(), obj);

                    // Optional debug:
                    if (obj.getRecordId() == null) {
                        System.err.println("⚠️ Object created without record_id! Check factory parsing.");
                    }
                } catch (Exception e) {
                    System.err.println("Error processing row: " + Arrays.toString(values));
                    e.printStackTrace();
                }
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

    public static int parseIntSafe(String val, int defaultValue) {
        try {
            return Integer.parseInt(val.trim());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static long parseLongSafe(String val, long defaultValue) {
        try {
            return Long.parseLong(val.trim());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static double parseDoubleSafe(String val, double defaultValue) {
        try {
            return Double.parseDouble(val.trim());
        } catch (Exception e) {
            return defaultValue;
        }
    }
}
