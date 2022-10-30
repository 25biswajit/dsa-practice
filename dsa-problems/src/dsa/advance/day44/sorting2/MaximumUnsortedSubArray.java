package dsa.advance.day44.sorting2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/*
Given an array A of non-negative integers of size N. Find the minimum sub-array Al, Al+1 ,..., Ar such that if we sort(in ascending order)
that sub-array, then the whole array should get sorted. If A is already sorted, output -1.
A = [1, 3, 2, 4, 5], Ans : [1, 2] , If we sort the sub-array A1, A2, then the whole array A gets sorted.
*/

public class MaximumUnsortedSubArray {
    @Test
    public void test1(){
        int[] arr = {1, 3, 2, 4, 5};
        int[] expected = {1,2};
        Assertions.assertArrayEquals(expected, subArrayUnSorted_bf(arr));
        Assertions.assertArrayEquals(expected, subArrayUnSorted_twoPointer(arr));
    }
    @Test
    public void test2(){
        int[] arr = {1, 6, 5, 7, 8, 9, 11,10, 13, 12, 14, 15};
        int[] expected = {1,9};
        Assertions.assertArrayEquals(expected, subArrayUnSorted_bf(arr));
        Assertions.assertArrayEquals(expected, subArrayUnSorted_twoPointer(arr));
    }
    @Test
    public void test3(){
        int[] arr = {4, 15, 4, 4, 15, 18, 20};
        int[] expected = {1,3};
        Assertions.assertArrayEquals(expected, subArrayUnSorted_bf(arr));
        Assertions.assertArrayEquals(expected, subArrayUnSorted_twoPointer(arr));
    }
    @Test
    public void test5(){
        int[] arr = {4, 4, 4, 15, 18, 20};
        int[] expected = {-1};
        Assertions.assertArrayEquals(expected, subArrayUnSorted_bf(arr));
        Assertions.assertArrayEquals(expected, subArrayUnSorted_twoPointer(arr));
    }
    @Test
    public void test4(){
        int[] arr = {2,3,5,7,10,6,11,15,18,20};
        int[] expected = {3,5};
        Assertions.assertArrayEquals(expected, subArrayUnSorted_bf(arr));
        Assertions.assertArrayEquals(expected, subArrayUnSorted_twoPointer(arr));
    }

    // N log N + N
    public int[] subArrayUnSorted_bf(int[] array) {
        int[] arrayCopy = Arrays.copyOf(array, array.length);
        Arrays.sort(arrayCopy);
        int end = -1,start=-1;
        int i = 0;
        while (i < array.length){
            if(array[i] != arrayCopy[i]){
                if(start == -1) { start = i; }
                else { end = i;}
            }
            i++;
        }
        if(start == -1){// array already Sorted
            return new int[]{-1};
        }else {
            return new int[]{start, end};
        }
    }

    // N
    public int[] subArrayUnSorted_twoPointer(int[] array) {
        int i = 0;
        while (i+1 < array.length && array[i] <= array[i+1]){
            i++;
        }
        if(i == array.length-1){
            return new int[]{-1};
        }
        int j = array.length-1;
        while (j-1 >=0 && array[j-1] <= array[j]){
            j--;
        }
        int[] res = new int[2];
        int min_unsorted = array[i + 1], max_unsorted = array[i + 1];
        for (int k = i; k <= j; k++) {
            max_unsorted = Math.max(max_unsorted, array[k]);
            min_unsorted = Math.min(min_unsorted, array[k]);
        }
        int k = 0;
        while (k <= i && array[k] <= min_unsorted){
            k++;
        }
        res[0] = k;
        k = array.length-1;
        while (k>=j && array[k] >= max_unsorted){
            k--;
        }
        res[1]=k;
        return res;
    }
}
/*
Brute Force: Create Array Copy & Sort Array Copy & the find 1st left & right occurrance while 1st left & right mismatch happens
Example :
Array = 4, 15, 4, 4, 15, 18, 20
Sorted Array Copy = 4, 4, 4, 15, 15, 18, 20
L = Index when A[i] != Copy[i] happens from 1st time while scanning from left side
R = Index when A[i] != Copy[i] happens from 1st time while scanning from right side

Optimised Approach : 2 Pointer Technique
Assume that Al, …, Ar is the minimum-unsorted-subarray which is to be sorted, then subarrays A0, …, Al-1 and Ar+1, …, AN-1 will be in sorted(ascending) order.
How would you solve it now?
Assume that Al, …, Ar is the minimum-unsorted-subarray which is to be sorted.
then min(Al, …, Ar) >= max(A0, …, Al-1)
and max(Al, …, Ar) <= min(Ar+1, …, AN-1)
You can use this to observe and solve.
How would you solve it now?
You can use two pointer technique to solve this question.
ARRAY = [sorted left part] + [unsorted part] + [sorted right part]
now condition is max(left) =< min(unsorted part) && min(right) >= max(unsorted) : it needs to be true
Steps :
Find i ( index ) = where left sorted part ends
Find j ( index ) = where right sorted part ends
Now [ j - i + 1] Part = Is Un Sorted Section
That means L should lie between 0 to i & R should lie between j to N-1
This has to be true for all elements
max(left sorted part) =< min(unsorted part) && max(unsorted part) <= min(right sorted part)
Find Max_UnSorted , Min_UnSorted
Find "L" = where , left sorted array elements > min(unsorted part)
Find "R" = where , right sorted array elements < max(unsorted part)
*/
