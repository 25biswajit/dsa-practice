package dsa.leetcode.selected;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class LCSubSeqLen_Memo_Tabu {
    String common;
    @Test
    public void test1(){
        common = new String();
        String a = "abcde", b = "ace";
        int res = longestCommonSubsequence(a,b);
        Assertions.assertEquals(3, res);
        Assertions.assertEquals(3, lcs_Interative(a,b));
    }

    public int longestCommonSubsequence(String a, String b) {
        int n = a.length();
        int m = b.length();
        int[][] dp = new int[n][m];
        fillArray(dp, -1);
        return lcs(a,b,n-1,m-1,dp);
    }

    private int lcs(String a, String b, int i, int j, int[][]dp){
        if(i < 0 || j < 0) return 0;
        if(dp[i][j]!=-1) return dp[i][j];

        int result;
        if(a.charAt(i)==b.charAt(j)){
            result = 1 + lcs(a,b,i-1,j-1,dp);
        }else{
            int option1 = lcs(a,b,i,j-1,dp);
            int option2 = lcs(a,b,i-1,j,dp);
            result = Integer.max(option1, option2);
        }
        return dp[i][j] = result;
    }

    public void fillArray(int[][] array, int value) {
        for (int[] ints : array) {
            Arrays.fill(ints, value);
        }
    }

    public int lcs_Interative(String a, String b){
        String result = "";
        int n= a.length(), m = b.length();
        int[][] dp = new int[n+1][m+1];
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
