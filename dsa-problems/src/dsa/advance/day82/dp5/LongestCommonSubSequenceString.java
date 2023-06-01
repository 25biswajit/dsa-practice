package dsa.advance.day82.dp5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class LongestCommonSubSequenceString {

    @Test
    public void test1(){
        String s1 = "KLAGRIP";
        String s2 = "LGIGKM";
        String result = printLongestCommonSubSeq(s1,s2);
        Assertions.assertEquals("LGI",result);
    }

    @Test
    public void test2(){
        String s1 = "MAICA";
        String s2 = "IAIDAS";
        String result = printLongestCommonSubSeq(s1,s2);
        Assertions.assertEquals("AIA",result);
    }

    @Test
    public void test3(){
        String s1 = "IAIDAS";
        String s2 = "MAICA";
        String result = printLongestCommonSubSeq(s1,s2);
        Assertions.assertEquals("AIA",result);
    }

    int dp[][] = null;
    public String printLongestCommonSubSeq(String word1, String word2){
        // Populate DP Table
        lengthLongestCommonSubSequence(word1,word2);

        int i = word1.length()-1;
        int j = word2.length()-1;
        String result = "";
        while (i >= 0 && j >= 0 && dp[i][j]!=0){
            if(word1.charAt(i) == word2.charAt(j)){
                result = word1.charAt(i) + result;
                i--;
                j--;
            }
            else {
                if(i > 0 && j > 0 && dp[i-1][j] > dp[i][j-1]){
                    i--;
                }
                else {
                    j--;
                }
            }
        }

        return result;
    }


    public int lengthLongestCommonSubSequence(String s1, String s2){
        dp = new int[s1.length()][s2.length()];
        Arrays.stream(dp).forEach(a -> Arrays.fill(a, -1));
        return lengthLongestCommonSubSequence(s1,s2,s1.length()-1, s2.length()-1);
    }

    public int lengthLongestCommonSubSequence(String s1, String s2, int i, int j){
        if(i<0 || j <0) return 0;
        if(dp[i][j]==-1) {
            if (s1.charAt(i) == s2.charAt(j)) {
                dp[i][j] = 1 + lengthLongestCommonSubSequence(s1, s2, i-1, j-1);
            } else {
                //Case 1
                int length1 = lengthLongestCommonSubSequence(s1, s2, i-1, j);
                //Case 2
                int length2 = lengthLongestCommonSubSequence(s1, s2, i, j-1);
                dp[i][j] = Math.max(length1, length2);
            }
        }
        return dp[i][j];
    }

}
