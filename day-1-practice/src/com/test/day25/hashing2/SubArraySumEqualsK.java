package com.test.day25.hashing2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
/*
https://www.youtube.com/watch?v=20v8zSo2v18
Pepcoding Solution Video
Watch HashMap & Heap playlist
https://www.youtube.com/watch?v=bit4Jn-ZoyQ&list=PL-Jc9J83PIiEp9DKNiaQyjuDeg3XSoVMR
*/

public class SubArraySumEqualsK {
    @Test
    public void test1(){
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(30,-30,20,-20));
        Assertions.assertEquals(solveBruteForce(list,0), solveOptimized(list,0));
    }
    @Test
    public void test2(){
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(0,4,2,1,0,2,3,2,0,6,1,0));
        Assertions.assertEquals(solveBruteForce(list,7), solveOptimized(list,7));
    }

    @Test
    public void test3(){
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(-2, 16, -12, 5, 7, -1, 2, 12, 11));
        Assertions.assertEquals(solveBruteForce(list,17), solveOptimized(list,17));
    }

    @Test
    public void test4(){
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(3,9,-2,4,1,-7,2,6,-5,8,-3,-7,6,2));
        Assertions.assertEquals(solveBruteForce(list,5), solveOptimized(list,5));
    }

    // Pepcoding Solution
    public int solveOptimized(ArrayList<Integer> list, int target){
        int c = 0;
        int sum = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(sum, 1);
        for (int i = 0; i< list.size(); i++){
            sum += list.get(i);
            int part = sum - target;
            c = c + map.getOrDefault(part,0);
            int freq = map.getOrDefault(sum,0);
            map.put(sum, freq + 1);
        }
        return c;
    }

    public int solveBruteForce(ArrayList<Integer> list, int target){
        int c = 0;
        for (int i = 0; i< list.size(); i++){
            int sum = 0;
            for(int j = i; j <list.size() ; j++){
                sum += list.get(j);
                if(sum == target){
                    ++c;
                }
            }
        }
        System.out.println(c);
        return c;
    }

    public int solveNotWorkedForAllCase(ArrayList<Integer> list, int target){
        if(target == 0) return solveSubArraySumZero(list);
        int c = 0;
        int sum = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(sum, 1);
        for (int i = 0; i< list.size(); i++){
            sum += list.get(i);
            if(sum == 0){
                Integer freq = map.get(sum);
                freq = freq == null ? 0 : freq;
                map.put(sum, freq + 1);
            }
            else if(sum % target == 0){
                int prevValue = sum - target;
                if(map.containsKey(prevValue)){
                    int prevCount = map.get(prevValue);
                    c = c + prevCount;
                }
                Integer freq = map.get(sum);
                freq = freq == null ? 0 : freq;
                map.put(sum, freq + 1);
            }
        }
        //System.out.println("Total :"+ c);
        return c;
    }

    public int solveSubArraySumZero(ArrayList<Integer> list) {
        int sum = 0;
        int c = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        for(Integer x : list){
            int freq = 1;
            sum += x;
            if(map.containsKey(sum)){
                c = (c + map.get(sum))%1000000007;
                freq = map.get(sum) + 1;
            }
            map.put(sum, freq);
        }
        return c;
    }
}
