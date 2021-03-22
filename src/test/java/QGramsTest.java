import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.QGrams;

public class QGramsTest {
    @Test
    void QgramGeneration(){
        //PRIMER TEST
        String[] qgram1=QgramsFactory.FirstTest().toArray(new String[0]);
        String[] qgram2= QGrams.nGrams(2,"ABCD").toArray(new String[0]);
        for(int i=0;i< qgram1.length;i++){
            Assertions.assertEquals(qgram1[i],qgram2[i]);
        }
        //SEGUNDO TEST
        qgram1=QgramsFactory.SecondTest().toArray(new String[0]);
        qgram2= QGrams.nGrams(2,"SALESAL").toArray(new String[0]);
        for(int i=0;i< qgram1.length;i++){
            Assertions.assertEquals(qgram1[i],qgram2[i]);
        }

    }

    @Test
    void Similarity() {
        Assertions.assertEquals((double)1/3, QGrams.similarity("ABCD", "ABTUIO",2));
        Assertions.assertEquals(0.6, QGrams.similarity("ABCD", "ABCE",2));
        Assertions.assertEquals(1, QGrams.similarity("BIBLIOTECA", "BIBLIOTECA",3));
        Assertions.assertEquals((double)3/7, QGrams.similarity("SALESAL", "ALALE",2));
    }
}
