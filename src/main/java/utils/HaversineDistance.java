package utils;

public class HaversineDistance {
    private static final int EARTH_RADIUS = 6371000; //RADIO en METROS

    public static double distance(Node start, Node end) {

        double dLat  = Math.toRadians((end.getLatitude() - start.getLatitude()));
        double dLong = Math.toRadians((end.getLongitude() - start.getLongitude()));

        double startLat = Math.toRadians(start.getLatitude());
        double endLat   = Math.toRadians(end.getLatitude());

        double a = haversin(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversin(dLong);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }

    public static double haversin(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }
}
