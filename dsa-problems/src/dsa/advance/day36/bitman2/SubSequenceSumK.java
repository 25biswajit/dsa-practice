package dsa.advance.day36.bitman2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
Find that there is any subsequence exists or not whose sum is equal to B.
A=[1,20,13,4,5], B=18
There is as subsequence present at indexes 1,3,4 whose sum is 18
*/
public class SubSequenceSumK {
    @Test
    public void test(){
        int[] array = {1,20,13,4,5};
        Assertions.assertTrue(isSubSeqPresentWIthSumK(array,18));
    }

    public boolean isSubSeqPresentWIthSumK(int[] array, int K){
        int n = array.length;

        for(int i = 0 ; i < (1<<n) ; i++){
            int sum = 0;
            for(int j = 0; j < n; j++){
                if(isIthBitSet(i,j)){
                    sum += array[j];
                }
            }
            if(sum == K){
                return true;
            }
        }
        return false;
    }

    private boolean isIthBitSet(int num, int i) {
        return ((num >> i) & 1 ) == 1;
    }
}
