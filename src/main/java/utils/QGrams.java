package utils;

import java.util.ArrayList;
import java.util.List;


public class QGrams {
    private static String fillTokens(String str,int n){
        StringBuilder str1 = new StringBuilder();
        int i;
        for(i = 0; i < n-1; i++){
            str1.append('*');
        }
        str1.append(str.toUpperCase());
        for(i=0; i < n-1; i++){
            str1.append('*');
        }
        return str1.toString();
    }
    public static List<String> nGrams(int n, String str) {
        String strTok = fillTokens(str,n);
        List<String> nGrams = new ArrayList<>();
        for (int i = 0; i < strTok.length() - n + 1; i++) {
            nGrams.add(strTok.substring(i, i + n));
        }
        return nGrams;
    }
    public static double similarity(String str1,String str2,int n) {
        List<String> nGramsStr1 = nGrams(n, str1);
        List<String> nGramsStr2 = nGrams(n, str2);
        List<String> auxList = new ArrayList<>();
        for (String aux : nGramsStr1) {
            if (nGramsStr2.contains(aux))
                auxList.add(aux);
        }
        int similarityResult = nGramsStr1.size() + nGramsStr2.size() - (nGramsStr1.size() + nGramsStr2.size() - 2 * auxList.size());
        return (double) similarityResult / ( nGramsStr1.size() + nGramsStr2.size() );
    }
}