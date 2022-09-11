package dsa.advance.day35.bitman1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

//REPEAT TWICE EXCEPT 2 UNIQUE
/*
Given an array of positive integers A, two integers appear only once, and all the other integers appear twice.
Find the two integers that appear only once.
Note: Return the two numbers in ascending order.
A = [1, 2, 3, 1, 2, 4] => 3 and 4 appear only once.
*/
public class SingleNumber3 {

    @Test
    public void test(){
        int[] arr = {10,8,8,9,12,9,6,11,10,6,12,17};
        int[] expected = {11,17};
        Assertions.assertArrayEquals(expected, getUniqueElements(arr));
    }

    public int[] getUniqueElements(int[] array) {
        int xorTwoUniqueElements = xor_unique_two(array);
        int splitBitPos = determine_any_set_bit(xorTwoUniqueElements);
        int[] result = split_array_by_bit_pos(splitBitPos,array);
        Arrays.sort(result);
        return result;
    }


    private int xor_unique_two(int array[]) {
        int xor = 0;
        for(int i = 0; i< array.length; i++){
            xor = xor ^ array[i];
        }
        return xor;
    }

    private int determine_any_set_bit(int xorTwoUniqueElements) {
        int pos = 0;
        while ( isIthBitSet( xorTwoUniqueElements, pos ) == false) {
            pos++;
        }
        return pos;
    }


    private int[] split_array_by_bit_pos(int splitBitPos, int[] array) {
        int elements_set_bit = 0;
        int elements_unset_bit = 0;
        for(int i = 0; i < array.length ; i++){
            if(isIthBitSet( array[i], splitBitPos)){
                elements_set_bit = elements_set_bit ^ array[i];
            }else{
                elements_unset_bit = elements_unset_bit ^ array[i];
            }
        }
        int[] result = {elements_set_bit,elements_unset_bit};
        return result;
    }

    private boolean isIthBitSet(int num, int i) {
        return ((num >> i) & 1 ) == 1;
    }

}
