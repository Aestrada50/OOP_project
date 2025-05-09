/**
 * The {@code RocketBodyFactory} class is responsible for creating instances of {@code RocketBody}.
 * It extends the {@code SpaceObjectFactory} class and overrides the {@code create} method
 * to construct a {@code RocketBody} object using the provided data.
 */
package factory;

import model.RocketBody;
import model.SpaceObject;
import model.TrackingSystem;

import java.util.Map;

public class RocketBodyFactory extends SpaceObjectFactory {

    /**
     * Creates a new {@code RocketBody} object using the provided data map.
     *
     * @param data A map containing the data required to create a {@code RocketBody} object.
     *             The map should include the following keys:
     *             <ul>
     *                 <li>{@code record_id} - The unique identifier for the rocket body.</li>
     *                 <li>{@code satellite_name} - The name of the satellite associated with the rocket body.</li>
     *                 <li>{@code country} - The country responsible for the rocket body.</li>
     *                 <li>{@code approximate_orbit_type} - The type of orbit the rocket body is in.</li>
     *                 <li>{@code launch_year} - The year the rocket body was launched.</li>
     *                 <li>{@code launch_site} - The site where the rocket body was launched.</li>
     *                 <li>{@code longitude} - The longitude of the rocket body.</li>
     *                 <li>{@code avg_longitude} - The average longitude of the rocket body.</li>
     *                 <li>{@code geohash} - The geohash location of the rocket body.</li>
     *                 <li>{@code days_old} - The age of the rocket body in days.</li>
     *                 <li>{@code conjunction_count} - The number of conjunction events involving the rocket body.</li>
     *             </ul>
     * @return A new {@code RocketBody} object populated with the provided data.
     */
    @Override
    public SpaceObject create(Map<String, String> data) {
        return new RocketBody(
            data.get("?record_id"),
            data.get("satellite_name"),
            data.get("country"),
            data.get("approximate_orbit_type"),
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
