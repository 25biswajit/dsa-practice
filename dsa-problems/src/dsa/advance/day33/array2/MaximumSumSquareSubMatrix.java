package dsa.advance.day33.array2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MaximumSumSquareSubMatrix {
    @Test
    public void test(){
        int [][] matrix = {{1, 1, 1, 1, 1},{2, 2, 2, 2, 2},{3, 8, 6, 7, 3},{4, 4, 4, 4, 4},{5, 5, 5, 5, 5}};
        Assertions.assertEquals(48, solve(matrix,3));
    }

    public int solve(int[][] matrix, int B) {
        int rowMax = matrix.length-1;
        int colMax = matrix[0].length-1;
        int max = Integer.MIN_VALUE;
        long[][] matrixPrefixSum = SubMatrixSumQueriesPrefixsum.prefixSumMatrix(matrix);
        for(int i = 0 ; i+B-1 <= rowMax ; i++){
            for(int j = 0 ; j+B-1 <= colMax ; j++){
                int tx = i;
                int ty = j;
                int bx = i+B-1;
                int by = j+B-1;
                int ans = (int)SubMatrixSumQueriesPrefixsum.getSubMatrixSumByQueryOptimized(tx,ty,bx,by,matrixPrefixSum);
                max = Math.max(ans, max);
            }
        }
        return max;
    }
}
