import model.PlaceLocation;
import utils.QGrams;
import utils.Stop;
import utils.StopType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class ResourceReader {

    public static List<PlaceLocation> getSites() {

        String line;
        List<PlaceLocation> sites=new ArrayList<>();
        try {
            BufferedReader buffer= new BufferedReader(new FileReader(getFileNameFromResource("espacios-culturales.csv")));
            buffer.readLine();
            while((line=buffer.readLine()) != null){
                String[] value=line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                sites.add(new PlaceLocation(value[3], Double.parseDouble(value[13]), Double.parseDouble(value[14])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sites;
    }
    public static List<Stop> getStops() {
        List<Stop> stops = new ArrayList<>();
        getBusStops(stops, getFileNameFromResource("paradas-de-colectivo.csv"));
        getSubwayStops(stops, getFileNameFromResource("estaciones-de-subte.csv"));
        return stops;
    }

    public static void getBusStops(List<Stop> stops, String path) {
        String line;
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(path));
            buffer.readLine();
            while((line=buffer.readLine())!=null){
                String[] value=line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                stops.add(new Stop(value[8], value[2], Double.parseDouble(value[3]), Double.parseDouble(value[4]), StopType.BUS));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getSubwayStops(List<Stop> stops, String path) {
        String line;
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(path));
            buffer.readLine();
            while((line=buffer.readLine())!=null){
                String[] value=line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                stops.add(new Stop(value[4], value[3], Double.parseDouble(value[1]), Double.parseDouble(value[0]), StopType.SUBWAY));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static PlaceLocation[] getTop10(String searchTerm,List<PlaceLocation> data){
        int qGrams = 2;
        searchTerm = searchTerm.toUpperCase();
        PlaceLocation[] top10 = new PlaceLocation[10];
        boolean flag;
        Arrays.fill(top10,null);
        for(PlaceLocation database : data) {
            flag = false;
            double similarity = QGrams.similarity(searchTerm, database.getName(), qGrams);
            PlaceLocation candidate = database;
            for(int i = 0 ; i < top10.length && !flag; i++){
                if(top10[i] == null){
                    top10[i] = candidate;
                    flag = true;
                } else if(similarity > QGrams.similarity(searchTerm, top10[i].getName(), qGrams)) {
                    similarity = QGrams.similarity(searchTerm, top10[i].getName(), qGrams);
                    PlaceLocation aux= top10[i];
                    top10[i] = candidate;
                    candidate = aux;
                }
            }
        }
        return top10;
    }

    public static String getFileNameFromResource(String fileName) {

        ClassLoader classLoader = ResourceReader.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("File not found: " + fileName);
        } else {
            try {
                return new File(resource.toURI()).getAbsolutePath();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
