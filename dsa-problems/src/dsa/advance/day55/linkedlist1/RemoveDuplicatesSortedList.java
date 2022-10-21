package dsa.advance.day55.linkedlist1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RemoveDuplicatesSortedList {

    @Test
    public void test1(){
        ListNode head = ListNode.prepareLinkedList(new int[]{1,1,1,2,2,2,3,3,4,5,5,5,5,5,6});
        ListNode expected = ListNode.prepareLinkedList(new int[]{1,2,3,4,5,6});
        Assertions.assertTrue(ListNode.compareLinkedList( expected, deleteDuplicates(head)) );
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode tempPrev=null,temp=head;

        while (temp != null){
            if(temp.next!=null && temp.val == temp.next.val){
                //delete temp, connect tempPrev -> temp.next
                if(tempPrev == null){ // remove head
                    ListNode deletedNode = temp;
                    head = head.next;
                    temp = temp.next;
                    deletedNode.next = null;
                }else if(tempPrev != null) {
                    ListNode deletedNode = temp;
                    tempPrev.next = temp.next;
                    temp = temp.next;
                    deletedNode.next = null;
                }
            }else {
                tempPrev = temp;
                temp = temp.next;
            }
        }
        System.out.println(ListNode.print(head));
        return head;
    }
}
