/**
 * The {@code Debris} class represents a piece of space debris, extending the {@code SpaceObject} class.
 * It includes additional properties such as whether the debris is still in orbit and its risk level.
 */
public class Debris extends SpaceObject {
    private boolean stillInOrbit;
    private String riskLevel;

    /**
     * Constructs a new {@code Debris} object with the specified parameters.
     *
     * @param recordId          The unique identifier for the debris.
     * @param satelliteName     The name of the satellite associated with the debris.
     * @param country           The country responsible for the debris.
     * @param orbitType         The type of orbit the debris is in.
     * @param launchYear        The year the debris was launched.
     * @param launchSite        The site where the debris was launched.
     * @param longitude         The longitude of the debris.
     * @param avgLongitude      The average longitude of the debris.
     * @param geohash           The geohash location of the debris.
     * @param daysOld           The age of the debris in days.
     * @param conjunctionCount  The number of conjunction events involving the debris.
     */
    public Debris(String recordId, String satelliteName, String country, String orbitType,
                  int launchYear, String launchSite, double longitude,
                  double avgLongitude, String geohash, int daysOld, long conjunctionCount) {
        super(recordId, satelliteName, country, orbitType, launchYear,
              launchSite, longitude, avgLongitude, geohash, daysOld, conjunctionCount);
    }

    /**
     * Checks if the debris is still in orbit.
     *
     * @return {@code true} if the debris is still in orbit, {@code false} otherwise.
     */
    public boolean isStillInOrbit() {
        return stillInOrbit;
    }

    /**
     * Sets whether the debris is still in orbit.
     *
     * @param stillInOrbit {@code true} if the debris is still in orbit, {@code false} otherwise.
     */
    public void setStillInOrbit(boolean stillInOrbit) {
        this.stillInOrbit = stillInOrbit;
    }

    /**
     * Gets the risk level of the debris.
     *
     * @return The risk level of the debris.
     */
    public String getRiskLevel() {
        return riskLevel;
    }

    /**
     * Sets the risk level of the debris.
     *
     * @param riskLevel The risk level to set for the debris.
     */
    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    /**
     * Displays detailed information about the debris, including its properties and inherited attributes.
     */
    @Override
    public void displayInfo() {
        System.out.println("Debris Information:"
                + "\nRecord ID: " + getRecordId()
                + "\nSatellite Name: " + getSatelliteName()
                + "\nCountry: " + getCountry()
                + "\nOrbit Type: " + getOrbitType()
                + "\nLaunch Year: " + getLaunchYear()
                + "\nLaunch Site: " + getLaunchSite()
                + "\nLongitude: " + getLongitude()
                + "\nAverage Longitude: " + getAvgLongitude()
                + "\nGeohash: " + getGeohash()
                + "\nDays Old: " + getDaysOld()
                + "\nConjunction Count: " + getConjunctionCount()
                + "\nStill In Orbit: " + (stillInOrbit ? "Yes" : "No")
                + "\nRisk Level: " + riskLevel);
    }
}
