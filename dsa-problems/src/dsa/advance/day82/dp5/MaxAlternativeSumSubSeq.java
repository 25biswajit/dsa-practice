package dsa.advance.day82.dp5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MaxAlternativeSumSubSeq {

    @Test
    public void test(){
        int[] nums = {4,5,5};
        Assertions.assertEquals(5, maxAlternatingSum(nums));
    }

    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[][] dp = new long[n][2];
        for(long[] arr : dp){
            Arrays.fill(arr,-1);
        }
        return solve(nums, 0, true, dp);//1 -> true
    }

    private long solve(int[] a, int i, boolean flag, long[][] dp){
        if(i >= a.length) return 0;
        int j = flag ? 1 : 0;
        if( dp[i][j] != -1 ) return dp[i][j];
        int value =  (flag ? a[i] : a[i] * -1 );
        long take = value + solve(a, i+1, !flag, dp);
        long skip = solve(a, i+1, flag, dp);
        long res = Math.max(take, skip);
        dp[i][j] = res;
        return res;
    }

}
