package dsa.advance.day41.recursion1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
Given a number A, check if it is a magic number or not.
A number is said to be a magic number if
the sum of its digits is calculated till a single digit recursively by adding the sum of the digits after every addition.
If the single digit comes out to be 1, then the number is a magic number.
Sum of digits of (83557) = 28,Sum of digits of (28) = 10,Sum of digits of (10) = 1, Single digit is 1, so it's a magic number. Return 1.
Sum of digits of (1291) = 13, Sum of digits of (13) = 4, Single digit is not 1, so it's not a magic number. Return 0.
*/

public class IsMagic {
    @Test
    public void test(){
        Assertions.assertEquals(1, solve(83557));
        Assertions.assertEquals(1, solveOptimised(83557));
        Assertions.assertEquals(0, solve(1291));
        Assertions.assertEquals(0, solveOptimised(1291));
    }

    // Here Divisibility rule of 9 is being applied
    // TC:O(1) , SC: O(1)
    public int solveOptimised(int num) {
        return num%9 == 1 ? 1 : 0;
    }

    // My Solution
    // TC:O(d) d is number of digit , SC: O(1)
    public int solve(int num) {
        return digitSum(num) != 1 ? 0 : 1;
    }
    public int digitSum(int num){
        if(num == 0) return 0;
        else return ((num % 10) + digitSum(num / 10)) % 9;
    }
}
