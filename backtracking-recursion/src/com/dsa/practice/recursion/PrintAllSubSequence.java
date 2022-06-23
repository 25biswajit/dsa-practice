package com.dsa.practice.recursion;

import org.junit.jupiter.api.Test;

// Print all Sub Sequence for a String
// if String size is more than 2^32 more space will be required to store, so printed
public class PrintAllSubSequence {
    @Test
    void test(){
        PrintAllSubSequence algo = new PrintAllSubSequence();
        algo.main("ABC");
    }

    void main(String word){
        printSubSequence(word, "");
    }
    void printSubSequence(String word, String printAbleWord){
        if(word.length() == 0){
            if(printAbleWord.length()>0) {
                System.out.println(printAbleWord);
            }
            return;
        }
        char ch = word.charAt(0);
        word = word.substring(1);
        printSubSequence(word, printAbleWord);
        printSubSequence(word, printAbleWord+""+ch);
    }
}