package com.test.day25.hashing2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class DistinctNumbersCountWindowSizeK {
    @Test
    public void test1(){
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 1, 3, 4, 3));
        Integer[] expected = {2, 3, 3, 2};
        Integer[] actual = solveOptimized(list,3).toArray(new Integer[0]);
        Assertions.assertArrayEquals(expected,actual);
    }

    public ArrayList<Integer> solveOptimized(ArrayList<Integer> list, int window) {
        ArrayList<Integer> result = new ArrayList<>();
        int i = 0;
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        while(i < window){
            int key = list.get(i);
            hashMap.put(key, hashMap.getOrDefault(key,0)+1);
            i++;
        }
        result.add( hashMap.size() );
        int p1 = 1;
        int p2 = i;
        while(p2 < list.size()){
            decrementOrDeleteFromMap(list.get(p1-1),hashMap);
            addEntry(list.get(p2),hashMap);
            p1++;
            p2++;
            result.add( hashMap.size() );
        }
        return result;
    }

    private void addEntry(int key, HashMap<Integer, Integer> hashMap) {
        hashMap.put(key, hashMap.getOrDefault(key,0)+1);
    }

    private void decrementOrDeleteFromMap(int key, HashMap<Integer, Integer> hashMap) {
        if(hashMap.containsKey(key)){
            int freq = hashMap.get(key)-1;
            if(freq == 0){
                hashMap.remove(key);
            }else{
                hashMap.put(key, freq);
            }
        }
    }
}
