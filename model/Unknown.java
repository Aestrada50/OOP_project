/**
 * The {@code Unknown} class represents a space object of an unknown type.
 * It extends the {@code SpaceObject} class and inherits its properties and methods.
 */
public class Unknown extends SpaceObject {

    /**
     * Constructs a new {@code Unknown} object with the specified parameters.
     *
     * @param recordId          The unique identifier for the unknown object.
     * @param satelliteName     The name of the satellite associated with the unknown object.
     * @param country           The country responsible for the unknown object.
     * @param orbitType         The type of orbit the unknown object is in.
     * @param launchYear        The year the unknown object was launched.
     * @param launchSite        The site where the unknown object was launched.
     * @param longitude         The longitude of the unknown object.
     * @param avgLongitude      The average longitude of the unknown object.
     * @param geohash           The geohash location of the unknown object.
     * @param daysOld           The age of the unknown object in days.
     * @param conjunctionCount  The number of conjunction events involving the unknown object.
     */
    public Unknown(String recordId, String satelliteName, String country, String orbitType,
                   int launchYear, String launchSite, double longitude,
                   double avgLongitude, String geohash, int daysOld, long conjunctionCount) {
        super(recordId, satelliteName, country, orbitType, launchYear,
              launchSite, longitude, avgLongitude, geohash, daysOld, conjunctionCount);
    }

    /**
     * Displays detailed information about the unknown object, including its properties
     * and inherited attributes.
     */
    @Override
    public void displayInfo() {
        System.out.println("Unknown Object Information:");
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
