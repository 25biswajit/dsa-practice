package dsa.leetcode;

import dsa.utils.ArrayUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
/*
Given an integer array nums of length n where all the integers of nums are in the range [1, n] and each integer appears once or twice,
return an array of all the integers that appears twice.
You must write an algorithm that runs in O(n) time and uses only constant extra space.
*/



public class FindDuplicateInArray_1_N {
    //https://leetcode.com/problems/find-all-duplicates-in-an-array/submissions/1242515919/
    @Test
    public void test1(){
        int[] input = {2,4,3,7,8,2,3,1};
        int[] expected_result = {2,3};
        List<Integer> list = findDuplicates(input);
        System.out.println(list);
        int[] result = ArrayUtils.convertToIntArray(list);
        Assertions.assertArrayEquals(result, expected_result);
    }

    @Test
    public void test2(){
        int[] input = {10,2,5,10,9,1,1,4,3,7};
        int[] expected_result = {10, 1};
        List<Integer> list = findDuplicates(input);
        System.out.println(list);
        int[] result = ArrayUtils.convertToIntArray(list);
        Assertions.assertArrayEquals(result, expected_result);
    }

    public List<Integer> findDuplicates(int[] arr) {
        int n = arr.length;
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < n; i++){
            int pos = Math.abs ( arr[i] ) - 1;

            if ( arr [ pos ] < 0 ){
                list.add( Math.abs ( arr [ i ] ));
            }else{
                arr[pos] = arr [ pos ] * -1;
            }
            ArrayUtils.printArray(arr);
        }
        return list;
    }
}
