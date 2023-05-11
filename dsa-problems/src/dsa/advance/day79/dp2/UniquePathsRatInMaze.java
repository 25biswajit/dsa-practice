package dsa.advance.day79.dp2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
/*
Unique Paths in a Grid
Given a grid of size n * m, lets assume you are starting at (1,1) and your goal is to reach (n, m).
At any instance, if you are on (x, y), you can either go to (x, y + 1) or (x + 1, y).
Now consider if some obstacles are added to the grids.
Return the total number unique paths from (1, 1) to (n, m).
Note: An obstacle is marked as 1 and empty space is marked 0 respectively in the grid.
*/

public class UniquePathsRatInMaze {
    Map<String, Integer> map = null;
    @Test
    public void test1(){
        int[][] matrix = {{0,0,0},{0,0,0},{0,0,0}};
        Assertions.assertEquals(6, uniquePathsWithObstacles(matrix));
    }
    @Test
    public void test2(){
        int[][] matrix = {{0,0,0},{0,1,0},{0,0,0}};
        Assertions.assertEquals(2, uniquePathsWithObstacles(matrix));
    }
    @Test
    public void test3(){
        int[][] matrix = {{0,1}};
        Assertions.assertEquals(0, uniquePathsWithObstacles(matrix));
    }
    @Test
    public void test4(){
        int[][] matrix = {{1,0}};
        Assertions.assertEquals(0, uniquePathsWithObstacles(matrix));
    }
    @Test
    public void test5(){
        int[][] matrix = {{0}};
        Assertions.assertEquals(1, uniquePathsWithObstacles(matrix));
    }

    public int uniquePathsWithObstacles(int[][] matrix) {
        map = new HashMap<>();
        uniquePathsRatInMaze(0,0,matrix);
        return map.get("0-0");
    }

    private int uniquePathsRatInMaze(int r, int c,int[][] matrix) {
        int rowLimit = matrix.length;
        int colLimit = matrix[0].length;
        String key = r +"-"+ c;
        if(r >= rowLimit || c >= colLimit || matrix[r][c] == 1){
            map.put(key, 0);
        }
        else if(r == rowLimit-1 && c == colLimit-1){
            map.put(key, 1);
        }
        else if(!map.containsKey(key)){
            int value = uniquePathsRatInMaze(r,c+1,matrix) + uniquePathsRatInMaze(r+1,c,matrix);
            map.put(key, value);
        }
        return map.get(key);
    }
}

/*
Suppose dp[i][j] represents the number of unique paths to reach cell (i, j).
To calculate dp[i][j], consider the following:
If cell (i, j) does not have an obstacle, the number of ways to reach this cell is the sum of the number of ways to reach the immediate neighbors preceding it (left and up).
If cells (i-1, j) and (i, j-1) both do not have obstacles, then dp[i][j] = dp[i-1][j] + dp[i][j-1].
If only cell (i-1, j) does not have an obstacle, then dp[i][j] = dp[i-1][j].
If only cell (i, j-1) does not have an obstacle, then dp[i][j] = dp[i][j-1].
If both cells (i-1, j) and (i, j-1) have obstacles, set dp[i][j] = 0.
Finally, the value of dp[n][m] will be the answer.
*/
