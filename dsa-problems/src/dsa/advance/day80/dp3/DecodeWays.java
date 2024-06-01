package dsa.advance.day80.dp3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DecodeWays {

    @Test
    public void test1(){
        int res = numDecodings("12");
        Assertions.assertEquals(2, res);
    }

    @Test
    public void test2(){
        int res = numDecodings("226");
        Assertions.assertEquals(3, res);
    }
    @Test
    public void test3(){
        int res = numDecodings("122016");
        Assertions.assertEquals(4, res);
    }

    public int numDecodings(String s) {
        //return decodeways(s);
        System.out.println("===");
        return decodeHelper(s);
    }

    private int decodeHelper(String s) {
        int n = s.length();
        if(n == 0) return 1;
        int ways = 0;
        for (int i = 1; i <= n; i++) {
            String substring = s.substring(0, i);
            System.out.println("Substr :" + substring);
            if (isValid(substring)) {
                ways += decodeHelper(s.substring(i));
            }
        }
        return ways;
    }

    private int decodeways(String s){
        int n = s.length();
        if(n == 0) return 1;
        if(s.charAt(0) == '0') return 0;
        int ways = decodeways(s.substring(1));
        if(n >= 2 && isValid(s.substring(0,2))){
            ways += decodeways(s.substring(2));
        }
        return ways;
    }

    private boolean isValid(String s){
        if(s.charAt(0) == '0') return false;
        Integer i = Integer.parseInt(s);
        return i >= 0 && i <= 26;
    }
}