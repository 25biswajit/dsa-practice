package dsa.leetcode.selected;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LongestPalindromicSubStr {
    LongestPalindromicSubStrSolution solution = new LongestPalindromicSubStrSolution();

    @Test
    public void test1(){
        String ans = solution.longestPalindrome("babad");
        Assertions.assertEquals("bab", ans);
    }

    @Test
    public void test2(){
        String ans = "babad";
        Assertions.assertEquals("aba", ans.substring(1, 4));
    }
}

class LongestPalindromicSubStrSolution{
    // Solution TC O(n2)
    int begin;
    int maxLen;
    public String longestPalindrome(String s) {
        int n = s.length();
        if(n < 2){
            return s;
        }
        for(int i = 0; i < n; i++){
            // odd length
            expandRange(s, i, i);
            // Even length
            expandRange(s, i, i+1);
        }
        return s.substring(begin, begin + maxLen);
    }

    private void expandRange(String s, int p1, int p2){
        int n = s.length();
        while(p1 >=0 && p2 < n && s.charAt(p1)==s.charAt(p2)){
            p2++;
            p1--;
        }
        int currentLength = p2 - p1 - 1;
        if(maxLen < currentLength){
            begin = p1+1;
            maxLen = currentLength;
        }
    }

}
