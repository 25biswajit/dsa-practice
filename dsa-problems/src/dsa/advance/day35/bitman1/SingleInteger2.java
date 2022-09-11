package dsa.advance.day35.bitman1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
APPEARS THRICE EXCEPT FOR ONE

Given an array of integers, every element appears thrice except for one, which occurs once.
Find that element that does not appear thrice.
NOTE: Your algorithm should have a linear runtime complexity.
Could you implement it without using extra memory?
A = [1, 2, 4, 3, 3, 2, 2, 3, 1, 1]
4 occurs exactly once in Input 1.
*/

public class SingleInteger2 {

    @Test
    public void test(){
        int[] arr = {5,7,11,4,5,7,11,4,5,7,11,4,9};
        Assertions.assertEquals(9, singleNumber2(arr));
    }

    public int singleNumber2(final int[] array) {
        int result = 0;
        int count;
        for(int i=0; i <32; i++){
            count = 0;
            for(int j=0; j< array.length ;j++){
                if(isIthBitSet(array[j],i)){
                    count++;
                }
            }
            if(count % 3 == 1){
                result = result + (1<<i);
            }
        }
        return result;
    }

    private boolean isIthBitSet(int num, int i) {
        return ((num >> i) & 1 ) == 1;
    }
}
