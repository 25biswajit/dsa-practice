package dsa.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TwoStringClose {

    @Test
    public void test1(){
        Assertions.assertTrue( closeStrings("cabbba", "abbccc") );
        Assertions.assertTrue( closeStrings("abbzzca", "babzzcz") );
    }

    public boolean closeStrings(String s, String t) {
        int[] map1 = new int[26];
        int[] map2 = new int[26];
        for(int i = 0; i < s.length(); i++){
            map1[s.charAt(i) - 'a']++;
        }
        for(int i = 0; i < t.length(); i++){
            map2[t.charAt(i) - 'a']++;
        }
        int count1 = 0;
        int count2 = 0;
        for(int i = 0; i < 26; i++){
            if(map1[i] !=0 && map2[i]!=0){
                count1 += map1[i];
                count2 += map2[i];
            }
            else if((map1[i] !=0 && map2[i]==0) || (map1[i] ==0 && map2[i]!=0) ){
                return false;
            }
        }
        return count1 == count2;
    }
}
