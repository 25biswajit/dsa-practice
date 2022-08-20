package com.test.day29.stack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//https://leetcode.com/problems/min-stack/discuss/49010/Clean-6ms-Java-solution
public class GetMinStackConstantTime {
    @Test
    public void test1(){
        Solution solution = new Solution();
        solution.push(-2);
        solution.push(0);
        solution.push(-3);
        Assertions.assertEquals(-3, solution.top());
        Assertions.assertEquals(-3, solution.getMin());
        solution.pop();
        Assertions.assertEquals(0, solution.top());
        Assertions.assertEquals(-2, solution.getMin());
        solution.pop();
        Assertions.assertEquals(-2, solution.top());
        Assertions.assertEquals(-2, solution.getMin());
    }

    @Test
    public void test2(){
        Solution solution = new Solution();
        solution.push(1);
        solution.push(2);
        solution.push(-2);
        Assertions.assertEquals(-2, solution.top());
        Assertions.assertEquals(-2, solution.getMin());
        solution.pop();
        Assertions.assertEquals(2, solution.top());
        Assertions.assertEquals(1, solution.getMin());
        solution.pop();
        Assertions.assertEquals(1, solution.top());
        Assertions.assertEquals(1, solution.getMin());
    }

    @Test
    public void test3(){
        Solution solution = new Solution();
        solution.push(-2);
        solution.push(0);
        solution.push(-1);
        Assertions.assertEquals(-1, solution.top());
        Assertions.assertEquals(-2, solution.getMin());
        solution.pop();
        Assertions.assertEquals(0, solution.top());
        Assertions.assertEquals(-2, solution.getMin());
        solution.pop();
        Assertions.assertEquals(-2, solution.top());
        Assertions.assertEquals(-2, solution.getMin());
    }
}

class Solution {
    Node head = null;

    public void push(int x) {
        Node newNode = new Node(x);
        if(head == null){
            newNode.min = x;
            head = newNode;
        }else{
            int front = getMin();
            newNode.min = Integer.min(front, x);
            newNode.next = head;
            head = newNode;
        }
        //System.out.println("Inserted :" + head);
    }

    public void pop() {
        if(head == null){
            throw new UnsupportedOperationException("Stack is empty");
        }
        head = head.next;
    }

    public int top() {
        if(head == null){
            throw new UnsupportedOperationException("Stack is empty");
        }
        return head.data;
    }

    public int getMin() {
        if(head == null){
            throw new UnsupportedOperationException("Stack is empty");
        }
        return head.min;
    }
}

class Node{
    int data;
    int min;
    Node next;
    Node(int data){
        this.data = data;
        this.next = null;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", min=" + min +
                '}';
    }
}
