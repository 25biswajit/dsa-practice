package dsa.arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StockMaxProfit2 {
    @Test
    public void test(){
        int[] inputPrices1 = {7,1,5,3,6,4};
        Assertions.assertEquals(5,  maxProfit(inputPrices1));

        int[] inputPrices2 = {7,6,4,3,2,1};
        Assertions.assertEquals(0,  maxProfit(inputPrices2));
    }

    // TC: O(N) - N iteration , SC: O(1)
    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int profit = 0;
        for(int i=0;i<prices.length;i++){
            if(min > prices[i]){
                min = prices[i];
            }else{ //if(min < prices[i])
                profit = Math.max(profit, prices[i]-min);
            }
        }
        return profit;
    }
}
