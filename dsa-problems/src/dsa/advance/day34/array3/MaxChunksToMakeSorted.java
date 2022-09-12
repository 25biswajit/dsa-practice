package dsa.advance.day34.array3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

/*
Given an array of integers A of size N that is a permutation of [0, 1, 2, ..., (N-1)],
if we split the array into some number of "chunks" (partitions), and individually sort each chunk.
After concatenating them in order of splitting, the result equals the sorted array.
What is the max number of chunks we could have made?

 A = [1, 2, 3, 4, 0]
 To get the 0 in the first index, we have to take all elements in a single chunk.

 A = [2, 0, 1, 3]
 We can divide the array into 2 chunks.
 First chunk is [2, 0, 1] and second chunk is [3].
*/

public class MaxChunksToMakeSorted {

    @Test
    public void test1(){
        int[] arr={1,2,0,3,5,4};
        Assertions.assertEquals(3 , maxSplitToBeSorted(arr));
        Assertions.assertEquals(3 , maxSplitToBeSorted_optimised(arr));
    }
    @Test
    public void test2(){
        int[] arr={1,2,3,4,0};
        Assertions.assertEquals(1 , maxSplitToBeSorted(arr));
        Assertions.assertEquals(1 , maxSplitToBeSorted_optimised(arr));
    }

    @Test
    public void test3(){
        int[] arr={2, 0, 1, 3};
        Assertions.assertEquals(2 , maxSplitToBeSorted(arr));
        Assertions.assertEquals(2 , maxSplitToBeSorted_optimised(arr));
    }

    // My Own Solution TC: O(N log N), SC: O(N)
    // Idea : Create a list of unique Intervals( Correct Index , Old index ) [ Pair(v1,v2) : v1 < v2 ]
    // Sort this list based on Start time
    // Merge the list & get the count of resultant List Elements
    // Eg: [ 1,2,0,3,5,4 ]
    // Intervals : [(0,1), (1,2), (3,3), (4,5)] -> After Merge : [(0,2), (3,3), (4,5)], Ans : 3
    public int maxSplitToBeSorted(int[] array) {
        Set<Interval> set = new TreeSet<>(new IntervalComparator());
        for(int i = 0; i < array.length; i++){
            int value = array[i];
            Interval interval = i > value ? new Interval(value , i) : new Interval(i, value);
            set.add(interval);
        }
        System.out.println(set);
        ArrayList<Interval> intervalArrayList = new ArrayList<>();
        intervalArrayList.addAll(set);
        return MergeOverlappingIntervals.mergeSortedIntervals(intervalArrayList).size();
    }

    // My Own Solution TC: O(N), SC: O(1)
    public int maxSplitToBeSorted_optimised(int[] array){
            int cnt = 0, ma = 0;
            for (int i = 0; i < array.length;i++) {
                ma = Math.max(ma, array[i]);
                if (ma == i) {
                    cnt += 1;
                }
            }
            return cnt;
    }
}
