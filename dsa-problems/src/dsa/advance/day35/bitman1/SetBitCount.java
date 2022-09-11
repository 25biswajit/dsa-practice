package dsa.advance.day35.bitman1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
Write a function that takes an integer and returns the number of 1 bits it has.
11 is represented as 1011 in binary. Ans = 3
*/
public class SetBitCount {
    @Test
    public void test(){
        Assertions.assertEquals(3, numSetBits(11));
    }

    public int numSetBits(int n) {
        int c = 0;
        while(n > 0){
            if((n & 1)==1){ c++; }
            n = n >> 1;
        }
        return c;
    }
}
