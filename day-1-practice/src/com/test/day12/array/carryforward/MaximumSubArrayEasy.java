package com.test.day12.array.carryforward;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

// Find Max of all SubArray Sum where Sum should not exceed B
public class MaximumSubArrayEasy {
    @Test
    public void test(){
        ArrayList<Integer> list1 = new ArrayList<>(Arrays.asList(2, 1, 3, 4, 5));
        Assertions.assertEquals( 12, maxSubArray( 12, list1));

        ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(2, 2, 2));
        Assertions.assertEquals( 0, maxSubArray(1, list2));

        ArrayList<Integer> list3 = new ArrayList<>(Arrays.asList(1, 2, 4, 4, 5, 5, 5, 7, 5));
        Assertions.assertEquals( 14, maxSubArray( 14, list3));
    }

    // TC : O ( N * N ) solution , SC: O ( 1 )
    public int maxSubArray(int B, ArrayList<Integer> C) {
        int n = C.size();
        int maxSum = 0;
        for(int i=0;i<n;i++){
            int sum = 0;
            for(int j=i;j<n;j++){
                sum = (sum + C.get(j)) % 1000000007;
                if(sum <= B){
                    maxSum = Integer.max(maxSum, sum);
                }else{ // If Max Sum exceeds the limit , further addition not required
                    break;
                }
            }
        }
        return maxSum;
    }

    // TC : O ( N ) solution , SC: O ( N )
    public int maxSubArrayPrefixSum(int B, ArrayList<Integer> C) {
        int n = C.size();
        int maxSum = 0;
        for(int i=0;i<n;i++){
            int sum = 0;
            for(int j=i;j<n;j++){
                sum = (sum + C.get(j)) % 1000000007;
                if(sum <= B){
                    maxSum = Integer.max(maxSum, sum);
                }else{ // If Max Sum exceeds the limit , further addition not required
                    break;
                }
            }
        }
        return maxSum;
    }
}
