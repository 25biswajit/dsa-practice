package dsa.advance.day57.linkedlist3;

import dsa.advance.day55.linkedlist1.ListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*Given a singly linked list A, determine if it's a palindrome. Return 1 or 0, denoting if it's a palindrome or not, respectively.*/
public class PalindromeLinkedList {
    @Test
    public void test1(){
        ListNode head = ListNode.prepareLinkedList(new int[]{1,2,3,2,1});
        Assertions.assertTrue(isPalindrome(head));
    }
    @Test
    public void test2(){
        ListNode head = ListNode.prepareLinkedList(new int[]{1,2,3,3,2,1});
        Assertions.assertTrue(isPalindrome(head));
    }

    public boolean isPalindrome(ListNode head){
        ListNode middle = findMiddle(head);
        ListNode middleNext = middle.next;
        middle.next = null;
        ListNode reverseHead = reverseList(middleNext);
        while (head != null && reverseHead !=null){
            if(reverseHead.val != head.val){
                return false;
            }
            else {
                head = head.next;
                reverseHead = reverseHead.next;
            }
        }
        return (reverseHead == null && (head == null || head.next == null));
    }

    private ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode reverseHead = null;
        ListNode temp = head;
        while (temp != null){
            temp = temp.next;
            head.next = reverseHead;
            reverseHead = head;
            head = temp;
        }
        return reverseHead;
    }
    private ListNode findMiddle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
/*
To check palindrome, we just have to reverse the latter half of the linked list, and then we can,
in (n/2) steps total can compare if all elements are the same or not.
For finding the midpoint, first, we can in O(N) traverse the whole list and calculate the total number of elements.
Reversing is again O(N).*/
