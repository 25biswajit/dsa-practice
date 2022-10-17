package dsa.advance.day53.patternMatch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
Given 2 strings A and B of size N and M respectively consisting of lowercase alphabets,
find the lexicographically smallest string that can be formed by concatenating non-empty prefixes of A and B (in that order).
Note: The answer string has to start with a non-empty prefix of string A followed by a non-empty prefix of string B.
A = "abba", B = "cdd" Ans = "abbac"
A = "abc", B = "adf" Ans = "aa"
A = "ef", B = "fg" Ans = "ef"
A = "efg", B = "abc" Ans = "ea"
*/

public class SmallestPrefixString {
    @Test
    public void test1(){
        String word1 = "abba";
        String word2 = "cdd";
        Assertions.assertEquals("abbac", smallestPrefix(word1,word2));
        Assertions.assertEquals("abbac", smallestPrefixMoreOptimised(word1,word2));
    }
    @Test
    public void test2(){
        String word1 = "abc";
        String word2 = "adf";
        Assertions.assertEquals("aa", smallestPrefix(word1,word2));
        Assertions.assertEquals("aa", smallestPrefixMoreOptimised(word1,word2));
    }
    @Test
    public void test3(){
        String word1 = "tom";
        String word2 = "riddle";
        Assertions.assertEquals("tomr", smallestPrefix(word1,word2));
        Assertions.assertEquals("tomr", smallestPrefixMoreOptimised(word1,word2));
    }

    // TC: O(A)
    public String smallestPrefix(String word1, String word2) {
        StringBuilder result = new StringBuilder("" + word1.charAt(0));
        int i = 1;
        int j = 0;
        while (i < word1.length() && j < word2.length()){
            char ch1 = word1.charAt(i);
            char ch2 = word2.charAt(j);
            String temp = "";
            if(ch1 < ch2){
                temp += ch1; i++;
            }else {
                temp += ch2; j++;
            }
            result.append(temp);

            if(j > 0){
                break;
            }
        }
        if(j == 0){
            result.append(word2.charAt(j));
        }
        return result.toString();
    }

    public String smallestPrefixMoreOptimised(String A, String B) {
        String ans = "";
        int i = 1;
        ans += A.charAt(0);
        while (i < A.length() && A.charAt(i) < B.charAt(0)) {
            ans += A.charAt(i);
            i++;
        }
        ans += B.charAt(0);
        return ans;
    }
}

/*
Main Qs : how many characters of the first string should be appended before taking the first character of the second string?
Formally, what length prefix of the first string should be appended to the answer string to make it lexicographically smallest
before adding the first character of the second string.
we keep appending characters from the first string till the current character is less than the first character of the second string.
After that, we add the first character of the second string, and we have our answer.
Idea :
You have to concatenate non-empty prefixes of A and B, in that order.
So here A= abba C= cdd
As we have to take at least a single element from A and B both, we take ans=A[0],
then we compare A[1] and B[0], as A[1] is less than B[0], we take A[1] too.. and so on.,
and as we have to take atleast single element from B, so we take B[0] and concatenate to our and string.
*/
