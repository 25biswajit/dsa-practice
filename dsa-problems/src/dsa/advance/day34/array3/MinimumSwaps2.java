package dsa.advance.day34.array3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MinimumSwaps2 {
    @Test
    public void test(){
        int[] arr={1,0,2,4,3,5,7,6,8,9};
        Assertions.assertEquals(3 , sortWithMinSwap(arr));
    }

    public int sortWithMinSwap(int[] array) {
        int i = 0;
        int countSwap = 0;
        while(i< array.length){
            if(array[i] != i){
                countSwap++;
                swap(i, array[i], array);
            }else {
                i++;
            }
        }
        return countSwap;
    }

    private void swap(int pos1, int pos2, int[] array){

        if(pos1 > array.length || pos2 > array.length){
            throw new IllegalArgumentException();
        }

        int temp = array[pos1];
        array[pos1] = array[pos2];
        array[temp] = temp;
    }
}
