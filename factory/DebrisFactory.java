package factory;
import model.Debris;
import model.SpaceObject;
import model.TrackingSystem;

import java.util.Map;

public class DebrisFactory extends SpaceObjectFactory {
    @Override
    public SpaceObject create(Map<String, String> data) {
        return new Debris(
            data.get("record_id"),
            data.get("satellite_name"),
            data.get("country"),
            data.get("orbit_type"),
            TrackingSystem.parseIntSafe(data.get("launch_year"), 0),
            data.get("launch_site"),
            TrackingSystem.parseDoubleSafe(data.get("longitude"), 0.0),
            TrackingSystem.parseDoubleSafe(data.get("avg_longitude"), 0.0),
            data.get("geohash"),
            TrackingSystem.parseIntSafe(data.get("days_old"), 0),
            TrackingSystem.parseLongSafe(data.get("conjunction_count"), 0L)
        );
    }
}
