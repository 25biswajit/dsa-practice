package dsa.advance.day81.dp4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/*
https://leetcode.com/problems/coin-change/description/

You are given an integer array coins and an integer amount representing a total amount of money.
Return the fewest/minimum number of coins requires to make up that amount.
If that amount of money cannot be made up by any combination of the coins, return -1.
You may assume that you have an infinite number of each kind of coin.
*/

public class CoinChangeInfinite {
    HashMap<String, Integer> dpStates = new HashMap<>();
    @Test
    public void test1(){
        int[] array = {1,2,3};
        int result = subsetSumWays(array.length-1, 4, array, new ArrayList<>());
        Assertions.assertEquals(result, 4);
    }

    @Test
    public void test2(){
        int[] array = {15,2,1};
        Assertions.assertEquals(6, minCoinReqInfinite(array,11));
        Assertions.assertEquals(6, minCoinReqInfiniteSimple(array,11));
    }
    @Test
    public void test3(){
        int[] array = {2};
        Assertions.assertEquals(3, minCoinReqInfinite(array,6));
        Assertions.assertEquals(3, minCoinReqInfiniteSimple(array,6));
    }
    @Test
    public void test4(){
        int[] array = {2};
        Assertions.assertEquals(-1, minCoinReqInfinite(array,3));
        Assertions.assertEquals(-1, minCoinReqInfiniteSimple(array,3));
    }

    // DP - Simple Iterative
    public int minCoinReqInfiniteSimple(int[] coins, int amount) {
        int[]dp=new int[amount+1];
        Arrays.fill(dp,amount+1);
        dp[0]=0;
        for(int c:coins){
            for(int i=c;i<=amount;i++){
                dp[i]=Math.min(dp[i],dp[i-c]+1);
            }
        }
        return dp[amount]<=amount ? dp[amount] : -1;
    }
    
    // DP State - in 2D array
    int dp[][] = null;
    public int minCoinReqInfinite(int[] array, int sum){
        dp = new int[array.length][sum+1];
        Arrays.stream(dp).forEach(a -> Arrays.fill(a,Integer.MAX_VALUE));
        int result = minCoinReqInfinite(array.length-1, sum, array);
        return result == Integer.MAX_VALUE-1 ? -1 : result;
    }

    private int minCoinReqInfinite(int i, int k, int[] array){
        if(k == 0) return 0;
        if(i < 0) return Integer.MAX_VALUE-1;
        if(dp[i][k] == Integer.MAX_VALUE){
            // leave
            dp[i][k] = minCoinReqInfinite(i-1,k,array);
            if(k >= array[i]){
                // pick
                dp[i][k] = Math.min ( dp[i][k] , minCoinReqInfinite(i,k-array[i],array) + 1);
            }
        }
        return dp[i][k];
    }
    
    // DP State - MAP
    public int subsetSumWays(int i, int sum, int[] array, ArrayList<Integer> p){
        if (sum == 0) {
            System.out.println(p);
            return 1;
        }
        if(i < 0) return 0;
        String key = i+"@"+sum;
        if(!dpStates.containsKey(key)){
            ArrayList<Integer> b = new ArrayList<>();
            b.addAll(p);
            int leave = subsetSumWays(i-1, sum, array, b);
            int pick = 0;
            if(sum >= array[i]){
                p.add(array[i]);
                pick = subsetSumWays(i, sum-array[i], array, p);
            }
            int value = leave + pick;
            dpStates.put(key,value);
        }
        return dpStates.get(key);
    }

}
