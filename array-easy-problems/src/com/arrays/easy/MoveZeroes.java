package com.arrays.easy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MoveZeroes {

    @Test
    public void test(){
        int[] input1 = {5,1,0,1,0,3};
        moveZeroes(input1);
        int[] expected = {5,1,1,3,0,0};
        Assertions.assertArrayEquals(expected, input1);
    }

    public void moveZeroes(int[] numbers) {
        int p1 = 0;
        int p2 = 0;
        while(p1<=p2 && p2<numbers.length){
            if(numbers[p1] == 0 && numbers[p2] == 0){
                p2++;
            }
            else if(numbers[p1] == 0 && numbers[p2]!=0){
                swap(numbers, p1, p2);
                p1++;
                p2++;
            }
            else if(numbers[p1] != 0){
                p1++;
                p2++;
            }
        }
    }

    private void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
