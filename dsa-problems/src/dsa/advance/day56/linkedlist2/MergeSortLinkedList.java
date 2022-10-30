package dsa.advance.day56.linkedlist2;

import dsa.advance.day55.linkedlist1.ListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
Sort a linked list, A in O(n log n) time using constant space complexity.
*/

public class MergeSortLinkedList {
    @Test
    public void test1(){
        ListNode head = ListNode.prepareLinkedList(new int[]{3,2,9,5,6});
        ListNode expected = ListNode.prepareLinkedList(new int[]{2,3,5,6,9});
        Assertions.assertTrue(ListNode.compareLinkedList( expected, mergeSort(head)) );
    }

    @Test
    public void test2(){
        ListNode head = ListNode.prepareLinkedList(new int[]{1,-2,6,3,2,7,9,4,7});
        ListNode expected = ListNode.prepareLinkedList(new int[]{-2,1,2,3,4,6,7,7,9});
        Assertions.assertTrue(ListNode.compareLinkedList( expected, mergeSort(head)) );
    }

    @Test
    public void test_Another(){
        ListNode head = ListNode.prepareLinkedList(new int[]{1,-2,6,3,2,7,9,4,7});
        ListNode expected = ListNode.prepareLinkedList(new int[]{-2,1,2,3,4,6,7,7,9});
        MergeSortLinkedListAnother another = new MergeSortLinkedListAnother();
        Assertions.assertTrue(ListNode.compareLinkedList( expected, another.sortList(head)) );
    }

    // TC: N Log N
    public ListNode mergeSort(ListNode head){
        if(head == null || head.next == null) return head;
        ListNode mid = findLinkedListMid(head);
        ListNode mid_next = mid.next;
        mid.next = null;
        return  merge(mergeSort(head), mergeSort(mid_next));
    }

    private ListNode findLinkedListMid(ListNode head) {
        ListNode slow = head, fast = head;
        while(fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode merge(ListNode head1, ListNode head2) {
        if(head1 == null) { return head2; }
        if(head2 == null) { return head1; }
        ListNode head = null, tail = null;
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

}
/*

    We can try to implement either merge sort or qsort.

        Let us look at the merge sort. We start traversing the singly linked list to find the midpoint of the singly linked list.
        Now, we will sort the first half and second half separately by calling the merge sort function on them.
        Then we only have to merge the 2 lists ( Note that we already have solved a problem to merge 2 lists ).
*/

class MergeSortLinkedListAnother{
    public ListNode sortList(ListNode A) {
        if (A == null || A.next == null)
            return A;
        ListNode first = A;
        ListNode second = findMidNode(A);
        first = sortList(first);
        second = sortList(second);
        ListNode res = mergeNodes(first, second);
        return res;

    }

    public ListNode mergeNodes(ListNode node1, ListNode node2) {
        ListNode dummy = new ListNode(0, null);
        ListNode head = dummy;
        while (node1 != null && node2 != null) {
            if (node1.val <= node2.val) {
                dummy.next = node1;
                node1 = node1.next;
            } else {
                dummy.next = node2;
                node2 = node2.next;
            }
            dummy = dummy.next;
        }
        dummy = addNodes(dummy, node1);
        addNodes(dummy, node2);
        head = head.next;
        return head;
    }

    public ListNode addNodes(ListNode node, ListNode node1) {
        while (node1 != null) {
            node.next = node1;
            node1 = node1.next;
            node = node.next;
        }
        return node;
    }

    public ListNode findMidNode(ListNode node) {
        ListNode doubleRate;
        ListNode prev;
        if (node == null || node.next == null)
            return node;
        doubleRate = node;
        prev = node;
        while (doubleRate.next != null && doubleRate.next.next != null) {
            prev = node;
            node = node.next;
            doubleRate = doubleRate.next.next;
        }
        prev = node;
        node = node.next;
        prev.next = null;
        return node;
    }
}
