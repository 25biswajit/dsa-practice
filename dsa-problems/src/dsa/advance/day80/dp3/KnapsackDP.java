package dsa.advance.day80.dp3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class KnapsackDP {

    @Test
    public void test1(){
        int[] values = {60, 100, 120};
        int[] weights = {10, 20, 30};
        Assertions.assertEquals(220, solveKnapsack(values,weights,50));
    }
    @Test
    public void test2(){
        int[] values = {359,963,465,706,146,282,828,962,492};
        int[] weights = {96,43,28,37,92,5,3,54,93};
        Assertions.assertEquals(5057, solveKnapsack(values,weights,383));
    }

    @Test
    public void test3(){
        int[] values = {100,60,120,150};
        int[] weights = {20,10,30,40};
        Assertions.assertEquals(220, solveKnapsack(values,weights,50));
    }

    int[][] dpTable = null;
    public int solveKnapsack(int[] values, int[] weights, int K) {
        dpTable = new int[values.length][K+1];
        Arrays.stream(dpTable).forEach(a -> Arrays.fill(a, -1));
        return knapsack(values.length-1, values, weights, K);
    }

    private int knapsack(int i, int[] values, int[] weights, int K){
        if(i < 0 || K <= 0) return 0;
        if(dpTable[i][K] == -1){
            int value = knapsack(i-1,values,weights,K);
            if(K >= weights[i]){
                int valueWith = knapsack(i-1,values,weights,K-weights[i]) + values[i];
                value = Math.max(value,valueWith);
            }
            dpTable[i][K] = value;
        }
        return dpTable[i][K];
    }
}
