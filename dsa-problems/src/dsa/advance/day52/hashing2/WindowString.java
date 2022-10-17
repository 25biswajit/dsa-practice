package dsa.advance.day52.hashing2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;

/*
Given a string A and a string B,
find the window with minimum length in A, which will contain all the characters in B in linear time complexity.
Note : that when the count of a character c in B is x, then the count of c in the minimum window in A should be at least x.
If there is no such window in A that covers all characters in B, return the empty string.
If there are multiple such windows, return the first occurring minimum window ( with minimum start index and length )
e.g:
A = "ADOBECODEBANC"
B = "ABC"
Windows : ADOBEC(6) , BECODEBA(8) , BANC(4)
Ans : BANC
*/
// Ref:
// https://www.youtube.com/watch?v=e1HlptlipB0
// https://leetcode.com/problems/minimum-window-substring/solution/
// https://just4once.gitbooks.io/leetcode-notes/content/leetcode/hash-table/076-minimum-window-substring.html
public class WindowString {
    @Test
    public void test1(){
        String word = "ADOBECODEBANC";
        String target = "ABC";
        Assertions.assertEquals("BANC", minWindowFreqArray(word,target)); // Accepted Optimised Solution
        Assertions.assertEquals("BANC", minWindowHashMap(word,target));  // Accepted Optimised Solution
        Assertions.assertEquals("BANC", minWindowPepCodingTLE(word,target)); // TLE
        Assertions.assertEquals("BANC", minWindowTLE(word,target)); // TLE
    }
    @Test
    public void test2(){
        String word = "AAAAAAA";
        String target = "AA";
        Assertions.assertEquals("AA", minWindowFreqArray(word,target)); // Accepted Optimised Solution
        Assertions.assertEquals("AA", minWindowHashMap(word,target)); // Accepted Optimised Solution
        Assertions.assertEquals("AA", minWindowPepCodingTLE(word,target)); // TLE
        Assertions.assertEquals("AA", minWindowTLE(word,target)); // TLE
    }

    public String minWindowFreqArray(String s, String t) {
        if (t.length() > s.length()) return "";
        int[] counts = new int[256];
        for (char c : t.toCharArray()) {
            counts[c]++;
        }
        int left = 0, right = 0, n = t.length(), start = 0, len = Integer.MAX_VALUE;
        char[] chars = s.toCharArray();
        while (right < s.length()) {
            if (counts[chars[right++]]-- > 0) n--;
            while (n == 0) {
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                if (counts[chars[left++]]++ >= 0) n++;
            }
        }
        len = len == Integer.MAX_VALUE ? 0 : len;
        return s.substring(start, start + len);
    }

    public String minWindowHashMap(String word, String refWord){
        int totalCount = refWord.length();
        HashMap<Character,Integer> freqMap = new HashMap<>();
        for(int i=0;i<refWord.length();i++){
            Character ch = refWord.charAt(i);
            freqMap.put(ch, freqMap.getOrDefault(ch,0)+1);
        }
        int length = 0;
        int start = 0;
        int currentCount = 0;
        for (int left = 0, right = 0; right < word.length(); right++){
            Character rightChar = word.charAt(right);
            if(!freqMap.containsKey(rightChar)) continue;

            freqMap.put(rightChar, freqMap.get(rightChar)-1);
            if(freqMap.get(rightChar) >= 0){
                currentCount++;
            }

            if (totalCount == currentCount) {
                while (freqMap.get(word.charAt(left)) == null || freqMap.get(word.charAt(left)) < 0){
                    if(freqMap.get(word.charAt(left))!=null){
                        freqMap.put(word.charAt(left) , freqMap.get(word.charAt(left))+1);
                    }
                    left++;
                }
                if(length == 0 || (right - left + 1) < length) {
                    length = right - left + 1;
                    start = left;
                }
            }
        }
        return word.substring(start , start+length);
    }

