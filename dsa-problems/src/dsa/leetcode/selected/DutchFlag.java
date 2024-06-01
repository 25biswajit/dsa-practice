package dsa.leetcode.selected;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//https://leetcode.com/problems/sort-colors/
public class DutchFlag {

    @Test
    public void test(){
        int[] arr = {0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1};
        int[] expected = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2};
        sortColors(arr);
        Assertions.assertArrayEquals(expected, arr);
    }

    public void sortColors(int[] arr) {
        int mid = 0, low = 0, high = arr.length - 1;
        if(high <=0) return;
        while(mid <= high){
            switch(arr[mid]){
                case 0:
                    swap(arr, mid, low);
                    mid++;
                    low++;
                    break;
                case 1:
                    mid++;
                    break;
                case 2:
                    swap(arr, mid, high);
                    high--;
                    break;
            }
        }
    }

    private void swap(int[] arr, int x, int y){
        int t = arr[x];
        arr[x] = arr[y];
        arr[y] = t;
    }
}
