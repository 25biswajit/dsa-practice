package dsa.leetcode.selected;

import dsa.utils.ArrayUtils;
import dsa.utils.MatrixUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MaximumLengthOfRepeatedSubarray {

    @Test
    public void findLength() {
        int[] a = {1,2,3,2,1};
        int[] b = {3,2,1,4,7};
        int c = maximumLengthOfRepeatedSubarray(a,b);
        Assertions.assertEquals(3, c);
    }

    public int maximumLengthOfRepeatedSubarray(int[] a, int[] b) {
        int n = a.length;
        int m = b.length;
        int[][] dp = new int[n][m];
        fillArray(dp, -1);
        return lcs(a,b,n-1,m-1,dp);
    }

    private int lcs(int[] a, int[] b, int i, int j, int[][] dp) {
        if (i < 0 || j < 0) return 0;
        if (dp[i][j] != -1) return dp[i][j];

        int result;
        if (a[i] == b[j]) {
            result = lcs(a, b, i - 1, j - 1, dp) + 1;
        } else {
            int option1 = lcs(a, b, i, j - 1, dp);
            int option2 = lcs(a, b, i - 1, j, dp);
            result = Integer.max( option1 , option2 );
        }
        return dp[i][j] = result;
    }

    public void fillArray(int[][] arr, int x) {
        for (int[] ints : arr) {
            Arrays.fill(ints, x);
        }
    }
}
