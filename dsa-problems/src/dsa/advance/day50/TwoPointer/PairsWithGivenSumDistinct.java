package dsa.advance.day50.TwoPointer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static dsa.utils.Constants.mod_prime;

/*
Given a sorted array of integers (all distinct) A and an integer B,
find and return how many pair of integers ( A[i], A[j] ) such that i != j have sum equal to B.
int[] array = { 1, 2, 3, 5, 6, 7}; Pair Sum = 8;
Pairs : (1,7), (2,6), (3,5)
Ans : 3
*/

// PairsWithGivenSum will work for both distinct & non-distinct
// If the array elements are distinct then algo will be less complex
public class PairsWithGivenSumDistinct {

    @Test
    public void test1(){
        int[] array = {3,7,8,12,19}; int k=15, expected = 2;
        Assertions.assertEquals(expected, countPairGivenSumDistinct_TwoPointer(array, k));
        Assertions.assertEquals(expected, countPairGivenSumDistinct_HashSet(array, k));
        Assertions.assertEquals(expected, countPairGivenSumDistinct_BinarySearch(array, k));
    }
    @Test
    public void test2(){
        int[] array = { 2, 3, 5, 6, 10};int k = 6;int expected=0;
        Assertions.assertEquals(expected, countPairGivenSumDistinct_TwoPointer(array, k));
        Assertions.assertEquals(expected, countPairGivenSumDistinct_HashSet(array, k));
        Assertions.assertEquals(expected, countPairGivenSumDistinct_BinarySearch(array, k));
    }
    @Test
    public void test3(){
        int[] array = { 1, 2, 3, 5, 6, 7};int k = 8;int expected=3;
        Assertions.assertEquals(expected, countPairGivenSum_BruteForce(array, k));
        Assertions.assertEquals(expected, countPairGivenSumDistinct_TwoPointer(array, k));
        Assertions.assertEquals(expected, countPairGivenSumDistinct_HashSet(array, k));
        Assertions.assertEquals(expected, countPairGivenSumDistinct_BinarySearch(array, k));
    }
    // TC : O(N^2) , SC : O(1)
    public int countPairGivenSum_BruteForce(int[] array, int k){
        int count = 0;
        for(int i = 0; i< array.length; i++){
            for(int j = i+1; j< array.length; j++){
                if(array[i] + array[j] == k){
                    System.out.println("Pairs :" + array[i] +","+ array[j]);
                    count++;
                }
            }
        }
        return count;
    }

    // TC : O(N) , SC : O(N)
    public int countPairGivenSumDistinct_HashSet(int[] array, int k){
        int count = 0;
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i< array.length; i++){
            int a = array[i];
            int b = k - a;
            if(set.contains(b)){
                count = (count + 1) % mod_prime;
            }else {
                set.add(a);
            }
        }
        return count;
    }

    public int countPairGivenSumDistinct_BinarySearch(int[] array, int k){
        int count = 0;
        for(int i = 0; i< array.length; i++){
            int a = array[i];
            int b = k - a;
            int countB = binarySearch(array, b, i);
            count = (count + countB) % mod_prime;
        }
        return count;
    }
    // TC : log N
    private int binarySearch(int[] array, int searchKey, int i) {
        int low = 0;
        int high = array.length - 1;
        while (low <= high){
            int mid = ( low + high ) / 2;
            if(array[mid] == searchKey){ return mid > i ? 1 : 0; }
            else if(array[mid] > searchKey){ high = mid - 1; }
            else { low = mid + 1;}
        }
        return 0;
    }

    // TC : O(N) , SC : O(1) // ** Test Case : 7,8 **
    public int countPairGivenSumDistinct_TwoPointer(int[] array, int k){
        long count = 0;
        int p1 = 0, p2 = array.length - 1;
        while (p1 < p2){
            int a = array[p1];
            int b = array[p2];
            int sum = a + b;
            if(sum == k) {
                count = (count + 1) % mod_prime;
                p1++;
            }
            else if(sum > k){ p2--; }
            else { p1++; }
        }
        return (int)count;
    }

}

/*
Two Pointer Approach:
We will have three conditions:
1. A[i] + A[j] < sum  --> We will increase the pointer i.
2. A[i] + A[j] > sum  --> We will decrease the pointer j.
3. A[i] + A[j] = sum  --> count++ , We will increase the pointer i.
*/
