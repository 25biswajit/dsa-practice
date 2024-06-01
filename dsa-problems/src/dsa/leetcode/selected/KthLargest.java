package dsa.leetcode.selected;

import dsa.utils.ArrayUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class KthLargest {

    @Test
    public void test(){
        int[] a = {3,2,1,5,6,4};
        int res = findKthLargest(a, 2);
        Assertions.assertEquals(5, res);
    }

    public int findKthLargest(int[] arr, int k) {
        int s = 0, e = arr.length - 1;
        int index = 0;
        while(true){
            index = partitionIndex(arr, s, e);
            ArrayUtils.printArray(arr);
            if(k-1 == index) break;
            if(k-1 < index){ e = index - 1; }
            else{ s = index + 1;}
        }
        return arr[index];
    }

    private int partitionIndex(int[] a, int s, int e){
        int p = s;
        s = s+1;

        while(s <= e){
            if(a[s] < a[p] && a[e] > a[p]){
                swap(a, s, e);
                s++;
                e--;
            }
            if(a[s] >= a[p]) { s++; }
            if(a[e] <= a[p]) { e--; }
        }
        swap(a, e, p);
        System.out.println("index:" + e);
        return e;
    }

    private void swap(int[] a, int s, int e){
        int temp = a[s];
        a[s] = a[e];
        a[e] = temp;
    }
}
