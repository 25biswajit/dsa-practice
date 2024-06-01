package dsa.advance.day83.dp6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class MatrixChainMultiplication {

    @Test
    public void test(){
        int[] array = {40, 20, 30, 10, 30};
        int result = matrixChainMultiplication(array);
        Assertions.assertEquals(26000, result);
    }

    int[][] dp = null;
    public int matrixChainMultiplication(int[] array) {
        dp = new int[array.length][array.length];
        Arrays.stream(dp).forEach(a->Arrays.fill(a,-1));
        return matrixChainMultiplication(array, 1, array.length-1);
    }

    private int matrixChainMultiplication(int[] array, int i, int j) {
        if(i==j) return 0;
        if(dp[i][j]==-1){
            int c = Integer.MAX_VALUE;
            for(int k = i; k < j;k++){
                int r = matrixChainMultiplication(array, i , k)
                        + matrixChainMultiplication(array, k+1 , j)
                        + array[i-1] * array[k] * array[j];
                c = Integer.min(c, r);
            }
            dp[i][j] = c;
        }
        return dp[i][j];
    }
}
