package dsa.advance.day46.binarySearch2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FindRotatedArray {
    @Test
    public void test1(){
        int[] array = {8,9,10,1,2,3,4,5,6,7};
        for(int i=0;i<array.length;i++){
            Assertions.assertEquals(i, searchRotatedArray(array,array[i]));
        }
    }
    @Test
    public void test2(){
        int[] array = {4,5,6,7,0,1,2};
        Assertions.assertEquals(4, searchRotatedArray(array,0));
    }

    public int searchRotatedArray_old(int[] array, int key) {
        int low = 0, high = array.length - 1;
        while (low <= high){
            int mid = (low + high)/2;
            if(array [mid] == key){
                return mid;
            }
            boolean isFrontSorted = array[low] <= array [mid];
            boolean isBackSorted = array[mid] <= array [high];

            if(isFrontSorted){
                if(array[low] <= key && key < array[mid]){
                    high = mid - 1;
                }else {
                    low = mid + 1;
                }
            }
            else if(isBackSorted){
                if(array[mid] < key && key <= array[high]){
                    low = mid + 1;
                }else {
                    high = mid - 1;
                }
            }
            else{
                return -1;
            }
        }
        return -1;
    }

    public int searchRotatedArray(int[] a, int k) {
        int n = a.length;
        if(n == 1 && a[0] != k) return -1;
        if(n == 1) return 0;

        int l = 0, h = n-1, mid = 0;

        while(l <= h){
            mid = (l + h)/2;
            if(a[mid] == k) return mid;
            // front sorted
            if(a[l] <= a[mid]){
                if(a[mid] > k && k >= a[l]){
                    h = mid - 1;
                }else{
                    l = mid + 1;
                }
            }
            // back sorted
            else if(a[mid] <= a[h]){
                if(a[mid] < k && k <= a[h]){
                    l = mid + 1;
                }else{
                    h = mid - 1;
                }
            }
        }
        return -1;
    }
}
