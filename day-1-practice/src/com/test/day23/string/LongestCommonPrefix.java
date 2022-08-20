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

    public String longestCommonPrefix(ArrayList<String> A) {
        String minLengthWord = A.get(0);

        for(String s : A){
            if(s.length() < minLengthWord.length()){
                minLengthWord = s;
            }
        }
        int n = minLengthWord.length();
        int i = 0;
        boolean flag = false;
        String result = "";
        while(i < n && !flag){
            char ithCharInMin = minLengthWord.charAt(i);
            for(String s : A){
                char ithChar = s.charAt(i);
                if(ithChar != ithCharInMin){
                    flag = true;
                    break;
                }
            }
            if(!flag){
                result = result + String.valueOf(ithCharInMin);
                i++;
            }
        }
        return result;
    }
}
