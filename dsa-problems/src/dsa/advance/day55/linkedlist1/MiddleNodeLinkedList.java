package dsa.advance.day55.linkedlist1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//Find and return the middle element of the linked list.
public class MiddleNodeLinkedList {

    @Test
    public void test1(){
        ListNode head = ListNode.prepareLinkedList(new int[]{1,5,6,2,3,4});
        Assertions.assertEquals(2, middle(head));
        Assertions.assertEquals(2, middle_Another(head));
    }
    @Test
    public void test2(){
        ListNode head = ListNode.prepareLinkedList(new int[]{1,2,3,4,5});
        Assertions.assertEquals(3, middle(head));
        Assertions.assertEquals(3, middle_Another(head));
    }

    // TC: O(N)
    public int middle(ListNode head){
        if(head == null){
            return 0;
        }
        ListNode slow = head, fast = head;
        while (fast!=null) {
            fast = fast.next;
            if (fast!=null) {
                slow = slow.next;
                fast = fast.next;
            }
        }
        return slow.val;
    }

    public int middle_Another(ListNode A) {
        if (A.next == null)
            return A.val;
        ListNode slow = A, fast = A;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow.val;
    }

}
/*   One way is to traverse the whole linked list and calculate the length and then traverse half the length to find the middle element.
We can do it in one traversal by maintaining a slow and fast pointer.
The fast pointer moves twice as the slow pointer does. When the fast pointer is at the end of the linked list, the slow pointer will point to the middle element.
Return the element at which the slow pointer points.*/
