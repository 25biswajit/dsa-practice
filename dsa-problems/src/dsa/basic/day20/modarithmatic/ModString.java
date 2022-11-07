package dsa.basic.day20.modarithmatic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ModString {
    @Test
    public void test(){
        Assertions.assertEquals(20, findMod("43535321",47));
    }

    public int findMod(String word, int mod) {
        String[] arr = word.split("");
        long sum = 0;
        long temp = 1;
        for(int i=arr.length-1;i>=0;i--){
            int num = Integer.parseInt(arr[i]);
            sum = ( sum + ( num * temp ) ) % mod;
            temp = (temp * 10) % mod;
        }
        return (int)sum;
    }

    // Not working for very big numbers
    public int findMod_GFH(String num, int mod) {
        int res = 0;
        for (int i = 0; i < num.length(); i++) {
            res = (res * 10 + (int) num.charAt(i) - '0') % mod;
        }
        return res;
    }
}
