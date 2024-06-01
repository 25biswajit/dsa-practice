package dsa.leetcode.selected;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//https://leetcode.com/problems/max-chunks-to-make-sorted-ii/
public class MaxChunkMakeSorted2 {

    @Test
    public void test1(){
        int[] a = {4,2,2,1,1};
        Assertions.assertEquals(1, maxChunksToSorted(a));
    }

    public int maxChunksToSorted(int[] a) {
        int n = a.length;
        int[] leftMax = new int[a.length];
        int[] rightMin = new int[a.length];
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++){
            max = Integer.max(max, a[i]);
            min = Integer.min(min, a[n-1-i]);

            leftMax[i] = max;
            rightMin[n-1-i] = min;
        }
        int chunk = 1;
        for(int i = 0; i < n-1; i++){
            if(leftMax[i] <= rightMin[i+1]){
                chunk += 1;
            }
        }
        return chunk;
    }
}
