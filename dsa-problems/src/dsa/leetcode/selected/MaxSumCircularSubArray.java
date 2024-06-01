package dsa.leetcode.selected;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MaxSumCircularSubArray {

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


    private int maxSubArraySumCircular(int[] arr) {
        int result = 0;
        boolean flag = checkAllSamePositive(arr) || checkAllSameNegative(arr);
        result = flag ? kadanes(arr): kadanesCircular(arr);
        return result;
    }

    private boolean checkAllSamePositive(int[] arr) {
        for(int a : arr){
            if(a < 0) return false;
        }
        return true;
    }
    private boolean checkAllSameNegative(int[] arr) {
        for(int a : arr){
            if(a >= 0) return false;
        }
        return true;
    }


    private int kadanes(int[] arr) {
        int maxSum = Integer.MIN_VALUE, sum = 0;
        for(int a : arr){
            sum += a;
            maxSum = Integer.max(maxSum, sum);
            if(sum < 0) sum = 0;
        }
        return maxSum;
    }

    private int kadanesCircular(int[] arr) {
        int minSum = Integer.MAX_VALUE, totalSum = 0, currentSum = 0;
        for(int a : arr){
            totalSum += a;
            currentSum += a;
            minSum = Integer.min(minSum, currentSum);
            if(currentSum > 0) currentSum = 0;
        }
        return totalSum - minSum;
    }


}
