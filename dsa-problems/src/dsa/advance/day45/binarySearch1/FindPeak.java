package dsa.advance.day45.binarySearch1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
Local Maxima
Given an array of integers A, find and return the peak element in it. An array element is peak if it is NOT smaller than its neighbors.
For corner elements, we need to consider only one neighbor. We ensure that answer will be unique.
NOTE: Users are expected to solve this in O(log(N)) time. The array may have duplicate elements.
A = [1, 2, 3, 4, 5] Ans : 5
A = [5, 17, 100, 11] Ans : 100
*/
public class FindPeak {
    @Test
    public void test1(){
        int[] array = {1, 2, 3, 4, 5};
        Assertions.assertEquals(5, findPeak(array));
    }
    @Test
    public void test2(){
        int[] array = {5, 17, 100, 11};
        Assertions.assertEquals(100, findPeak(array));
    }
    @Test
    public void test3(){
        int[] array = {50, 17, 10, 5};
        Assertions.assertEquals(50, findPeak(array));
    }
    @Test
    public void test4(){
        int[] array = {10, 100, 100};
        Assertions.assertEquals(100, findPeak(array));
    }
    @Test
    public void test5(){
        int[] array = {10, 100};
        Assertions.assertEquals(100, findPeak(array));
    }
    @Test
    public void test6(){
        int[] array = {100, 100, 100};
        Assertions.assertEquals(100, findPeak(array));
    }
    @Test
    public void test7(){
        int[] array = {100};
        Assertions.assertEquals(100, findPeak(array));
    }

    @Test
    public void test8(){
        int[] array = {3,4,3,2,1};
        Assertions.assertEquals(4, findPeak(array));
    }

    // TC: O(log N), SC: O(1)
    public static int findPeak(int[] array){
        int n = array.length;
        if(n < 2){
            return array[0];
        }
        if(array[0] > array[1]){ return array[0]; }
        if(array[n-2] < array[n-1]){ return array[n-1]; }
        int low = 0, high = array.length-1;
        while (low <= high){
            int mid = (low + high)/2;
            if(mid == array.length - 1 || mid == 0){
                return array[mid];
            }
            else if(array[mid-1] < array[mid] && array[mid] > array[mid+1]){
                return array[mid];
            }
            else if(array[mid] > array[mid+1]){
                high = mid - 1;
            }
            else{
                low = mid + 1;
            }
        }
        return -1;
    }
}
