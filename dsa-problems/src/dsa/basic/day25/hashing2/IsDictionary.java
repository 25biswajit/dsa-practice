package dsa.basic.day25.hashing2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IsDictionary {
    //@Test
    public void test1(){
        String dictionary = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        List<String> inputs = Arrays.asList("ABD","ABC");
        Assertions.assertEquals( 0, solve(inputs, dictionary));
    }
    //@Test
    public void test2(){
        String dictionary = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        List<String> inputs = Arrays.asList("ABCE","ABC");
        Assertions.assertEquals( 0, solve(inputs, dictionary));
    }
    //@Test
    public void test3(){
        String dictionary = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        List<String> inputs = Arrays.asList("ABCDEF","ABD");
        Assertions.assertEquals( 1, solve(inputs, dictionary));
    }
    @Test
    public void test4(){
        String dictionary = "adhbcfegskjlponmirqtxwuvzy";
        List<String> inputs = Arrays.asList("hello", "scaler", "interviewbit");
        Assertions.assertEquals( 1, solve(inputs, dictionary));
    }

    public int solve(List<String> list, String dictionary) {
        Map<Character,Integer> map = buildDictionaryMap(dictionary);
        return checkIsValidConsecutiveWords(list,map);
    }

    private int checkIsValidConsecutiveWords(List<String> list, Map<Character, Integer> map) {
        int p1 = 0;
        int p2 = 1;
        while (p2 < list.size()){
            String s1 = list.get(p1);
            String s2 = list.get(p2);
            if(!isValidWords(s1,s2,map)){
                return 0;
            }
            p1++;
            p2++;
        }
        return 1;
    }

    private boolean isValidWords(String s1, String s2, Map<Character, Integer> map) {
        int len1 = s1.length();
        int len2 = s2.length();
        int i=0;
        int j=0;
        while (i < len1 && j < len2){
            int pos1 = map.get( s1.charAt(i) );
            int pos2 = map.get( s2.charAt(j) );
            if(pos1 == pos2){
                i++;
                j++;
            }
            else if( pos1 > pos2 ){
                return false;
            }else if(pos2 > pos1){
                return true;
            }
        }
        if(len1 >= len2 && i < len1){
            return false;
        }
        return true;
    }

    private Map<Character, Integer> buildDictionaryMap(String dictionary) {
        Map<Character,Integer> map = new HashMap<>();
        int c = 0;
        for(char ch : dictionary.toCharArray()){
            map.put(ch, ++c);
        }
        return map;
    }
}