package dsa.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class LongestConsecutiveSeq {

        @Test
        public void test(){
            int a[] = {0,3,7,2,5,8,4,6,0,1};
            Assertions.assertEquals(9, longestConsecutive(a));
            Assertions.assertEquals(9, longestConsecutiveSet(a));
        }

        public int longestConsecutive(int[] arr) {
            Map<Integer, Boolean> map = new HashMap<>();

            for(int a : arr){
                map.put(a, true);
            }

            int max = 0; // max length
            int len = 0; // current length
            for(int a : arr){
                if(!map.get(a)){
                    continue;
                }

                len = 1;
                int next = a + 1;
                while(map.getOrDefault(next, false)){
                    map.put(next, false);
                    len++;
                    next++;
                }

                int prev = a - 1;
                while(map.getOrDefault(prev, false)){
                    map.put(prev, false);
                    len++;
                    prev--;
                }

                max = Math.max(max, len);
            }

            return max;
        }

    public int longestConsecutiveSet(int[] arr) {
        Set<Integer> set = Arrays.stream(arr).boxed().collect(Collectors.toSet());

        int max = 0; // max length
        int len = 0; // current length
        for(int a : arr){
            set.remove(a);
            len = 1;
            int next = a + 1;
            while(set.contains(next)){
                set.remove(next);
                len++;
                next++;
            }

            int prev = a - 1;
            while(set.contains(prev)){
                set.remove(prev);
                len++;
                prev--;
            }

            max = Math.max(max, len);
        }

        return max;
    }
}
