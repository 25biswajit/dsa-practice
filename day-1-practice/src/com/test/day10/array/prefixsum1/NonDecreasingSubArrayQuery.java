package com.test.day10.array.prefixsum1;
/*

A = [1, 7, 3, 4, 9]
B = [ [1, 2], [2, 4] ]
Query 1: range [1, 2] is {1, 7} which is non-decreasing. Therefore, ans = 1
Query 2: range [2, 4] is {7, 3, 4} which is not non-decreasing. Therefore, ans = 0
Output [1,0]
*/

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class NonDecreasingSubArrayQuery {

    @Test
    public void test1(){
        ArrayList<Integer> inputList = new ArrayList<>(Arrays.asList(1, 7, 3, 4, 9));
        ArrayList<ArrayList<Integer>> rangeList = new ArrayList<>();
        rangeList.add(new ArrayList<>(Arrays.asList(1, 2)));
        rangeList.add(new ArrayList<>(Arrays.asList(2, 4)));
        ArrayList<Integer> result = solve(inputList, rangeList);
        System.out.println(result);
    }

    @Test
    public void test2(){
        ArrayList<Integer> inputList = new ArrayList<>(Arrays.asList(7, 7, 1, 6, 9));
        ArrayList<ArrayList<Integer>> rangeList = new ArrayList<>();
        rangeList.add(new ArrayList<>(Arrays.asList(1, 5)));
        ArrayList<Integer> result = solve(inputList, rangeList);
        System.out.println(result);
    }

    // O(N) Solution
    public ArrayList<Integer> solve(ArrayList<Integer> listInput, ArrayList<ArrayList<Integer>> rangeList) {

        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<Integer> prefixIrregularSum = getPrefixIrregularSum(listInput);

        for(ArrayList<Integer> range : rangeList){
            Integer start = prefixIrregularSum.get( range.get(0) - 1);
            Integer end = prefixIrregularSum.get( range.get(1) - 1);

            if(end - start == 0){
                result.add(1);
            }else {
                result.add(0);
            }
        }

        return result;
    }

    private ArrayList<Integer> getPrefixIrregularSum(ArrayList<Integer> listInput) {
        ArrayList<Integer> prefixArr = new ArrayList<>(listInput);
        prefixArr.set(0, 0);
        for(int i=1;i<listInput.size();i++){
            int count = prefixArr.get(i-1);
            if(listInput.get(i) < listInput.get(i-1)){
                count++;
            };
            prefixArr.set(i, count);
        }
        return prefixArr;
    }

    // O(N^2) Solution - Brute Force
    public ArrayList<Integer> solveBruteForce(ArrayList<Integer> listInput, ArrayList<ArrayList<Integer>> rangeList) {
        ArrayList<Integer> result = new ArrayList<>();

        for(ArrayList<Integer> range : rangeList){
            Integer start = range.get(0);
            Integer end = range.get(1);
            Integer temp;
            boolean flag = false;
            for(int i = start; i< end; i++){
                temp = listInput.get(i - 1);
                if(temp > listInput.get(i)){
                    flag = true;
                    break;
                }
            }
            result.add( flag ? 0 : 1);
        }
        return result;
    }
}
