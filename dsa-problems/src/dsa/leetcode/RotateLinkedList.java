package dsa.leetcode;

import dsa.advance.day55.linkedlist1.ListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RotateLinkedList {

    @Test
    public void test(){
        ListNode head = ListNode.prepareLinkedList(new int[]{1,2,3,4,5,6});
        ListNode resultNode = rotateRight(head, 2);
        ListNode expected = ListNode.prepareLinkedList(new int[]{5,6,1,2,3,4});
        Assertions.assertTrue( ListNode.compareLinkedList(expected, resultNode) );
    }


    public ListNode rotateRight(ListNode head, int k) {
        // Edge Cases
        if(head == null || head.next == null || k == 0) return head;
        int size = length(head);
        k = k % size;
        if(k == 0) return head;

        // Logic
        k = size - k;
        ListNode t = head;
        for(int i = 1; i < k; i++){
            t = t.next;
        }

        ListNode newHead = t.next;
        t.next = null;
        t = newHead;
        while(t!=null && t.next!=null){
            t = t.next;
        }
        t.next = head;
        return newHead;
    }

    private int length(ListNode head){
        ListNode t = head;
        int c = 0;
        while(t!=null){
            t = t.next;
            c++;
        }
        return c;
    }
}
