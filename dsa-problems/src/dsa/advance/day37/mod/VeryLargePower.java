package dsa.advance.day37.mod;

import static dsa.utils.Constants.mod_prime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VeryLargePower {
    @Test
    public void test(){
        Assertions.assertEquals(64, solve(2,3));
        Assertions.assertEquals(666348826, solve(2,27));
    }

    public int solve(int A, int B){
        if(A == 0) return 0;
        long fact = factorial(B);
        int result = pow(A, fact, mod_prime);
        return result;
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

    private long factorial(long n){
        if(n == 1) return 1L;
        else return (n * factorial(n-1)) % (mod_prime-1);
    }
}
