package dsa.advance.day62.queue2;

import static dsa.utils.Constants.mod_prime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;

public class SumMinMaxWindow {
    @Test
    public void test(){
        int[] arr = {2, 5, -1, 7, -3, -1, -2};
        Assertions.assertEquals(18,sumMinMaxWindow(arr,4));
    }

    public int sumMinMaxWindow(final int[] array, int window){
        int[] slidingMin = slidingMinimum(array,window);
        int[] slidingMax = slidingMaximum(array,window);
        long sum = 0;
        for(int i = 0; i < slidingMax.length; i++){
            sum = (sum%mod_prime + slidingMax[i]%mod_prime + slidingMin[i]%mod_prime + mod_prime)%mod_prime;
        }
        return (int)(sum);
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
