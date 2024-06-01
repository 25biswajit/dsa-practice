package dsa.leetcode.graph;

import dsa.model.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

public class RottenOranges {
    @Test
    public void test1(){
        int[][] grid = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        Assertions.assertEquals(4, orangesRotting(grid));
    }

    public int orangesRotting(int[][] grid) {
        int freshOranges = 0;
        Queue<Pair<Integer,Integer>> queue = new LinkedList<>();
        int n = grid.length, m = grid[0].length;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m;j++){
                if(grid[i][j] == 2) {
                    queue.add(new Pair<>(i,j));
                }else if(grid[i][j] == 1) {
                    freshOranges++;
                }
            }
        }
        if(freshOranges == 0) return 0;
        int minElapsed = 0;
        int[][] dirs = {{-1,0},{0,1},{1,0},{0,-1}};
        while (!queue.isEmpty() && freshOranges > 0){
            minElapsed++;
            int size = queue.size();
            for(int i = 0; i < size; i++){
                Pair<Integer,Integer> top = queue.poll();
                for(int j = 0; j < 4; j++){
                    int x = top.first + dirs[j][0];
                    int y = top.second + dirs[j][1];
                    if(x >= 0 && y >= 0 && x < n && y < m && grid[x][y] == 1){
                        grid[x][y] = 2;
                        freshOranges--;
                        queue.add(new Pair<>(x,y));
                    }
                }
            }
        }

        return freshOranges > 0 ? -1 : minElapsed;
    }

}
