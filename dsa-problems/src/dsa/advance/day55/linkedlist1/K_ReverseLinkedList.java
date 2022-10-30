package dsa.advance.day55.linkedlist1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
Given a singly linked list A and an integer B, reverse the nodes of the list B at a time and return the modified linked list.
Input: 1,2,3,4,5,6,7,8,9,10, B = 3
Output: 3,2,1,6,5,4,9,8,7,10
*/

public class K_ReverseLinkedList {
    @Test
    public void test(){
        ListNode head = ListNode.prepareLinkedList(new int[]{1,2,3,4,5,6,7,8,9,10});
        ListNode expected = ListNode.prepareLinkedList(new int[]{3,2,1,6,5,4,9,8,7,10});
        Assertions.assertTrue(ListNode.compareLinkedList( expected, reverse_K_recursion(3, head)) );

        ListNode headNew = ListNode.prepareLinkedList(new int[]{1,2,3,4,5,6,7,8,9,10});
        Assertions.assertTrue(ListNode.compareLinkedList( expected, reverse_K_iterative(headNew,3)) );
    }
    // O(n^2) - My Solution - Recursion
    public ListNode reverse_K_recursion(int k, ListNode head){
        if(head == null){
            return null;
        }
        ListNode reverseHead = null;
        ListNode reverseTail = head;
        ListNode temp = head;
        int k_ = k;
        while ( k > 0 && temp!=null){
            head = head.next;
            temp.next = reverseHead;
            reverseHead = temp;
            temp = head;
            k--;
        }
        ListNode newReverseHead = reverse_K_recursion(k_, head);
        reverseTail.next = newReverseHead;
        return reverseHead;
    }

    // Another Solution - O(n^2) - Used Dummy Node - Iterative
    public ListNode reverse_K_iterative(ListNode A, int B) {
        ListNode dummy = new ListNode(0,null);
        ListNode prev = dummy;
        prev.next = A;
        while (A != null) {
            int cnt = 1;
            ListNode cur = A;
            while (cur.next != null && cnt < B) {
                cnt++;
                cur = cur.next;
            }
            if (cnt == B) {
                ListNode next = cur.next;
                cur.next = null;
                ListNode rev = reverse(A);
                prev.next = rev;
                A.next = next;
            }
            prev = A;
            A = A.next;
        }
        return dummy.next;
    }

    public ListNode reverse(ListNode A) {
        ListNode prev = new ListNode(0,null), head = A;
        prev.next = A;
        while (A != null) {
            ListNode tmp = A.next;
            A.next = prev;
            prev = A;
            A = tmp;
        }
        head.next = null;
        return prev;
    }
}
/*
The given linked list can be split into buckets of length K. For doing this; you can use two pointers that are K elements apart in the linked
list.
Now, let us try to solve the problem for one bucket, i.e., reversing
a single linked list. For this, you can check this.
So now, our problem has been modified to solve the problem for each bucket and then concatenating the lists to get a final
K-reversed linked list which is just an implementation issue.
*/
