package dsa.advance.day43.sorting2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



public class QuickSort {
    @Test
    public void test1(){
        int[] array = {1, 4, 10, 2, 1, 5};
        int[] expected = {1,1,2,4,5,10};
        Assertions.assertArrayEquals(expected, solve(array));
    }

    public int[] solve(int[] array) {
        quickSort(array, 0, array.length - 1);
        return array;
    }

    private void quickSort(int[] array, int s, int e){
        if(s > e) return; // s>=e will be more efficient
        int pivotIndex = rearrange(array, s, e);
        quickSort(array, s, pivotIndex-1);
        quickSort(array, pivotIndex+1, e);
    }

    private int rearrange(int[] array, int s, int e){
        int pivotIndex = s;
        s = s + 1;
        while(s <= e){
            if(array[pivotIndex] >= array[s]) s++;
            else if(array[pivotIndex] < array[e]) e--;
            //array[pivotIndex] >= array[e] && array[pivotIndex] <= array[s]
            else{
                swap(array, s, e);
                s++;
                e--;
            }
        }
        swap(array, pivotIndex, e);
        return e;
    }

    private void swap(int[] array, int s, int e){
        int temp = array[s];
        array[s] = array[e];
        array[e] = temp;
    }
}