package dsa.advance.day33.array2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class MaxSumSubMatrix {
    @Test
    public void test1(){
        int[][] matrix =
                { { 0, -2, -7, 0 },
                  { 9, 2, -6, 2 },
                  { -4, 1, -4, 1 },
                  { -1, 8, 0, -2 } };

        Assertions.assertEquals(15, solve(matrix));
    }

    @Test
    public void test2(){
        int[][] matrix = { {1, -9, -10, 1}, {-1, 10, 10, 1}, {0, 9, 9, -9}, {-1, -1, -1, -1}};
        Assertions.assertEquals(38, solve(matrix));
    }

    @Test
    public void test3(){
        int[][] matrix = { {11}};
        Assertions.assertEquals(11, solve(matrix));
    }

    @Test
    public void test4(){
        int[][] matrix = {{-6, -13}, {-19, -18}};
        Assertions.assertEquals(-6, solve(matrix));
    }

    // O(N3)
    public int solve(int[][] matrix) {

        int ROW = matrix.length;
        int COL = matrix[0].length;
        int maxSum = Integer.MIN_VALUE;
        int[] sum = new int[ROW];

        for(int cStart = 0 ; cStart < COL ; cStart++){
            Arrays.fill(sum, 0);
            for(int cEnd = cStart; cEnd < COL ; cEnd++){
                for(int r = 0; r < ROW; r++){
                    sum[r] += matrix[r][cEnd];
                }
                maxSum = Integer.max(maxSum , kadanes(sum));
            }
        }
        return maxSum;
    }

    private int kadanes(int[] array) {
        int currentSum = array[0];
        int maxSum = Integer.max(currentSum, Integer.MIN_VALUE);
        for(int i = 1 ; i < array.length ; i++){
            currentSum = Integer.max(array[i] , currentSum + array[i]);
            maxSum = Integer.max(currentSum, maxSum);
        }
        return maxSum;
    }
}
