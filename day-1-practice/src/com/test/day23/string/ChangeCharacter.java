package com.test.day23.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ChangeCharacter {
    @Test
    public void test1(){
        Assertions.assertEquals(2, solve("abcabbccd",3));
    }

    public int solve(String word, int b) {
        int count = 0;
        // Create Char Array with Count
        int[] dictionary = new int[26];
        char[] wordArray = word.toCharArray();
        for(char ch:wordArray){
            int index = ch - 'a';
            dictionary[index]++;
        }
        // Sort Char Array based on Count
        Arrays.sort(dictionary);

        // Replace min occurred letters
        int i = 0;
        while (b > 0){
            if(dictionary[i] == 0){
                i++;
            }else {
                // Reduce Count at current index
                dictionary[i]--;
                b--;
            }
        }

        // Get final count of distinct letters
        for(int j = 0;j<dictionary.length;j++){
            if(dictionary[j]>0){
                count++;
            }
        }
        return count;
    }
}
