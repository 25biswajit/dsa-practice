package com.test.day25.hashing2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CountPairSum {
    @Test
    public void test1(){
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1,2,1,2));
        Assertions.assertEquals(4, solve(list,3));
    }

    @Test
    public void test2(){
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1,1,1,1));
        Assertions.assertEquals(6, solve(list,2));
    }

    public int solve(ArrayList<Integer> list, int target) {
        int c = 0;
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i< list.size(); i++){
            int a = list.get(i);
            int b = target - a;
            List<Integer> list2 = map.get(b);
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
