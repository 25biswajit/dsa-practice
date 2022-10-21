package dsa.advance.day55.linkedlist1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MiddleNodeLinkedList {

    @Test
    public void test1(){
        ListNode head = ListNode.prepareLinkedList(new int[]{1,5,6,2,3,4});
        Assertions.assertEquals(2, middle(head));
    }
    @Test
    public void test2(){
        ListNode head = ListNode.prepareLinkedList(new int[]{1,2,3,4,5});
        Assertions.assertEquals(3, middle(head));
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

}
