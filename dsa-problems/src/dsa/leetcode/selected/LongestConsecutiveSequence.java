package dsa.leetcode.selected;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class LongestConsecutiveSequence {

    @Test
    public void test1(){
        int[] a = {9,1,4,7,3,-1,0,5,100,101,8,-1,6};
        int ans = longestConsecutiveSeq(a);
        Assertions.assertEquals(7, ans);
    }
    @Test
    public void test2(){
        int[] a = {9,1,4,7,3,-1,0,5,100,101,8,-1,6};
        int ans = longestConsecutiveSeqOptimised(a);
        Assertions.assertEquals(7, ans);
    }


    public int longestConsecutiveSeq(int[] a){
        Arrays.sort(a);
        int max = 0;
        int prev = 0;
        int len = 1;
        int curr = prev+1;
        while(curr < a.length){
            if(a[curr] - a[prev] == 1){
                len++;
            }
            else if(a[curr] == a[prev]){
                // Do Nothing
            }
            else {
                len = 1;
            }
            prev++;curr++;
            max = Integer.max(max, len);
        }
        return max;
    }

    public int longestConsecutiveSeqOptimised(int[] array){
        HashMap<Integer,Boolean> map = new HashMap<>();
        for(Integer i : array){
            map.put(i, true);
        }

        for(Integer key : map.keySet()){
            if(map.containsKey(key-1)){
                map.put(key, false);
            }
        }

        int maxLen = 0;
        int len = 0;
        for(Integer key : map.keySet()){
            int nextElem = key+1;
            len = 1;
            while(map.containsKey(nextElem)){
                nextElem++;
                len++;
            }
            maxLen = Integer.max(maxLen, len);
        }

        return maxLen;
    }
}
