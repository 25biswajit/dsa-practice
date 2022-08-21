package com.test.day24.hashing1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class SubArrayZeroSum {
    @Test
    public void test(){
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(9,3,-1,0,-2,-3,-6));
        Assertions.assertEquals(1, solveWithoutExtraSpace(list));
        Assertions.assertEquals(1, solveWithPrefixSum(list));

        list = new ArrayList<>(Arrays.asList(30,-30));
        Assertions.assertEquals(1, solveWithoutExtraSpace(list));
        Assertions.assertEquals(1, solveWithPrefixSum(list));

        list = new ArrayList<>(Arrays.asList(0));
        Assertions.assertEquals(1, solveWithoutExtraSpace(list));
        Assertions.assertEquals(1, solveWithPrefixSum(list));
    }
    // With Prefix Sum
    public int solveWithPrefixSum(ArrayList<Integer> integers) {
        int count = 0;
        long[] prefixArr = derivePrefixSum(integers);
        HashSet<Long> set = new HashSet<>();
        for(long x : prefixArr){
            if(x == 0 || set.contains(x)){
                count++;
            }
            set.add(x);
        }
        count = handleEdgeCase(prefixArr[integers.size()-1],integers.size(),count);
        System.out.println("Count:" + count);
        return count > 0 ? 1 : 0;
    }

    private long[] derivePrefixSum(ArrayList<Integer> list){
        long[] prefixArr = new long[list.size()];
        prefixArr[0] = list.get(0);
        for(int i=1; i< list.size(); i++){
            prefixArr[i] = prefixArr[i-1]+list.get(i);
        }
        return prefixArr;
    }

    // Without Creating Prefix Sum
    public int solveWithoutExtraSpace(ArrayList<Integer> list) {
        int count = 0;
        long sum = 0;
        HashSet<Long> set = new HashSet<>();
        for(Integer x : list){
            sum = sum + x;
            if(x == 0 || sum == 0 || set.contains(sum)){
                count++;
            }
            set.add(sum);
        }
        count = handleEdgeCase(sum,list.size(),count);
        System.out.println("Count:" + count);
        return count > 0 ? 1 : 0;
    }

    private int handleEdgeCase(long sum, int inputSize,int count) {
        if(sum == 0 && inputSize>1){
            count++;
        }
        return count;
    }
}

