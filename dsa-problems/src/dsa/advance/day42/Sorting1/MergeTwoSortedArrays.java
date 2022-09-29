package dsa.advance.day42.Sorting1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//Given two sorted integer arrays A and B, merge B and A as one sorted array and return it as an output.
public class MergeTwoSortedArrays {
    @Test
    public void test1(){
        int[] one = {4, 7, 9};
        int[] two = {2, 11, 19};
        int[] merged = {2, 4, 7, 9, 11, 19};
        int[] actual = mergeSortedArray(one,two);
        Assertions.assertArrayEquals(merged,actual);
    }
    @Test
    public void test2(){
        int[] one = {4, 7, 9};
        int[] two = {};
        int[] merged = {4, 7, 9};
        int[] actual = mergeSortedArray(one,two);
        Assertions.assertArrayEquals(merged,actual);
    }

    //TC: O(N+M), SC: O(N+M)
    public int[] mergeSortedArray(final int[] arraySortedOne, final int[] arraySortedTwo) {
        int[] mergedSortedArray = new int[arraySortedOne.length + arraySortedTwo.length];

        int p1=0,p2=0,p3=0;

        while(p1 < arraySortedOne.length && p2 < arraySortedTwo.length){
            if(arraySortedOne[p1] < arraySortedTwo[p2]){
                mergedSortedArray[p3] = arraySortedOne[p1];p1++;p3++;
            }else {
                mergedSortedArray[p3] = arraySortedTwo[p2];p2++;p3++;
            }
        }
        while (p1 < arraySortedOne.length){
            mergedSortedArray[p3] = arraySortedOne[p1];p1++;p3++;
        }
        while (p2 < arraySortedTwo.length){
            mergedSortedArray[p3] = arraySortedTwo[p2];p2++;p3++;
        }
        return mergedSortedArray;
    }
}
