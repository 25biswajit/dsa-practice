package dsa.advance.day42.Sorting1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

//Find the K th smallest element.
public class KthSmallestElement {

    @Test
    public void test(){
        int[] array = {2, 1, 4, 3, 2};
        Assertions.assertEquals(2 ,kthSmallest_heap_(array,3));
        Assertions.assertEquals(2 ,kthSmallest_heap(array,3));
        Assertions.assertEquals(2 ,kthSmallest(array,3));
    }

    // TC: O(N log K), SC: O(K)
    public int kthSmallest_heap(final int[] array, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, Collections.reverseOrder());
        for(int i=0; i<array.length;i++){
            maxHeap.add(array[i]);
            if(maxHeap.size() > k){
                maxHeap.remove();
            }
        }
        return maxHeap.peek();
    }

    public int kthSmallest_heap_(final int[] array, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, Collections.reverseOrder());
        for(int i = 0; i<k && k<array.length; i++){
            maxHeap.add(array[i]);
        }
        for(int i=k; k<array.length && i<array.length;i++){
            if(array[i] < maxHeap.peek()){
                maxHeap.remove();
                maxHeap.add(array[i]);
            }
        }
        return maxHeap.peek();
    }

    // TC: O(N log N)
    public int kthSmallest(final int[] A, int B) {
        Arrays.sort(A);
        return A[B - 1];
    }
}
