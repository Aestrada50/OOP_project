public class Satellite extends SpaceObject {

    public Satellite(String recordId, String satelliteName, String country, String orbitType,
                     int launchYear, String launchSite, double longitude,
                     double avgLongitude, String geohash, int daysOld, long conjunctionCount) {
        super(recordId, satelliteName, country, orbitType, launchYear,
              launchSite, longitude, avgLongitude, geohash, daysOld, conjunctionCount);
    }

    @Override
    public void displayInfo() {
        System.out.println("Satellite Information:"
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
                + "\nConjunction Count: " + getConjunctionCount());
    }
}

