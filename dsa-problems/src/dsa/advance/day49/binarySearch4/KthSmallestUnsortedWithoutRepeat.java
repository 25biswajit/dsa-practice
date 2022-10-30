package dsa.advance.day49.binarySearch4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class KthSmallestUnsortedWithoutRepeat {
    @Test
    public void test1(){
        int[] array1 = {2,8,3,11,14};
        int[] array2 = {2,8,3,11,14};
        Arrays.sort(array2);
        for(int i = 0; i < array2.length; i++){
            Assertions.assertEquals(array2[i], KthSmallestUnsortedWithoutRepeat(array1,i));
            Assertions.assertEquals(array2[i], KthSmallestUnsortedWitRepeat(array1,i));
        }
    }
    @Test
    public void test2(){
        int[] array1 = {11,24,18,3,5,27,34,9,40};
        int[] array2 = {11,24,18,3,5,27,34,9,40};
        Arrays.sort(array2);
        for(int i = 0; i < array2.length; i++){
            Assertions.assertEquals(array2[i], KthSmallestUnsortedWithoutRepeat(array1,i));
            Assertions.assertEquals(array2[i], KthSmallestUnsortedWitRepeat(array1,i));
        }
    }
    @Test
    public void test3(){
        int[] array1 = {15,10,15,16,4,19,10,15};
        int[] array2 = {4,10,10,15,15,15,16,19};
        for(int i = 0; i < array2.length; i++){
            Assertions.assertEquals(array2[i], KthSmallestUnsortedWitRepeat(array1,i));
        }
    }

    public int KthSmallestUnsortedWithoutRepeat(int array[], int k){
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for(int i = 0; i< array.length; i++){
            min = Integer.min(min, array[i]);
            max = Integer.max(max, array[i]);
        }
        int ans = -1;
        int low = min, high = max;
        while (low <= high){
            int mid =  ( low + high )/2;
            int count = countElemLessThan(array,mid);
            if(count == k) {
                ans = mid;
                low = mid + 1;
            }
            else if(count > k) { high = mid - 1;}
            else {low = mid + 1;}
        }
        return ans;
    }
    public int KthSmallestUnsortedWitRepeat(int array[], int k){
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for(int i = 0; i< array.length; i++){
            min = Integer.min(min, array[i]);
            max = Integer.max(max, array[i]);
        }
        int ans = -1;
        int low = min, high = max;
        while (low <= high){
            int mid =  ( low + high )/2;
            int count = countElemLessThan(array,mid);
            if(count > k) { high = mid - 1;}
            else {ans = mid; low = mid + 1;}
        }
        return ans;
    }

    private int countElemLessThan(int[] array, int mid) {
        int count = 0;
        for(int i=0; i< array.length; i++){
            if(array[i] < mid){
                count++;
            }
        }
        return count;
    }
}
