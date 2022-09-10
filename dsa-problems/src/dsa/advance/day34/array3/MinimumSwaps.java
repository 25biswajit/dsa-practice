package dsa.advance.day34.array3;

/*
Given an array of integers A and an integer B,
Find and return the minimum number of swaps required to bring all the numbers less than or equal to B together.
Note: It is possible to swap any two elements, not necessarily consecutive.
A = [1, 12, 10, 3, 14, 10, 5]
B = 8
A = [1, 12, 10, 3, 14, 10, 5]
After swapping  12 and 3, A => [1, 3, 10, 12, 14, 10, 5].
After swapping  the first occurence of 10 and 5, A => [1, 3, 5, 12, 14, 10, 10].
Now, all elements less than or equal to 8 are together.
*/

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// Sliding Window
public class MinimumSwaps {
    @Test
    public void test1(){
        int[] array = {1, 12, 10, 3, 14, 10, 5};
        int B = 8;
        Assertions.assertEquals(2, minSwapBringTogether(array,B));
    }
    @Test
    public void test2(){
        int[] array = {1, 12, 10, 3, 14, 5};
        int B = 8;
        Assertions.assertEquals(1, minSwapBringTogether(array,B));
    }
    @Test
    public void test3(){
        int[] array = {52, 7, 93, 47, 68, 26, 51, 44, 5, 41, 88, 19, 78, 38, 17, 13, 24, 74, 92, 5, 84, 27, 48, 49, 37, 59, 3, 56, 79, 26, 55, 60, 16, 83, 63, 40, 55, 9, 96, 29, 7, 22, 27, 74, 78, 38, 11, 65, 29, 52, 36, 21, 94, 46, 52, 47, 87, 33, 87, 70};
        int B = 19;
        Assertions.assertEquals(7, minSwapBringTogether(array,B));
    }
/*
    First, we will find the number of elements that are less than or equal to B. Let the count comes out to be X.
    We know that we need at most X-1 swaps to make all X elements to be consecutive.
    Maintain a window of X and check how many elements we need to swap so that all X elements come in that window.
    We store the minimum of all that and return that.

    Hint 1: This  problem can be solved using sliding window concept.
    Hint 2 : Need to find how many numbers are less than B.
    Hint 3: Create window = count of numbers are less than B.
*/

    public int minSwapBringTogether(int[] array, int B) {
        int n = array.length;
        int totalCountLessThatB = 0;
        for(int i = 0; i< n; i++){
            if(array[i] <= B){
                totalCountLessThatB++;
            }
        }

        int i = 0;
        int window = totalCountLessThatB;
        int countLessThanB_currentWindow = 0;
        while (i< window){
            if(array[i] <= B){
                countLessThanB_currentWindow++;
            }
            i++;
        }
        int minSwap = totalCountLessThatB - countLessThanB_currentWindow;
        i = window;
        while (i < n){
            if(array[i-window] <= B){
                countLessThanB_currentWindow--;
            }
            if(array[i] <= B){
                countLessThanB_currentWindow++;
            }
            minSwap = Math.min(minSwap, totalCountLessThatB - countLessThanB_currentWindow);
            i++;
        }
        return minSwap;
    }
}
