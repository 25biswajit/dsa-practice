package dsa.basic.practice;

public class RecursionTest {
    public static void main(String[] args) {
        RecursionTest recursionTest = new RecursionTest();
        //System.out.println(1<<5);
        System.out.println(recursionTest.powMod(-1,1,20));
        //System.out.println(recursionTest.solve(4,6));
    }

    public int solve(int A, int B) {
        if(A == 1 && B == 1) return 0;
        int mid = (1 << (A-1)) / 2;
        if(B > mid){
            return 1-solve(A-1, B-mid);
        }else{
            return solve(A-1, B);
        }
    }

    public int powMod(int A, int B, int C) {
        int res = 0;
        if(A==0) return 0;
        if(B == 0) return 1;
        long p = powMod(A, B/2, C);
        if(B % 2 == 0){
            res = (int)((p * p) % C);
        }else{
            res = (int)(((p * p)%C * A) % C);
        }
        return res < 0 ? C+res : res;
    }
}
