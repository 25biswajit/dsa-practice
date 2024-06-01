package dsa.leetcode.selected;

import org.junit.jupiter.api.Test;

import java.util.*;

public class GroupAnagram {

    @Test
    public void test1(){
        String[] words = {"eat","tea","tan","ate","nat","bat"};
        List<List<String>> result = groupAnagrams(words);
        result.forEach( l -> {
            l.forEach(System.out::println);
            System.out.print("\n");
        });
       //{{"bat"},{"nat","tan"},{"ate","eat","tea"}};
    }
    @Test
    public void test2(){
        String[] words = {"eat","tea","tan","ate","nat","bat"};
        List<List<String>> result = groupAnagramsSort(words);
        result.forEach( l -> {
            l.forEach(System.out::println);
            System.out.print("\n");
        });
        //{{"bat"},{"nat","tan"},{"ate","eat","tea"}};
    }

    // without Sort
    public List<List<String>> groupAnagrams(String[] words) {
        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();

        for(String str : words){
            String key = convertFreqString(str);
            if(!map.containsKey(key)){
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(str);
        }

        for(String k : map.keySet()){
            result.add(map.get(k));
        }
        return result;
    }

    private String convertFreqString(String str) {
        int[] alphabet = new int[26];
        for(char c : str.toCharArray()){
            alphabet[c - 'a']++;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i< 26; i++){
            if(alphabet[i] > 0){
                stringBuilder.append((char)('a' + i));
                stringBuilder.append(alphabet[i]);
            }
        }
        return stringBuilder.toString();
    }

    // with Sort
    public List<List<String>> groupAnagramsSort(String[] words) {
        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();

        for(String str : words){
            String key = convertToSortedKey(str);

            if(!map.containsKey(key)){
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(str);
        }

        for(String k : map.keySet()){
            result.add(map.get(k));
        }
        return result;
    }

    private String convertToSortedKey(String str) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
