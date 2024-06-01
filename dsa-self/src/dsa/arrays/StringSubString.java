package dsa.arrays;

import java.util.ArrayList;
import java.util.List;

public class StringSubString {


    public static void main(String[] args) {
        String s = "abc";
        int n =  s.length();
        List<String> list = new ArrayList<>();
        for(int i  = 0 ; i < n-1; i++){
            for(int j = i; j < n; j++){
                int len = j - i + 1;
                list.add(s.substring( i,len + i));
            }
        }
        System.out.println(list);
    }

}
