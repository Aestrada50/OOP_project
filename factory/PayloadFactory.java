/**
 * The {@code PayloadFactory} class is responsible for creating instances of {@code Payload}.
 * It extends the {@code SpaceObjectFactory} class and overrides the {@code create} method
 * to construct a {@code Payload} object using the provided data.
 */
package factory;

import model.Payload;
import model.SpaceObject;
import model.TrackingSystem;

import java.util.Map;

public class PayloadFactory extends SpaceObjectFactory {

    /**
     * Creates a new {@code Payload} object using the provided data map.
     *
     * @param data A map containing the data required to create a {@code Payload} object.
     *             The map should include the following keys:
     *             <ul>
     *                 <li>{@code record_id} - The unique identifier for the payload.</li>
     *                 <li>{@code satellite_name} - The name of the satellite associated with the payload.</li>
     *                 <li>{@code country} - The country responsible for the payload.</li>
     *                 <li>{@code orbit_type} - The type of orbit the payload is in.</li>
     *                 <li>{@code launch_year} - The year the payload was launched.</li>
     *                 <li>{@code launch_site} - The site where the payload was launched.</li>
     *                 <li>{@code longitude} - The longitude of the payload.</li>
     *                 <li>{@code avg_longitude} - The average longitude of the payload.</li>
     *                 <li>{@code geohash} - The geohash location of the payload.</li>
     *                 <li>{@code days_old} - The age of the payload in days.</li>
     *                 <li>{@code conjunction_count} - The number of conjunction events involving the payload.</li>
     *             </ul>
     * @return A new {@code Payload} object populated with the provided data.
     */
    @Override
    public SpaceObject create(Map<String, String> data) {
        return new Payload(
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
