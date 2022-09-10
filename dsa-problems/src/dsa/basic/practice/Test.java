package dsa.basic.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Test  {
    public static void main(String[] args) {
        Test t = new Test();
        ArrayList<String> list = new ArrayList<>(Arrays.asList("ac80","xyy95","pqr95"));
        list = t.solve(list);
        list.forEach(s -> System.out.println(s));

        list = new ArrayList<>(Arrays.asList("abc2","xyz4","pqr2","kkk9"));
        list = t.solve(list);
        //System.out.println(list);
    }

    public ArrayList<String> solve(ArrayList<String> A) {
        MarksComp comp = new MarksComp();
        Collections.sort(A, comp);
        return A;
    }
}
class MarksComp implements Comparator<String> {

    public int compare(String s1, String s2){
        int m1 = Util.getMarks(s1);
        int m2 = Util.getMarks(s2);
        //System.out.println(m1 + " " + m2);
        if(m1 > m2){
            return -1;
        }else if(m1 == m2){
            return 1;
        }else{
            return 1;
        }

    }
}

class Util{

    public static int getMarks(String s1){
        int n = s1.length();
        StringBuilder sb1 = new StringBuilder();
        for(int i=n-2;i<n;i++){
            char c1 = s1.charAt(i);
            if(c1 >= '0' && c1<='9'){
                sb1.append(c1);
            }
        }
        String s = sb1.toString();
        return Integer.parseInt(s);
    }

}


