package dsa.leetcode.selected;

import dsa.utils.ArrayUtils;
import org.junit.jupiter.api.Test;

public class RotateArray {

    @Test
    public void test(){
        int[] a = {1,2,3,4,5,6,7};
        rotate(a,3);
    }

    public void rotate(int[] a, int k) {
        int n = a.length;
        k = (n - 1 - k) % n;
        reverse (a, 0, k);
        ArrayUtils.printArray(a);
        reverse (a, k+1, n-1);
        ArrayUtils.printArray(a);
        reverse (a, 0, n-1);
        ArrayUtils.printArray(a);
    }

    private void reverse(int[] a, int s, int e){
        while(s < e){
            swap(a, s, e);
            s++;
            e--;
        }
    }

    private void swap(int[] a, int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
