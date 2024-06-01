package dsa.advance.day62.queue2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;

public class SlidingWindowMinimum {
    @Test
    public void test(){
        int[] arr = {2, 5, -1, 7, -3, -1, -2};
        int[] expected = {-1, -3, -3, -3};
        int[] actual = slidingMinimum(arr, 4);
        Assertions.assertArrayEquals(expected,actual);
    }

    public int[] slidingMinimum(final int[] array, int window) {
        ArrayList<Integer> list = new ArrayList<>();
        Deque<Integer> deque = new LinkedList<>();
        for(int i = 0; i < window; i++){
            while (!deque.isEmpty() && array[i] < deque.peekLast()){
                deque.removeLast();
            }
            deque.addLast(array[i]);
        }
        list.add(deque.peekFirst());
        for(int i = window; i < array.length; i++){
            if (!deque.isEmpty() && Objects.equals( array[i-window] , deque.peekFirst())){
                deque.pollFirst();
            }
            while (!deque.isEmpty() && array[i] < deque.peekLast()){
                deque.removeLast();
            }
            deque.addLast(array[i]);
            list.add(deque.peekFirst());
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }



}
