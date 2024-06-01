package dsa.advance.day62.queue2;

import dsa.utils.ArrayUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class SlidingWindowMaximum {
    @Test
    public void test(){
        int[] arr = {3,15,6,12,4,2,10,9,13,7,2,5,3};
        int[] expected = {15,15,12,12,10,13,13,13,13,7};
        int[] actual = slidingMaximum(arr, 4);
        Assertions.assertArrayEquals(expected,actual);
    }

    public int[] slidingMaximum(final int[] array, int window) {
        ArrayList<Integer> list = new ArrayList<>();
        Deque<Integer> deque = new LinkedList<>();
        for(int i = 0; i < window; i++){
            while (!deque.isEmpty() && array[i] > deque.peekLast()){
                deque.removeLast();
            }
            deque.addLast(array[i]);
        }
        list.add(deque.peekFirst());
        for(int i = window; i < array.length; i++){
            if (!deque.isEmpty() && Objects.equals( array[i-window] , deque.peekFirst())){
                deque.pollFirst();
            }
            while (!deque.isEmpty() && array[i] > deque.peekLast()){
                deque.removeLast();
            }
            deque.addLast(array[i]);
            list.add(deque.peekFirst());
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }



}
