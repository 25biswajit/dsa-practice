package dsa.leetcode.selected;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class LCSubSeq_Print_Tabu {

    @Test
    public void test1(){
        String a = "abcde", b = "ace";
        String res = longestCommonSubsequence(a,b);
        Assertions.assertEquals("ace", res);
    }

    private String longestCommonSubsequence(String a, String b) {
        int n= a.length(), m = b.length();
        int[][] dp = new int[n+1][m+1];
        findLongestCommonSubSeqLength(a,b,dp);
        String result = "";
        int i = n, j = m;
        while(i > 0 && j > 0){
            if(a.charAt(i-1) == b.charAt(j-1)){
                result = a.charAt(i-1) + result;
                i--;
                j--;
            }
            else {
                int len1 = dp[i-1][j];
                int len2 = dp[i][j-1];
                if(len1 >= len2) i--;
                else j--;
            }
        }
        return result;
    }

    public int findLongestCommonSubSeqLength(String a, String b, int[][] dp){
        int n= a.length(), m = b.length();
        Arrays.stream(dp).forEach(ar -> Arrays.fill(ar, -1));
        // fill 1st row with zero
        Arrays.fill(dp[0], 0);
        //fill 1st col with zero
        for(int i = 0; i <=m; i++){
            dp[i][0] = 0;
        }
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                if(a.charAt(i-1)==b.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[n][m];
    }

}
