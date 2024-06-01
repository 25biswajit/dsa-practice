package dsa.advance.day82.dp5;

import dsa.utils.MatrixUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LongestCommonSubSequenceString {

    @Test
    public void test1(){
        String s1 = "KLAGRIP";
        String s2 = "LGIGKM";
        /*String s1 = "acgf";
        String s2 = "adgf";*/
        String result = printLongestCommonSubSeq(s1,s2);
        Assertions.assertEquals("LGI",result);
        //Assertions.assertEquals("agf",result);
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


    Map<String, String> map; // Map to store LCS strings
    public String printLongestCommonSubSeq_(String text1, String text2) {
        map = new HashMap<>();
        return lcs(text1.toCharArray(), text2.toCharArray(), text1.length() - 1, text2.length() - 1);
    }

    private String lcs(char[] ch1, char[] ch2, int i, int j) {
        if (i < 0 || j < 0) return ""; // Base case: empty string for no common subsequence
        String key = i + "." + j;
        if (map.containsKey(key)) return map.get(key);
        String value;
        if (ch1[i] == ch2[j]) {
            // If characters match, append the character to the LCS and recursively call for the previous characters
            value = lcs(ch1, ch2, i - 1, j - 1) + ch1[i];
        } else {
            // If characters don't match, choose the longer LCS by excluding one character at a time
            String excludeI = lcs(ch1, ch2, i, j - 1);
            String excludeJ = lcs(ch1, ch2, i - 1, j);
            value = excludeI.length() > excludeJ.length() ? excludeI : excludeJ;
        }
        map.put(key, value);
        return value;
    }

    public String printLongestCommonSubSeq(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        // Initialize DP table to store the length of LCS for each pair of indices
        int[][] dp = new int[m + 1][n + 1];

        // Populate DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1; // Characters match, increment LCS length
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]); // Characters don't match, choose max of left or top cell
                }
            }
        }

        // Reconstruct LCS string using DP table
        StringBuilder lcs = new StringBuilder();
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                lcs.insert(0, text1.charAt(i - 1)); // Insert character at the beginning of LCS string
                i--;
                j--;
            } else if (dp[i - 1][j] >= dp[i][j - 1]) {
                i--; // Move to the cell with the larger value
            } else {
                j--;
            }
        }

        return lcs.toString();
    }

}
