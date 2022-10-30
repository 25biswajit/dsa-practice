package dsa.advance.day56.linkedlist2;

import dsa.advance.day55.linkedlist1.ListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
Given a singly linked list : A0 → A1 → … → An-1 → An
reorder it to: A0 → An → A1 → An-1 → A2 → An-2 → …
You must do this in-place without altering the nodes' values.
*/

public class ReorderList {
    @Test
    public void test1(){
        ListNode head = ListNode.prepareLinkedList(new int[]{11,22,33,44,55,66});
        ListNode expected = ListNode.prepareLinkedList(new int[]{11,66,22,55,33,44});
        Assertions.assertTrue(ListNode.compareLinkedList( expected, reorderList(head)) );
    }
    @Test
    public void test2(){
        ListNode head = ListNode.prepareLinkedList(new int[]{11,22,33,44,55,66,77});
        ListNode expected = ListNode.prepareLinkedList(new int[]{11,77,22,66,33,55,44});
        Assertions.assertTrue(ListNode.compareLinkedList( expected, reorderList(head)) );
    }

    // O(N)
    public ListNode reorderList(ListNode head) {
        if(head == null || head.next == null || head.next.next == null) return head;
        ListNode ans = head;
        ListNode middle = findMiddle(head);
        ListNode middleNext = middle.next;
        middle.next = null;
        ListNode reverseHead = reverseList(middleNext);
        ListNode temp1 = head, temp2 = reverseHead;
        while (temp1!=null && temp2!=null){
            temp1 = temp1.next;
            temp2 = temp2.next;
            head.next = reverseHead;
            head = temp1;
            reverseHead.next = head;
            reverseHead = temp2;
        }
        return ans;
    }

    private ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode reverseHead = null;
        ListNode temp = head;
        while (temp != null){
            temp = temp.next;
            head.next = reverseHead;
            reverseHead = head;
            head = temp;
        }
        return reverseHead;
    }

    private ListNode findMiddle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
