package dsa.advance.day82.dp5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class RegularExpressionMatch {

    @Test
    public void test1(){
        String word = "aaa";String pattern = "a?a";
        Integer result = isMatch(word,pattern);
        Assertions.assertEquals(1, result);
    }
    @Test
    public void test2(){
        String word = "aaa";String pattern = "a*";
        Integer result = isMatch(word,pattern);
        Assertions.assertEquals(1, result);
    }
    @Test
    public void test3(){
        String word = "aaa";String pattern = "*a";
        Integer result = isMatch(word,pattern);
        Assertions.assertEquals(1, result);
    }
    @Test
    public void test4(){
        String word = "aaaaa";String pattern = "a*a";
        Integer result = isMatch(word,pattern);
        Assertions.assertEquals(1, result);
    }
    @Test
    public void test5(){
        String word = "baa";String pattern = "*a";
        Integer result = isMatch(word,pattern);
        Assertions.assertEquals(1, result);
    }
    @Test
    public void test6(){
        String word = "baa";String pattern = "a*";
        Integer result = isMatch(word,pattern);
        Assertions.assertEquals(0, result);
    }
    @Test
    public void test7(){
        String word = "aaaa";String pattern = "a******";
        Integer result = isMatch(word,pattern);
        Assertions.assertEquals(1, result);
    }
    @Test
    public void test8(){
        String word = "aab";String pattern = "c*a*b";
        Integer result = isMatch(word,pattern);
        Assertions.assertEquals(0, result);
    }

    // TC O(N * M) SC O(N * M)
    int dp[][] = null;
    public int isMatch(String word, String pattern){
        dp = new int[word.length()][pattern.length()];
        Arrays.stream(dp).forEach(a -> Arrays.fill(a, -1));
        return isMatch(word, pattern, word.length()-1, pattern.length()-1);
    }

    private int isMatch(String word, String pattern, int i, int j){
        if(i<0 && j<0) return 1;
        if(i<0 && j >=0) {
            for(int k = 0; k <= j; k++){
                if(pattern.charAt(k) != '*'){
                    return 0;
                }
            }
            return 1;
        }
        if(j<0 && i >=0) return 0;
        if(dp[i][j]==-1){
            if(word.charAt(i) == pattern.charAt(j) || pattern.charAt(j) == '?'){
                dp[i][j] = isMatch(word,pattern,i-1,j-1);
            }
            else if(pattern.charAt(j) == '*'){
                dp[i][j] = isMatch(word,pattern,i,j-1) | isMatch(word,pattern,i-1,j);
            }
            else {
                dp[i][j] = 0;
            }
        }
        return dp[i][j];
    }
}