package dsa.advance.day55.linkedlist1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
/*
Given a singly linked list, delete middle of the linked list.
For example, if given linked list is 1->2->3->4->5 then linked list should be modified to 1->2->4->5
If there are even nodes, then there would be two middle nodes, we need to delete the second middle element.
For example, if given linked list is 1->2->3->4->5->6 then it should be modified to 1->2->3->5->6.
Return the head of the linked list after removing the middle node.
If the input linked list has 1 node, then this node should be deleted and a null node should be returned.
*/
public class RemoveMidNode {

    @Test
    public void test1(){
        ListNode head = ListNode.prepareLinkedList(new int[]{1,2,3,4,5});
        ListNode expected = ListNode.prepareLinkedList(new int[]{1,2,4,5});
        Assertions.assertTrue(ListNode.compareLinkedList( expected, removeMiddle(head)) );
    }
    @Test
    public void test2(){
        ListNode head = ListNode.prepareLinkedList(new int[]{1,2,3,4,5,6});
        ListNode expected = ListNode.prepareLinkedList(new int[]{1,2,3,5,6});
        Assertions.assertTrue(ListNode.compareLinkedList( expected, removeMiddle(head)) );
    }
    @Test
    public void test3(){
        ListNode head = ListNode.prepareLinkedList(new int[]{1});
        Assertions.assertTrue(ListNode.compareLinkedList( null, removeMiddle(head)) );
    }

    public ListNode removeMiddle(ListNode head) {
        if(head == null || head.next == null){
            return null;
        }
        ListNode slowPrev =  null;
        ListNode slow =  head;
        ListNode fast =  head;
        while (fast!=null){
            fast = fast.next;
            if(fast != null){
                slowPrev = slow;
                slow = slow.next;
                fast = fast.next;
            }
        }
        // Delete slow Node
        if(slow != null && slowPrev!=null){
            slowPrev.next = slow.next;
            slow.next = null;
        }
        return head;
    }
}
