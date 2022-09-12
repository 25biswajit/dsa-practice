package dsa.advance.day33.array2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MaxSumRowColSortedMatrix {
    @Test
    public void test1(){
        int[][] rowColSortedMatrix = {{1, 2, 3},{4, 5, 6},{7, 8, 9}};
        long result = maxSumRowColSortedMatrix(rowColSortedMatrix);
        Assertions.assertEquals(45, result);
    }
    @Test
    public void test2(){
        int[][] rowColSortedMatrix = {{-76, -73, -46, -42, -38},{-18, -3, 72, 88, 93}};
        long result = maxSumRowColSortedMatrix(rowColSortedMatrix);
        Assertions.assertEquals(253, result);
    }

    public long maxSumRowColSortedMatrix(int[][] matrix){
        int row = matrix.length;
        int col = matrix[0].length;
        long max = Long.MIN_VALUE;
        long[][] psm = SubMatrixSumQueriesPrefixsum.prefixSumMatrix(matrix);// O(N * M)
        // As matrix is row & col wise sorted it is quite obvious the bottom rights will (N-1)(M-1)
        int bx = row - 1;
        int by = col - 1;
        for (int i = 0 ; i < row ; i++){
            for (int j = 0 ; j < col ; j++){
                int tx = i;
                int ty = j;
                long ans = SubMatrixSumQueriesPrefixsum.getSubMatrixSumByQueryOptimized(tx,ty,bx,by,psm);
                max = Math.max(ans, max);
            }
        }
        return max;
    }
}
