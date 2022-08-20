package com.test.day10.array.prefixsum1;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class RangeSum {

    @Test
    public void test(){
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(7, 3, 1, 5, 5, 5, 1, 2, 4, 5));
        ArrayList<ArrayList<Integer>> inputRangeList = new ArrayList<>();
        inputRangeList.add(new ArrayList<>(Arrays.asList(7,10)));
        inputRangeList.add(new ArrayList<>(Arrays.asList(3,10)));
        inputRangeList.add(new ArrayList<>(Arrays.asList(3,5)));
        inputRangeList.add(new ArrayList<>(Arrays.asList(1,10)));
        System.out.println( rangeSum(list, inputRangeList) );
    }


    public ArrayList<Long> rangeSum(ArrayList<Integer> A, ArrayList<ArrayList<Integer>> B) {
        ArrayList<Long> sumList = new ArrayList<>();
        ArrayList<Long> prefixSumList = prefixSum(A);
        System.out.println(prefixSumList);
        Long sumStart = 0L;
        for(ArrayList<Integer> range : B){
            Integer s = range.get(0);
            Integer e = range.get(1);
            if(s-2 >= 0){
                sumStart = prefixSumList.get(s-2);
            }else {
                sumStart = 0L;
            }
            Long sum = prefixSumList.get(e-1) - sumStart;
            sumList.add(sum);
        }
        return sumList;
    }

    private ArrayList<Long> prefixSum(ArrayList<Integer> list){
        System.out.println(list);
        ArrayList<Long> prefixSumList = new ArrayList<>();
        prefixSumList.add(new Long(list.get(0)));
        for(int i=1; i<list.size();i++){
            Long sum = new Long( prefixSumList.get(i-1) + list.get(i));
            prefixSumList.add(sum);
        }
        System.out.println(prefixSumList.size());
        return prefixSumList;
    }
}
