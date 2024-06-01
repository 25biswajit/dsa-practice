package dsa.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MergeSortedArray {

    @Test
    public void test(){
        int a[] = {1,4,8,10};
        int b[] = {2,3,9};
        merge(a,b);
        int expected1[] = {1,2,3,4};
        Assertions.assertArrayEquals(expected1, a);
        int expected2[] = {8,9,10};
        Assertions.assertArrayEquals(expected2, b);
    }


    public void merge(int[] first, int[] second) {
        int l1 = first.length;
        int l2 = second.length;

        // 'MAXN' will store the value greater than all the elements of 'ARR1' and 'ARR2'.
        int max = findMax(first,second);
        int p1 = 0, p2 = 0, p3 = 0;

        // Incrementing 'MAXN' by 1 to avoid collision in modulo operation.
        max++;

        while(p1 < l1 && p2 < l2){

            // Extracting the old values.
            int e1 = first[p1] % max;
            int e2 = second[p2] % max;

            // Updating element with new value.
            if(Integer.compare(e1,e2) == -1){
                if(p3 < l1){ first[p3] += (e1 * max);}
                else{ second[p3 - l1] += (e1 * max); }
                p1++;
            }
            else{
                if(p3 < l1){ first[p3] += (e2 * max); }
                else{ second[p3 - l1] += (e2 * max); }
                p2++;
            }
            p3++;
        }

        // Processing remaining elements of 'ARR1'.
        while(p1 < l1){
            int e1 = first[p1] % max;
            if(p3 < l1){ first[p3] += (e1 * max);}
            else{ second[p3 - l1] += (e1 * max); }
            p1++;
            p3++;
        }

        // Processing remaining elements of 'ARR2'.
        while(p2 < l2){
            int e2 = second[p2] % max;
            if(p3 < l1){ first[p3] += (e2 * max);}
            else{ second[p3 - l1] += (e2 * max); }
            p2++;
            p3++;
        }

        // Bring back to Original Sorted Value
        convertToOrginalArray(first,max);
        convertToOrginalArray(second,max);
    }

    private void convertToOrginalArray(int[] arr, int max) {
        for(int i = 0; i < arr.length; i++){
            arr[i] = arr[i] / max;
        }
    }

    private int findMax(int[] a, int[] b) {
        int max = Integer.MIN_VALUE;
        for(int v : a){
            max = Math.max(v, max);
        }
        for(int v : b){
            max = Math.max(v, max);
        }
        return max;
    }
}
