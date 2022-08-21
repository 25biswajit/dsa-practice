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
        Integer[] actual = solveOptimized(list,5).toArray(new Integer[0]);
        Assertions.assertArrayEquals(expected,actual);
    }
    @Test
    public void test2(){
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(30, -10, -10, - 5, -5, 30, -30));
        Integer[] expected = solveBruteForce(list,0).toArray(new Integer[0]);
        Integer[] actual = solveOptimized(list,0).toArray(new Integer[0]);
        Assertions.assertArrayEquals(expected,actual);
    }
    public ArrayList<Integer> solveOptimized(ArrayList<Integer> list, int target){
        ArrayList<Integer> result;
        HashMap<Long,Integer> map = new HashMap<>();
        int start = 0;
        int end = -1;
        long sum = 0;
        for (int i = 0; i< list.size(); i++){
            sum += list.get(i);
            int a = list.get(i);

            if(sum == target){
                start = 0;
                end = i;
                break;
            }

            long b = sum - target;
            if(map.containsKey(b)){
                start = map.get(b)+1;
                end = i;
                break;
            }
            map.put(sum, i);
        }
        System.out.println("Start :" +start + " End:"+ end);
        if(end == -1){
            result = new ArrayList<>();
            result.add(end);
        }else {
            result = new ArrayList<>( list.subList(start, end+1) );
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
