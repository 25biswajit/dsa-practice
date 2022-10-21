package dsa.advance.day55.linkedlist1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
