package dsa.leetcode.selected;

import dsa.advance.day55.linkedlist1.ListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddNumbersLinkedList {
    @Test
    public void test1(){
        int[] a = {2,4,3};
        int[] b = {5,6,4};
        ListNode l1= ListNode.prepareLinkedList(a);
        ListNode l2= ListNode.prepareLinkedList(b);
        ListNode res = addTwoNumbers(l1,l2);
        int[] c = {7,0,8};
        ListNode expected = ListNode.prepareLinkedList(c);
        Assertions.assertTrue( ListNode.compareLinkedList(expected, res) );
    }
    @Test
    public void test2(){
        int[] a = {2,4,3};
        int[] b = {8,6,4};
        ListNode l1= ListNode.prepareLinkedList(a);
        ListNode l2= ListNode.prepareLinkedList(b);
        ListNode res = addTwoNumbers(l1,l2);
        int[] c = {0, 1, 8};
        ListNode expected = ListNode.prepareLinkedList(c);
        Assertions.assertTrue( ListNode.compareLinkedList(expected, res) );
    }

    @Test
    public void test3(){
        int[] a = {9,9,9,9};
        int[] b = {9,9};
        ListNode l1= ListNode.prepareLinkedList(a);
        ListNode l2= ListNode.prepareLinkedList(b);
        ListNode res = addTwoNumbers(l1,l2);
        int[] c = {8,9,0,0,1};
        ListNode expected = ListNode.prepareLinkedList(c);
        Assertions.assertTrue( ListNode.compareLinkedList(expected, res) );
    }

    @Test
    public void test4(){
        int[] a = {2,4,9};
        int[] b = {5,6,4,9};
        ListNode l1= ListNode.prepareLinkedList(a);
        ListNode l2= ListNode.prepareLinkedList(b);
        ListNode res = addTwoNumbers(l1,l2);
        int[] c = {7,0,4,0,1};
        ListNode expected = ListNode.prepareLinkedList(c);
        Assertions.assertTrue( ListNode.compareLinkedList(expected, res) );
    }

    public ListNode addTwoNumbers_(ListNode n1, ListNode n2) {
        ListNode dummy = new ListNode(-1);
        ListNode res = dummy;
        int carry = 0;
        while(n1!=null && n2!=null){
            int sum = n1.val + n2.val + carry;
            carry = sum / 10;
            sum = sum % 10;
            ListNode out = new ListNode(sum);
            res.next = out;
            res = out;
            n1 = n1.next;
            n2 = n2.next;
        }
        handleExtraDigit(n1,carry,res);
        handleExtraDigit(n2,carry,res);

        if(carry > 0){
            res.next = new ListNode(carry);
        }

        return dummy.next;

    }

    private void handleExtraDigit(ListNode node, int carry, ListNode res) {
        while(node!=null){
            int sum = node.val + carry;
            carry = sum / 10;
            sum = sum % 10;
            ListNode out = new ListNode(sum);
            res.next = out;
            res = out;
            node = node.next;
        }
    }

    public ListNode addTwoNumbers(ListNode n1, ListNode n2) {
        ListNode dummy = new ListNode(-1);
        ListNode res = dummy;
        int carry = 0;
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
        return dummy.next;
    }
}
