package dsa.advance.day83.dp6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class LongestPalindromicSubSeq {
    int dp[][] = null;
    @Test
    public void test(){
        String s = "bbbab";
        int result = longestPalindromicSubSeq(s);
        Assertions.assertEquals(result, 4);
    }

    // TC O(N) + O(N^2), SC : O(N^2)
    public int longestPalindromicSubSeq(String s) {
        dp = new int[s.length()][s.length()];
        Arrays.stream(dp).forEach(a -> Arrays.fill(a, -1));
        // O(N)
        String reversedString = new StringBuilder(s).reverse().toString();
        return longestCommonSubSeq(s, reversedString, s.length()-1, s.length()-1);
    }

    // O(N^2)
    private int longestCommonSubSeq(String s1, String s2, int i, int j){
        if(i<0 || j<0) return 0;
        if(dp[i][j]==-1){
            int res;
            if(s1.charAt(i)==s2.charAt(j)){
                res = 1 + longestCommonSubSeq(s1,s2,i-1,j-1);
            }
            else {
                int a = longestCommonSubSeq(s1,s2,i-1,j);
                int b = longestCommonSubSeq(s1,s2,i,j-1);
                res = Integer.max(a,b);
            }
            dp[i][j] = res;
        }
        return dp[i][j];
    }
}
/*
Approach:
The algorithm is stated as follows:
We are given a string (say s), make a copy of it and store it( say string t).
Reverse the original string s.
Find the longest common subsequence
*/

