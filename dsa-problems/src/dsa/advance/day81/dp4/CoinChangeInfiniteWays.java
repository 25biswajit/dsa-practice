package dsa.advance.day81.dp4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/*
You are given a set of coins A. In how many ways can you make sum B assuming you have infinite amount of each coin in the set.
Coins in set A will be unique. Expected space complexity of this problem is O(B).
The answer can overflow. So, return the answer % (10^6 + 7).
Return the number of combinations that make up that amount.

Input: amount = 5, coins = [1,2,5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1
*/

public class CoinChangeInfiniteWays {
    HashMap<String, Integer> dpStates = new HashMap<>();
    @Test
    public void test1(){
        int[] array = {1,2,3};
        int result = coinChangeInfiniteWays(array, 4);
        //1,1,1,1 - 2,2 - 3,1 - 2,1,1
        Assertions.assertEquals(result, 4);
    }

    @Test
    public void test2(){
        int[] array = {15,2,1};
        // (1 * 11), (2 * 5 + 1), (2*4 + 1*3), (2*3 + 1*5), (2*2 + 1*7), (2 + 1*9)
        Assertions.assertEquals(6, coinChangeInfiniteWays(array,11));
    }
    @Test
    public void test3(){
        int[] array = {2};
        // 2 * 3
        Assertions.assertEquals(1, coinChangeInfiniteWays(array,6));
    }
    @Test
    public void test4(){
        int[] array = {2};
        // Not Possible
        Assertions.assertEquals(0, coinChangeInfiniteWays(array,3));
    }

    int dp[][] = null;
    public int coinChangeInfiniteWays(int[] array, int sum){
        dp = new int[array.length][sum+1];
        Arrays.stream(dp).forEach(a -> Arrays.fill(a,-1));
        return coinChangeInfiniteWays(array.length-1, sum, array);
    }

    private int coinChangeInfiniteWays(int i, int k, int[] array){
        if(k == 0) return 1;
        if(i<0) return 0;
        if(dp[i][k] == -1){
            dp[i][k] = coinChangeInfiniteWays(i-1,k,array);
            if(k >= array[i]){
                dp[i][k] = dp[i][k] + coinChangeInfiniteWays(i,k-array[i],array);
            }
        }
        return dp[i][k];
    }

}
