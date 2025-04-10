public abstract class SpaceObject {
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

    // Getters and setters
    public String getRecordId() { return recordId; }
    public void setRecordId(String recordId) { this.recordId = recordId; }

    public String getSatelliteName() { return satelliteName; }
    public void setSatelliteName(String satelliteName) { this.satelliteName = satelliteName; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getOrbitType() { return orbitType; }
    public void setOrbitType(String orbitType) { this.orbitType = orbitType; }

    public int getLaunchYear() { return launchYear; }
    public void setLaunchYear(int launchYear) { this.launchYear = launchYear; }

    public String getLaunchSite() { return launchSite; }
    public void setLaunchSite(String launchSite) { this.launchSite = launchSite; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    public double getAvgLongitude() { return avgLongitude; }
    public void setAvgLongitude(double avgLongitude) { this.avgLongitude = avgLongitude; }

    public String getGeohash() { return geohash; }
    public void setGeohash(String geohash) { this.geohash = geohash; }

    public int getDaysOld() { return daysOld; }
    public void setDaysOld(int daysOld) { this.daysOld = daysOld; }

    public long getConjunctionCount() { return conjunctionCount; }
    public void setConjunctionCount(long conjunctionCount) { this.conjunctionCount = conjunctionCount; }

    // Abstract method
    public abstract void displayInfo();
}
