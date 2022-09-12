package dsa.advance.day37.mod;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


/*
Given two integers A and B, find the greatest possible positive integer M, such that A % M = B % M.
1 <= A, B <= 10 pow 9
A != B
*/

public class A_B_Modulo {
    @Test
    public void test(){
        Assertions.assertEquals(12, solve_naive(4,16));
        Assertions.assertEquals(1341076, solve_naive(6816621,8157697));
        Assertions.assertEquals(12, solve_easy(4,16));
        Assertions.assertEquals(1341076, solve_easy(6816621,8157697));
        Assertions.assertEquals(12, solve(4,16));
        Assertions.assertEquals(1341076, solve(6816621,8157697));
    }

    public int solve_easy(int A, int B) {
        int max = Math.max(A, B);
        int min = Math.min(A, B);
        return max-min;
    }

    public int solve(int A, int B) {
        return Math.abs(A-B);
    }


    public int solve_naive(int A, int B) {
        int max = Math.max(A,B);
        int min = Math.min(A,B);
        List<Integer> factors = calculateFactors(max-min);
        int result = factors.get(0);
        for(int i =1; i < factors.size();i++){
            result = Math.max(result, factors.get(i));
        }
        return result;
    }

    private List<Integer> calculateFactors(int num) {
        List<Integer> list = new ArrayList<>();
        for(int i = 1; i * i <= num; i++){
            if(num % i == 0){
                list.add(i);
                if(num/i != i){
                    list.add(num/i);
                }
            }
        }
        return list;
    }
}
