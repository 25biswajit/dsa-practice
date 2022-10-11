package dsa.advance.day45.binarySearch1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FindPeak {
    @Test
    public void test1(){
        int[] array = {1, 2, 3, 4, 5};
        Assertions.assertEquals(5, findPeak(array));
    }
    @Test
    public void test2(){
        int[] array = {5, 17, 100, 11};
        Assertions.assertEquals(100, findPeak(array));
    }
    @Test
    public void test3(){
        int[] array = {50, 17, 10, 5};
        Assertions.assertEquals(50, findPeak(array));
    }

    public int findPeak(int[] array){
        int low = 0, high = array.length-1;
        while (low < high){
            int mid = (low + high)/2;

            if(array[mid] > array[mid+1]){
                
            }
            else if(array[mid-1] < array[mid] && array[mid] < array[mid+1]){
                low = mid + 1;
            }
            else if(array[mid-1] > array[mid] && array[mid] > array[mid+1]){
                high = mid - 1;
            }
        }
        if(high == low){
            return array[high];
        }
        return -1;
    }
}
