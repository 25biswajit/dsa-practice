package dsa.leetcode.selected;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class LongestPalindromicSubSeq {

    @Test
    public void test1(){
        String a = "bbbab";
        Assertions.assertEquals(4, longestPalindromicSubSeq(a));
    }

    public int longestPalindromicSubSeq(String a){
        int n = a.length();
        int[][] dp = new int[n][n];
        Arrays.stream(dp).forEach( arr -> Arrays.fill(arr,-1));
        return lps(a,dp, 0, n-1);
    }

    private int lps(String a, int[][] dp, int i, int j) {
        if(i > j) return 0;
        if(i == j && i == 1) return 1;
        if(dp[i][j]!=-1) return dp[i][j];
        int result = 0;
        if(a.charAt(i)==a.charAt(j)){
            result = 2 + lps(a,dp,i+1,j-1);
        }else {
            result = Math.max(lps(a,dp,i+1,j), lps(a,dp,i,j-1));
        }
        return dp[i][j] = result;
    }
}
