package dsa.arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FindKthSmallest {

    @Test
    public void test(){
        int[] a = {7, 10, 4, 3, 20, 15};

        Assertions.assertEquals(4, findKthSmallest(a,2));
    }

    private int findKthSmallest(int[] a, int k){
        int index = -1;
        int n = a.length;
        int l = 0, h = n - 1;
        while(l <= h){
            index = quickSelect(a, l, h);
            if(index == k -1) break;
            else if( index > k - 1) h = index - 1;
            else l = index + 1;
        }

        return a[index];
    }

    private int quickSelect(int[] a, int l, int h) {
        int p = l;
        l++;
        while(l <= h){
            if(a[p] < a[l] && a[p] > a[h]){
                swap_(a,l,h);
                l++;
                h--;
            }
            if(a[p] >= a[l]){
                l++;
            }
            if(a[p] <= a[h]){
                h--;
            }
        }
        swap_(a, p, h);
        return h;
    }

    private void swap_(int[] a, int l, int h) {
        int  temp = a[l];
        a[l] = a[h];
        a[h] = temp;
    }
}
