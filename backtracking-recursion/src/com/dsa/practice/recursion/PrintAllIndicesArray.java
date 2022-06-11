package com.dsa.practice.recursion;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// Print All Indices for a specific number in an Array
public class PrintAllIndicesArray {
    @Test
    public void test(){
        PrintAllIndicesArrayAlgorithm algorithm = new PrintAllIndicesArrayAlgorithm();
        int[] array = new int[]{1,2,3,4,5,3,7,8,3};
        Assertions.assertArrayEquals(new int[]{2,5,8} , algorithm.main(array,3));
    }
}

class PrintAllIndicesArrayAlgorithm{

    int[] main(int[] array, int number){
        return printAllIndicesArray(array, 0, number, 0);
    }

    int[] printAllIndicesArray(int[] array, int index, int number, int foundSoFarCount){
        if(index == array.length){
            return new int[foundSoFarCount];
        }
        int[] newArray =
                printAllIndicesArray(array,
                        index+1,
                        number,
                        array[index] == number ? foundSoFarCount+1 : foundSoFarCount);
        if(array[index] == number) {
            newArray[foundSoFarCount] = index;
        }
        return newArray;
    }
}
