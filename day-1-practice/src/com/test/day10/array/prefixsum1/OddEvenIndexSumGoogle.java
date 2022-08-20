package com.test.day10.array.prefixsum1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class OddEvenIndexSumGoogle {
    @Test
    public void test(){
        ArrayList<Integer> integers = new ArrayList<>(Arrays.asList(4,3,2,7,6,-2));
        Assertions.assertEquals(2, solve(integers));
    }

    public int solve(ArrayList<Integer> A) {
        ArrayList<Integer> pfEven = getPrefixSumEven(A);
        ArrayList<Integer> pfOdd = getPrefixSumOdd(A);
        int count = 0;
        int n = A.size();

        // Delete 0 th Index
        if(pfEven.get(n-1) - pfEven.get(0) == pfOdd.get(n-1) - pfOdd.get(0)){
            count++;
        }
        // Delete n-1 th Index
        if(pfEven.get(n-2) == pfOdd.get(n-2)){
            count++;
        }
        // Iterate between 1st Index to n-2 th Index
        for(int i=1; i<= n-2 ; i++){
            int leftSumEven = pfEven.get(i-1);
            int leftSumOdd = pfOdd.get(i-1);
            int rightSumEven = pfOdd.get(n-1)-pfOdd.get(i);
            int rightSumOdd = pfEven.get(n-1)-pfEven.get(i);

            if(leftSumEven - leftSumOdd + rightSumEven - rightSumOdd == 0){
                count++;
            }
        }
        return count;
    }

    private ArrayList<Integer> getPrefixSumOdd(ArrayList<Integer> A){
        ArrayList<Integer> list = new ArrayList<>(A);
        int sum = 0;
        for(int i=0;i<A.size();i++){
            if(i % 2 != 0){
                sum += A.get(i);
            }
            list.set(i, sum);
        }
        return list;
    }

    private ArrayList<Integer> getPrefixSumEven(ArrayList<Integer> A){
        ArrayList<Integer> list = new ArrayList<>(A);
        int sum = 0;
        for(int i=0;i<A.size();i++){
            if(i % 2 == 0){
                sum += A.get(i);
            }
            list.set(i, sum);
        }
        return list;
    }
}
