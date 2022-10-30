package dsa.advance.day45.binarySearch1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SearchRange {
    @Test
    public void test1(){
        int[] array = {5, 7, 7, 8, 8, 10};
        int[] expected = {3,4};
        Assertions.assertArrayEquals(expected, searchRange(array,8));
    }

    public int[] searchRange(final int[] array, int key) {
        int[] ans = new int[2];
        int firstOcc = findFirstOcc(array,key);
        if(firstOcc == -1){
            ans[0] = -1; ans[1] = -1;
        }else {
            int lastOcc = findLastOcc(array,key);
            ans[0] = firstOcc; ans[1] = lastOcc;
        }
        return ans;
    }

    private int findFirstOcc(int[] array, int searchKey) {
        int low = 0;
        int high = array.length - 1;
        int firstOcc = -1;
        while (low <= high){
            int mid = ( low + high ) / 2;
            if(array[mid] == searchKey){
                firstOcc = mid;
                high = mid - 1;
            }else if(array[mid] > searchKey){
                high = mid - 1;
            }else {
                low = mid + 1;
            }
        }
        return firstOcc;
    }
    private int findLastOcc(int[] array, int searchKey) {
        int low = 0;
        int high = array.length - 1;
        int lastOcc = -1;
        while (low <= high){
            int mid = ( low + high ) / 2;
            if(array[mid] == searchKey){
                lastOcc = mid;
                low = mid + 1;
            }else if(array[mid] > searchKey){
                high = mid - 1;
            }else {
                low = mid + 1;
            }
        }
        return lastOcc;
    }
}
