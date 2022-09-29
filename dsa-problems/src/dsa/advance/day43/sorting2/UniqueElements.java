package dsa.advance.day43.sorting2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

//given an array A of N elements. You have to make all elements unique. To do so, in one step you can increase any number by one. Find the minimum number of steps.
public class UniqueElements {
    @Test
    public void test1(){
        int[] array = {1,1,2,2};
        Assertions.assertEquals(4, uniqueElementsCountIncrement(array));
    }

    @Test
    public void test2(){
        int[] array = {51, 6, 10, 8, 22, 61, 56, 48, 88, 85, 21, 98, 81, 76, 71, 68, 18, 6, 14, 23, 72, 18, 56, 30, 97, 100, 81, 5, 99, 2, 85, 67, 46, 32, 66, 51, 76, 53, 36, 31, 81, 56, 26, 75, 69, 54, 54, 54, 83, 41, 86, 48, 7, 32, 85, 23, 47, 23, 18, 45, 79, 95, 73, 15, 55, 16, 66, 73, 13, 85, 14, 80, 39, 92, 66, 20, 22, 25, 34, 14, 51, 14, 17, 10, 100, 35, 9, 83, 31, 60, 24, 37, 69, 62};
        Assertions.assertEquals(239, uniqueElementsCountIncrement(array));
    }

    public int uniqueElementsCountIncrement(int[] array){
        Arrays.sort(array);
        int i = 1;
        int count = 0;
        while (i < array.length){
            if(array[i-1]>=array[i]){
                array[i]++;
                count++;
            }else {
                i++;
            }
        }
        return count;
    }
}
