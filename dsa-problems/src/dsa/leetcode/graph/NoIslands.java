package dsa.leetcode.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NoIslands {
    
    @Test
    public void test(){
        char [][] grid = {
            {'1', '1', '0', '0', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '1', '0', '0'},
            {'0', '0', '0', '1', '1'}
        };
        Assertions.assertEquals(3, numIslands(grid));
    }

    public int numIslands(char[][] grid) {
        int n = grid.length, m  = grid[0].length, count = 0;
        for(int i = 0; i < n;i++){
            for(int j = 0;j < m; j++){
                if(grid[i][j] == '1'){
                    dfs(grid, i , j);
                    count++;
                }
            }
        }
        return count;
    }
    private void dfs(char[][] grid, int i, int j){
        int n = grid.length, m  = grid[0].length;
        if(i < 0 || j < 0 || i >= n || j >= m || grid[i][j] == '0') return;
        grid[i][j] = '0';
        dfs(grid, i-1, j);
        dfs(grid, i, j+1);
        dfs(grid, i+1, j);
        dfs(grid, i, j-1);
    }
}
