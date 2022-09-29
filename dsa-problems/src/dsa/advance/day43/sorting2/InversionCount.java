package dsa.advance.day43.sorting2;

import dsa.utils.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//Count Pair if i < j and A[i] > A[j]
public class InversionCount {
    Integer inversionCount = 0;

    @Test
    public void test1(){
        int[] arr = {3, 2, 1};
        Assertions.assertEquals(3, calculateInversionCount(arr));
    }

    @Test
    public void test2(){
        int[] arr = {45, 10, 15, 25, 50};
        Assertions.assertEquals(3, calculateInversionCount(arr));
    }

    // TC: O(N log N), SC: O(N + log N) = O(N)
    public int calculateInversionCount(int[] array){
        inversionCount = 0;
        mergeSort(array, 0, array.length-1);
        return inversionCount;
    }

    public void mergeSort(int[] array, int start, int end){
        if(start == end) return;
        int mid = (start + end)/2;
        mergeSort(array, start, mid);
        mergeSort(array, mid+1, end);
        mergeSortedSubArray(array, start, mid, end);
    }

    public int[] mergeSortedSubArray(final int[] array, int start, int mid, int end) {
        int[] mergedSortedArray = new int[end-start+1];

        int p1=start,p2=mid+1,p3=0;

        while(p1 <= mid && p2 <= end){
            if(array[p1] <= array[p2]){
                mergedSortedArray[p3] = array[p1];p1++;p3++;
            }else {
                mergedSortedArray[p3] = array[p2];p2++;p3++;
                inversionCount = ( inversionCount + (mid - p1 + 1) ) % Constants.mod_prime;
            }
        }
        while (p1 <= mid){
            mergedSortedArray[p3] = array[p1];p1++;p3++;
        }
        while (p2 <= end){
            mergedSortedArray[p3] = array[p2];p2++;p3++;
        }
        for(int i=start,j=0;i<=end;i++,j++){
            array[i] = mergedSortedArray[j];
        }
        return mergedSortedArray;
    }


}
