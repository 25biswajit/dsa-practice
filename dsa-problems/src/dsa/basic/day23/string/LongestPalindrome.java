package dsa.basic.day23.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LongestPalindrome {

    @Test
    public void test(){
        Assertions.assertEquals(longestPalindrome("aaabaaa"), longestPalindromicSubstring("aaabaaa"));//Output : aaabaaa
        Assertions.assertEquals(longestPalindrome("aaabaaaa"), longestPalindromicSubstring("aaabaaaa")); // aaabaaa
        Assertions.assertEquals(longestPalindrome("bb"), longestPalindromicSubstring("abb")); // bb
        Assertions.assertEquals(longestPalindrome("a"), longestPalindromicSubstring("a")); // a
        Assertions.assertEquals(longestPalindrome("a"), longestPalindromicSubstring("ac")); // a
    }

    public String longestPalindrome(String word) {
        String[] arr = word.split("");
        int n = arr.length;
        String res = arr[0];
        // Odd Middle
        for(int i=1;i<n-1;i++){
            int p1 = i-1;
            int p2 = i+1;
            res = deriveLatestLongestPalWithinRange(word, p1, p2, res, arr);
        }

        // Even Middle
        for(int i=0,j=i+1;j<n;i++,j++){
            int p1 = i;
            int p2 = j;
            res = deriveLatestLongestPalWithinRange(word, p1, p2, res, arr);
        }
        return res;
    }

    private String getStringWithinRange(String word, int p1, int p2, String currResult){
        String curPalSub = word.substring(p1, p2+1);
        if(currResult.length() < curPalSub.length() ){
            currResult = curPalSub;
        }
        return currResult;
    }

    private String deriveLatestLongestPalWithinRange(String word, int p1, int p2, String result, String[] arr){
        int n = arr.length;
        String preChar="",postChar = "";
        while(p1>=0 && p2<n){
            preChar = arr[p1];
            postChar = arr[p2];
            if(preChar.equals(postChar)){
                result = getStringWithinRange(word, p1, p2, result);
                p1--;
                p2++;
            }else {
                break;
            }
        }
        return result;
    }

    // New Approach ore Concise
    public String longestPalindromicSubstring(String word) {
        String[] arr = word.split("");
        int n = arr.length;
        String maxSubstring = "";
        String currentSubstring = "";
        int p1,p2;
        for(int i=0;i<n;i++){
            p1 = p2 = i;
            currentSubstring = expandMid(word, p1, p2);
            if(currentSubstring.length() > maxSubstring.length()){
                maxSubstring = currentSubstring;
            }
        }
        for(int i=0;i<n-1;i++){
            p1 = i; p2 = i+1;
            currentSubstring = expandMid(word, p1, p2);
            if(currentSubstring.length() > maxSubstring.length()){
                maxSubstring = currentSubstring;
            }
        }
        return maxSubstring;
    }

    private String expandMid(String word, int p1, int p2) {
        String currentSubstring = "";
        int n = word.length();
        while(p1>=0 && p2<n){
            if(word.charAt(p1)==word.charAt(p2)){
                currentSubstring = word.substring(p1,p2+1);
                p1--;
                p2++;
            }else {
                break;
            }
        }
        return currentSubstring;
    }
}
