package dsa.advance.day56.linkedlist2;

import dsa.advance.day55.linkedlist1.ListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
You are given a linked list that contains a loop.
You need to find the node, which creates a loop and break it by making the node point to NULL.
Input: 30 -> 20 -> 40 -> 50 -> 60 -> 40 ( cycle )
Output: 30 -> 20 -> 40 -> 50 -> 60 -> Null
*/

public class RemoveLoop {

    @Test
    public void test1(){
        ListNode node5 = new ListNode(60, null);
        ListNode node4 = new ListNode(50, node5);
        ListNode node3 = new ListNode(40, node4);
        ListNode node2 = new ListNode(20, node3);
        ListNode node1 = new ListNode(30, node2);
        node5.next = node3;
        // 30 -> 20 -> 40 -> 50 -> 60 -> 40 ( cycle )
        // 30 -> 20 -> 40 -> 50 -> 60 -> Null
        ListNode headWithNoCycle = removeLoop(node1);
        Assertions.assertEquals(30, headWithNoCycle.val);
        Assertions.assertEquals(20, headWithNoCycle.next.val);
        Assertions.assertEquals(40, headWithNoCycle.next.next.val);
        Assertions.assertEquals(50, headWithNoCycle.next.next.next.val);
        Assertions.assertEquals(60, headWithNoCycle.next.next.next.next.val);
        Assertions.assertEquals(null, headWithNoCycle.next.next.next.next.next);
    }
    @Test
    public void test2(){
        ListNode node4 = new ListNode(40, null);
        ListNode node3 = new ListNode(30, node4);
        ListNode node2 = new ListNode(20, node3);
        ListNode head = new ListNode(10, node2);
        //10 -> 20 -> 30 -> 40
        Assertions.assertEquals(null, removeLoop(head));
    }

    public ListNode removeLoop(ListNode head) {
        ListNode slow = head, fast = head;
        boolean isCycleFound =  false;
        while (fast!= null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                isCycleFound = true;
                break;
            }
        }
        if(isCycleFound){
            ListNode prev = null, slow1 = slow, slow2 = head;
            while (slow1!=slow2){
                prev = slow1;
                slow1 = slow1.next;
                slow2 = slow2.next;
            }
            prev.next = null;
            return head;
        }
        return null;
    }



}
