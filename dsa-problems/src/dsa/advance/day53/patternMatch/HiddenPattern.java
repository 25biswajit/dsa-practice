package dsa.advance.day53.patternMatch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/*
Given two strings - a text A and a pattern B, having lower-case alphabetic characters.
You have to determine the number of occurrences of pattern B in text A as its substring. i.e. the number of times B occurs as a substring in A.
Input 1:
A = "abababa"
B = "aba"
A has 3 substrings equal to B - A[1, 3], A[3, 5] and A[5, 7]
*/

public class HiddenPattern {
    @Test
    public void test1(){
        String text = "abababa";
        String pattern = "aba";
        Assertions.assertEquals(3, countPattern(text, pattern));
    }
    @Test
    public void test2(){
        String text = "mississipi";
        String pattern = "ss";
        Assertions.assertEquals(2, countPattern(text, pattern));
    }
    @Test
    public void test3(){
        String text = "hello";
        String pattern = "hi";
        Assertions.assertEquals(0, countPattern(text, pattern));
    }

    // TC: O(N), SC: O(N)
    public int countPattern(String text, String pattern){
        String combinedText = pattern + "_" + text;
        int[] LPS = deriveLPSArray(combinedText);
        int count = (int) Arrays.stream(LPS).filter(x -> x == pattern.length()).count();
        return count;
    }

    public static int[] deriveLPSArray(String text){
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

    // Some New Approach Not Understood
    /*To solve the problem, we know that we need to compare each substring of A having length	B	of A, with B efficiently.
    We can solve the problem using the technique of “Rolling hash function” through Rabin-Karp algorithm.

    We want to solve the problem of comparing strings efficiently. The brute force way of doing so is just to compare the letters of both strings, which has a time complexity of O(min(n1,n2)), if n1 and n2 are the sizes of the two strings. We want to do better.
    The idea behind strings is the following:
    We convert each string into an integer and compare those instead of the strings.
    Comparing two strings is then an O(1) operation.

    For the conversion, we need a so-called hash function. The goal of it is to convert a string into an integer, the so-called hash of the string.
    The following condition has to hold:
            if two strings s and t are equal (s = t), then also their hashes have to be equal (hash(s)=hash(t)).
    Otherwise, we will not be able to compare strings.

    We can define hash of string s having length n as:

    hash(s) = (s[n - 1] + s[n - 2] * p + s[n - 3] * p^2 + ...+ s[0] * p ^ (n - 1)) mod m
    ,where p and m are some chosen, positive numbers.
    Generally, the values used for p and mod are : p = 31 and mod = 1e9 + 9

    Now, as we know how to get hash value for a string, we can traverse through string A, and to find hash value of substring having length |B| and ending at index ‘i’, in O(1) time,
    we can use the hash value of substring having length |B| and ending at ‘i - 1’ , using the fact that only the leftmost character at index ‘i-m’ was removed and character
    at index ‘i’ was added to form the current string from previous one.*/
    public int countPatternNew(final String A, final String B) {
        int n = A.length();
        int m = B.length();

        int ans = 0;

        long p = 31;
        int mod = 1000000009;

        long hashB = 0;

        for (int i = 0; i < m; i++)
        {
            hashB = (((hashB * p) % mod ) + (B.charAt(i) - 'a' + 1)) % mod;
        }

        long curHashA = 0;
        long pp = 1;

        for (int i = 0; i < m - 1;i++){
            pp = (pp * p) % mod;
        }

        for (int i = 0; i < m; i++)
        {
            curHashA = ((curHashA * p) % mod + (A.charAt(i) - 'a' + 1)) % mod;
        }

        if(curHashA == hashB){
            ans++;
        }

        for (int i = m; i < n; i++){
            long prvHashA = curHashA;
            curHashA = ((((prvHashA - (A.charAt(i - m) - 'a' + 1) * pp) % mod) * p) % mod + (A.charAt(i) - 'a' + 1)) % mod;
            if(curHashA < 0) curHashA += mod;
            if(curHashA == hashB){
                ans++;
            }
        }

        return ans;
    }

}
