package dsa.basic.practice;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BitMan {

    @Test
    public void main() {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("1000","1110","1010","0011","0101","0001","0010","0000"));
        list.forEach(x -> System.out.print(Integer.parseInt(x,2)+" "));
        System.out.println();
        int sum = Integer.parseInt(solve(4,3,list), 2);
        System.out.println(sum);
/*

        ArrayList<String> list = new ArrayList<>(Arrays.asList("10","10","01","01","11","10","10","10"));
        int foo = Integer.parseInt(solve(2,2,list), 2);
        System.out.println(foo);
*/

//        list = new ArrayList<>(Arrays.asList("1111111","01010101"));
//        System.out.println(Integer.parseInt("1111111",2));
//        System.out.println(Integer.parseInt("01010101",2));
//        System.out.println(Integer.parseInt("10101010",2));
    }

    public String solve(int maxStringLen, int maxFlipAllowed, ArrayList<String> stringArrayList) {
        Collections.sort(stringArrayList);
        for(int i=0;i<stringArrayList.size();i++){
            String s = stringArrayList.get(i);
            if(maxFlipAllowed>0){
                System.out.println("Flipped " + Integer.parseInt(s,2));
                s = s.replaceAll("0","x").replaceAll("1","0").replaceAll("x","1");
                stringArrayList.set(i,s);
                System.out.println("New " + Integer.parseInt(s,2));
                maxFlipAllowed--;
            }
        }

        stringArrayList.forEach(x -> System.out.print(Integer.parseInt(x,2)+" "));
        System.out.println();

        int i=0;
        String sum = stringArrayList.get(i);
        while(i<stringArrayList.size()-1){
            String s2 = stringArrayList.get(i+1);
            sum = addBinString(sum,s2);
            i++;
        }
        return sum;
    }

    private String addBinString(String s1, String s2) {

        int rem = 0;
        String result = "";
        int maxLen = Integer.max(s1.length(),s2.length());
        System.out.println(maxLen +" "+ s1 + " " + s2);
        s1 = addZeroIfReq(maxLen,s1);
        s2 = addZeroIfReq(maxLen,s2);
        System.out.println( s1 + " " + s2);
        for(int i=maxLen-1; i>=0;i--){
            int d1 = Integer.parseInt(s1.charAt(i)+"");
            int d2 = Integer.parseInt(s2.charAt(i)+"");
            int sum = rem + d1 + d2;
            result = sum%2 + result;
            rem = sum / 2;
        }
        if(rem == 1){
            result = rem + result;
        }
        return result;
    }

    private String addZeroIfReq(int maxLen, String s1) {
        while(s1.length() < maxLen){
            s1 = "0"+s1;
        }
        return s1;
    }
}
