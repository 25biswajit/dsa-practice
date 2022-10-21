package dsa.advance.day55.linkedlist1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RemoveNthFromEnd {

    @Test
    public void test1(){
        ListNode head = ListNode.prepareLinkedList(new int[]{1,2,3,4,5,6,7,8,9,10});
        ListNode expected = ListNode.prepareLinkedList(new int[]{1,2,3,4,5,6,7,9,10});
        Assertions.assertTrue(ListNode.compareLinkedList( expected, removeNthFromEnd(head, 3)) );
    }
    @Test
    public void test2(){
        ListNode head = ListNode.prepareLinkedList(new int[]{1,2,3,4,5,6,7,8,9,10});
        ListNode expected = ListNode.prepareLinkedList(new int[]{2,3,4,5,6,7,8,9,10});
        Assertions.assertTrue(ListNode.compareLinkedList( expected, removeNthFromEnd(head, 20)) );
    }
    @Test
    public void test3(){
        ListNode head = ListNode.prepareLinkedList(new int[]{1,2,3,4,5,6,7,8,9,10});
        ListNode expected = ListNode.prepareLinkedList(new int[]{2,3,4,5,6,7,8,9,10});
        Assertions.assertTrue(ListNode.compareLinkedList( expected, removeNthFromEnd(head, 10)) );
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        int length = 0;
        int k = n;
        ListNode ans =  head;
        ListNode prevSlow = null;
        ListNode slow =  head;
        ListNode fast =  head;
        while (k > 0 && fast!=null){
            fast = fast.next;
            k--;
            length++;
        }
        while (fast!=null){
            prevSlow = slow;
            slow = slow.next;
            fast = fast.next;
            length++;
        }
        // Delete 1st Node
        if(length <= n){
            ListNode deleted = head;
            head = head.next;
            deleted.next = null;
            ans = head;
        }
        // Delete slow Node
        else if(slow != null && prevSlow!=null){
            prevSlow.next = slow.next;
            slow.next = null;
        }
        return ans;
    }
}
