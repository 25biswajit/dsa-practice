package dsa.advance.day82.dp5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class EditDistance {

    @Test
    public void test1(){
        String s1 = "geek";
        String s2 = "gesek";
        Integer result = editDistance(s1,s2);
        Assertions.assertEquals(1, result);
    }

    // TC O(N * M) SC O(N * M)
    int dp[][] = null;
    public int editDistance(String s1, String s2){
        dp = new int[s1.length()][s2.length()];
        Arrays.stream(dp).forEach(a -> Arrays.fill(a, -1));
        return editDistance(s1, s2, s1.length()-1, s2.length()-1);
    }

    private int editDistance(String s1, String s2, int i, int j){
        if(i<0 && j<0) return 0;
        if(i<0 && j >=0) return j+1;
        if(j<0 && i >=0) return i+1;
        if(dp[i][j]==-1){
            if(s1.charAt(i)==s2.charAt(j)){
                dp[i][j] = editDistance(s1,s2,i-1,j-1);
            }
            else {
                dp[i][j] = 1 +
                        Integer.min( editDistance(s1,s2,i,j-1),
                                Integer.min(editDistance(s1,s2,i-1,j), editDistance(s1,s2,i-1,j-1)) );
            }
        }
        return dp[i][j];
    }

}
