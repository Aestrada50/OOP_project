package factory;
import model.SpaceObject;
import model.Unknown;
import java.util.Map;

public class UnknownFactory extends SpaceObjectFactory {
    @Override
    public SpaceObject create(Map<String, String> data) {
        return new Unknown(
            data.get("record_id"), data.get("satellite_name"), data.get("country"), data.get("orbit_type"),
            Integer.parseInt(data.get("launch_year")), data.get("launch_site"),
            Double.parseDouble(data.get("longitude")), Double.parseDouble(data.get("avg_longitude")),
            data.get("geohash"), Integer.parseInt(data.get("days_old")), Long.parseLong(data.get("conjunction_count"))
        );
    }
}

