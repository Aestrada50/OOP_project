public class Payload extends SpaceObject implements Satellite {

    public Payload(String recordId, String satelliteName, String country, String orbitType,
                   int launchYear, String launchSite, double longitude,
                   double avgLongitude, String geohash, int daysOld, long conjunctionCount) {
        super(recordId, satelliteName, country, orbitType, launchYear,
              launchSite, longitude, avgLongitude, geohash, daysOld, conjunctionCount);
    }

    @Override
    public void displayInfo() {
        System.out.println("Payload Information:");
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
