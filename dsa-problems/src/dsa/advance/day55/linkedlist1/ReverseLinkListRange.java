package dsa.advance.day55.linkedlist1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReverseLinkListRange {
    @Test
    public void test1(){ // x, y [ where x < n , y < n ]
        ListNode head = ListNode.prepareLinkedList(new int[]{1,2,3,4,5,6,7,8,9,10});
        ListNode expected = ListNode.prepareLinkedList(new int[]{1,2,9,8,7,6,5,4,3,10});
        Assertions.assertTrue(ListNode.compareLinkedList( expected, reverseBetween(head,3,9)) );
    }
    @Test
    public void test4(){ // x, n [ where x < n ]
        ListNode head = ListNode.prepareLinkedList(new int[]{1,2,3,4,5,6,7,8,9,10});
        ListNode expected = ListNode.prepareLinkedList(new int[]{1,2,10,9,8,7,6,5,4,3});
        Assertions.assertTrue(ListNode.compareLinkedList( expected, reverseBetween(head,3,10)) );
    }
    @Test
    public void test2(){ // 1, y [ where y < n ]
        ListNode head = ListNode.prepareLinkedList(new int[]{1,2,3,4,5,6,7,8,9,10});
        ListNode expected = ListNode.prepareLinkedList(new int[]{6,5,4,3,2,1,7,8,9,10});
        Assertions.assertTrue(ListNode.compareLinkedList( expected, reverseBetween(head,1,6)) );
    }
    @Test
    public void test3(){ // 1, n
        ListNode head = ListNode.prepareLinkedList(new int[]{1,2,3,4,5});
        ListNode expected = ListNode.prepareLinkedList(new int[]{5,4,3,2,1});
        Assertions.assertTrue(ListNode.compareLinkedList( expected, reverseBetween(head,1,5)) );
    }

    public ListNode reverseBetween(ListNode head, int B, int C) {
        ListNode temp = head;
        ListNode nodeB = null,nodeC = null, preNodeB = null, postNodeC = null;
        int index = 0;
        while (temp!=null){
            index++;
            if(index + 1 == B){
                preNodeB = temp;
            }
            else if(index == B){
                nodeB = temp;
            }
            else if(index == C){
                nodeC = temp;
            }
            temp = temp.next;
        }
        if(nodeC!=null){
            postNodeC = nodeC.next;
        }

        if(preNodeB!=null && nodeB!=null && nodeC!=null){
            // reverse ( X, Y )
            preNodeB.next = null;
            nodeC.next = null;
            preNodeB.next = reverseList(nodeB);
            nodeB.next = postNodeC;
        }
        else if(preNodeB==null && nodeB!=null && nodeC!=null){
            // reverse ( 1 , Y )
            nodeC.next = null;
            head = reverseList(nodeB);
            nodeB.next = postNodeC;
        }
        else {
            // invalid case
        }

        return head;
    }

    public ListNode reverseList(ListNode head) {
        ListNode reverseHead = null;
        ListNode currentHead = head;
        while(currentHead != null){
            ListNode tempHead = currentHead.next;
            currentHead.next = reverseHead;
            reverseHead = currentHead;
            currentHead = tempHead;
        }
        return reverseHead;
    }
}
