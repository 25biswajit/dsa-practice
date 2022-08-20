package com.test.day25.hashing2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class PairsWithGivenXor {
    @Test
    public void test1(){
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(17, 18, 8, 13, 15, 7, 11, 5, 4, 9, 12, 6, 10, 14, 16, 3));
        Assertions.assertEquals(5, solve(list,9));
    }
    @Test
    public void test2(){
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(17, 18, 8, 13, 15, 7, 11, 5, 4, 9, 12, 6, 10, 14, 16, 3));
        Assertions.assertEquals(5, solveOptimized(list,9));
    }

    // O(n^2)
    public int solve(ArrayList<Integer> A, int B) {
        int c = 0;
        for(int i=0;i<A.size();i++){
            for(int j=i+1;j<A.size();j++){
                if((A.get(i) ^ A.get(j)) == B){
                    System.out.println(String.format( "Pair: A[%d]=%d , A[%d]=%d",i, A.get(i), j,A.get(j)));
                    ++c;
                }
            }
        }
        return c;
    }

    // O(n^2)
    public int solveOptimized(ArrayList<Integer> list, int target) {
        int c = 0;
        HashSet<Integer> set = new HashSet<>();
        for(Integer a : list){
            int b = a ^ target;
            if(set.contains(b)){
                ++c;
            }else {
                set.add(a);
            }
        }
        return c;
    }
}
