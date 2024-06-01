package dsa.advance.day56.linkedlist2;

import dsa.advance.day55.linkedlist1.ListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
Merge two sorted linked lists, A and B, and return it as a new list.
The new list should be made by splicing together the nodes of the first two lists and should also be sorted.
*/

public class MergeSortedLinkedList {
    @Test
    public void test_mySolution(){
        ListNode head1 = ListNode.prepareLinkedList(new int[]{1,3,6,7});
        ListNode head2 = ListNode.prepareLinkedList(new int[]{2,4,5,8,9,15,20});
        ListNode expected = ListNode.prepareLinkedList(new int[]{1,2,3,4,5,6,7,8,9,15,20});
        Assertions.assertTrue(ListNode.compareLinkedList( expected, mergeTwoLists(head1, head2)) );
    }
    @Test
    public void test_another(){
        ListNode head1 = ListNode.prepareLinkedList(new int[]{1,3,6,7});
        ListNode head2 = ListNode.prepareLinkedList(new int[]{2,4,5,8,9,15,20});
        ListNode expected = ListNode.prepareLinkedList(new int[]{1,2,3,4,5,6,7,8,9,15,20});
        Assertions.assertTrue(ListNode.compareLinkedList( expected, mergeTwoLists_another(head1, head2)) );
    }

    public ListNode mergeTwoLists(ListNode head1, ListNode head2) {
        if(head1 == null) { return head2; }
        if(head2 == null) { return head1; }
        ListNode head, tail;
        if(head1.val < head2.val){
            head = tail = head1;
            head1 = head1.next;
        }else {
            head = tail = head2;
            head2 = head2.next;
        }

        while (head1!=null && head2!=null){
            if(head1.val < head2.val){
                tail.next = head1;
                tail = tail.next;
                head1 = head1.next;
            }else {
                tail.next = head2;
                tail = tail.next;
                head2 = head2.next;
            }
        }
        if(head1 == null && head2!=null){
            tail.next = head2;
        }else {
            tail.next = head1;
        }
        return head;
    }

    public ListNode mergeTwoLists_another(ListNode A, ListNode B) {
        if (A == null) return B;
        if (B == null) return A;
        ListNode head;
        ListNode node = new ListNode(0,null);
        head = node;
        while (A != null && B != null) {
            if (A.val < B.val) {
                node.next = A;
                A = A.next;
            } else {
                node.next = B;
                B = B.next;
            }
            node = node.next;
        }
        node.next = A == null ? B : A;
        head = head.next;
        return head;
    }
}
