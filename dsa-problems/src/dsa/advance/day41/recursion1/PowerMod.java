package dsa.advance.day41.recursion1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PowerMod {
    @Test
    public void test1(){
        Assertions.assertEquals(2, pow(2,3,3));
        Assertions.assertEquals(6, pow(5,3,7));
    }
    @Test
    public void test2(){
        Assertions.assertEquals(20805472, pow(71045970,41535484,64735492));
    }

    // TC:O(log P) , SC: O(log P)
    public int pow(int num, int p, int mod) { // n ^ p % mod
        if(num < 0) num = ( num % mod + mod ) % mod;
        if(num == 0) return 0;
        if(p == 0) return 1;
        else{
            long res = pow(num, p/2, mod);
            res = ( res % mod * res % mod ) % mod;
            if(p % 2 != 0){
                res = ( res % mod * num % mod ) % mod;
            }
            return (int)res;
        }
    }
}
