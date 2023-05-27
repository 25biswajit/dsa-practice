package dsa.advance.day81.dp4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;

/*Given a set of non-negative integers and a value sum, the task is to check if there is a subset of the given set whose sum is equal to the given sum.
Input: set[] = {3, 34, 4, 12, 5, 2}, sum = 9
Output: True
Explanation: There is a subset (4, 5) with sum 9.

Input: set[] = {3, 34, 4, 12, 5, 2}, sum = 30
Output: False
Explanation: There is no subset that add up to 30.
*/
public class SubSetSumExists {

    @Test
    public void test1(){
        int[] array = {3,34,4,12,5,2};
        Assertions.assertTrue(IsExistSubsetSum(array, 9));
        Assertions.assertTrue(doExistSubsetSum(array, 9));
    }

    @Test
    public void test2(){
        int[] array = {7,4,9,6,10,13, 14, 11};
        Assertions.assertTrue(IsExistSubsetSum(array, 22));
        Assertions.assertTrue(doExistSubsetSum(array, 22));
        Assertions.assertTrue(IsExistSubsetSum(array, 26));
        Assertions.assertTrue(doExistSubsetSum(array, 26));
    }

    @Test
    public void testNegative(){
        int[] array = {6,-4,3,-2,-3,1,-5};
        Assertions.assertTrue(IsExistSubsetSum(array, 2));
        Assertions.assertTrue(doExistSubsetSum_(array, 2));
    }

    // approach 1 - without dp table
    public boolean IsExistSubsetSum(int[] array, int sum){
        return IsExistSubsetSum(array.length-1, sum, array);
    }

    private boolean IsExistSubsetSum(int i, int k, int[] array){
        if(k == 0) return true;
        if(i<0) return false;
        boolean value = IsExistSubsetSum(i-1,k,array);
        if(k >= array[i]){
            value = value || IsExistSubsetSum(i-1,k-array[i],array);
        }
        return value;
    }

    // approach 2 - using dp memoization
    int dp[][] = null;
    public boolean doExistSubsetSum(int[] array, int sum){
        dp = new int[array.length][sum+1];
        Arrays.stream(dp).forEach(a -> Arrays.fill(a,-1));
        return doExistSubsetSum(array.length-1, sum, array) == 1;
    }

    private int doExistSubsetSum(int i, int k, int[] array){
        if(k == 0) return 1;
        if(i<0) return 0;
        if(dp[i][k] == -1){
            dp[i][k] = doExistSubsetSum(i-1,k,array);
            if(k >= array[i]){
                dp[i][k] = dp[i][k] | doExistSubsetSum(i-1,k-array[i],array);
            }
        }
        return dp[i][k];
    }

    // If Negative elements present in Array we can't use DP table, Use HashMap there
    // approach 3 - using dp memoization Hashmap
    HashMap<String, Integer> dpMap = new HashMap<>();
    public boolean doExistSubsetSum_(int[] array, int sum){
        return doExistSubsetSum_(array.length-1, sum, array) == 1;
    }

    private int doExistSubsetSum_(int i, int k, int[] array){
        if(k == 0) return 1;
        if(i<0) return 0;
        String key = i+"_"+k;
        Integer value;
        if(!dpMap.containsKey(key)){
            value = doExistSubsetSum_(i-1,k,array);
            if(k >= array[i]){
                value = value | doExistSubsetSum_(i-1,k-array[i],array);
            }
            dpMap.put(key,value);
        }
        return dpMap.get(key);
    }


}
