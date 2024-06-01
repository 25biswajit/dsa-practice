package dsa.leetcode.selected;

import dsa.advance.day55.linkedlist1.ListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddNumbersLinkedList2 {
    @Test
    public void test1(){
        int[] a = {7,2,4,3};
        int[] b = {5,6,4};
        ListNode l1= ListNode.prepareLinkedList(a);
        ListNode l2= ListNode.prepareLinkedList(b);
        ListNode res = addTwoNumbers(l1,l2);
        int[] c = {7,8,0,7};
        ListNode expected = ListNode.prepareLinkedList(c);
        Assertions.assertTrue( ListNode.compareLinkedList(expected, res) );
    }

    public ListNode addTwoNumbers(ListNode n1, ListNode n2) {
        ListNode dummy = new ListNode(-1);
        ListNode res = dummy;
        int carry = 0;
        n1 = reverse(n1);
        n2 = reverse(n2);

        while(n1!=null || n2!=null || carry!=0){
            int digit1 = n1 != null ? n1.val : 0;
            int digit2 = n2 != null ? n2.val : 0;
            int sum = digit1 + digit2 + carry;
            carry = sum / 10;
            sum = sum % 10;
            ListNode out = new ListNode(sum);
            res.next = out;
            res = out;
            n1 = n1!=null ? n1.next : null;
            n2 = n2!=null ? n2.next : null;
        }
        return reverse(dummy.next);
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
