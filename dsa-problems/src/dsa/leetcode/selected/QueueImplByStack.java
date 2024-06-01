package dsa.leetcode.selected;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

public class QueueImplByStack {
    @Test
    public void test1(){
        MyQueue queue = new MyQueue();
        queue.add(10);
        queue.add(20);
        queue.add(30);
        queue.add(40);
        Assertions.assertEquals(10, queue.top());
        Assertions.assertEquals(10, queue.remove());
        Assertions.assertEquals(20, queue.top());
        Assertions.assertEquals(20, queue.remove());
        Assertions.assertEquals(30, queue.remove());
        Assertions.assertEquals(40, queue.remove());
        Assertions.assertThrows(RuntimeException.class, queue::remove);
    }

}

class MyQueue<I>{

    Stack<I> stack1;
    Stack<I> stack2;

    MyQueue(){
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }
    public void add(I element){
        stack1.push(element);
    }

    public I remove(){
        if(stack1.isEmpty()){
            throw new RuntimeException("Its empty");
        }
        while (!stack1.isEmpty()){
           stack2.push(stack1.pop());
        }
        I ans = stack2.pop();
        while (!stack2.isEmpty()){
            stack1.push(stack2.pop());
        }
        return ans;
    }

    public I top(){
        if(stack1.isEmpty()){
            throw new RuntimeException("Its empty");
        }
        while (!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
        I ans = stack2.peek();
        while (!stack2.isEmpty()){
            stack1.push(stack2.pop());
        }
        return ans;
    }

    public boolean empty() {
        return stack1.isEmpty();
    }
}
