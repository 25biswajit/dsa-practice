package dsa.advance.day45.binarySearch1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
Given a sorted array A of size N and a target value B, return the index (0-based indexing) if the target is found.
If not, return the index where it would be if it were inserted in order.
NOTE: You may assume no duplicates in the array. Users are expected to solve this in O(log(N)) time.
A = [1, 3, 5, 6], B = 5, Ans : 2
*/

public class SortedInsertPosition {
    @Test
    public void test1(){
        int[] array = {1,3,5,6,8};
        Assertions.assertEquals(1, searchInsert(array,2));
        Assertions.assertEquals(4, searchInsert(array,8));
        Assertions.assertEquals(2, searchInsert(array,5));
        Assertions.assertEquals(0, searchInsert(array,0));
        Assertions.assertEquals(5, searchInsert(array,9));
        Assertions.assertEquals(1, searchInsert_Good(array,2));
        Assertions.assertEquals(4, searchInsert_Good(array,8));
        Assertions.assertEquals(2, searchInsert_Good(array,5));
        Assertions.assertEquals(0, searchInsert_Good(array,0));
        Assertions.assertEquals(5, searchInsert_Good(array,9));
    }

    public int searchInsert(int[] array, int key) {
        int low = 0;
        int high = array.length - 1;
        if(array[high] < key){ return high+1;}
        if(array[low] > key){ return low; }
        int ans = -1;
        while (low <= high){
            int mid = (low + high)/2;
            if(array[mid] == key){
                return mid;
            }
            else if(array[mid] > key){
                ans = mid;
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }
        return ans;
    }

    public int searchInsert_Good(int[] array, int key) {
        int low = 0;
        int high = array.length - 1;
        while (low <= high){
            int mid = (low + high)/2;
            if(array[mid] == key){
                return mid;
            }
            else if(array[mid] > key){
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }
        return low;
    }
}