    public String minWindowPepCodingTLE(String word, String refWord){
        int refWordCount = refWord.length();
        HashMap<Character,Integer> wordMap = new HashMap<>();
        HashMap<Character,Integer> refWordMap = new HashMap<>();
        for(int i=0;i<refWord.length();i++){
            Character ch = refWord.charAt(i);
            refWordMap.put(ch, refWordMap.getOrDefault(ch,0)+1);
        }
        int i = 0, j = 0;
        String ans = "";
        int wordCount = 0;
        while (true){
            boolean flag1 = false;
            boolean flag2 = false;
            //acquire
            while (i < word.length() && refWordCount > wordCount){
                Character ch = word.charAt(i);
                wordMap.put(ch, wordMap.getOrDefault(ch,0)+1);
                i++;

                int currentFreq = wordMap.get(ch);
                int actualFreq = refWordMap.getOrDefault(ch, 0);
                if(currentFreq <= actualFreq){
                    wordCount++;
                }
                flag1 = true;
            }
            while (j < i && refWordCount == wordCount){
                // Collect
                String currentAns = word.substring(j, i);
                if(ans.length() == 0 || ans.length() > currentAns.length()){
                    ans = currentAns;
                    System.out.println(ans);
                }

                // Release
                Character ch = word.charAt(j);
                removeFromMap(ch, wordMap);
                j++;

                int currentFreq = wordMap.getOrDefault(ch, 0);
                int actualFreq = refWordMap.getOrDefault(ch, 0);
                if(currentFreq < actualFreq){
                    wordCount--;
                }
                flag2 = true;
            }
            if(!flag2 && !flag1){
                break;
            }
        }
        return ans;
    }

    private void removeFromMap(Character key, HashMap<Character, Integer> map) {
        if(map.containsKey(key)){
            int c = map.get(key);
            if( c == 1 ) map.remove(key);
            else map.put(key, c-1);
        }
    }

    // TLE - First Solution
    public String minWindowTLE(String word, String target){
        String ans = "";
        HashMap<String, Integer> targetFreqMap = new HashMap<>();
        Arrays.stream(target.split("")).forEach(s ->
            targetFreqMap.put(s, targetFreqMap.getOrDefault(s,0)+1)
        );
        HashMap<String, Integer> freqMap = new HashMap<>();
        Arrays.stream(word.split("")).forEach(s -> {
            if (targetFreqMap.containsKey(s)) {
                freqMap.put(s, freqMap.getOrDefault(s, 0) + 1);
            }
        });
        if(isMatched(freqMap , targetFreqMap)){
            ans = word;
            freqMap.clear();
        }else {
            return ans;
        }
        int i = 0, j = 0, n = word.length();

        while (i < n){
            if(isMatched(freqMap, targetFreqMap)){
                String newAns = word.substring(i,j);
                if(ans.length() > newAns.length()){
                    ans = newAns;
                }
                String s = String.valueOf( word.charAt(i) );
                if(targetFreqMap.containsKey(s)){
                    int freq = freqMap.get(s)-1;
                    if(freq == 0){
                        freqMap.remove(s);
                    }else {
                        freqMap.put(s, freq);
                    }
                }
                i++;
            }
            else if(j < n && !isMatched(freqMap, targetFreqMap)){
                // If FreqMap & targetFreqMap Not Matched
                String s = String.valueOf( word.charAt(j) );
                if(targetFreqMap.containsKey(s)){
                    freqMap.put(s, freqMap.getOrDefault(s,0)+1);
                }
                j++;
            }
            else{
                i++;
            }
        }
        return ans;
    }

    private boolean isMatched(HashMap<String, Integer> freqMap, HashMap<String, Integer> targetFreqMap) {
        if( freqMap.size() == targetFreqMap.size() ){
            for(String key : targetFreqMap.keySet()){
                if(freqMap.getOrDefault(key,0) < targetFreqMap.get(key)){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}

//Explanation
//Hint:Think two pointers and hashing.How can you move left and right pointers to fit all the characters of B?
/*
you have a start and end pointer starting from the beginning of the string.
You keep moving the end pointer and including more characters until you have all B characters included.
At this point, you start moving start pointer and popping out characters until you still have all the characters of B included.
Update your answer and keep repeating the process.
*/




