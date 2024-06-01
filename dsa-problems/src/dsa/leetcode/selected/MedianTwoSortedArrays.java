package dsa.leetcode.selected;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
The overall run time complexity should be O(log (m+n)).
*/

public class MedianTwoSortedArrays {

    @Test
    public void test1(){
        int[] a1 = {1,3};
        int[] a2 = {2};
        Assertions.assertEquals(2.00, findMedianSortedArrays(a1,a2));
    }

    @Test
    public void test2(){
        int[] a1 = {1,5, 8, 10, 18, 20};
        int[] a2 = {2, 3, 6, 7};
        Assertions.assertEquals(6.5, findMedianSortedArrays(a1,a2));
    }

    @Test
    public void test3(){
        int[] a1 = {1, 5, 8, 10, 18, 20};
        int[] a2 = {21, 30, 35, 40};
        Assertions.assertEquals(19, findMedianSortedArrays(a1,a2));
    }

    @Test
    public void test4(){
        int[] a1 = {1, 6, 8, 10, 20};
        int[] a2 = {2, 3, 6, 18};
        Assertions.assertEquals(6, findMedianSortedArrays(a1,a2));
    }

    @Test
    public void test5(){
        int[] a1 = {1, 3, 5, 8, 10};
        int[] a2 = {2, 3, 6, 18};
        Assertions.assertEquals(5, findMedianSortedArrays(a1,a2));
    }

    public double findMedianSortedArrays(int[] a1, int[] a2) {
        int n1 = a1.length, n2 = a2.length;
        //if(n1 > n2) return findMedianSortedArrays(a2, a1);
        int low = 0, high = n1;
        int mid1, mid2, l1, l2, r1, r2;

        while(low <= high){
            mid1 = (low + high) / 2;
            //mid2 = (n1 + n2 + 1) / 2 - mid1;
            mid2 = (n1 + n2) / 2 - mid1;

            l1 = mid1 > 0 ? a1[mid1 - 1] : Integer.MIN_VALUE;
            l2 = mid2 > 0 ? a2[mid2 - 1] : Integer.MIN_VALUE;
            r1 = mid1 < n1 ? a1[mid1] : Integer.MAX_VALUE;
            r2 = mid2 < n2 ? a2[mid2] : Integer.MAX_VALUE;

            if(l1 > r2) {
                high = mid1 - 1;
            } else if(l2 > r1) {
                low = mid1 + 1;
            } else {
                return ((n1 + n2) % 2 == 0) ?
                        ((Math.max(l1, l2) + Math.min(r1, r2)) / 2.0) :
                        //Math.max(l1, l2);
                        Math.min(r1, r2);
            }
        }
        return 0;
    }
}
