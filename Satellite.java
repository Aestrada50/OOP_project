/**
 * The {@code Satellite} interface defines the contract for satellite-related objects.
 * It provides methods to retrieve satellite properties and display information.
 */
public interface Satellite {

    /**
     * Gets the unique record ID of the satellite.
     *
     * @return The record ID of the satellite.
     */
    String getRecordId();

    /**
     * Gets the name of the satellite.
     *
     * @return The name of the satellite.
     */
    String getSatelliteName();

    /**
     * Gets the country responsible for the satellite.
     *
     * @return The country responsible for the satellite.
     */
    String getCountry();

    /**
     * Gets the type of orbit the satellite is in.
     *
     * @return The orbit type of the satellite.
     */
    String getOrbitType();

    /**
     * Gets the year the satellite was launched.
     *
     * @return The launch year of the satellite.
     */
    int getLaunchYear();

    /**
     * Gets the site where the satellite was launched.
     *
     * @return The launch site of the satellite.
     */
    String getLaunchSite();

    /**
     * Gets the current longitude of the satellite.
     *
     * @return The longitude of the satellite.
     */
    double getLongitude();

    /**
     * Gets the average longitude of the satellite.
     *
     * @return The average longitude of the satellite.
     */
    double getAvgLongitude();

    /**
     * Gets the geohash location of the satellite.
     *
     * @return The geohash location of the satellite.
     */
    String getGeohash();

    /**
     * Gets the age of the satellite in days.
     *
     * @return The age of the satellite in days.
     */
    int getDaysOld();

    /**
     * Gets the number of conjunction events involving the satellite.
     *
     * @return The conjunction count of the satellite.
     */
    long getConjunctionCount();

    /**
     * Displays detailed information about the satellite.
     */
    void displayInfo();
}


