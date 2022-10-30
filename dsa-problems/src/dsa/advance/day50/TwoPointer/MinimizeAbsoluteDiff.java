package dsa.advance.day50.TwoPointer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MinimizeAbsoluteDiff {

    @Test
    public void test1(){
        int[] A = {1, 4, 5, 8, 10};
        int[] B = {6, 9, 15};
        int[] C = {2, 3, 6, 6};
        Assertions.assertEquals(1, minimizeAbsMaxMinDiff(A,B,C));
    }

    public int minimizeAbsMaxMinDiff(final int[] A, final int[] B, final int[] C) {
        int pA = 0;
        int pB = 0;
        int pC = 0;
        int ans = Integer.MAX_VALUE;
        while (pA < A.length && pB < B.length && pC < C.length){
            int a = A[pA], b = B[pB], c = C[pC];
            int max = Integer.max(a, Integer.max(b, c));
            int min = Integer.min(a, Integer.min(b, c));
            ans = Integer.min( ans, Math.abs(max-min));
            if(min == a){ pA++; }
            else if(min == b){ pB++; }
            else { pC++; }
        }
        return ans;
    }
}
