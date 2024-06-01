package dsa.advance.day82.dp5;

import dsa.utils.MathUtils;
import dsa.utils.MatrixUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
/*
Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.
A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without c
hanging the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".
A common subsequence of two strings is a subsequence that is common to both strings.

Example 1:
Input: text1 = "abcde", text2 = "ace"
Output: 3
Explanation: The longest common subsequence is "ace" and its length is 3.
Example 2:

Input: text1 = "abc", text2 = "abc"
Output: 3
Explanation: The longest common subsequence is "abc" and its length is 3.
Example 3:

Input: text1 = "abc", text2 = "def"
Output: 0
Explanation: There is no such common subsequence, so the result is 0.
*/


public class LongestCommonSubSequenceLength {

    @Test
    public void test1(){
        /*String s1 = "abbcdgf";
        String s2 = "bachegf";*/
        String s1 = "acgf";
        String s2 = "adgf";
        int result = lengthLongestCommonSubSequence(s1,s2);
        Assertions.assertEquals(3,result);
    }

    // TC O(N * M), SC O(N * M)
    int dp[][] = null;
    public int lengthLongestCommonSubSequence(String s1, String s2){
        dp = new int[s1.length()][s2.length()];
        Arrays.stream(dp).forEach(a -> Arrays.fill(a, -1));
        int ans  = lengthLongestCommonSubSequence(s1,s2,s1.length()-1, s2.length()-1);
        MatrixUtils.printMatrix(dp);
        return ans;
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
/*
Suppose LCS[i][j] represents the longest common subsequence of A[1..i] and B[1..j]
A[1..i] represents first i characters of string A
A[1..j] represents first j characters of string B

For every i, j we have two conditions A[i] == B[j] or not. Divide the problem based on this condition.

Recursion relation to divide the problem into smaller sub problems can be written as:-

LCS(i, j) = maximum (LCS(i-1, j-1] + 1,       //if(A[i] = B[j])
LCS(A[i-1], B[j],
LCS(A[i], B[j-1] )
LCS[ len(A) ][ len(B) ] will be our answer.

Think about the time complexity of this solution.
*/

