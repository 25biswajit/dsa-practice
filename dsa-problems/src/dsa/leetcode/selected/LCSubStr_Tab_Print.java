package dsa.leetcode.selected;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LCSubStr_Tab_Print {

    @Test
    public void test1(){
        String a = "SAKYATZ", b = "TLKAKYAL";
        int res = longestCommonSubstring(a,b);
        Assertions.assertEquals(4, res);
    }

    @Test
    public void test2(){
        String a = "wasdijkl", b = "wsdjkl";
        int res = longestCommonSubstring(a,b);
        Assertions.assertEquals(3, res);
    }

    @Test
    public void test3(){
        String a = "tpx", b = "ytp";
        int res = longestCommonSubstring(a,b);
        Assertions.assertEquals(2, res);
    }

    public int longestCommonSubstring(String a, String b){
        int max = 0,end = 0;
        int n= a.length(), m = b.length();
        int[][] dp = new int[n+1][m+1];
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                if(a.charAt(i-1)==b.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                    if (dp[i][j] > max) {
                        max = dp[i][j];
                        end = i; // update the end position of the longest common substring
                    }
                }
            }
        }
        String longestCommonSubstring = a.substring(end - max, end);
        System.out.println(longestCommonSubstring);
        return max;
    }

}
