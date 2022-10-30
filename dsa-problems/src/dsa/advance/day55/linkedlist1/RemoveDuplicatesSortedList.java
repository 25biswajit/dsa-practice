package dsa.advance.day55.linkedlist1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//Given a sorted linked list, delete all duplicates such that each element appears only once.
public class RemoveDuplicatesSortedList {

    @Test
    public void test1(){
        ListNode head = ListNode.prepareLinkedList(new int[]{1,1,1,2,2,2,3,3,4,5,5,5,5,5,6});
        ListNode head1 = ListNode.prepareLinkedList(new int[]{1,1,1,2,2,2,3,3,4,5,5,5,5,5,6});
        ListNode expected = ListNode.prepareLinkedList(new int[]{1,2,3,4,5,6});
        Assertions.assertTrue(ListNode.compareLinkedList( expected, deleteDuplicates(head)) );
        Assertions.assertTrue(ListNode.compareLinkedList( expected, deleteDuplicates_Another(head1)) );
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

/*Skip the node where head->next != NULL && head->val == head->next->val.
Make sure you take care of corner cases :
1) Do you handle repetitions at the end? ex : 1 -> 1
2) Do you handle cases where there is just one element? ex : 1
3) Do you handle cases where there is just one element repeated numerous times? 1->1->1->1->1->1
*/
    public ListNode deleteDuplicates_Another(ListNode A) {
        if (A == null)
            return A;
        ListNode next;
        ListNode prevNode;
        int prev = A.val;
        next = A.next;
        prevNode = A;
        while (next != null) {
            if (next.val == prev) {
                prevNode.next = next.next;
            } else {
                prevNode = next;
                prev = next.val;
            }
            next = next.next;
        }
        return A;
    }
}
