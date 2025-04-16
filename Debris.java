public class Debris extends SpaceObject{
    private boolean stillInOrbit;
    private String riskLevel;

    public Debris(String recordId, String satelliteName, String country, String orbitType,
                  int launchYear, String launchSite, double longitude,
                  double avgLongitude, String geohash, int daysOld, long conjunctionCount) {
        super(recordId, satelliteName, country, orbitType, launchYear,
              launchSite, longitude, avgLongitude, geohash, daysOld, conjunctionCount);
    }

    public boolean isStillInOrbit() {
        return stillInOrbit;
    }

    public void setStillInOrbit(boolean stillInOrbit) {
        this.stillInOrbit = stillInOrbit;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

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
