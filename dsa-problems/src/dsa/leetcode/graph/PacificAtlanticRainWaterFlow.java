package dsa.leetcode.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PacificAtlanticRainWaterFlow {

    @Test
    public void test(){
        int[][] matrix = {
                {1, 2, 2, 3, 5},
                {3, 2, 3, 4, 4},
                {2, 4, 5, 3, 1},
                {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4}
        };

        List<List<Integer>> expected = List.of(
            List.of(0, 4),List.of(1, 3),List.of(1, 4),List.of(2, 2),List.of(3, 0),List.of(3, 1),List.of(4, 0)
        );

        List<List<Integer>> result = pacificAtlantic(matrix);
        Assertions.assertEquals(expected.size(), result.size());

        for (List<Integer> coordinates : expected) {
            Assertions.assertTrue(result.contains(coordinates));
        }
    }

    int[][] dirs = {{-1,0}, {1,0}, {0,-1},{0,1}};
    public List<List<Integer>> pacificAtlantic(int[][] h) {
        List<List<Integer>> list = new ArrayList<>();
        int row = h.length;
        int col = h[0].length;
        boolean[][] pacific = new boolean[row][col];
        boolean[][] atlantic = new boolean[row][col];

        // DFS
        //Row wise
        for(int i = 0; i < row; i++){
            dfs(h, i, 0, Integer.MIN_VALUE, row, col, pacific);
            dfs(h, i, col-1, Integer.MIN_VALUE, row, col, atlantic);
        }
        //col wise
        for(int i = 0; i < col; i++){
            dfs(h, 0, i, Integer.MIN_VALUE, row, col, pacific);
            dfs(h, row-1, i, Integer.MIN_VALUE, row, col, atlantic);
        }

        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(pacific[i][j] && atlantic[i][j]){
                    list.add(Arrays.asList(i,j));
                }
            }
        }

        return list;
    }

    private void dfs(int[][] h, int i, int j, int prev, int row, int col, boolean[][] ocean){
        if(i < 0 || i >= row || j < 0 || j >= col) return;
        int current = h[i][j];
        if(current < prev) {
            System.out.println(i + "," + j + " Current:" + current + "Prev: "+ prev);
            return;
        }else {
            System.out.println("Flowing.... "+ i + "," + j + " Current:" + current + "Prev: "+ prev);
        }
        if(ocean[i][j]) return;
        ocean[i][j] = true;
        for(int d[] : dirs){
            dfs(h, i+d[0], j+d[1], current, row, col, ocean);
        }
    }
}
