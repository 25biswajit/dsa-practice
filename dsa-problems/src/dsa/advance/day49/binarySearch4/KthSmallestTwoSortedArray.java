package dsa.advance.day49.binarySearch4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class KthSmallestTwoSortedArray {

    @Test
    public void test1(){
        int[] array1 = {3,3,6,7,7,11,14,17};
        int[] array2 = {2,2,10,10,13,20,20};
        Assertions.assertEquals(10, KthSmallestTwoSortedArray(array1,array2,8) );
    }

    public int KthSmallestTwoSortedArray(int[] array1, int[] array2, int k){
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for(int i = 0; i< array1.length; i++){
            min = Integer.min(min, array1[i]);
            max = Integer.max(max, array1[i]);
        }
        for(int i = 0; i< array2.length; i++){
            min = Integer.min(min, array2[i]);
            max = Integer.max(max, array2[i]);
        }
        int ans = -1;
        int low = min, high = max;
        while (low <= high){
            int mid =  ( low + high )/2;
            int count = countElemLessThan(array1,mid);
            count += countElemLessThan(array2,mid);
            if(count > k) { high = mid - 1;}
            else {ans = mid;low = mid + 1;}
        }
        return ans;
    }

    private int countElemLessThanOld(int[] array,int mid) {
        int count = 0;
        for(int i=0; i< array.length; i++){
            if(array[i] < mid){
                count++;
            }else {
                break;
            }
        }
        return count;
    }

    private int countElemLessThan(int[] array,int key) {
        int count = -1;
        int low = 0, high = array.length-1;
        while(low <= high){
            int mid = (low + high)/2;
            if(array[mid] < key){
                count = mid;
                low = mid + 1;
            }else {
                high = mid - 1;
            }
        }
        return count + 1;
    }
}
