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

    public int searchRotatedArray(int[] array, int key) {
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
}
