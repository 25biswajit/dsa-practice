package dsa.leetcode.selected;

/*
Given an integer array nums and two integers left and right, return the number of contiguous non-empty subarrays such that the value of the maximum array element in that subarray is in the range [left, right].

The test cases are generated so that the answer will fit in a 32-bit integer.



Example 1:

Input: nums = [2,1,4,3], left = 2, right = 3
Output: 3
Explanation: There are three subarrays that meet the requirements: [2], [2, 1], [3].
Example 2:

Input: nums = [2,9,2,5,6], left = 2, right = 8
Output: 7

*/

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SubArrayCountBoundedMax {

    @Test
    public void test1(){
        int[] a = {2,1,4,3};
        int res = numSubarrayBoundedMax(a, 2, 3);
        Assertions.assertEquals(3, res);
    }
    @Test
    public void test2(){
        int[] a = {7,3,4,9,2,7,8,6,11,4,12,2,9};
        int res = numSubarrayBoundedMax(a, 3, 7);
        Assertions.assertEquals(10, res);
    }
    public int numSubarrayBoundedMax(int[] a, int LB, int UB) {
        int len = 0, ans = 0, i = 0, j = 0;
        while(j < a.length){
            if(LB <= a[j] && a[j] <= UB){
                len = j - i + 1;
                ans += len;
            }
            else if(LB > a[j]){
                ans += len;
            }
            //UB < a[j]
            else{
                len = 0;
                i = j+1;
            }
            j++;
        }
        return ans;
    }
}
