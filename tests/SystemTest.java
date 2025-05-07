package tests;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import factory.DebrisFactory;
import factory.SpaceObjectFactory;
import model.Debris;
import model.SpaceObject;
import model.TrackingSystem;
import java.util.*;

public class SystemTest {

    private TrackingSystem trackingSystem;
    private DebrisFactory debrisFactory;
    private SpaceObjectFactory spaceObjectFactory;
    private SpaceObject obj;
    Map<String, String> data;

    @BeforeEach
    public void setUp() {
        // Simulate internal storage manually
        trackingSystem = new TrackingSystem();
        trackingSystem.loadObjectsFromCSV("tests/test_rso_metrics.csv");
        debrisFactory = new DebrisFactory();
        data = new HashMap<>();
        
        
    }

    @AfterEach
    public void tearDown() {
        trackingSystem = null;
        debrisFactory = null;
        obj = null;
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


    @Test
    public void testParseIntSafe_InvalidHandling() {
        assertDoesNotThrow(() -> trackingSystem.loadObjectsFromCSV("nonexistent.csv"),
                "Loading a missing file should not crash the system");
    }

    // Testing debris Factory creatiion to ensure data is parsed correctly
    @Test
    public void testDebrisCreationFromCSV() {
        // Simulate parsed CSV row for D123
        
        data.put("record_id", "D123");
        data.put("satellite_name", "DebrisOne");
        data.put("country", "USA");
        data.put("orbit_type", "LEO");
        data.put("launch_year", "2000");
        data.put("launch_site", "AFETR");
        data.put("longitude", "45.0");
        data.put("avg_longitude", "46.0");
        data.put("geohash", "geohash1");
        data.put("days_old", "5000");
        data.put("conjunction_count", "3");

        obj = debrisFactory.create(data);

        // Assert it's a Debris instance with expected values
        assertTrue(obj instanceof Debris);
        assertEquals("D123", obj.getRecordId());
        assertEquals("DebrisOne", obj.getSatelliteName());
        assertEquals("USA", obj.getCountry());
        assertEquals("LEO", obj.getOrbitType());
        assertEquals(2000, obj.getLaunchYear());
        assertEquals("AFETR", obj.getLaunchSite());
        assertEquals(45.0, obj.getLongitude());
        assertEquals(46.0, obj.getAvgLongitude());
        assertEquals("geohash1", obj.getGeohash());
        assertEquals(5000, obj.getDaysOld());
        assertEquals(3, obj.getConjunctionCount());
    }

    @Test
    public void testDebrisFactoryViaDispatcher() {
        Map<String, String> data = new HashMap<>();
        data.put("record_id", "D123");
        data.put("satellite_name", "DebrisOne");
        data.put("country", "USA");
        data.put("orbit_type", "LEO");
        data.put("launch_year", "2000");
        data.put("launch_site", "AFETR");
        data.put("longitude", "45.0");
        data.put("avg_longitude", "46.0");
        data.put("geohash", "geohash1");
        data.put("days_old", "5000");
        data.put("conjunction_count", "3");

        spaceObjectFactory = SpaceObjectFactory.getFactory("DEBRIS");
        assertNotNull(spaceObjectFactory);

        obj = spaceObjectFactory.create(data);

        assertTrue(obj instanceof Debris, "Expected Debris instance");

        assertEquals("D123", obj.getRecordId());
        assertEquals("DebrisOne", obj.getSatelliteName());
        assertEquals("USA", obj.getCountry());
        assertEquals("LEO", obj.getOrbitType());
        assertEquals(2000, obj.getLaunchYear());
        assertEquals("AFETR", obj.getLaunchSite());
        assertEquals(45.0, obj.getLongitude());
        assertEquals(46.0, obj.getAvgLongitude());
        assertEquals("geohash1", obj.getGeohash());
        assertEquals(5000, obj.getDaysOld());
        assertEquals(3, obj.getConjunctionCount());
    }

}