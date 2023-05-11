package dsa.advance.day79.dp2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MinSumPathMatrix {
    Map<String, Integer> map = null;

    @Test
    public void test1(){
        int[][] matrix = {{1, 3, 2},{4, 3, 1},{5, 6, 1}};
        Assertions.assertEquals(8, minPathSum(matrix));
        Assertions.assertEquals(8, minPathSumNew(matrix));
        Assertions.assertEquals(8, minPathSumLeetCode(matrix));
    }
    @Test
    public void test2(){
        int[][] matrix = {{2,3,6,7}, {2,3,4,5}};
        Assertions.assertEquals(16, minPathSum(matrix));
        Assertions.assertEquals(16, minPathSumNew(matrix));
        Assertions.assertEquals(16, minPathSumLeetCode(matrix));
    }
    @Test
    public void test3(){
        int[][] matrix = {{1, -3, 2},{2, 5, 10},{5, -5, 1}};
        Assertions.assertEquals(-1, minPathSum(matrix));
        Assertions.assertEquals(-1, minPathSumNew(matrix));
        Assertions.assertEquals(-1, minPathSumLeetCode(matrix));
    }

    // MLE might come here that means you are using more space than allocated,
    // To save space instead of using map of string , use dp matrix of same size as matrix
    // because int array occupies less space compared to string
    public int minPathSum(int[][] matrix) {
        map = new HashMap<>();
        minSumPathMatrix(0,0,matrix);
        return map.get("0-0");
    }

    private int minSumPathMatrix(int row, int col, int[][] matrix){
        int rowLimit = matrix.length;
        int colLimit = matrix[0].length;
        String key = row +"-"+ col;
        if(row >= rowLimit || col >= colLimit){
            return Integer.MAX_VALUE;
        }
        else if(!map.containsKey(key)){
            if(row == rowLimit-1 && col == colLimit-1){
                map.put(key, matrix[row][col]);
            }
            else{
                int right = minSumPathMatrix(row,col+1,matrix);
                int bottom = minSumPathMatrix(row+1,col,matrix);
                Integer value = Math.min(right, bottom)+matrix[row][col];
                map.put(key, value);
            }
        }
        return map.get(key);
    }

    //https://leetcode.com/problems/minimum-path-sum/solutions/3345991/try-ones-more-using-my-hints-easy-java-solution-2-approaches-recursive-and-iterative/
    public int minPathSumLeetCode(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int dp[][] = new int[grid.length][grid[0].length];
        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                if(i == 0 && j == 0) dp[i][j] = grid[i][j];
                else if(i == 0) dp[i][j] = dp[i][j-1] + grid[i][j];
                else if(j == 0) dp[i][j] = dp[i-1][j] + grid[i][j];
                else {
                    dp[i][j] = grid[i][j] + Math.min(dp[i][j-1], dp[i-1][j]);
                }
            }
        }
        return dp[n-1][m-1];
    }

    int dp[][] = null;
    public int minPathSumNew(int[][] matrix) {
        int rowLimit = matrix.length;
        int colLimit = matrix[0].length;
        dp = new int[rowLimit][colLimit];
        Arrays.stream(dp).forEach(a -> Arrays.fill(a, -1));
        minSumPathMatrixNew(0,0,matrix);
        return dp[0][0];
    }

    private int minSumPathMatrixNew(int row, int col, int[][] matrix){
        int rowLimit = matrix.length;
        int colLimit = matrix[0].length;
        if(row >= rowLimit || col >= colLimit){
            return Integer.MAX_VALUE;
        }
        else if(dp[row][col]==-1){
            if(row == rowLimit-1 && col == colLimit-1){
                dp[row][col] = matrix[row][col];
            }
            else{
                int right = minSumPathMatrixNew(row,col+1,matrix);
                int bottom = minSumPathMatrixNew(row+1,col,matrix);
                Integer value = Math.min(right, bottom)+matrix[row][col];
                dp[row][col] = value;
            }
        }
        return dp[row][col];
    }
}
