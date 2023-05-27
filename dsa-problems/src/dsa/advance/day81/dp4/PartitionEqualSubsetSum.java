package dsa.advance.day81.dp4;

/*
Given an integer array nums, return true if you can partition the array into two subsets such that the sum of the elements
in both subsets is equal or false otherwise.
*/

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class PartitionEqualSubsetSum {

    @Test
    public void test1(){
        int[] array = {1,5,11,5};
        Assertions.assertTrue( canPartition(array));
    }

    @Test
    public void test2(){
        int[] array = {1,2,3,5};
        Assertions.assertFalse( canPartition(array));
    }

    @Test
    public void test3(){
        int[] array = {3,3,3,4,5};
        Assertions.assertTrue( canPartition(array));
    }

    @Test
    public void test4(){
        int[] array = {100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,99,97};
        Assertions.assertTrue( canPartition(array));
    }

    int[][] dp = null;
    public boolean canPartition(int[] array){
        int sum = Arrays.stream(array).sum();
        if(sum % 2 == 0){
            dp = new int[array.length][sum/2 + 1];
            Arrays.stream(dp).forEach(a -> Arrays.fill(a,-1));
            return IsExistSubsetSum(array.length-1 , sum/2, array) == 1;
        }
        return false;
    }

    private int IsExistSubsetSum(int i, int k, int[] array){
        if(k == 0) return 1;
        if(i<0) return 0;
        if(dp[i][k] == -1){
            dp[i][k] = IsExistSubsetSum(i-1,k,array);
            if(k >= array[i]){
                dp[i][k] = dp[i][k] | IsExistSubsetSum(i-1,k-array[i],array);
            }
        }
        return dp[i][k];
    }
}
