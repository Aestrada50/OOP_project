/**
 * The {@code UnknownFactory} class is responsible for creating instances of {@code Unknown}.
 * It extends the {@code SpaceObjectFactory} class and overrides the {@code create} method
 * to construct an {@code Unknown} object using the provided data.
 */
package factory;

import model.Unknown;
import model.SpaceObject;
import model.TrackingSystem;

import java.util.Map;

public class UnknownFactory extends SpaceObjectFactory {

    /**
     * Creates a new {@code Unknown} object using the provided data map.
     *
     * @param data A map containing the data required to create an {@code Unknown} object.
     *             The map should include the following keys:
     *             <ul>
     *                 <li>{@code record_id} - The unique identifier for the unknown object.</li>
     *                 <li>{@code satellite_name} - The name of the satellite associated with the unknown object.</li>
     *                 <li>{@code country} - The country responsible for the unknown object.</li>
     *                 <li>{@code approximate_orbit_type} - The type of orbit the unknown object is in.</li>
     *                 <li>{@code launch_year} - The year the unknown object was launched.</li>
     *                 <li>{@code launch_site} - The site where the unknown object was launched.</li>
     *                 <li>{@code longitude} - The longitude of the unknown object.</li>
     *                 <li>{@code avg_longitude} - The average longitude of the unknown object.</li>
     *                 <li>{@code geohash} - The geohash location of the unknown object.</li>
     *                 <li>{@code days_old} - The age of the unknown object in days.</li>
     *                 <li>{@code conjunction_count} - The number of conjunction events involving the unknown object.</li>
     *             </ul>
     * @return A new {@code Unknown} object populated with the provided data.
     */
    @Override
    public SpaceObject create(Map<String, String> data) {
        return new Unknown(
            data.get("record_id"),
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
