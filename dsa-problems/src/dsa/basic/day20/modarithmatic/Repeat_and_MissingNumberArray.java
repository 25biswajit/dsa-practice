package dsa.basic.day20.modarithmatic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Repeat_and_MissingNumberArray {
    @Test
    public void test(){
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(3,1,2,5,3));
        Integer[] expected = {3,4};
        Integer[] actual = repeatedNumber(list).toArray(new Integer[0]);
        Assertions.assertArrayEquals(expected,actual);
    }
    

    public ArrayList<Integer> repeatedNumber(final List<Integer> list) {
        int n = list.size();
        long arraySum = getArraySum(list);
        long arraySquareSum = getArraySquareSum(list);
        long expectedSum = getExpectedSum(n);
        long expectedSquareSum = getExpectedSquareSum(n);
        long d = arraySquareSum - expectedSquareSum;
        long c = arraySum-expectedSum;
        ArrayList<Integer> result = new ArrayList<>();
        long repeat = (d/c + c)/2;
        long missing = (d/c - c)/2;
        result.add((int)repeat);
        result.add((int)missing);
        System.out.println(result);
        return result;
    }

    private long getExpectedSquareSum(int n) {
        long sum = 0;
        while (n > 0){
            long l = n;
            sum += l*l;
            n--;
        }
        return sum;
    }

    private long getExpectedSum(int n) {
        long sum = 0L;
        while (n > 0){
            sum += n;
            n--;
        }
        return sum;
    }

    private long getArraySquareSum(List<Integer> list) {
        long sum = 0;
        for (Integer x : list){
            long l = (long)x;
            sum += l*l;
        }
        return sum;
    }

    private long getArraySum(List<Integer> list) {
        long sum = 0;
        for (Integer x : list){
            sum += x;
        }
        return sum;
    }
}
