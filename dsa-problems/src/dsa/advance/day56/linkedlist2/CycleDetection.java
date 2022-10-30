package dsa.advance.day56.linkedlist2;


import dsa.advance.day55.linkedlist1.ListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
*/
public class CycleDetection {
    @Test
    public void test1(){
        ListNode node4 = new ListNode(40, null);
        ListNode node3 = new ListNode(30, node4);
        ListNode node2 = new ListNode(20, node3);
        ListNode head = new ListNode(10, node2);
        node4.next = node2;
        Assertions.assertEquals(20, detectCycle(head).val);
    }
    @Test
    public void test2(){
        ListNode node4 = new ListNode(40, null);
        ListNode node3 = new ListNode(30, node4);
        ListNode node2 = new ListNode(20, node3);
        ListNode head = new ListNode(10, node2);
        Assertions.assertEquals(null, detectCycle(head));
    }

    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        boolean isCycleFound =  false;
        while (fast!= null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                isCycleFound = true;
                break;
            }
        }
        if(isCycleFound){
            ListNode slow1 = slow, slow2 = head;
            while (slow1!=slow2){
                slow1 = slow1.next;
                slow2 = slow2.next;
            }
            return slow1;
        }
        return null;
    }
}
