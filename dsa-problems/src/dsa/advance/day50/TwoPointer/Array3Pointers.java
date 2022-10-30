package dsa.advance.day50.TwoPointer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Array3Pointers {
    @Test
    public void test1(){
        int[] A = {1, 4, 10};
        int[] B = {2, 15, 20};
        int[] C = {10, 12};
        Assertions.assertEquals(5, minimizeMaxPairDiff(A,B,C));
    }

    public int minimizeMaxPairDiff(final int[] A, final int[] B, final int[] C) {
        int pA = A.length-1;
        int pB = B.length-1;
        int pC = C.length-1;
        int ans = Integer.MAX_VALUE;
        while (pA >= 0 && pB >=0 && pC >= 0){
            int a = A[pA], b = B[pB], c = C[pC];
            int diffA_B = Math.abs(a-b), diffB_C = Math.abs(b-c), diffC_A = Math.abs(c-a);
            ans = Integer.min( ans, Integer.max(diffA_B, Integer.max(diffB_C, diffC_A)));
            int pMax = Integer.max(a, Integer.max(b, c));
            if(pMax == a){ pA--; }
            else if(pMax == b){ pB--; }
            else { pC--; }
        }
        return ans;
    }
}
