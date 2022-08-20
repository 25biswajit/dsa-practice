package com.test.day23.string;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ReverseWord {
    @Test
    public void test(){
        System.out.println(solve("Hello"));
    }

    public String solve(String A) {
        String[] sentenceArray = A.split("");
        int p1 = 0;
        int p2 = sentenceArray.length-1;
        while(p1 <= p2){
            swap(sentenceArray, p1, p2);
            p1++;
            p2--;
        }

        String result = "";
        for(String s : sentenceArray){
            result = result + s;
        }
        return result;
    }

    private void swap(String[] sentenceArray, int i, int j){
        String temp = sentenceArray[i];
        sentenceArray[i] = sentenceArray[j];
        sentenceArray[j] = temp;
    }
}
