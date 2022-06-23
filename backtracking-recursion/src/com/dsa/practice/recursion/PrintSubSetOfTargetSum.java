package com.dsa.practice.recursion;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PrintSubSetOfTargetSum {

    @Test
    public void test(){
        int[] array = new int[]{10,20,30,40,50};
        int targetSum = 60;
        main(array,targetSum);
    }

    void main(int[] array, int targetSum){
        List<Integer> numList = new ArrayList<>();
        printTargetSumSubset(array, 0, targetSum, numList);
    }

    void printTargetSumSubset(int[] arr, int startIndex, int targetSum, List<Integer> set){
        if(startIndex == arr.length){
            if(targetSum == set.stream().mapToInt(Integer::new).sum()){
                System.out.println(set);
            }
            return;
        }

        int num = arr[startIndex];
        List<Integer> newCopiedSet = new ArrayList<>(set);
        printTargetSumSubset(arr, startIndex+1, targetSum, newCopiedSet);
        newCopiedSet.add(num);
        printTargetSumSubset(arr, startIndex+1, targetSum, newCopiedSet);
    }
}
