package dsa.leetcode.selected;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MaxSumSubArrayCircular {

    @Test
    public void test1(){
        int[] arr = {1,2,-3,-2,6};
        Assertions.assertEquals(9, maxSubArraySumCircular(arr));
    }

    @Test
    public void test2(){
        int[] arr = {6,-20,-10,9};
        Assertions.assertEquals(15, maxSubArraySumCircular(arr));
    }

    @Test
    public void test4(){
        int[] arr = {-9,-2,-3,-1,-6};
        Assertions.assertEquals(-1, maxSubArraySumCircular(arr));
    }

    @Test
    public void test5(){
        int[] arr = {-3,-2,-3};
        Assertions.assertEquals(-2, maxSubArraySumCircular(arr));
    }

    @Test
    public void test3(){
        int[] arr = {9,2,3,2,6};
        Assertions.assertEquals(22, maxSubArraySumCircular(arr));
    }

    public int maxSubArraySumCircular(int[] arr) {
        int minSumSubArray = Integer.MAX_VALUE, maxSumSubArray = Integer.MIN_VALUE;
        int sumMin = 0, sumMax = 0;
        int totalSum = 0;
        for(int i : arr){
            totalSum += i;

            sumMax = Integer.max(i, sumMax+i);
            maxSumSubArray = Integer.max(maxSumSubArray, sumMax);

            sumMin = Integer.min(i, sumMin+i);
            minSumSubArray = Integer.min(minSumSubArray, sumMin);
        }

        return (maxSumSubArray > 0) ? Integer.max(maxSumSubArray, totalSum - minSumSubArray) : maxSumSubArray;
    }

}
