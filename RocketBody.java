/**
 * The {@code RocketBody} class represents a rocket body object in space, extending the {@code SpaceObject} class.
 * It inherits properties such as record ID, satellite name, country, orbit type, and other attributes
 * from the {@code SpaceObject} class.
 */
public class RocketBody extends SpaceObject {

    /**
     * Constructs a new {@code RocketBody} object with the specified parameters.
     *
     * @param recordId          The unique identifier for the rocket body.
     * @param satelliteName     The name of the satellite associated with the rocket body.
     * @param country           The country responsible for the rocket body.
     * @param orbitType         The type of orbit the rocket body is in.
     * @param launchYear        The year the rocket body was launched.
     * @param launchSite        The site where the rocket body was launched.
     * @param longitude         The longitude of the rocket body.
     * @param avgLongitude      The average longitude of the rocket body.
     * @param geohash           The geohash location of the rocket body.
     * @param daysOld           The age of the rocket body in days.
     * @param conjunctionCount  The number of conjunction events involving the rocket body.
     */
    public RocketBody(String recordId, String satelliteName, String country, String orbitType,
                      int launchYear, String launchSite, double longitude,
                      double avgLongitude, String geohash, int daysOld, long conjunctionCount) {
        super(recordId, satelliteName, country, orbitType, launchYear,
              launchSite, longitude, avgLongitude, geohash, daysOld, conjunctionCount);
    }

    /**
     * Displays detailed information about the rocket body, including its properties and inherited attributes.
     */
    @Override
    public void displayInfo() {
        System.out.println("Rocket Body Information:");
        System.out.println("Record ID: " + getRecordId());
        System.out.println("Satellite Name: " + getSatelliteName());
        System.out.println("Country: " + getCountry());
        System.out.println("Orbit Type: " + getOrbitType());
        System.out.println("Launch Year: " + getLaunchYear());
        System.out.println("Launch Site: " + getLaunchSite());
        System.out.println("Longitude: " + getLongitude());
        System.out.println("Average Longitude: " + getAvgLongitude());
        System.out.println("Geohash: " + getGeohash());
        System.out.println("Days Old: " + getDaysOld());
        System.out.println("Conjunction Count: " + getConjunctionCount());
    }
}
