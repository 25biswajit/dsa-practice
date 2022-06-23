package com.dsa.practice.recursion;

import org.junit.jupiter.api.Test;

public class PrintPermutation {

    @Test
    void test(){
       PrintPermutation obj = new PrintPermutation();
       obj.main("ABC");
    }

    void main(String word){
        printPermutation(word, "");
    }

    void printPermutation(String word, String newWord){
        if(word.length() == 0){
            System.out.println(newWord);
        }

        for(int i=0; i<word.length();i++){
            newWord = newWord + word.charAt(i); // A, AB, ABC
            String left = word.substring(0,i);
            String right = word.substring(i+1);
            word = left + right; // BC, C, X
            printPermutation(word, newWord);
        }
    }
}
