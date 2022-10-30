package dsa.advance.day44.sorting2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static dsa.utils.Constants.mod_prime;

import java.util.Arrays;

public class MaxMinMagic {
    @Test
    public void test1(){
        int[] arr = {3, 11, -1, 5};
        int[] expected = {14,10};
        Assertions.assertArrayEquals(expected, maxMinMagic(arr));
    }

    public int[] maxMinMagic(int[] array){
        Arrays.sort(array);
        int[] result = new int[2];
        int start = 0, end = array.length-1;
        long max = 0;
        while (start < end){
            max += new Long(array[end]) - new Long(array[start]);
            end--;
            start++;
        }
        max = max % mod_prime;
        result[0] = (int) max;

        int p1 = 0, p2 = 1;
        long min = 0;
        while (p1 < array.length && p2 < array.length){
            min = ( min + new Long(array[p2]) - new Long(array[p1]) );
            p1 = p1 + 2;
            p2 = p2 + 2;
        }
        min = min % mod_prime;
        result[1] = (int) min;
        return result;
    }
}
