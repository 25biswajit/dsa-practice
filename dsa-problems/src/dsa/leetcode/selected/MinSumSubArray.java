package dsa.leetcode.selected;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MinSumSubArray {

    @Test
    public void test1(){
        int[] arr = {1,2,-3,-2,6};
        Assertions.assertEquals(-5, minSumSubArray(arr));
    }
    @Test
    public void test2(){
        int[] arr = {1,2,3,2,6};
        Assertions.assertEquals(1, minSumSubArray(arr));
    }

    public int minSumSubArray(int[] arr){
        int min = Integer.MAX_VALUE;
        int sum = 0;
        for(int a : arr){
            sum += a;
            min = Integer.min(min, sum);
            if(sum > 0) sum = 0;
        }
        return min;
    }

}
