package dsa.advance.day82.dp5;

import dsa.utils.MatrixUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LongestCommonSubString {

    @Test
    public void test(){
        String s1 = "abcdaf";
        String s2 = "zbcdf";
        int ans = findLongestCommonSubString(s1,s2);
        Assertions.assertEquals(3, ans);
    }

    int dp[][] = null;
    public int findLongestCommonSubString(String A, String B){
        int n = A.length();
        int m = B.length();
        dp = new int[n+1][m+1]; // 0

        for(int i = 1; i < n+1; i++){
            for(int j = 1; j < m+1; j++){
                if(A.charAt(i-1) == B.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }
            }
        }
        MatrixUtils.printMatrix(dp);

        int max = Integer.MIN_VALUE; // Initialize max to the smallest integer value

        for (int[] row : dp) {
            for (int value : row) {
                if (value > max) {
                    max = value; // Update max if current value is greater
                }
            }
        }

        return max;
    }
}
