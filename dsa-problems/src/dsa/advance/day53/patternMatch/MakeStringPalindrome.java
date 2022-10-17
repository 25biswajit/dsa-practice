package dsa.advance.day53.patternMatch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
Given a string A of size N consisting only of lowercase alphabets.
The only operation allowed is to insert characters in the beginning of the string.
Find and return how many minimum characters are needed to be inserted to make the string a palindrome string.
Word = "bbc" we need to append "c" to the front of "bbc" to make it palindrome = "cbbc"
Word = "abc" we need to append "cb" to the front of "abc" to make it palindrome = "cbabc"
*/

public class MakeStringPalindrome {
    @Test
    public void test1(){
        Assertions.assertEquals(1, countAppendToFront("bbc"));
        Assertions.assertEquals(0, countAppendToFront("bbb"));
        Assertions.assertEquals(2, countAppendToFront("abc"));
    }

    // TC: O(N), SC:O(N)
     public int countAppendToFront(String text){
         String pattern = text;
         String combinedText = pattern + "@" + new StringBuilder(text).reverse();
         int[] LPSArray = computeLPSArray(combinedText);
         int longestPrefixPalindromeLength = LPSArray[LPSArray.length-1];
         return text.length() - longestPrefixPalindromeLength;
     }

    public static int[] computeLPSArray(String text){
        char[] word = text.toCharArray();
        int n = word.length;
        int LPS[] = new int[n];
        LPS[0] = 0;
        for(int i=1;i < n;i++){
            int x = LPS[i-1];
            while (word[i]!=word[x]){
                if(x==0){ x=-1;break;}
                x = LPS[x-1];
            }
            LPS[i]=x+1;
        }
        return LPS;
    }
}