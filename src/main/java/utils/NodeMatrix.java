package utils;

import java.util.List;

public class NodeMatrix {
    final int ROW = 100;
    final int COL = 100;
    double minLat = 0 , minLon = 0 , maxLat = 0 , maxLon = 0 ;
    private final NodeSet[][] matrix;
    public NodeMatrix() {
        matrix = new NodeSet[ROW][COL];
    }

    public NodeMatrix(List<Stop> busStopList) {
        matrix = new NodeSet[ROW][COL];
        getMaxMinData(busStopList);
    }

    private void getMaxMinData(List<Stop> busStops) {
        Stop aux = busStops.get(0);
        minLat = maxLat = aux.getLatitude();
        minLon = maxLon = aux.getLongitude();
        for(Stop stop : busStops) {
            double busLat = stop.getLatitude();
            double busLon = stop.getLongitude();
            if(busLat >= maxLat) maxLat = busLat;
            if(busLat <= minLat) minLat = busLat;
            if(busLon >= maxLon) maxLon = busLon;
            if(busLon <= minLon) minLon = busLon;
        }
    }

    public NodeSet[][] getMatrix() {
        return matrix;
    }

    private double getNodeXRange() {
        return (maxLon - minLon) / ROW;
    }

    private double getNodeYRange() {
        return (maxLat - minLat) / COL;
    }

    private int longitudeToRow(double longitude) {
        int r = (int) (Math.floor((longitude - minLon) / getNodeXRange()));
        return r == 100 ? 99 : r;
    }

    private int latitudeToCol(double latitude) {
        int r = (int) (Math.floor((latitude - minLat) / getNodeYRange()));
        return r == 100 ? 99 : r;
    }

    public void insertBusStop(Stop stop) {
        int row = longitudeToRow(stop.getLongitude());
        int col = latitudeToCol(stop.getLatitude());
        if(row < 0 || row >= ROW || col < 0 || col >= COL) return;
        if(matrix[row][col] == null) {
            matrix[row][col] = new NodeSet();
        }
        matrix[row][col].getNodeSet().add(new Node(stop));
    }


}
