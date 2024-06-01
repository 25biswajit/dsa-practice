package dsa.leetcode.selected;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LCSubSeq_Print_Memo {

    @Test
    public void test1(){
        String a = "abcde", b = "ace";
        String res = longestCommonSubsequence(a,b);
        Assertions.assertEquals("ace", res);
    }

    public String longestCommonSubsequence(String a, String b) {
        int n = a.length();
        int m = b.length();
        String[][] dp = new String[n][m];
        return lcs(a,b,n-1,m-1,dp);
    }

    private String lcs(String a, String b, int i, int j, String[][] dp){
        if(i < 0 || j < 0) return "";
        if(dp[i][j] != null) return dp[i][j];

        String result;
        if(a.charAt(i)==b.charAt(j)){
            result = lcs(a,b,i-1,j-1,dp) + a.charAt(i);
        }else{
            String option1 = lcs(a,b,i,j-1,dp);
            String option2 = lcs(a,b,i-1,j,dp);
            result = option1.length() > option2.length() ? option1 : option2;
        }
        return dp[i][j] = result;
    }

}
