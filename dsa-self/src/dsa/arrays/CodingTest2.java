package dsa.arrays;

/*

Problem:- Rotate an array to the left or right direction by count k.

Example 1:
Input :- Array = [1,2,3,4,5,6,7], direction = left, k = 1
Output :- Array = [2,3,4,5,6,7,1]

Input :- Array = [1,2,3,4,5,6,7], direction = left, k = 2
Output :- Array = [3,4,5,6,7,1,2]

Input :- Array = [1,2,3,4,5,6,7], direction = left, k = 8
Output :- Array = [2,3,4,5,6,7,1]

Input :- Array = [1,2,3,4,5,6,7], direction = right, k = 3
Output :- Array = [5,6,7,1,2,3,4]

Input :- Array = [1,2,3,4,5,6,7], direction = right, k = 1
Output :- Array = [7,1,2,3,4,5,6]

Note: Treat array as a circular array where it can be rotated even if the number of rotations is more than the length of the array.
*/

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CodingTest2 {

    @Test
    public void test1(){
        int[] a = {1,2,3,4,5,6,7};
        int[] expected = {3,4,5,6,7,1,2};
//        int[] expected = {2,3,4,5,6,7,1};
//        int[] expected = {1,2,3,4,5,6,7};
        Assertions.assertArrayEquals(expected, rotateLeft(a, 9));
    }


    public int [] rotateLeft(int[] a, int k){
        int n = a.length;
        k = k % n;

        reverse(a, 0, k-1);
        reverse(a, k, n-1);
        reverse(a, 0, n-1);

        return a;
    }


    public void reverse(int[] a, int l, int r){
        while(l < r){
            swapF(a, l, r);
            l++;
            r--;
        }
    }

    private void swapF(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

}
