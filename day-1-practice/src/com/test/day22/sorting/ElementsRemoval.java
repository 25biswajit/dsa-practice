package com.test.day22.sorting;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class ElementsRemoval {
    @Test
    public void test(){
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(3,1,9,7));
        Assertions.assertEquals(solve(list), solve1(list));
    }

    public int solve(ArrayList<Integer> list) {
        Comparator<Integer> reverseComp = Comparator.reverseOrder();
        Collections.sort(list, reverseComp);
        Integer cost = 0;
        for(int i = 0; i < list.size();i++){
            cost = cost + ( list.get(i) * (i+1) );
        }
        return cost;
    }

    public int solve1(ArrayList<Integer> list) {
        Comparator<Integer> reverseComp = Comparator.reverseOrder();
        Collections.sort(list, reverseComp);
        Integer sum = 0;
        for(Integer x : list){
            sum += x;
        }
        Integer cost = sum;
        for(int i = 1; i < list.size();i++){
            sum = sum - list.get(i-1);
            cost = cost + sum;
        }
        return cost;
    }
}
