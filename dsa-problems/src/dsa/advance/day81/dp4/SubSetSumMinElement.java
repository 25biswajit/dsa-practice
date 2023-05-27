package dsa.advance.day81.dp4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/*
An array of size N is given and a value K. You have to find the minimum subset size so that subset sum is exactly equal to K,
if not print -1. 0 < K, a[i], N < 10^6.
Every element can be picked only Once at MAX
*/
public class SubSetSumMinElement {

    @Test
    public void test1(){
        int[] array = {3,34,4,12,5,2};
        Assertions.assertEquals(2, minElementsReqSubSetSum(array, 9));
    }

    @Test
    public void test2(){
        int[] array = {7,4,9,6,10,13, 14, 11};
        Assertions.assertEquals(2, minElementsReqSubSetSum(array, 22));
    }

    @Test
    public void test3(){
        int[] array = {7,4,9,6,10,13, 14, 11};
        Assertions.assertEquals(3, minElementsReqSubSetSum(array, 26));
    }

    @Test
    public void test4(){
        int[] array = {2};
        Assertions.assertEquals(-1, minElementsReqSubSetSum(array, 6));
    }

    @Test
    public void test5(){
        int[] array = {2};
        Assertions.assertEquals(-1, minElementsReqSubSetSum(array, 3));
    }

    // TC O(N*K) SC O(N*K) - N size of Array,K Target Sum
    // approach 2 - using dp memoization
    int dp[][] = null;
    public int minElementsReqSubSetSum(int[] array, int sum){
        dp = new int[array.length][sum+1];
        Arrays.stream(dp).forEach(a -> Arrays.fill(a,Integer.MAX_VALUE));
        int result = minElementsReqSubSetSum(array.length-1, sum, array);
        return result == Integer.MAX_VALUE-1 ? -1 : result;
    }

    private int minElementsReqSubSetSum(int i, int k, int[] array){
        if(k == 0) return 0;
        if(i<0) return Integer.MAX_VALUE-1;
        if(dp[i][k] == Integer.MAX_VALUE){
            dp[i][k] = minElementsReqSubSetSum(i-1,k,array);
            if(k >= array[i]){
                dp[i][k] = Math.min( dp[i][k] , minElementsReqSubSetSum(i-1,k-array[i],array) + 1);
            }
        }
        return dp[i][k];
    }
}
