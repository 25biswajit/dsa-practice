package com.test.day25.hashing2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CountPairDifference {
    @Test
    public void test1(){
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(3, 5, 1, 2));
        Assertions.assertEquals(1, solve(list,4));
    }

    @Test
    public void test2(){
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 1, 2));
        Assertions.assertEquals(4, solve(list,1));
    }

    @Test
    public void test3() {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(18, 10, 26, 17, 30, 13, 30, 10, 20, 13, 10));
        Assertions.assertEquals(5, solve(list, 10));
    }

    public int solve(ArrayList<Integer> list, int target) {
        int c = 0;
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i< list.size(); i++){
            int a = list.get(i);
            int b = a - target;
            List<Integer> list2 = map.get(b);
            if(list2 != null){
                c = (c + list2.size())%1000000007;
            }

            b = a + target;
            list2 = map.get(b);
            if(list2 != null){
                c = (c + list2.size())%1000000007;
            }

            List<Integer> list1 = map.get(a);
            if(list1 == null){
                list1 = new ArrayList<>();
            }
            list1.add(i);
            map.put(a, list1);
        }
        return c;
    }
}
