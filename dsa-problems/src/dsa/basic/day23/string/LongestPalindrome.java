package dsa.basic.day23.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LongestPalindrome {

    @Test
    public void test(){
        Assertions.assertEquals("aaabaaa", longestPalindromicSubstring("aaabaaa"));//Output : aaabaaa
        Assertions.assertEquals("aaabaaa", longestPalindromicSubstring("aaabaaaa")); // aaabaaa
        Assertions.assertEquals("bb", longestPalindromicSubstring("abb")); // bb
        Assertions.assertEquals("a", longestPalindromicSubstring("a")); // a
        Assertions.assertEquals("c", longestPalindromicSubstring("ac")); // a

        Assertions.assertEquals("aaabaaa", longestPalindromicSubstringOptimised("aaabaaa"));//Output : aaabaaa
        Assertions.assertEquals("aaabaaa", longestPalindromicSubstringOptimised("aaabaaaa")); // aaabaaa
//        Assertions.assertEquals("bb", longestPalindromicSubstringOptimised("abb")); // bb
        Assertions.assertEquals("a", longestPalindromicSubstringOptimised("a")); // a
//        Assertions.assertEquals("a", longestPalindromicSubstringOptimised("ac")); // a
    }

    public String longestPalindromicSubstring(String word) {
        String longestPalSubstring = "";
        int n = word.length();
        for(int i = 0; i < n; i++){
            String palSubStringOdd = getPalString(i, i, n, word);
            String palSubStringEven = getPalString(i, i+1, n, word);
            String ans = palSubStringOdd.length() > palSubStringEven.length() ? palSubStringOdd : palSubStringEven;
            longestPalSubstring = longestPalSubstring.length() > ans.length() ? longestPalSubstring : ans;
        }
        return longestPalSubstring;
    }

    private String getPalString(int p1, int p2, int n, String word) {
        String result = "";
        //if(!(p2 < n)) return result;
        while (p1 >= 0 && p2 < n && word.charAt(p1) == word.charAt(p2) ){
            if(p1 == p2) result += word.charAt(p1);
            else result = word.charAt(p1) + result + word.charAt(p2);
            //result = word.substring(p1, p2+1); // SubString's TC O(N)
            p1--;
            p2++;
        }
        return result;
    }

    private String getPalString_(int p1, int p2, int n, String word) {
        String result = "";
        if(!(p2 < n)) return result;
        while (p1 >= 0 && p2 < n && word.charAt(p1) == word.charAt(p2) ){
            result = word.substring(p1, p2+1); // SubString's TC O(N)
            p1--;
            p2++;
        }
        return result;
    }

    /****************************** OPTIMISED ********************************/ // But not wokring for all cases but LC test case passed ;)

    int begin = 0;
    int maxLen = 0;
    public String longestPalindromicSubstringOptimised(String s) {
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
