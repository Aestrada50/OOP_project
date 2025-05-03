package model;

/**
 * The {@code SpaceObject} class is an abstract representation of a space object.
 * It implements the {@code Satellite} interface and provides common properties
 * and methods for all space objects, such as record ID, satellite name, country,
 * orbit type, and other attributes.
 */
public abstract class SpaceObject implements Satellite {
    private String recordId;
    private String satelliteName;
    private String country;
    private String orbitType;
    private int launchYear;
    private String launchSite;
    private double longitude;
    private double avgLongitude;
    private String geohash;
    private int daysOld;
    private long conjunctionCount;

    /**
     * Constructs a new {@code SpaceObject} with the specified parameters.
     *
     * @param recordId          The unique identifier for the space object.
     * @param satelliteName     The name of the satellite associated with the space object.
     * @param country           The country responsible for the space object.
     * @param orbitType         The type of orbit the space object is in.
     * @param launchYear        The year the space object was launched.
     * @param launchSite        The site where the space object was launched.
     * @param longitude         The longitude of the space object.
     * @param avgLongitude      The average longitude of the space object.
     * @param geohash           The geohash location of the space object.
     * @param daysOld           The age of the space object in days.
     * @param conjunctionCount  The number of conjunction events involving the space object.
     */
    public SpaceObject(String recordId, String satelliteName, String country, String orbitType,
                       int launchYear, String launchSite, double longitude,
                       double avgLongitude, String geohash, int daysOld, long conjunctionCount) {
        this.recordId = recordId;
        this.satelliteName = satelliteName;
        this.country = country;
        this.orbitType = orbitType;
        this.launchYear = launchYear;
        this.launchSite = launchSite;
        this.longitude = longitude;
        this.avgLongitude = avgLongitude;
        this.geohash = geohash;
        this.daysOld = daysOld;
        this.conjunctionCount = conjunctionCount;
    }

    /**
     * Gets the unique record ID of the space object.
     *
     * @return The record ID of the space object.
     */
    public String getRecordId() { return recordId; }

    /**
     * Sets the unique record ID of the space object.
     *
     * @param recordId The record ID to set.
     */
    public void setRecordId(String recordId) { this.recordId = recordId; }

    /**
     * Gets the name of the satellite associated with the space object.
     *
     * @return The satellite name.
     */
    public String getSatelliteName() { return satelliteName; }

    /**
     * Sets the name of the satellite associated with the space object.
     *
     * @param satelliteName The satellite name to set.
     */
    public void setSatelliteName(String satelliteName) { this.satelliteName = satelliteName; }

    /**
     * Gets the country responsible for the space object.
     *
     * @return The country responsible for the space object.
     */
    public String getCountry() { return country; }

    /**
     * Sets the country responsible for the space object.
     *
     * @param country The country to set.
     */
    public void setCountry(String country) { this.country = country; }

    /**
     * Gets the type of orbit the space object is in.
     *
     * @return The orbit type of the space object.
     */
    public String getOrbitType() { return orbitType; }

    /**
     * Sets the type of orbit the space object is in.
     *
     * @param orbitType The orbit type to set.
     */
    public void setOrbitType(String orbitType) { this.orbitType = orbitType; }

    /**
     * Gets the year the space object was launched.
     *
     * @return The launch year of the space object.
     */
    public int getLaunchYear() { return launchYear; }

    /**
     * Sets the year the space object was launched.
     *
     * @param launchYear The launch year to set.
     */
    public void setLaunchYear(int launchYear) { this.launchYear = launchYear; }

    /**
     * Gets the site where the space object was launched.
     *
     * @return The launch site of the space object.
     */
    public String getLaunchSite() { return launchSite; }

    /**
     * Sets the site where the space object was launched.
     *
     * @param launchSite The launch site to set.
     */
    public void setLaunchSite(String launchSite) { this.launchSite = launchSite; }

    /**
     * Gets the current longitude of the space object.
     *
     * @return The longitude of the space object.
     */
    public double getLongitude() { return longitude; }

    /**
     * Sets the current longitude of the space object.
     *
     * @param longitude The longitude to set.
     */
    public void setLongitude(double longitude) { this.longitude = longitude; }

    /**
     * Gets the average longitude of the space object.
     *
     * @return The average longitude of the space object.
     */
    public double getAvgLongitude() { return avgLongitude; }

    /**
     * Sets the average longitude of the space object.
     *
     * @param avgLongitude The average longitude to set.
     */
    public void setAvgLongitude(double avgLongitude) { this.avgLongitude = avgLongitude; }

    /**
     * Gets the geohash location of the space object.
     *
     * @return The geohash location of the space object.
     */
    public String getGeohash() { return geohash; }

    /**
     * Sets the geohash location of the space object.
     *
     * @param geohash The geohash to set.
     */
    public void setGeohash(String geohash) { this.geohash = geohash; }

    /**
     * Gets the age of the space object in days.
     *
     * @return The age of the space object in days.
     */
    public int getDaysOld() { return daysOld; }

    /**
     * Sets the age of the space object in days.
     *
     * @param daysOld The age in days to set.
     */
    public void setDaysOld(int daysOld) { this.daysOld = daysOld; }

    /**
     * Gets the number of conjunction events involving the space object.
     *
     * @return The conjunction count of the space object.
     */
    public long getConjunctionCount() { return conjunctionCount; }

    /**
     * Sets the number of conjunction events involving the space object.
     *
     * @param conjunctionCount The conjunction count to set.
     */
    public void setConjunctionCount(long conjunctionCount) { this.conjunctionCount = conjunctionCount; }

    /**
     * Displays detailed information about the space object.
     * This method must be implemented by subclasses.
     */
    public abstract void displayInfo();
}
