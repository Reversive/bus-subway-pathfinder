package controller;

import model.PlaceLocation;

import java.util.ArrayList;
import java.util.List;

public class PlaceFactory{
    public static List<PlaceLocation> Test1 (){
        String[] places={"GALERIA DE BUENOS AIRES","CASTORERA","GIER MUSIC","GALERIA VASARI","PROLOGO","CASA BRANDON","GALERIA ARROYO","MUNDO HISPANO","EL GATO VIEJO","GALERIA VAN EYCK"};
        List<PlaceLocation> list=new ArrayList<>();
        for(String place:places){
            list.add(new PlaceLocation(place,0,0));
        }
        return list;
    }
    public static List<PlaceLocation> Test2 (){
        String[] places={"CAFE RETIRO","GALERIA VASARI","CASTORERA","CINE VILLAGE RECOLETA","CAFE ROMA","CASA RANDON","GALERIA ARROYO","LUNA PARK","CARLOS PELLEGRINI","CIRCULO TROVADOR"};
        List<PlaceLocation> list=new ArrayList<>();
        for(String place:places){
            list.add(new PlaceLocation(place,0,0));
        }
        return list;
    }
}