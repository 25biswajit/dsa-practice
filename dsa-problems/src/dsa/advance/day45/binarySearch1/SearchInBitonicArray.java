package dsa.advance.day45.binarySearch1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
Given a bitonic sequence A of N distinct elements, write a program to find a given element B in the bitonic sequence in O(logN) time.
A Bitonic Sequence is a sequence of numbers which is first strictly increasing then after a point strictly decreasing.
A = [3, 9, 10, 20, 17, 5, 1]
B = 20
Output : 3

A = [5, 6, 7, 8, 9, 10, 3, 2, 1]
B = 30
Output : -1
*/
public class SearchInBitonicArray {
    @Test
    public void test1(){
        int[] array = {3, 9, 10, 20, 17, 5, 1}; int key = 20;
        Assertions.assertEquals(3, searchInBitornicArray(array,key));
    }
    @Test
    public void test2(){
        int[] array = {5, 6, 7, 8, 9, 10, 3, 2, 1}; int key = 30;
        Assertions.assertEquals(-1, searchInBitornicArray(array,key));
    }
    @Test
    public void test3(){ // when mid is before peak or bitonic point i.e. on increasing side
        int[] array = {1, 2, 6, 7, 8, 9, 10, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 5, 4, 3};
        for(int i = 0; i < array.length ; i++){
            int key = array[i];
            int result = searchInBitornicArray(array,key);
            System.out.println(key + " found in :" + result);
            Assertions.assertEquals(i, result);
        }
    }
    @Test
    public void test4(){ // when mid is after peak or bitonic point i.e. on decreasing side
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11};
        for(int i = 0; i < array.length ; i++){
            int key = array[i];
            int result = searchInBitornicArray(array,key);
            System.out.println(key + " found in :" + result);
            Assertions.assertEquals(i, result);
        }
    }
    // TC: 3 * log N = O( log N) , SC: O(1)
    public int searchInBitornicArray(int[] array, int key){
        int ansIndex = -1;
        int peakIndex = findPeakIndex(array);
        if(peakIndex == -1 || array[peakIndex] < key) return -1;
        if(array[peakIndex] == key) return peakIndex;
        else {
            ansIndex = search(array, 0, peakIndex-1, true, key);
            if(ansIndex == -1) ansIndex = search(array, peakIndex+1, array.length-1, false, key);
        }
        return ansIndex;
    }

    private int search(int[] array, int low, int high, boolean isIncreasing, int key) {
        while (low <= high){
            int mid = (low + high)/2;
            if(array[mid] == key){
                return mid;
            }
            if(isIncreasing){
                if(array[mid] > key){ high = mid - 1; }
                else { low = mid + 1; }
            }else {
                if(array[mid] > key){  low = mid + 1;}
                else { high = mid - 1; }
            }
        }
        return -1;
    }

    private int findPeakIndex(int[] array){
        int n = array.length;
        if(n < 2){
            return 0;
        }
        if(array[0] > array[1]){ return 0; }
        if(array[n-2] < array[n-1]){ return n-1; }
        int low = 0, high = array.length-1;
        while (low <= high){
            int mid = (low + high)/2;
            if(mid == array.length - 1 || mid == 0){
                return mid;
            }
            else if(array[mid-1] < array[mid] && array[mid] > array[mid+1]){
                return mid;
            }
            else if(array[mid] > array[mid+1]){
                high = mid - 1;
            }
            else{
                low = mid + 1;
            }
        }
        return -1;
    }
}

/*
An efficient solution is based on Binary Search.
The idea is to find the bitonic/peak point k which is the index of the maximum element of given sequence.
If the element to be searched is greater than maximum element return -1,
else search the element in both halves.
*/
