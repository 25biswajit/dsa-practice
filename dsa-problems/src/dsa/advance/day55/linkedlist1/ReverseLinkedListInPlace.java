package dsa.advance.day55.linkedlist1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
You are given a singly linked list having head node A. You have to reverse the linked list and return the head node of that reversed list.
NOTE: You have to do it in-place and in one-pass.
*/

public class ReverseLinkedListInPlace {

    @Test
    public void test1(){
        ListNode head = ListNode.prepareLinkedList(new int[]{1,2,3,4,5});
        ListNode expected = ListNode.prepareLinkedList(new int[]{5,4,3,2,1});
        Assertions.assertTrue(ListNode.compareLinkedList( expected, reverseList(head)) );
    }
    // TC: O(N)
    public ListNode reverseList(ListNode head) {
        ListNode reverseHead = null;
        ListNode currentHead = head;
        while(currentHead != null){
            ListNode tempHead = currentHead.next;
            currentHead.next = reverseHead;
            reverseHead = currentHead;
            currentHead = tempHead;
        }
        return reverseHead;
    }
}
