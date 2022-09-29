package dsa.advance.day37.mod;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PrimeModuloInverse {
    @Test
    public void test(){
        Assertions.assertEquals(4, solve(6,23));
    }


    public int solve(int a, int b) { // (a ^ (b-2)) % b
        return  pow(a, b-2, b);
    }

    private int pow(int a, long p, int mod) {
        if(a == 0) return 0;
        if(a < 0) a = ( a % mod + mod ) % mod;
        if(p == 0) return 1;
        long res = pow(a, p/2, mod);
        res = ( res * res ) % mod;
        if(p % 2 != 0){
            res = ( res * a ) % mod;
        }
        return (int)res;
    }
}
