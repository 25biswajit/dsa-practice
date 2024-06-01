package dsa.basic.day16.interviewprob;

/*
Given a binary string A. It is allowed to do at most one swap between any 0 and 1.
Find and return the length of the longest consecutive 1’s that can be achieved.
*/

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class LengthLongestConsecutiveOneBySwap {
    @Test
    public void test(){
        Assertions.assertEquals(3, solveSwap("111000"));
        Assertions.assertEquals(7, solveSwap("111011101"));
    }

    public int solveSwap(String word){
        String[] a = word.split("");
        Map<Integer, Integer> map = new HashMap<>();
        int maxLength = 0;
        int countOne = 0;
        int totalCountOne = 0;

        for(int i = 0; i < a.length; i++){
            if(a[i].equals("0")){
                map.put(i, countOne);
                countOne = 0;
            }else {
                countOne++;
                totalCountOne++;
            }
        }
        countOne = 0;
        for(int i = a.length-1; i >=0; i--){
            if(a[i].equals("0")){
                int result = countOne + map.getOrDefault(i,0);
                map.put(i, result);
                maxLength = Math.max(maxLength, result);
                countOne = 0;
            }else {
                countOne++;
            }
        }

        if(map.size()==0){
            // Edge Case in case No Zero present
            maxLength = a.length;
        }else {
            maxLength = ( maxLength < totalCountOne ) ? maxLength + 1 : maxLength;
        }
        return maxLength;
    }
}
