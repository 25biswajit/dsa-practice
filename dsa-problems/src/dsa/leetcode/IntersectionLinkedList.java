package dsa.leetcode;

import dsa.advance.day55.linkedlist1.ListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IntersectionLinkedList {

    @Test
    public void test1(){
        // LL1
        ListNode l1 = new ListNode(10);
        ListNode l2 = new ListNode(20);
        l1.next = l2;
        ListNode l3 = new ListNode(30);
        l2.next = l3;
        ListNode l4 = new ListNode(40);
        l3.next = l4;
        ListNode l5 = new ListNode(50);
        l4.next = l5;
        ListNode l6 = new ListNode(60);
        l5.next = l6;
        ListNode l7 = new ListNode(70);
        l6.next = l7;

        // LL2
        ListNode l8 = new ListNode(25);
        ListNode l9 = new ListNode(35);
        l8.next = l9;
        l9.next = l4;

        ListNode result = getIntersectionNode(l1, l8);
        Assertions.assertEquals(l4, result);
    }


    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode tempA = headA, tempB = headB;
        int countA = 0;
        int countB = 0;
        while(tempA != null){
            tempA = tempA.next;
            countA++;
        }
        while(tempB != null){
            tempB = tempB.next;
            countB++;
        }

        int diff = 0;
        tempA = headA;
        tempB = headB;

        if(countA > countB){
            diff = countA - countB;
            while(tempA != null && diff > 0){
                tempA = tempA.next;
                diff--;
            }
        }
        else if(countA < countB){
            diff = countB - countA;
            while(tempB != null && diff > 0){
                tempB = tempB.next;
                diff--;
            }
        }

        if(diff == 0){
            while(tempB != null && tempA != null){
                if(tempA == tempB){
                    return tempA;
                }
                tempA = tempA.next;
                tempB = tempB.next;
            }
        }

        return null;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode slow = head, fast = head;
        for(int i = 0; i < n; i++){
            fast = fast.next;
        }

        while(fast.next!=null){
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }

}
