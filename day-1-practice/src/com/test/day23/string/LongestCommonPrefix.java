package com.test.day23.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class LongestCommonPrefix {
    @Test
    public void test(){
        ArrayList<String> list = new ArrayList<>(Arrays.asList("abcd", "abde", "abcf"));
        Assertions.assertEquals("ab", longestCommonPrefix(list));

        list = new ArrayList<>(Arrays.asList("abcd", "abcd", "abcd"));
        Assertions.assertEquals("abcd", longestCommonPrefix(list));
    }

    public String longestCommonPrefix(ArrayList<String> list) {
        String minLengthWord = findMinLenWord(list);
        int n = minLengthWord.length();
        String result = "";
        for(int i=0;i < n;i++){
            char ithCharInMin = minLengthWord.charAt(i);
            for(String s : list){
                char ithChar = s.charAt(i);
                if(ithChar != ithCharInMin){
                    return result;
                }
            }
            result = result + ithCharInMin;
        }
        return result;
    }

    private String findMinLenWord(ArrayList<String> list) {
        String minLengthWord = list.get(0);
        for(String s : list){
            if(s.length() < minLengthWord.length()){
                minLengthWord = s;
            }
        }
        return minLengthWord;
    }
}
