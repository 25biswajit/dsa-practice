package dsa.leetcode;

import dsa.utils.MatrixUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MaxSquareInMatrixDP {

    @Test
    public void test(){
        int[][] matrix = {
                {1,0,1,0,0},
                {1,0,1,1,1},
                {1,1,1,1,1},
                {1,0,0,1,0}
        };
        Assertions.assertEquals(4, maximalSquare(matrix));
    }
    public int maximalSquare(int[][] m) {
        int row = m.length;
        int col = m[0].length;
        int max = 0;
        int dp[][] = new int[row][col];

        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(i == 0 || j == 0 || m[i][j] == 0){
                    dp[i][j] = m[i][j];
                }
                else if(m[i][j] == 1){
                    dp[i][j] = 1 + min (
                            dp[i-1][j],
                            dp[i][j-1],
                            dp[i-1][j-1]
                    );

                    max = Math.max( dp[i][j], max);
                }
            }
        }

        MatrixUtils.printMatrix(dp);

        return max * max;
    }

    private int min(int a, int b, int c){
        return Math.min(Math.min(a, b), c);
    }
}
