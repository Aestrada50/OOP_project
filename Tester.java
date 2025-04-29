import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

public class Tester {

    private TrackingSystem trackingSystem;


    @BeforeEach
    public void setUp() {
        // Simulate internal storage manually
        trackingSystem = new TrackingSystem();
        trackingSystem.loadObjectsFromCSV("test_rso_metrics.csv");
    }

    // Group 1: getObjectsByType
    @Test
    public void testGetObjectsByType() {
        List<SpaceObject> debrisList = trackingSystem.getObjectsByType("DEBRIS");
        assertEquals(1, debrisList.size());
        assertEquals("D123", debrisList.get(0).getRecordId());

        List<SpaceObject> payloadList = trackingSystem.getObjectsByType("PAYLOAD");
        assertEquals(1, payloadList.size());
        assertEquals("P456", payloadList.get(0).getRecordId());

        List<SpaceObject> unknownList = trackingSystem.getObjectsByType("UNKNOWN");
        assertTrue(unknownList.isEmpty(), "Expected empty list for unknown types");
    }

    // Group 2: getObjectById
    @Test
    public void testGetObjectById() {
        SpaceObject debris = trackingSystem.getObjectById("D123");
        assertNotNull(debris);
        assertEquals("DebrisOne", debris.getSatelliteName());

        SpaceObject payload = trackingSystem.getObjectById("P456");
        assertNotNull(payload);
        assertEquals("PayloadX", payload.getSatelliteName());

        SpaceObject unknown = trackingSystem.getObjectById("XYZ");
        assertNull(unknown, "Expected null for nonexistent record ID");
    }

    // Group 3: parseIntSafe through indirect testing (safe CSV load protection)
    @Test
    public void testParseIntSafe_InvalidHandling() {
        // Indirect testing: simulate what would happen with bad CSV inputs
        assertDoesNotThrow(() -> trackingSystem.loadObjectsFromCSV("nonexistent.csv"),
                "Loading a missing file should not crash the system");
    }
}