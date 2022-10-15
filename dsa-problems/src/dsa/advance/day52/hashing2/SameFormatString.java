package dsa.advance.day52.hashing2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.HashSet;

/*
Given two strings A and B. Check if B contains same characters as that of given string A and in the same order.
Input 1: A = "HIRED", B = "HHHIIIRRRRREEEEEDDDDD" -> True
Input 2: A = "HIRED", B = "DDHHHHIIIIRRRRREEEEDDD" -> False
*/

public class SameFormatString {
    @Test
    public void test1(){
        String word = "HIRED";
        String target = "HHIIRREEDD";
        Assertions.assertEquals(1, solve(word,target));
        Assertions.assertEquals(1, solve1(word,target));
    }
    @Test
    public void test2(){
        String word = "HIRED";
        String target = "DDHHIIRREEDD";
        Assertions.assertEquals(0, solve(word,target));
        Assertions.assertEquals(0, solve1(word,target));
    }
    @Test
    public void test3(){
        String word = "HIRED";
        String target = "HHHIIIRRRRREEEEE";
        Assertions.assertEquals(0, solve(word,target));
        Assertions.assertEquals(0, solve1(word,target));
    }
    @Test
    public void test4(){
        String word = "HIRED";
        String target = "HIRE";
        Assertions.assertEquals(0, solve(word,target));
        Assertions.assertEquals(0, solve1(word,target));
    }
    @Test
    public void test5(){
        String word = "ABA";
        String target = "AABBA";
        Assertions.assertEquals(1, solve(word,target));
        Assertions.assertEquals(1, solve1(word,target));
    }
    @Test
    public void test6(){
        String word = "HIR";
        String target = "HIRED";
        Assertions.assertEquals(0, solve(word,target));
        Assertions.assertEquals(0, solve1(word,target));
    }
    @Test
    public void test7(){
        String word = "HIRE";
        String target = "HHRREE";
        Assertions.assertEquals(0, solve(word,target));
        Assertions.assertEquals(0, solve1(word,target));
    }

    //SC: O(N) TC:O(N)
    public int solve1(final String word, final String target) {
        HashSet<String> set = new HashSet<>();
        for(int i=0; i<word.length();i++){
            set.add(word.charAt(i)+"_"+(i+1));
        }
        int counter = 1;
        String prev = String.valueOf(target.charAt(0));
        String element = prev+"_"+counter;
        if(set.contains(element)){
            set.remove(element);
            counter++;
        }else {
            return 0;
        }
        for ( int j = 1; j < target.length(); j++){
            String current = String.valueOf(target.charAt(j));
            if(!current.equals(prev)){
                element = current+"_"+counter;
                if(set.contains(element)){
                    set.remove(element);
                    prev = current;
                    counter++;
                }
                else {
                    return 0;
                }
            }
        }
        return set.isEmpty() ? 1 : 0;
    }

    //SC: O(1) TC:O(N)
    public int solve(final String word, final String target) {
        int i = 0, j = 0, count = 0;
        while(i < word.length() && j < target.length()){
            char c1 = word.charAt(i);
            char c2 = target.charAt(j);
            if(c1 == c2) {
               count++;
            }
            while (c1 == c2){
                j++;
                if(j < target.length()){
                    c2 = target.charAt(j);
                }else {
                    break;
                }
            }
            i++;
        }
        if(i == word.length() && j == target.length() && count == word.length()){
            return 1;
        }
        return 0;
    }
}
