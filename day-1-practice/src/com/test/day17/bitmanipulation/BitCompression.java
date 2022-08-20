package com.test.day17.bitmanipulation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class BitCompression {
    @Test
    public void test(){
        ArrayList<Integer> list1 = new ArrayList<>(Arrays.asList(1,3,5));
        Assertions.assertEquals(7 , compressBitsNew(list1));

        ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(31,22,37,74,51,64,107,18,29,10,111,92,73,85,45));
        Assertions.assertEquals(121 , compressBitsNew(list2));
    }

    public int compressBits(ArrayList<Integer> A) {

        for(int i=0; i <= A.size()-2; i++){
            for(int j=1; j <=A.size()-1; j++){
                if(i < j) {
                    System.out.println(String.format("Prev : A[%d] = %d, A[%d] = %d", i, A.get(i), j, A.get(j)));
                    Integer a1 = A.get(i) & A.get(j);
                    Integer a2 = A.get(i) | A.get(j);
                    A.set(i, a1);
                    A.set(j, a2);
                    System.out.println(String.format("After : A[%d] = %d, A[%d] = %d", i, a1, j, a2));
                }
            }
        }
        System.out.println(A);
        int c = 0;
        for(Integer x : A){
            c = c ^ x;
        }
        return c;
    }

    public int compressBitsNew(ArrayList<Integer> A) {
        int i = 0;
        int j = 1;
        while(j < A.size()){
            System.out.println(String.format("Prev : A[%d] = %d, A[%d] = %d", i, A.get(i), j, A.get(j)));
            Integer a1 = A.get(i) & A.get(j);
            Integer a2 = A.get(i) | A.get(j);
            A.set(i, a1);
            A.set(j, a2);
            System.out.println(String.format("After : A[%d] = %d, A[%d] = %d", i, a1, j, a2));
            i++;
            j++;
        }
        System.out.println(A);
        int c = 0;
        for(Integer x : A){
            c = c ^ x;
        }
        return c;
    }
}
