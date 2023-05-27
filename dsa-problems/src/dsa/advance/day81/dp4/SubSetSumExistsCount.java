package dsa.advance.day81.dp4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
/*
Given an array arr[] of length N and an integer X, the task is to find the number of subsets with a sum equal to X.
Examples:
Input: arr[] = {1, 2, 3, 3}, X = 6
Output: 3
All the possible subsets are {1, 2, 3},
{1, 2, 3} and {3, 3}

Input: arr[] = {1, 1, 1, 1}, X = 1
Output: 4
*/

public class SubSetSumExistsCount {

    @Test
    public void test1(){
        int[] array = {3,34,4,12,5,2};
        Assertions.assertEquals(countSubSetSumExist(array, 9),2);
    }

    @Test
    public void test2(){
        int[] array = {7,4,9,6,10,13, 14, 11};
        Assertions.assertEquals(countSubSetSumExist(array, 22),3);
    }

    // approach 2 - using dp memoization
    int dp[][] = null;
    public int countSubSetSumExist(int[] array, int sum){
        dp = new int[array.length][sum+1];
        Arrays.stream(dp).forEach(a -> Arrays.fill(a,-1));
        return countSubSetSumExist(array.length-1, sum, array);
    }

    private int countSubSetSumExist(int i, int k, int[] array){
        if(k == 0) return 1;
        if(i<0) return 0;
        if(dp[i][k] == -1){
            dp[i][k] = countSubSetSumExist(i-1,k,array);
            if(k >= array[i]){
                dp[i][k] = dp[i][k] + countSubSetSumExist(i-1,k-array[i],array);
            }
        }
        return dp[i][k];
    }
}
