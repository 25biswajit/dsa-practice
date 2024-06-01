package dsa.leetcode.selected;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

public class StackImplByQueue {
    @Test
    public void test(){
        MyStack<Integer> myStack = new MyStack<>();
        myStack.add(10);
        myStack.add(20);
        myStack.add(30);
        Assertions.assertEquals(30, myStack.remove());
        Assertions.assertEquals(20, myStack.remove());
        Assertions.assertEquals(10, myStack.remove());
        myStack.add(40);
        Assertions.assertEquals(40, myStack.remove());
        Assertions.assertThrows(RuntimeException.class, ()-> myStack.remove());
    }
}

class MyStack<I>{
    Queue<I> queue1;
    Queue<I> queue2;

    MyStack(){
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    public void add(I element){
        while(!queue1.isEmpty()){
            queue2.add(queue1.poll());
        }
        queue1.add(element);
        while(!queue2.isEmpty()){
            queue1.add(queue2.poll());
        }
    }

    public I remove(){
        if(queue1.isEmpty()){
            throw new RuntimeException("Its empty");
        }
        return queue1.poll();
    }

}
