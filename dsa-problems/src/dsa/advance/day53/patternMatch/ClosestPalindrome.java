package dsa.advance.day53.patternMatch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
Given a string A of size N consisting of lowercase alphabets,
We will call a string ClosestPalindrome,
if it is possible to make the given string a palindrome by changing exactly one of its character.
A = "abbba" , YES
Explanation, We can change the character at index 3(1-based) to any other character. The string will be palindromic.
A = A = "adaddb" , NO
*/

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

/*
Check a String Palindrome or not ?
If Palindrome and Text length is ODD, Then Yes, We can change at least middle char to any char.
If Palindrome and Text length is Even, Then Not possible.
If Not  Palindrome:
We apply our standard palindrome checking algorithm : count the number of times a set of mirror indices has different characters.
If at the end of processing, count > 1 indicates we will have to change more than one character to make it a palindrome.
If the count is 1, the answer is always yes.
*/
