package com.test.day24.hashing1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class CheckPalindrome {
    @Test
    public void test(){
        String word = "ADBDBDACABABE";
        Assertions.assertEquals(solve(word),solveNew(word));

        word = "ADBDBCACABABE";
        Assertions.assertEquals(solve(word),solveNew(word));
    }

    public int solve(String A) {
        Set<String> set = new HashSet<>();
        String[] arr = A.split("");
        for(String s : arr){
            if(set.contains(s)){
                set.remove(s);
            }else{
                set.add(s);
            }
        }
        return set.size() > 1 ? 0 : 1;
    }

    public int solveNew(String word) {
        HashMap<String,Integer> map = new HashMap<>();
        String[] arr = word.split("");
        int sum = 0;
        for(String s : arr){
            if(map.containsKey(s)){
                int count = map.get(s);
                if(count == 0){
                    count++;
                    sum++;
                }else {
                    count --;
                    sum--;
                }
                map.put(s,count);
            }else{
                map.put(s,1);
                sum++;
            }
        }
        return sum > 1 ? 0 : 1;
    }


}
