package dsa.advance.day44.sorting2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static dsa.utils.Constants.mod_prime;

import java.util.Arrays;

/*
Given an array of integers A of size N where N is even.
Divide the array into two subsets such that
1.Length of both subset is equal.
2.Each element of A occurs in exactly one of these subset.
Magic number = sum of absolute difference of corresponding elements of subset.
Note: You can reorder the position of elements within the subset to find the value of the magic number.
For Ex:-
subset 1 = {1, 5, 1},
subset 2 = {1, 7, 11}
Magic number = abs(1 - 1) + abs(5 - 7) + abs(1 - 11) = 12
Return an array B of size 2, where B[0] = maximum possible value of Magic number modulo 109 + 7, B[1] = minimum possible value of a Magic number modulo 109 + 7.
A = [3, 11, -1, 5]
All possible magical numbers:-
sub1 = {3, 11}, sub2 = {-1, 5}, Magic Number = abs(3 - -1) + abs(11 - 5) = 10
sub1 = {3, -1}, sub2 = {11, 5}, Magic Number = abs(3 - 11) + abs(-1 - 5) = 14
sub1 = {3, 5}, sub2 = {11, -1}, Magic Number = abs(3 - 11) + abs(5 - -1) = 14
sub1 = {11, -1}, sub2 = {3, 5}, Magic Number = abs(11 - 3) + abs(-1 - 5) = 14
sub1 = {11, 5}, sub2 = {3, -1}, Magic Number = abs(11 - 3) + abs(5 - -1) = 14
sub1 = {-1, 5}, sub2 = {3, 11}, Magic Number = abs(-1 - 3) + abs(5 - 11) = 10
maximum of all magic number = 14 % 10^9 + 7 = 14
minimum of all magic number = 10 % 10^9 + 7 = 10
*/


public class MaxMinMagic {
    @Test
    public void test1(){
        int[] arr = {3, 11, -1, 5};
        int[] expected = {14,10};
        Assertions.assertArrayEquals(expected, maxMinMagic(arr));
    }

    // N log N + N
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
