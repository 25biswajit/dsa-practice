package com.test.day22.sorting;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class LargestNumber {
    @Test
    public void test(){
        List<Integer> list = Arrays.asList(3, 30, 34, 5, 9);
        Assertions.assertEquals("9534330",largestNumber(list));

        list = Arrays.asList(2, 3, 9, 0);
        Assertions.assertEquals("9320",largestNumber(list));

        list = Arrays.asList(0,0,0,0,0);
        Assertions.assertEquals("0",largestNumber(list));
    }


    public String largestNumber(final List<Integer> list) {

        List<String> listStr = new ArrayList<>();
        int zeroCount = 0;
        for(Integer x : list){
            if(x == 0){
                zeroCount++;
            }
            listStr.add(String.valueOf(x));
        }

        if(zeroCount == list.size()){
            return "0";
        }

        Collections.sort(listStr, new Comparator<String>()
        {
            // A comparison function which is used by
            // sort() in printLargest()
            @Override public int compare(String X, String Y)
            {

                // first append Y at the end of X
                String XY = X + Y;

                // then append X at the end of Y
                String YX = Y + X;

                // Now see which of the two
                // formed numbers
                // is greater
                return XY.compareTo(YX) > 0 ? -1 : 1;
            }
        });

        String s = "";
        for(String x : listStr){
            s = s + x;
        }

        return s;
    }
}
