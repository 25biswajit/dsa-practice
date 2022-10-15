package dsa.advance.day52.hashing2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

/*
Given a string A, find the length of the longest substring without repeating characters.
Note: Users are expected to solve in O(N) time complexity.
A = "abcabcbb"
Ans : Substring "abc" is the longest substring without repeating characters in string A.
*/
public class LongestSubstringWithoutRepeat {
    @Test
    public void test(){
        String word = "abcdcba";
        Assertions.assertEquals(4, lengthOfLongestSubstring(word));
    }

    public int lengthOfLongestSubstring(String word) {
        int i = 0, j = 0, n = word.length(), ans = 0;
        HashSet<Character> set = new HashSet<>();
        while (j < n){
            if (!set.contains(word.charAt(j))){
                set.add(word.charAt(j));
                ans = Integer.max(ans, set.size());
                j++;
            }else {
                set.remove(word.charAt(i));
                i++;
            }
        }
        return ans;
    }
}
