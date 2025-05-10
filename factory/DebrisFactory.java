/**
 * The {@code DebrisFactory} class is responsible for creating instances of {@code Debris}.
 * It extends the {@code SpaceObjectFactory} class and overrides the {@code create} method
 * to construct a {@code Debris} object using the provided data.
 */
package factory;

import model.Debris;
import model.SpaceObject;
import model.TrackingSystem;

import java.util.Map;

public class DebrisFactory extends SpaceObjectFactory {

    /**
     * Creates a new {@code Debris} object using the provided data map.
     *
     * @param data A map containing the data required to create a {@code Debris} object.
     *             The map should include the following keys:
     *             <ul>
     *                 <li>{@code record_id} - The unique identifier for the debris.</li>
     *                 <li>{@code satellite_name} - The name of the satellite associated with the debris.</li>
     *                 <li>{@code country} - The country responsible for the debris.</li>
     *                 <li>{@code approximate_orbit_type} - The type of orbit the debris is in.</li>
     *                 <li>{@code launch_year} - The year the debris was launched.</li>
     *                 <li>{@code launch_site} - The site where the debris was launched.</li>
     *                 <li>{@code longitude} - The longitude of the debris.</li>
     *                 <li>{@code avg_longitude} - The average longitude of the debris.</li>
     *                 <li>{@code geohash} - The geohash location of the debris.</li>
     *                 <li>{@code days_old} - The age of the debris in days.</li>
     *                 <li>{@code conjunction_count} - The number of conjunction events involving the debris.</li>
     *             </ul>
     * @return A new {@code Debris} object populated with the provided data.
     */
    @Override
    public SpaceObject create(Map<String, String> data) {
        return new Debris(
            data.get("record_id"),
            data.get("satellite_name"),
            data.get("country"),
            data.get("approximate_orbit_type"), 
            TrackingSystem.parseIntSafe(data.get("launch_year"), 0),
            data.get("launch_site"),
            TrackingSystem.parseDoubleSafe(data.get("longitude"), 0),
            TrackingSystem.parseDoubleSafe(data.get("avg_longitude"), 0),
            data.get("geohash"),
            TrackingSystem.parseIntSafe(data.get("days_old"), 0),
            TrackingSystem.parseLongSafe(data.get("conjunction_count"), 0)
        );
    }
}
