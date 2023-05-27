package dsa.advance.day79.dp2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class MaxSumWithoutAdjElem {
    int[] dpTable = null;
    @Test
    public void test(){
        int[][] matrix = {{1,2,3,4},{2,3,4,5}};
        dpTable = new int[matrix[0].length + 1];
        Arrays.fill(dpTable, 0, dpTable.length, -1);
        Assertions.assertEquals(8, maxSum(matrix, matrix[0].length));
    }

    // TC O(N*M) SC O(N*M)
    public int maxSum(int[][] matrix, int i) {
        if(i <= 0) return 0;
        if (dpTable[i] == -1){
            dpTable[i] = Integer.max(
                    maxSum(matrix, i-1),
                    Integer.max(matrix[0][i-1],matrix[1][i-1]) + maxSum(matrix, i-2));
        }
        return dpTable[i];
    }

}
