package dsa.advance.day50.TwoPointer;

import static dsa.utils.Constants.mod_prime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

/*
Given a sorted array of integers (not necessarily distinct) A and an integer B,
find and return how many pair of integers ( A[i], A[j] ) such that i != j have sum equal to B.
*/

public class PairsWithGivenSum {

    @Test
    public void test1(){
        int[] array = {3,7,8,12,19}; int k=15, expected = 2;
        Assertions.assertEquals(expected, countPairGivenSum_TwoPointer(array, k));
        Assertions.assertEquals(expected, countPairGivenSum_HashMap(array, k));
        Assertions.assertEquals(expected, countPairGivenSum_BinarySearch(array, k));
    }
    @Test
    public void test2(){
        int[] array = { 2, 3, 5, 6, 10};int k = 6;int expected=0;
        Assertions.assertEquals(expected, countPairGivenSum_TwoPointer(array, k));
        Assertions.assertEquals(expected, countPairGivenSum_HashMap(array, k));
        Assertions.assertEquals(expected, countPairGivenSum_BinarySearch(array, k));
    }
    @Test
    public void test3(){
        int[] array = { 1, 2, 6, 6, 7, 9, 9};int k = 13;int expected=2;
        Assertions.assertEquals(expected, countPairGivenSum_TwoPointer(array, k));
        Assertions.assertEquals(expected, countPairGivenSum_HashMap(array, k));
        Assertions.assertEquals(expected, countPairGivenSum_BinarySearch(array, k));
    }
    @Test
    public void test4(){
        int[] array = { 2, 3, 3, 5, 7, 7, 8, 9, 9, 10, 10};int k = 11;int expected=4;
        Assertions.assertEquals(expected, countPairGivenSum_TwoPointer(array, k));
        Assertions.assertEquals(expected, countPairGivenSum_HashMap(array, k));
        Assertions.assertEquals(expected, countPairGivenSum_BinarySearch(array, k));
    }
    @Test
    public void test5(){
        int[] array = { 1, 1, 3, 3, 5, 5, 6, 6, 6, 9, 10 };int k = 9;int expected = 6;
        Assertions.assertEquals(expected, countPairGivenSum_TwoPointer(array, k));
        Assertions.assertEquals(expected, countPairGivenSum_HashMap(array, k));
        Assertions.assertEquals(expected, countPairGivenSum_BinarySearch(array, k));
    }

    @Test
    public void test6(){
        int[] array = { 2, 2, 3, 4, 4, 5, 6, 7, 10  };int k = 8;int expected = 4;// [ (6,2), (6,2), (4,4), (5,3) ]
        Assertions.assertEquals(expected, countPairGivenSum_TwoPointer(array, k));
        Assertions.assertEquals(expected, countPairGivenSum_HashMap(array, k));
        Assertions.assertEquals(expected, countPairGivenSum_BinarySearch(array, k));
    }

    @Test
    public void test7(){
        int[] array = { 4, 4, 4, 4};int k = 8;int expected = 6;
        Assertions.assertEquals(expected, countPairGivenSum_TwoPointer(array, k));
        Assertions.assertEquals(expected, countPairGivenSum_HashMap(array, k));
        Assertions.assertEquals(expected, countPairGivenSum_BinarySearch(array, k));
    }
    @Test
    public void test8(){
        int[] array = { 3,3,3,4,4,4};int k = 7;int expected = 9;
        Assertions.assertEquals(expected, countPairGivenSum_BruteForce(array, k));
        Assertions.assertEquals(expected, countPairGivenSum_TwoPointer(array, k));
        Assertions.assertEquals(expected, countPairGivenSum_HashMap(array, k));
        Assertions.assertEquals(expected, countPairGivenSum_BinarySearch(array, k));
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
    public int countPairGivenSum_HashMap(int[] array, int k){
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i< array.length; i++){
            int a = array[i];
            int b = k - a;
            if(map.containsKey(b)){
                count = (count + map.get(b)) % mod_prime;
            }
            map.put(a, (map.getOrDefault(a,0)+1 % mod_prime) );
        }
        return count;
    }

    public int countPairGivenSum_BinarySearch(int[] array, int k){
        int count = 0;
        for(int i = 0; i< array.length; i++){
            int a = array[i];
            int b = k - a;
            int countB = binarySearch(array, b, i);
            count = (count + countB) % mod_prime;
        }
        return count;
    }
    // TC : 2 log N
    private int binarySearch(int[] array, int searchKey, int i) {
        int firstOcc = findFirstOcc(array,searchKey);
        if(firstOcc == -1){
            return 0;
        }
        int lastOcc = findLastOcc(array,searchKey);
        if (firstOcc == lastOcc && i < lastOcc){
            return 1;
        }
        int totalFreq = lastOcc - firstOcc + 1;
        if(i < firstOcc){
            return totalFreq;
        }
        if(firstOcc <= i && i <= lastOcc){
            int currentFreq = i - firstOcc + 1;
            return totalFreq - currentFreq;
        }
        return 0;
    }
    private int findFirstOcc(int[] array, int searchKey) {
        int low = 0;
        int high = array.length - 1;
        int firstOcc = -1;
        while (low <= high){
            int mid = ( low + high ) / 2;
            if(array[mid] == searchKey){
                firstOcc = mid;
                high = mid - 1;
            }else if(array[mid] > searchKey){
                high = mid - 1;
            }else {
                low = mid + 1;
            }
        }
        return firstOcc;
    }
    private int findLastOcc(int[] array, int searchKey) {
        int low = 0;
        int high = array.length - 1;
        int lastOcc = -1;
        while (low <= high){
            int mid = ( low + high ) / 2;
            if(array[mid] == searchKey){
                lastOcc = mid;
                low = mid + 1;
            }else if(array[mid] > searchKey){
                high = mid - 1;
            }else {
                low = mid + 1;
            }
        }
        return lastOcc;
    }

    // TC : O(N) , SC : O(1) // ** Test Case : 7,8 **
    public int countPairGivenSum_TwoPointer(int[] array, int k){
        long count = 0;
        int p1 = 0, p2 = array.length - 1;
        while (p1 < p2){
            int a = array[p1];
            int b = array[p2];
            int sum = a + b;
            if(sum == k && a == b){
                long c = p2 - p1 + 1;
                c = ( (c * (c-1)) / 2 ) % mod_prime ;
                count = (count + c)% mod_prime ;
                break;
            }
            else if(sum == k){
                long p1_c = 1L, p2_c = 1L;
                while (p1 < p2 && array[p1] == array[p1+1]) {
                    p1_c++;
                    p1++;
                }
                while (p1 < p2 && array[p2] == array[p2-1]) {
                    p2_c++;
                    p2--;
                }
                count = count % mod_prime + (p1_c * p2_c ) % mod_prime;
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
3. A[i] + A[j] = sum  --> We will count the frequency of A[i] and A[j] and multiply them and add to the answer.
Note, that if A[i] and A[j] are equal in value, then we will have to change the formula instead:
freq(A[i]) * freq(A[i]) –> freq(A[i]) * (freq(A[i]) - 1) / 2
to avoid overcounting pairs.
*/
