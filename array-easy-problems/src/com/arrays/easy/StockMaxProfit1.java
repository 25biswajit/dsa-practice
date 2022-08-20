package com.arrays.easy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StockMaxProfit1 {
    @Test
    public void test(){
        int[] inputPrices1 = {7,1,5,3,6,4};
        Assertions.assertEquals(5,  maxProfit(inputPrices1));

        int[] inputPrices2 = {7,6,4,3,2,1};
        Assertions.assertEquals(0,  maxProfit(inputPrices2));
    }

    // TC: O(N) = 2 * N iteration , SC: O(N) - Not Optimised Solution
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[] minArr = new int[prices.length];
        int[] maxArr = new int[prices.length];
        minArr[0] = prices[0];
        maxArr[n-1] = prices[n-1];

        for(int i=1;i<n; i++){
            if(minArr[i-1] > prices[i]){
                minArr[i] = prices[i];
            }else{
                minArr[i] = minArr[i-1];
            }
        }
        for(int i=n-2;i>=0; i--){
            if(maxArr[i+1] < prices[i]){
                maxArr[i] = prices[i];
            }else{
                maxArr[i] = maxArr[i+1];
            }
        }
        int maxProfit = 0;
        for(int i=0; i<n;i++){
            int diff = maxArr[i] - minArr[i];
            if(diff > maxProfit){
                maxProfit = diff;
            }
        }
        return maxProfit;

    }
}
