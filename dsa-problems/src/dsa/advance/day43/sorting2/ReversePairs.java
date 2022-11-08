package dsa.advance.day43.sorting2;


import dsa.utils.ArrayUtils;
import dsa.utils.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/*
** Hard
Given an array of integers A, we call (i, j) an important reverse pair if i < j and A[i] > 2*A[j].
Return the number of important reverse pairs in the given array A.
A= 2,4,3,5,1, ans = 3 Pair = (4,1)(3,1),(5,1)
A=3,5,9,13,1,2,4,6 Ans = 10
*/
public class ReversePairs {

    @Test
    public void test1(){
        int[] array = {1, 3, 2, 3, 1};
        Assertions.assertEquals(2, countPairs(array));
    }
    @Test
    public void test2(){
        int[] array = {10, 3, 8,15, 6, 12, 2, 18, 7, 1};
        Assertions.assertEquals(17, countPairs(array));
    }
    @Test
    public void test3(){
        int[] array = {-1,-2,-3};
        Assertions.assertEquals(3, countPairs(array));
    }
    @Test
    public void test4(){
        int[] array = {2,2,-2};
        Assertions.assertEquals(2, countPairs(array));
    }
    @Test
    public void test7(){
        int[] array = {2,4,3,5,1};
        Assertions.assertEquals(3, countPairs(array));
    }
    @Test
    public void test5(){ // ??????
        int[] array = {769, -71, 599, -1438, -530, -512, 1680, 1907, -301, 492, -844, 1765, -188, 685, -1879, -699, -990, 1022, 459, 528, -930, 1051, 1412, -1598, 245, -480, 1979, 1212, 1177, 447, -509, 881, 1968, -1603, -429, 1165, 405, 426, -1610, 931, -835, -1156, 1273, -143, -940, 199, -1886, -1646, 390, -1349, -256, -256, -103, -135, 637, -605, 55, -1805, -17, -229, 1162, 288, -818, -922, 32, -1032, -1823, -1874, -1650, 146, 721, 1586, 1969, 1720, -993, -1137, -1233, -1629, -879, -277, 444, -1191, -1273, 127, 1785, 1407, -1460, 414, -1578, -1348, 72, -794, 632, 877, 338, 1921, -650, -1314, 1187, -40 };
        Assertions.assertEquals(2761, countPairs(array));
    }
    @Test
    public void test6() { // ??????
        int[] array = {2000000000, 2000000000, -2000000000};
        Assertions.assertEquals(2, countPairs(array));
    }
    @Test
    public void test8(){
        int[] array = {3,5,9,13,1,2,4,6};
        Assertions.assertEquals(10, countPairs(array));
    }
    @Test
    public void test9(){
        int[] array = {769, -71, 599, -1438, -530, -512, 1680, 1907, -301, 492, -844, 1765, -188, 685, -1879, -699, -990, 1022, 459, 528, -930, 1051, 1412, -1598, 245, -480, 1979, 1212, 1177, 447, -509, 881, 1968, -1603, -429, 1165, 405, 426, -1610, 931, -835, -1156, 1273, -143, -940, 199, -1886, -1646, 390, -1349, -256, -256, -103, -135, 637, -605, 55, -1805, -17, -229, 1162, 288, -818, -922, 32, -1032, -1823, -1874, -1650, 146, 721, 1586, 1969, 1720, -993, -1137, -1233, -1629, -879, -277, 444, -1191, -1273, 127, 1785, 1407, -1460, 414, -1578, -1348, 72, -794, 632, 877, 338, 1921, -650, -1314, 1187, -40 };
        Assertions.assertEquals(2761, reversePairs(array));
    }
    @Test
    public void test10(){
        int[] array = {769, -71, 599, -1438, -530, -512, 1680, 1907, -301, 492, -844, 1765, -188, 685, -1879, -699, -990, 1022, 459, 528, -930, 1051, 1412, -1598, 245, -480, 1979, 1212, 1177, 447, -509, 881, 1968, -1603, -429, 1165, 405, 426, -1610, 931, -835, -1156, 1273, -143, -940, 199, -1886, -1646, 390, -1349, -256, -256, -103, -135, 637, -605, 55, -1805, -17, -229, 1162, 288, -818, -922, 32, -1032, -1823, -1874, -1650, 146, 721, 1586, 1969, 1720, -993, -1137, -1233, -1629, -879, -277, 444, -1191, -1273, 127, 1785, 1407, -1460, 414, -1578, -1348, 72, -794, 632, 877, 338, 1921, -650, -1314, 1187, -40 };
        Assertions.assertEquals(2761, reversePairs_(array));
    }

    // TC: O(N log N), SC: O(N + log N) = O(N)
    Integer count = 0;
    public int countPairs(int[] array){
        count = 0;
        mergeSort(array, 0, array.length-1);
        ArrayUtils.printArray(array);
        return count;
    }

    /*Time Complexity : O( N log N ) + O (N) + O (N)
    Reason : O(N) – Merge operation , O(N) – counting operation ( at each iteration of i , j doesn’t start from 0 . Both of them move linearly )
    Space Complexity : O(N)
    Reason : O(N) – Temporary ArrayList*/
    public void mergeSort(int[] array, int start, int end){
        if(start == end) return;
        int mid = (start + end)/2;
        mergeSort(array, start, mid);
        mergeSort(array, mid+1, end);
        merge(array, start, mid, end);
    }

