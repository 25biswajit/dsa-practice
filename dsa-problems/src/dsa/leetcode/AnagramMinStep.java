package dsa.leetcode;

import dsa.utils.ArrayUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AnagramMinStep {

    @Test
        public void test(){
            Assertions.assertEquals( 7, minSteps("leetcode","coats"));
        }


        public int minSteps(String s, String t) {
            int[] map = new int[26];
            for(int i = 0; i < s.length(); i++){
                map[s.charAt(i) - 'a']++;
            }
            for(int i = 0; i < t.length(); i++){
                map[t.charAt(i) - 'a']--;
            }

            ArrayUtils.printArray(map);

            int steps = 0;
            for(int i = 0; i < 26; i++){
                steps += Math.abs(map[i]);
            }
            return steps;
        }

}
