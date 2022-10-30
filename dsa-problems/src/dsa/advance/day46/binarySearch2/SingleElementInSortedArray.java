package dsa.advance.day46.binarySearch2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
Given a sorted array of integers A where every element appears twice except for one element which appears once,
find and return this single element that appears only once.
NOTE: Users are expected to solve this in O(log(N)) time.
A= {9,9,4,4,5,6,6,8,8,1,1}, Ans = 5.
*/

public class SingleElementInSortedArray {
    @Test
    public void test1(){
        int[] a = {9,9,4,4,5,6,6,8,8,1,1};
        Assertions.assertEquals(5, findSingleElement(a));
    }
    @Test
    public void test2(){
        int[] a = {1, 1, 2, 2, 3};
        Assertions.assertEquals(3, findSingleElement(a));
    }
    @Test
    public void test3(){
        int[] a = {5, 9, 9, 4, 4, 10, 10};
        Assertions.assertEquals(5, findSingleElement(a));
    }
    // TC: O(log N)
    public int findSingleElement(int[] array){
        int n = array.length;
        if(array[0] != array[1]) return array[0];
        if(array[n-2] != array[n-1]) return array[n-1];
        int low = 1;
        int high = n - 2;
        while (low <= high ){
            int mid = (low + high)/2;
            if(array[mid] == array[mid-1]){
                mid = mid-1;
            }
            if(mid % 2 == 0){ // Even
                if(array[mid] != array[mid+1]){
                    return array[mid];
                }else {
                    low = mid + 2;
                }
            }
            else{ //mid % 2 == 1 ( Odd )
                high = mid - 1;
            }
        }
        return -1;
    }

}
