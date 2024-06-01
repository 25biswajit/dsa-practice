package dsa.advance.day83.dp6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class LongestPalindromicSubSeq_2 {
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
        return longestPalindromicSubSeq(s, 0, s.length()-1);
    }

    // O(N^2)
    private int longestPalindromicSubSeq(String s, int i, int j){
        if(i>j) return 0;
        else if(j-i==0) return 1;
        /*else if(j-i==1){
            if(s.charAt(i)==s.charAt(j)) return 2;
            else return 1;
        }*/
        else {
            if(dp[i][j]==-1){
                if(s.charAt(i)==s.charAt(j)){
                    dp[i][j] = 2 + longestPalindromicSubSeq(s, i+1, j-1);
                }
                else {
                    dp[i][j] = Integer.max(longestPalindromicSubSeq(s, i+1, j),
                            longestPalindromicSubSeq(s, i, j-1));
                }
            }
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

