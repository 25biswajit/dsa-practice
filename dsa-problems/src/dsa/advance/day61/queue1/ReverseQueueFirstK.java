package dsa.advance.day61.queue1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/* Reverse the order of the first K elements of the Queue, leaving the other elements in the same relative order.
A = [1, 2, 3, 4, 5]
B = 3
Reverse first 3 elements so the array becomes [3, 2, 1, 4, 5]
*/

public class ReverseQueueFirstK {
    @Test
    public void test(){
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9));
        Integer[] expected = {3, 2, 1, 4, 5, 6, 7, 8, 9};
        Integer[] actual = reverseFirstK(list,3).toArray(new Integer[0]);
        Assertions.assertArrayEquals(expected,actual);
    }
    //TC: O(N), SC: O(1)
    public ArrayList<Integer> reverseFirstK(ArrayList<Integer> list, int k){
        Queue<Integer> queue = new LinkedList<>(list);
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < k; i++){
            stack.push(queue.poll());
        }
        while (!stack.isEmpty()){
            queue.add(stack.pop());
        }
        for(int i = 0; i < queue.size()-k; i++){
            queue.add(queue.poll());
        }
        return new ArrayList<>(queue);
    }
}

/*
The idea is to use an auxiliary queue.
1) Create an empty queue.
2) Append the first B elements in the queue
3) One by one, dequeue the elements from the queue and update the array at ith position. (Initially i = B-1)
4) Repeat 3 until the queue is empty. Also, decrement i by one at each step.
*/
