package dsa.advance.day42.sorting1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;


/*Given an array of positive integers A, check and return whether the array elements are consecutive or not.
NOTE: Try this with O(1) extra space.*/
public class ArrayConsecutiveElements {
    @Test
    public void test1(){
        int[] a = {3, 2, 1, 4, 5};
        Assertions.assertEquals(1, solve(a));
    }
    @Test
    public void test2(){
        int[] a = {3, 2, 1, 5, 5};
        Assertions.assertEquals(0, solve(a));
    }

    // TC: O(N Log N)
    public int solve(int[] A) {
        Arrays.sort(A);
        for(int i=1; i<A.length;i++){
            if(A[i] - A[i-1] != 1){
                return 0;
            }
        }
        return 1;
    }
}
