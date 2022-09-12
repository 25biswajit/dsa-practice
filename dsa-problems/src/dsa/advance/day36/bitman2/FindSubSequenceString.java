package dsa.advance.day36.bitman2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
/*
Given two strings A and B, find if A is a subsequence of B.
A = "bit", B = "dfbkjijgbbiihbmmt", True
A = "apple", B = "appel", False
*/
public class FindSubSequenceString {
    @Test
    public void test1(){
        String sequence = "bit";
        String word = "dfbkjijgbbiihbmmt";
        Assertions.assertTrue(isSequencePresent(sequence,word));
        Assertions.assertTrue(isSequencePresentIterative(sequence,word));
    }
    @Test
    public void test2(){
        String sequence = "apple";
        String word = "appel";
        Assertions.assertFalse(isSequencePresent(sequence,word));
        Assertions.assertFalse(isSequencePresentIterative(sequence,word));
    }
    @Test
    public void test3(){
        String sequence = "apple";
        String word = "apple";
        Assertions.assertTrue(isSequencePresent(sequence,word));
        Assertions.assertTrue(isSequencePresentIterative(sequence,word));
    }

    public boolean isSequencePresent(String sequence, String word) {
        HashMap<String, ArrayList<Integer>> map = new HashMap<>();
        int pos = 0;
        for(String s : word.split("")){
            if(!map.containsKey(s)){
               ArrayList<Integer> list = new ArrayList<>();
               map.put(s, list);
            }
            map.get(s).add(pos);
            pos++;
        }
        System.out.println(map);
        String result = "";
        int lastPos = -1;
        for(String s : sequence.split("")){
            boolean flag = false;
            if(!map.containsKey(s)){
                return false;
            }
            ArrayList<Integer> positions = map.get(s);
            for(Integer x : positions){
                if(x > lastPos){
                    result += s;
                    lastPos = x;
                    flag = true;
                    break;
                }
            }
            System.out.println("s: " + s + " Last Pos:" + lastPos + " Result:" + result);
            if (!flag) {
                return false;
            }
        }
        System.out.println("Final : " + result);
        return result.equals(sequence);
    }

    public boolean isSequencePresentIterative(String sequence, String word) {
        int i = 0;
        int j = 0;
        String result = "";
        while(i < sequence.length() && j < word.length()){
            char seq_char = sequence.charAt(i);
            char word_char = word.charAt(j);
            if(seq_char == word_char){
                result += seq_char;
                i++;
                j++;
            }
            else {
                j++;
            }
        }
        return result.equals(sequence);
    }
}
