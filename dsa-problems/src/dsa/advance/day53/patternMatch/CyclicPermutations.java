package dsa.advance.day53.patternMatch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/*
Given two binary strings A and B, count how many cyclic shift of B when taken XOR with A give 0.
NOTE: If there is a string, S0, S1, ... Sn-1 , then it is a cyclic shift is of the form Sk, Sk+1, ... Sn-1, S0, S1, ... Sk-1 where k can be any integer from 0 to N-1.
Input 1:
A = "1001", B = "0011", Output : 1
A = "1010", B = "1010", Output : 2
*/

public class CyclicPermutations {
    @Test
    public void test1(){
        String A = "1001", B = "0011";
        Assertions.assertEquals(1, countCyclicRotation(A,B));
        Assertions.assertEquals(1, solve(A,B));
    }
    @Test
    public void test2(){
        String A = "1010", B = "1010";
        Assertions.assertEquals(2, countCyclicRotation(A,B));
        Assertions.assertEquals(2, solve(A,B));
    }

    // TC: O(N) : My Solution
    public int countCyclicRotation(String A, String B) {
        String text = B+B;
        String pattern = A;
        String combinedText = pattern + "_" + text;
        int[] LPS = deriveLPSArray(combinedText);
        long count = Arrays.stream(LPS).filter(e -> e==pattern.length()).count();
        if(A.equals(B)){
            count--;
        }
        return count > 0 ? (int)count : 0;
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

    // Other Solution
    public int solve(String A, String B) {
        // append B to B to tackle cyclic permutations
        B += B;
        int n = A.length(), m = B.length();
        int lps[] = deriveLPSArray(A);
        int i = 0, l = 0, ans = 0;
        while (i < m - 1) {
            if (B.charAt(i) == A.charAt(l)) {
                i++;
                l++;
            }
            if (l == n) {
                l = lps[l - 1];
                ans++;
            } else if (i < m && B.charAt(i) != A.charAt(l)) {
                if (l > 0) {
                    l = lps[l - 1];
                } else {
                    i++;
                }
            }
        }
        return ans;
    }
}

/*
Explanation: Solve2 **
We know that the Xor of two elements is zero only when both the elements are equal.
We have to check if the A == B or not.
To overcome the cyclic shift problem, we can append the string A[0:len(A)-1] to A
For example : if A = “abc”, After appending the string A[0:len(A)-1] to A, A becomes “abcab”.
Now, we have to find the number of occurrences of the string B in string A.
We can use any string matching algorithm like the KMP Z algorithm to find the number of occurrences
*/
