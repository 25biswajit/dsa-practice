package com.test.day20.modarithmatic;

/*
If you want to write
A%M=B%M for B>A
then we can say
B%M-A%M=0
(B-A)%M=0
Now B-A is basically divisible by M
So maximum value of M will be B-A
Write same for A>B
it will be A-B
*/

public class A_B_and_Modulo {
    public int solve(int A, int B) {
        return Integer.max((B-A),(A-B));
    }
}
