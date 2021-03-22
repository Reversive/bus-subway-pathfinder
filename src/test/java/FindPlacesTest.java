import controller.PlaceFactory;
import model.PlaceLocation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FindPlacesTest{
    @Test
    public void Test1(){
        String[] top10={"GALERIA ARROYO","GALERIA VASARI","GALERIA VAN EYCK","GALERIA DE BUENOS AIRES","PROLOGO","GIER MUSIC","EL GATO VIEJO","CASTORERA","CASA BRANDON","MUNDO HISPANO"};
        PlaceLocation[] sites=ResourceReader.getTop10("galeria arroyo", PlaceFactory.Test1());
        for(int i=0;i< top10.length;i++){
            Assertions.assertEquals(top10[i],sites[i].getName());
        }
    }
    @Test
    public void Test2(){
        String[] top10={"CAFE ROMA","CAFE RETIRO","CASTORERA","CASA RANDON","CARLOS PELLEGRINI","CIRCULO TROVADOR","CINE VILLAGE RECOLETA","LUNA PARK","GALERIA VASARI","GALERIA ARROYO"};
        PlaceLocation[] sites=ResourceReader.getTop10("caf", PlaceFactory.Test2());
        for(int i=0;i< top10.length;i++){
            Assertions.assertEquals(top10[i],sites[i].getName());
        }
    }
}