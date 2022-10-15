package dsa.advance.day52.hashing2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;

/*
You are given two strings, A and B, of size N and M, respectively.
You have to find the count of all permutations of A present in B as a substring.
You can assume a string will have only lowercase letters. [ 1 <= N < M <= 10^5 ]
Input 1:
 A = "abc"
 B = "abcbacabc"
Permutations of A that are present in B as substring are:
    1. abc
    2. cba
    3. bac
    4. cab
    5. abc
    So ans is 5.
*/

public class PermutationsOfAinB {
    @Test
    public void test(){
        String wordA = "abc";
        String wordB = "abcbacabc";
        Assertions.assertEquals( 5, countPermutation(wordA,wordB));
    }


    public int countPermutation(String wordA, String wordB){
        int count = 0;
        HashMap<String, Integer> freqMapA = deriveFreqMap(wordA);
        HashMap<String, Integer> freqMapB = deriveFreqMap(wordB.substring(0, wordA.length()));

        if(freqMapA.equals(freqMapB)){
            count++;
        }
        int i = 1;
        int j = wordA.length();
        while (j < wordB.length()){
            delete(freqMapB,String.valueOf( wordB.charAt(i-1)));
            append(freqMapB,String.valueOf( wordB.charAt(j)));
            if(freqMapA.equals(freqMapB)){
                count++;
            }
            i++;
            j++;
        }
        return count;
    }

    private void append(HashMap<String, Integer> freqMapB, String s) {
        freqMapB.put(s, freqMapB.getOrDefault(s, 0)+1);
    }

    private void delete(HashMap<String, Integer> freqMapB, String s) {
        if(freqMapB.containsKey(s)){
            int f = freqMapB.get(s) - 1;
            if(f == 0) { freqMapB.remove(s); }
            else { freqMapB.put(s, f); };
        }
    }

    private HashMap<String, Integer> deriveFreqMap(String word) {
        HashMap<String, Integer> freqMap = new HashMap<>();
        Arrays.stream(word.split("")).forEach(s ->
                freqMap.put(s, freqMap.getOrDefault(s,0)+1)
        );
        return freqMap;
    }
}
