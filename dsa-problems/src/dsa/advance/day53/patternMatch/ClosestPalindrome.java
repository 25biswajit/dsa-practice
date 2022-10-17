package dsa.advance.day53.patternMatch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClosestPalindrome {
    @Test
    public void test(){
        Assertions.assertEquals("YES", isClosestPalindrome("abbba"));
        Assertions.assertEquals("NO", isClosestPalindrome("adaddb"));
        Assertions.assertEquals("YES", isClosestPalindrome("aaaaa"));
        Assertions.assertEquals("NO", isClosestPalindrome("abba"));
        Assertions.assertEquals("YES", isClosestPalindrome("aaaaaaaaaabaaaaaaaaa"));
    }

    // TC: O(N) SC: O(1)
    public String isClosestPalindrome(String text){
        // Palindrome ? Yes
        if(text.equals(new StringBuilder(text).reverse().toString())){
            return text.length()%2 == 1 ? "YES" : "NO";
        }
        // Palindrome ? No
        else {
            int i = 0;
            int j = text.length()-1;
            int count = 0;
            while (i < j){
                if(text.charAt(i)!=text.charAt(j)) { count++; }
                i++;j--;
            }
            return count > 1 ? "NO" : "YES";
        }
    }
}
