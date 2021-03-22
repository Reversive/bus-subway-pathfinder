package utils;

import java.util.Objects;

public class Stop {
    private String route;
    private final Double latitude;
    private final Double longitude;
    private final String stopName;
    StopType stopType;

    public Stop(String route, String stopName, Double latitude, Double longitude,StopType stopType) {
        this.route = route;
        this.latitude = latitude;
        this.longitude = longitude;
        this.stopName = stopName;
        this.stopType=stopType;
    }

    public String getStopName() {
        return stopName;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) { this.route = route; }

    public double distance(Stop busStop) {
        return HaversineDistance.distance(new Node(this), new Node(busStop));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stop busStop = (Stop) o;
        return  Objects.equals(route, busStop.route) &&
                Objects.equals(latitude, busStop.latitude) &&
                Objects.equals(longitude, busStop.longitude) &&
                Objects.equals(stopName, busStop.stopName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(route, latitude, longitude, stopName);
    }

    @Override
    public String toString() {
        return " Route: " + getRoute() + " Lat: " + getLatitude() + " Long: " + getLongitude();
    }
}
