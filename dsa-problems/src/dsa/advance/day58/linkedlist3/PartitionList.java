package dsa.advance.day58.linkedlist3;

import dsa.advance.day55.linkedlist1.ListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
Given a linked list A and a value B, partition it such that all nodes less than B come before nodes greater than or equal to B.
You should preserve the original relative order of the nodes in each of the two partitions.
A = {1,10,5,12,7,13,8,15} or {10,1,12,5,13,7,15,8}
Output: {1,5,7,8,10,12,13,15}
*/

public class PartitionList {
    @Test
    public void test1(){
        ListNode head = ListNode.prepareLinkedList(new int[]{1,4,3,2,5,2});
        ListNode expected = ListNode.prepareLinkedList(new int[]{1,2,2,4,3,5});
        ListNode result = partition(head, 3);
        Assertions.assertTrue(ListNode.compareLinkedList( expected, result) );
    }
    @Test
    public void test3(){
        ListNode head = ListNode.prepareLinkedList(new int[]{1,2,3,4,5});
        ListNode expected = ListNode.prepareLinkedList(new int[]{1,2,3,4,5});
        ListNode result = partition(head, 5);
        Assertions.assertTrue(ListNode.compareLinkedList( expected, result) );
    }
    @Test
    public void test2(){
        ListNode head = ListNode.prepareLinkedList(new int[]{1, 2, 3, 1, 3});
        ListNode expected = ListNode.prepareLinkedList(new int[]{1, 1, 2, 3, 3});
        Assertions.assertTrue(ListNode.compareLinkedList( expected, partition(head, 2)) );
    }
    @Test
    public void test4(){
        ListNode head = ListNode.prepareLinkedList(new int[]{1,4,5,2,3,2});
        ListNode expected = ListNode.prepareLinkedList(new int[]{1,4,2,3,2,5});
        ListNode result = partition(head, 5);
        Assertions.assertTrue(ListNode.compareLinkedList( expected, result) );
    }
    @Test
    public void test5(){
        ListNode head = ListNode.prepareLinkedList(new int[]{1,10,5,12,7,13,8,15});
        ListNode expected = ListNode.prepareLinkedList(new int[]{1,5,7,8,10,12,13,15});
        ListNode result = partition(head, 9);
        Assertions.assertTrue(ListNode.compareLinkedList( expected, result) );
    }
    @Test
    public void test6(){
        ListNode head = ListNode.prepareLinkedList(new int[]{384 , 183 , 463 , 31});
        ListNode expected = ListNode.prepareLinkedList(new int[]{31 , 384 , 183 , 463 });
        ListNode result = partition(head, 77);
        Assertions.assertTrue(ListNode.compareLinkedList( expected, result) );
    }
    @Test
    public void test7(){
        ListNode head = ListNode.prepareLinkedList(new int[]{10,1,12,5,13,7,15,8});
        ListNode expected = ListNode.prepareLinkedList(new int[]{1,5,7,8,10,12,13,15});
        ListNode result = partition(head, 9);
        Assertions.assertTrue(ListNode.compareLinkedList( expected, result) );
    }
    @Test
    public void test8(){
        ListNode head = ListNode.prepareLinkedList(new int[]{1});
        ListNode expected = ListNode.prepareLinkedList(new int[]{1});
        ListNode result = partition(head, 9);
        Assertions.assertTrue(ListNode.compareLinkedList( expected, result) );
    }
    @Test
    public void test9(){
        ListNode head = ListNode.prepareLinkedList(new int[]{1,2,3,4,5});
        ListNode expected = ListNode.prepareLinkedList(new int[]{1,2,3,4,5});
        ListNode result = partition(head, 1);
        Assertions.assertTrue(ListNode.compareLinkedList( expected, result) );
    }
    @Test
    public void test10(){
        ListNode head = ListNode.prepareLinkedList(new int[]{1,2,3,4,5});
        ListNode expected = ListNode.prepareLinkedList(new int[]{1,2,3,4,5});
        ListNode result = partition(head, 5);
        Assertions.assertTrue(ListNode.compareLinkedList( expected, result) );
    }

    // TC: O(N) SC: O(1)
    public ListNode partition(ListNode head, int target) {
        if(head == null || head.next == null) return head;
        ListNode temp = head, leftHead = null, leftTemp = null, rightHead = null, rightTemp = null;
        while (temp!=null){
            temp = temp.next;
            head.next = null;
            if(head.val < target){
                if(leftHead == null){
                    leftHead = leftTemp = head;
                }else {
                    leftTemp.next = head;
                    leftTemp = leftTemp.next;
                }
            }
            else {
                if(rightHead == null){
                    rightHead = rightTemp = head;
                }else {
                    rightTemp.next = head;
                    rightTemp = rightTemp.next;
                }
            }
            head = temp;
        }
        if(leftTemp != null){
            leftTemp.next = rightHead;
            return leftHead;
        }
        else if(leftTemp == null && rightHead != null){
            return rightHead;
        }
        return null;
    }

    public ListNode partitionOld(ListNode head, int target) {
        ListNode prev = null, temp = head, leftHead = null, rightHead = null, leftTemp = null;

        while (temp!=null){
            if(temp.val < target){
                temp = temp.next;
                if(leftTemp == null){
                    leftHead = head;
                    leftTemp = head;
                } else {
                    if(prev!=null){
                        prev.next = head.next;
                    }
                    leftTemp.next = head;
                    leftTemp = leftTemp.next;
                }
                head.next = null;
            }
            // temp.val >= target
            else {
                if(rightHead == null){
                    rightHead = temp;
                }
                prev =  temp;
                temp = temp.next;
            }
            head = temp;
        }
        if(leftTemp != null){
            leftTemp.next = rightHead;
        }
            return leftHead;
    }
}
