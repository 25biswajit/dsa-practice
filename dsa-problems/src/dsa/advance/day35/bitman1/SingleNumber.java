package dsa.advance.day35.bitman1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
Given an array of integers A, every element appears twice except for one. Find that integer that occurs once.
NOTE: Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
A = [1, 2, 2, 3, 1] Ans : 3
*/

public class SingleNumber {
    @Test
    public void test(){
        int[] arr = {1, 2, 2, 3, 1};
        Assertions.assertEquals(3, singleNumber(arr));
    }

    public int singleNumber(final int[] array) {
        int missing = array[0];
        for(int i = 1; i < array.length; i++){
            missing = missing ^ array[i];
        }
        return missing;
    }
}
