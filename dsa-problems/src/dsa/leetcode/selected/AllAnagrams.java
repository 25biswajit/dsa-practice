package dsa.leetcode.selected;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class AllAnagrams {

    @Test
    public void test1(){
        String s = "cbaebabacd", p = "abc";
        System.out.println(findAnagrams(s,p));
    }

    @Test
    public void test2(){
        String s = "aaaaaaaaaaaaa", p = "aaaaaaaaaa";
        System.out.println(findAnagrams(s,p));
    }

    public List<Integer> findAnagrams(String s1, String s2) {
        List<Integer> ans = new ArrayList<>();
        if(s2.length() > s1.length()){
            return ans;
        }
        int[] given = getMap(s2);
        String word = s1.substring(0, s2.length());
        int[] current = getMap ( word );
        if( compare(given, current) ){
            ans.add(0);
        }
        int p1 = 1;
        int p2 = s2.length();
        while(p2 < s1.length()){
            current[ s1.charAt(p1-1) - 'a']--;
            current[ s1.charAt(p2) - 'a']++;
            if( compare(given, current) ){
                ans.add(p1);
            }
            p1++;
            p2++;
        }
        return ans;
    }


    private int[] getMap(String p){
        int[] a = new int[26];
        for(char c : p.toCharArray()){
            a[ c - 'a']++;
        }
        return a;
    }

    private boolean compare(int[] a, int[] b){
        for(int i = 0; i < 26; i++){
            if(a[i]!=b[i]){
                return false;
            }
        }
        return true;
    }
}
