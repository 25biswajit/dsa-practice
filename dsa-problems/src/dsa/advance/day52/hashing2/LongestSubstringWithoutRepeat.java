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

/*Method 1- Brute Force:

We can consider all substrings one by one and check for each substring whether it contains all unique characters or not. There will be n*(n+1)/2 substrings. Whether a substring contains all unique characters or not can be checked in linear time by scanning it from left to right and keeping a map of visited characters.
Time complexity of this solution would be O(n^3).

Method 2- Better:

The idea is to use window sliding. Whenever we see repetition, we remove the previous occurrence and slide the window.
Time complexity of this solution would be O(n^2).

Method 3- Optimized:

This solution uses extra space to store the last indexes of already visited characters.
The idea is to scan the string from left to right,
keep track of the maximum length Non-Repeating Character Substring seen so far in res.
When we traverse the string, to know the length of current window we need two indexes.

1) Ending index ( j ) : We consider current index as ending index.
2) Starting index ( i ) : It is same as previous window if current character was not present in the previous window.
To check if the current character was present in the previous window or not, we store last index of every character
in an array lasIndex[]. If lastIndex[str[j]] + 1 is more than previous start,
then we updated the start index i. Else we keep same i.

Time Complexity: O(n + d) where n is length of the input string and d is number of characters in input string alphabet.
For example, if string consists of lowercase English characters then value of d is 26.
Auxiliary Space: O(d)*/
