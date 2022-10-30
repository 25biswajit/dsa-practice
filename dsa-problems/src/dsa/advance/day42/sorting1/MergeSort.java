package dsa.advance.day42.sorting1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MergeSort {
    @Test
    public void test(){
        int[] unSortedArray = {1, 4, 10, 2, 1, 5};
        int[] sortedArray = {1, 1, 2, 4, 5, 10};
        Assertions.assertArrayEquals(sortedArray, sort(unSortedArray));
    }

    public int[] sort(int[] array) {
        mergeSort(array, 0, array.length-1);
        return array;
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
