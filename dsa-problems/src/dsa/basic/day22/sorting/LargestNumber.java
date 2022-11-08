package dsa.basic.day22.sorting;

import dsa.utils.ArrayUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/*Given an array A of non-negative integers, arrange them such that they form the largest number.
Note: The result may be very large, so you need to return a string instead of an integer.
A = [3, 30, 34, 5, 9]
Reorder the numbers to [9, 5, 34, 3, 30] to form the largest number.*/
public class LargestNumber {
    @Test
    public void test(){
        List<Integer> list = Arrays.asList(3, 30, 34, 5, 9);
        Assertions.assertEquals("9534330",largestNumber(list));
        Assertions.assertEquals("9534330",largestNumber(ArrayUtils.convertToIntArray(list)));

        list = Arrays.asList(2, 3, 9, 0);
        Assertions.assertEquals("9320",largestNumber(list));
        Assertions.assertEquals("9320",largestNumber(ArrayUtils.convertToIntArray(list)));

        list = Arrays.asList(0,0,0,0,0);
        Assertions.assertEquals("0",largestNumber(list));
        Assertions.assertEquals("0",largestNumber(ArrayUtils.convertToIntArray(list)));
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

        // A comparison function which is used by
        // sort() in printLargest()
        Collections.sort(listStr, (X, Y) -> {

            // first append Y at the end of X
            String XY = X + Y;

            // then append X at the end of Y
            String YX = Y + X;

            // Now see which of the two
            // formed numbers
            // is greater
            return XY.compareTo(YX) > 0 ? -1 : 1;
        });

        String s = "";
        for(String x : listStr){
            s = s + x;
        }

        return s;
    }

    public String largestNumber(int[] numbers){
        Integer[] sorted = ArrayUtils.convertToIntegerArray(numbers);
        Arrays.sort(sorted, (a,b) -> {
            String ab = a+""+b;
            String ba = b+""+a;
            return ab.compareTo(ba) > 0 ? -1 : 1;
        });
        StringBuilder ans = new StringBuilder();
        int zeros = 0;
        for(Integer x : sorted){
            ans.append(x);
            if(x == 0) zeros++;
        }
        return zeros == numbers.length ? "0" : ans.toString();
    }
}

/*
Approach
The idea is to sort the numbers in descending order, and then just concatenate them.
However, observe that just sorting in descending order won’t give us our optimal answer.
Take the example of 431 and 50, our code will concatenate it in order 43150 but observe that 50431 gives us a better result.
So, the idea comes that we need to implement a custom sorting algorithm that sorts our array in a manner that we can obtain our optimal answer
by concatenating it.
How to Sort the Array?
To compare the numbers A and B, we can view both of them as strings, and make them of the same length by calculating (A + B) and (B + A).
Then we can just compare these 2 strings lexicographically, and sort them according to it.
*/

