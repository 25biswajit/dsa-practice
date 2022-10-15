package dsa.advance.day52.hashing2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
You are given a string A. Find the number of substrings that start and end with 'a'.
A = "aab"
Substrings that start and end with 'a' are:
    1. "a"
    2. "aa"
    3. "a"
*/

public class CountSubstringStartEndWithA {
    @Test
    public void test1(){
        String word = "abcabcabcabcabcabcabcabcabcabc";
        String charA = "a";
        Assertions.assertEquals(55, countSubstringA(word,charA));
    }

    // TC: O(N), SC:O(1)
    public int countSubstringA(String word, String charA){
        int count = 0;
        for(String s : word.split("")){
            if(s.equals(charA)){
                count++;
            }
        }
        count = count + (count * (count - 1))/2;
        return count;
    }
}
