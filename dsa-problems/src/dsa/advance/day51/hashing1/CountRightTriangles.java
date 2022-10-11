package dsa.advance.day51.hashing1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

/*
Given two arrays of integers A and B of size N each, where each pair (A[i], B[i]) for 0 <= i < N represents a unique point (x, y) in 2D Cartesian plane.
Find and return the number of unordered triplets (i, j, k) such that (A[i], B[i]), (A[j], B[j]) and (A[k], B[k]) form a right-angled triangle with the triangle having one side parallel to the x-axis and one side parallel to the y-axis.
NOTE: The answer may be large so return the answer modulo (109 + 7).
A = [1, 1, 2, 3, 3]
B = [1, 2, 1, 2, 1]
6 triplets which make a right angled triangle are:     (1, 1), (1, 2), (2, 2)
                                                       (1, 1), (3, 1), (1, 2)
                                                       (1, 1), (3, 1), (3, 2)
                                                       (2, 1), (3, 1), (3, 2)
                                                       (1, 1), (1, 2), (3, 2)
                                                       (1, 2), (3, 1), (3, 2)
*/

public class CountRightTriangles {
    @Test
    public void test(){
        int[] x = {1, 1, 2, 3, 3};
        int[] y = {1, 2, 1, 2, 1};
        Assertions.assertEquals(6, countTriangle(x,y));
    }

    //TC: O(N) SC:O(N)
    public int countTriangle(int[] x, int[] y){
        HashMap<Integer,Integer> freqMapX = deriveFreqMap(x);
        HashMap<Integer,Integer> freqMapY = deriveFreqMap(y);
        int count = 0;
        for(int i = 0 ; i < x.length; i++){
            int cx = freqMapX.get(x[i]);
            int cy = freqMapY.get(y[i]);
            count += (cx-1)*(cy-1);
        }
        return count;
    }

    private HashMap<Integer, Integer> deriveFreqMap(int[] array) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int x : array){
            map.put(x, map.getOrDefault(x,0)+1);
        }
        return map;
    }
}
