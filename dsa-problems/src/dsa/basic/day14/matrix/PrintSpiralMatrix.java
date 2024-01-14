package dsa.basic.day14.matrix;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/spiral-matrix/description/
public class PrintSpiralMatrix {

    @Test
    public void test1() {
        int[][] matrix = new int[][] {
                new int[] { 1, 2, 3, 4 },
                new int[] { 5, 6, 7, 8 },
                new int[] { 9, 10, 11, 12 },
        };
        List<Integer> list = spiralOrder(matrix);
        Assertions.assertEquals(Arrays.asList(1,2,3,4,8,12,11,10,9,5,6,7), list);
    }

    @Test
    public void test2() {
        int[][] matrix = new int[][] {
                new int[] { 1 },
        };
        List<Integer> list = spiralOrder(matrix);
        Assertions.assertEquals(Arrays.asList(1), list);
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        int limit = matrix.length * matrix[0].length;
        int minrow = 0;
        int mincol = 0;
        int maxrow = matrix.length-1;
        int maxcol = matrix[0].length-1;
        while(limit > 0){
            // Upper wall
            for(int i=minrow,j=mincol;j<=maxcol && limit > 0;j++){
                list.add(matrix[i][j]);
                limit--;
            }
            minrow++;
            // Right wall
            for(int i=minrow,j=maxcol;i<=maxrow && limit > 0;i++){
                list.add(matrix[i][j]);
                limit--;
            }
            maxcol--;
            // Bottom wall
            for(int i=maxrow,j=maxcol;j>=mincol && limit > 0;j--){
                list.add(matrix[i][j]);
                limit--;
            }
            maxrow--;
            // Left wall
            for(int i=maxrow,j=mincol;i>=minrow && limit > 0;i--){
                list.add(matrix[i][j]);
                limit--;
            }
            mincol++;
        }
        return list;
    }
}
