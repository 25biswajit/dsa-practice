package dsa.advance.day78.dp1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Stairs {
    @Test
    public void test(){
        Assertions.assertEquals(5, climbStairs(4));
        Assertions.assertEquals(8, climbStairs(5));
    }
    public int climbStairs(int number) {
        if(number <= 1) return number;
        long a = 0L;
        long b = 1L;
        long c = 0;
        for(int i = 2; i <= number+1 ; i++){
            c = (b + a)%1000000007;
            a = b;
            b = c;
        }
        return (int) c;
    }
}
