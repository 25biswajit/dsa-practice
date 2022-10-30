package dsa.advance.day42.sorting1;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/*
Given an array A of size N, Groot wants you to pick 2 indices i and j such that
1 <= i, j <= N
A[i] % A[j] is maximum among all possible pairs of (i, j).
Help Groot in finding the maximum value of A[i] % A[j] for some i, j.
*/

public class MaxMod {
    @Test
    public void test1(){
        int[] a = {927, 707, 374, 394, 279, 799, 878, 937, 431, 112};
        Assertions.assertEquals(927, findMaxMod(a));
        Assertions.assertEquals(927, findMaxMod_(a));
    }
    @Test
    public void test2(){
        int[] a = {5,5,5,5,5,5};
        Assertions.assertEquals(0, findMaxMod(a));
        Assertions.assertEquals(0, findMaxMod_(a));
    }
    @Test
    public void test3(){
        int[] a = {0,0,0,0};
        Assertions.assertEquals(0, findMaxMod(a));
        Assertions.assertEquals(0, findMaxMod_(a));
    }

    // TC: O(N), SC: O(1)
    public int findMaxMod(int[] array){
        int max1 = -1;
        int max2 = -1;
        for(int i=0;i<array.length;i++){
            if(max1 < array[i]){
                max2 = max1;
                max1 = array[i];
            }
            else if(max2 < array[i] && max1 > array[i]){
                max2 = array[i];
            }
        }
        if(max1 != 0 && max2 !=-1){
            return max2 % max1;
        }else {
            return 0;
        }
    }

    // TC: O(N Log N), SC: O(1)
    public int findMaxMod_(int[] A) {
        int n = A.length;
        int flag = 0;
        Arrays.sort(A);
        //keep the pointer at the end of the array
        int i = n - 1;
        // keep going backwards until we dont find a different element
        while (flag == 0 && i != 0) {
            if (A[i] == A[i - 1])
                i--;
            else
                flag = 1;
        }
        //if we find a different element before reaching the end of the array, print the element
        //at the index before that
        //else return 0 because in that case all the elements are same and no
        //matter which indices we choose, the answer will always be 0
        if (i != 0)
            return A[i - 1];
        else
            return 0;
    }
}
