public interface Satellite {
    String getRecordId();
    String getSatelliteName();
    String getCountry();
    String getOrbitType();
    int getLaunchYear();
    String getLaunchSite();
    double getLongitude();
    double getAvgLongitude();
    String getGeohash();
    int getDaysOld();
    long getConjunctionCount();

    void displayInfo();
}


