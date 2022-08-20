package com.test.day25.hashing2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class SubArrayWithGivenSumFirstOcc {
    @Test
    public void test1(){
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Integer[] expected = solveBruteForce(list,5).toArray(new Integer[0]);
        Integer[] actual = solveOptimizedNew(list,5).toArray(new Integer[0]);
        Assertions.assertArrayEquals(expected,actual);
    }
    @Test
    public void test2(){
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(30, -10, -10, - 5, -5, 30, -30));
        Integer[] expected = solveBruteForce(list,0).toArray(new Integer[0]);
        Integer[] actual = solveOptimizedNew(list,0).toArray(new Integer[0]);
        Assertions.assertArrayEquals(expected,actual);
    }

    public ArrayList<Integer> solveOptimizedNew(ArrayList<Integer> list, int target){
        ArrayList<Integer> result = new ArrayList<>();
        HashMap<Integer,Integer> map = new HashMap<>();
        ArrayList<Integer> prefixSum = derivePrefixSum(list);
        map.put(0, -1);
        for (int i = 0; i< prefixSum.size(); i++){
            int a = prefixSum.get(i);
            int b = a - target;
            if(map.containsKey(b)){
                int first = map.get(b);
                result = new ArrayList<>( list.subList(first+1, i+1));
                System.out.println(result);
                return result;
            }
            map.put(a, i);
        }
        if(result.isEmpty()){
            result.add(-1);
        }
        return result;
    }

    public ArrayList<Integer> derivePrefixSum(ArrayList<Integer> list){
        ArrayList<Integer> prefixSum = new ArrayList<>(list);
        for(int i = 1; i < prefixSum.size(); i++){
            int sum = prefixSum.get(i-1) + prefixSum.get(i);
            prefixSum.set(i , sum);
        }
        return prefixSum;
    }

    public ArrayList<Integer> solveOptimized(ArrayList<Integer> list, int target){
        ArrayList<Integer> result = new ArrayList<>();
        int sum = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(sum, -1);
        for (int i = 0; i< list.size(); i++){
            sum += list.get(i);
            int part = sum - target;
            if(map.containsKey(part)){
                int first = map.get(part);
                if(first >= 0){
                    result = new ArrayList<>( list.subList(first+1, i+1));
                    return result;
                }
            }
            map.put(sum, i);
        }
        if(result.isEmpty()){
            result.add(-1);
        }
        return result;
    }

    public ArrayList<Integer> solveBruteForce(ArrayList<Integer> list, int target) {
        ArrayList<Integer> result = new ArrayList<>();
        int c = 0;
        for (int i = 0; i< list.size(); i++){
            int sum = 0;
            for(int j = i; j <list.size() ; j++){
                sum += list.get(j);
                if(sum == target){
                    result = new ArrayList<>( list.subList(i, j+1) );
                    return result;
                }
            }
        }
        if(result.isEmpty()){
            result.add(-1);
        }
        return result;
    }
}
