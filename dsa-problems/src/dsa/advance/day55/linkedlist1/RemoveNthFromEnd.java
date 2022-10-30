package dsa.advance.day55.linkedlist1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
Given a linked list A, remove the B-th node from the end of the list and return its head.
For example, Given linked list: 1->2->3->4->5, and B = 2. After removing the second node from the end, the linked list becomes 1->2->3->5.
NOTE: If B is greater than the size of the list, remove the first node of the list.
NOTE: Try doing it using constant additional space.
*/

public class RemoveNthFromEnd {

    @Test
    public void test1(){
        ListNode head = ListNode.prepareLinkedList(new int[]{1,2,3,4,5,6,7,8,9,10});
        ListNode head1 = ListNode.prepareLinkedList(new int[]{1,2,3,4,5,6,7,8,9,10});
        ListNode expected = ListNode.prepareLinkedList(new int[]{1,2,3,4,5,6,7,9,10});
        Assertions.assertTrue(ListNode.compareLinkedList( expected, removeNthFromEnd(head, 3)) );
        Assertions.assertTrue(ListNode.compareLinkedList( expected, removeNthFromEnd_Another(head1, 3)) );
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
/*
Obviously, since we do not have back pointers, reaching the end node and then making our way back is not an option.
There are 2 approaches :
1) Find out the length of the list in one go. Then you know the number of the node to be removed. Traverse to the node and remove it.
2) Make the first pointer go n nodes. Then move the second and first pointer simultaneously. This way, the first pointer is always ahead of the
second pointer by n nodes. So when the first pointer reaches the end, you are on the node to be removed.
*/

    public ListNode removeNthFromEnd_Another(ListNode A, int B) {
        int n;
        ListNode node;
        if (A == null)
            return null;
        n = 0;
        node = A;
        while (node != null) {
            n++;
            node = node.next;
        }
        if (B >= n) {
            return A.next;
        }
        node = A;
        for (int i = 0; i < n - B - 1; i++)
            node = node.next;
        node.next = node.next.next;
        return A;
    }
}