    // O(N) + O(N)
    public void merge(int[] array, int start, int mid, int end){
        int j = mid + 1;
        // count the number of pairs where A[i] > 2*A[j] where i > j
        // O(N)
        for(int i = start; i <= mid; i++){
            while (j <= end && array[i] > 2 * (long)array[j]){
                j++;
            }
            count += (j-(mid+1));
        }
        // O(N)
        // Merge 2 Sorted Array
        int[] temp = new int[end - start + 1];
        int p1 = start, p2 = mid+1, p3 = 0;
        while (p1 <= mid && p2 <= end){
            if(array[p1] <= array[p2]){
                temp[p3] = array[p1];
                p1++;
                p3++;
            }else {
                temp[p3] = array[p2];
                p2++;
                p3++;
            }
        }
        while (p1 <= mid){
            temp[p3] = array[p1];
            p1++;
            p3++;
        }
        while (p2 <= end){
            temp[p3] = array[p2];
            p2++;
            p3++;
        }

        for(int k= 0,i=start; i<=end; i++,k++){
            array[i] = temp[k];
        }
    }

    public int reversePairs(int[] numbers) {
        return mergesort_and_count(numbers, 0, numbers.length-1);
    }
    private int mergesort_and_count(int[] array, int start, int end){
        if (start < end) {
            int mid = (start + end) / 2;
            // divide the array into two half and sort them
            int count = mergesort_and_count(array, start, mid) + mergesort_and_count(array, mid + 1, end);
            // count the number of pairs where A[i] > 2*A[j] where i > j
            int j = mid + 1;
            for(int i = start; i <= mid; i++){
                while (j <= end && array[i] > 2 * (long)array[j]){
                    j++;
                }
                count += (j-(mid+1));
            }
            Arrays.sort(array, start, end+1); // Instead of Sort We can use merge 2 sorted Array
            return count;
        } else
            return 0;
    }

    public int reversePairs_(int[] numbers) {
        return mergeSort_(numbers, 0, numbers.length-1);
    }
    private int mergeSort_(int[] array, int start, int end){
        if (start < end) {
            int mid = (start + end) / 2;
            // divide the array into two half and sort them
            int count = mergeSort_(array, start, mid) + mergeSort_(array, mid + 1, end);
            // count the number of pairs where A[i] > 2*A[j] where i > j
            int j = mid + 1;
            for(int i = start; i <= mid; i++){
                while (j <= end && array[i] > 2 * (long)array[j]){
                    j++;
                }
                count += (j-(mid+1));
            }
            mergeSortedSubArray(array, start, mid, end);
            return count;
        } else
            return 0;
    }

    public int[] mergeSortedSubArray(final int[] array, int start, int mid, int end) {
        int[] mergedSortedArray = new int[end-start+1];

        int p1=start,p2=mid+1,p3=0;

        while(p1 <= mid && p2 <= end){
            if(array[p1] <= array[p2]){
                mergedSortedArray[p3] = array[p1];p1++;p3++;
            }else {
                mergedSortedArray[p3] = array[p2];p2++;p3++;
            }
        }
        while (p1 <= mid){
            mergedSortedArray[p3] = array[p1];p1++;p3++;
        }
        while (p2 <= end){
            mergedSortedArray[p3] = array[p2];p2++;p3++;
        }
        for(int i=start,j=0;i<=end;i++,j++){
            array[i] = mergedSortedArray[j];
        }
        return mergedSortedArray;
    }
}

/*We can use two loops and calculate the number of pairs that satisfy the condition, but the time complexity will be O(N^2), which will not work in the worst case.
So we can think of a better solution, i.e., using merge sort.
We will do a usual merge sort, but before calling the merge function, we will calculate the number of pairs using two pointers, considering that the two arrays are sorted individually.
Likewise, we will do this till our mergesort ends, i.e., the array becomes sorted.

https://www.youtube.com/watch?v=QJm2sIJINpA
https://takeuforward.org/data-structure/count-reverse-pairs/
Intuition:

-> We will be using the Merge Sort Algorithm to solve this problem. We split the whole array into 2  parts creating a Merge Sort Tree-like structure. During the conquer step we do the following task :

-> We take the left half of the Array and Right half of the Array, both are sorted in themselves.

-> We will be checking the following condition arr[i] <= 2*arr[j]  where i is the pointer in the Left Array and j is the pointer in the Right Array.

-> If at any point arr[i] <= 2*arr[j] , we add 1  to the answer as this pair has a contribution to the answer.

-> If Left Array gets exhausted before Right Array we keep on adding the distance j pointer traveled as both the arrays are Sorted so the next ith element from Left Subarray will equally contribute to the answer.

-> The moment when both Arrays get exhausted we perform a merge operation. This goes on until we get the original array as a Sorted array.



Approach :

-> We first of all call a Merge Sort function, in that we recursively call Left Recursion and Right Recursion after that we call Merge function in order to merge both Left and Right Calls we initially made and compute the final answer.

-> In the Merge function, we will be using low, mid, high values to count the total number of inversion pairs for the Left half and Right half of the Array.

->  Now, after the above task, we need to Merge the both Left and Right sub-arrays using a temporary vector.

-> After this, we need to copy back the temporary vector to the Original Array. Then finally we return the number of pairs we counted.
*/
