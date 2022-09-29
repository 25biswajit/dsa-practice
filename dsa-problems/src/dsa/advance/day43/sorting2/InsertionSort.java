package dsa.advance.day43.sorting2;

import dsa.utils.ArrayUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InsertionSort {
    @Test
    public void test1(){
        int[] unSortedArray = {1, 4, 10, 2, 1, 5};
        Assertions.assertEquals(6, swapCountInsertionSort(unSortedArray));
    }

    public int swapCountInsertionSort(int[] array) {
        int n = array.length;
        int swapCount = 0;
        for (int i = 0; i < n; i++){
            for (int j = n - 2; j >= 0; j--) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                    swapCount++;
                }
            }
        }
        ArrayUtils.printArray(array);
        return swapCount;
    }

    private void swap(int[] array, int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
