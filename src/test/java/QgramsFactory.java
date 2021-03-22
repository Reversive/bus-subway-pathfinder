import java.util.ArrayList;
import java.util.List;

public class QgramsFactory {
    public static List<String> FirstTest(){
        List<String> first=new ArrayList<>();
        first.add("*A");
        first.add("AB");
        first.add("BC");
        first.add("CD");
        first.add("D*");
        return first;
    }
    public static List<String> SecondTest(){
        List<String> second= new ArrayList<>();
        second.add("*S");
        second.add("SA");
        second.add("AL");
        second.add("LE");
        second.add("ES");
        second.add("SA");
        second.add("AL");
        second.add("L*");
        return second;
    }
}
