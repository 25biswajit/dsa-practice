package com.test.day17.bitmanipulation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SolutionUnset {
    @Test
    public void test(){
        Assertions.assertEquals(32 , solve(37L,3));
    }

    public Long solve(Long A, int B) {
        Long result = A;
        long na = A.longValue();
        for(int i = 0 ; i < B ; i++){
            if( ((na >> i) & 1) == 1 ){
                result = result - (1 << i);
            }
        }
        return result;
    }

    public Long solve1(Long A, int B) {
        int c = 0;
        while(A > 0){
            A = A >> 1;
            c++;
        }
        c = c - B;
        A = new Long((1 << c) - 1);
        System.out.println( A << B);
        return new Long(((1 << c) - 1) << B);

    }
}
