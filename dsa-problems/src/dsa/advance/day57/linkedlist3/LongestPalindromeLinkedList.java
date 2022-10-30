package dsa.advance.day57.linkedlist3;

import dsa.advance.day55.linkedlist1.ListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
Given a linked list of integers. Find and return the length of the longest palindrome list that exists in that linked list.
A palindrome list is a list that reads the same backward and forward.
Expected memory complexity : O(1)

Problem Constraints:
1 <= length of the linked list <= 2000
1 <= Node value <= 100

A= 2,3,7,3,2,2,3,7,3,9
Ans : 8

Solution:
N^2 solution time complexity can also pass,
So can we just retrieve the numbers from the list and them find longest palindromic subarray??
We can iterate over the list and store all numbers
in another list. Now we can use N^2 brute force solution
to calculate the longest palindromic substring in
the given list.
*/

public class LongestPalindromeLinkedList {
    @Test
    public void test1(){
        ListNode head = ListNode.prepareLinkedList(new int[]{2,3,7,3,2,3,7,3,9});
        Assertions.assertEquals(7, longestPalindromeLength(head));
    }
    @Test
    public void test2(){
        ListNode head = ListNode.prepareLinkedList(new int[]{3,2,2,3});
        Assertions.assertEquals(4, longestPalindromeLength(head));
    }
    @Test
    public void test3(){
        ListNode head = ListNode.prepareLinkedList(new int[]{2,3,7,3,2,2,3,7,3,9});
        ListNode headCopy = ListNode.prepareLinkedList(new int[]{2,3,7,3,2,2,3,7,3,9});
        Assertions.assertEquals(8, longestPalindromeLength(head));
        Assertions.assertEquals(8, longestPalindromeLength_Another(headCopy));
    }
    @Test
    public void test4(){
        ListNode head = ListNode.prepareLinkedList(new int[]{2,1,2,1,2,2,1,3,2,2});
        Assertions.assertEquals(5, longestPalindromeLength(head));
    }
    @Test
    public void test5(){
        ListNode head = ListNode.prepareLinkedList(new int[]{2,2});
        Assertions.assertEquals(2, longestPalindromeLength(head));
    }
    @Test
    public void test6(){
        ListNode head = ListNode.prepareLinkedList(new int[]{2,1,2,2});
        Assertions.assertEquals(3, longestPalindromeLength(head));
    }
    @Test
    public void test7(){
        ListNode head = ListNode.prepareLinkedList(new int[]{2,2,1,3});
        Assertions.assertEquals(2, longestPalindromeLength(head));
    }

    // O(N^2)
    public int longestPalindromeLength(ListNode head){
        if(head == null) return 0;
        if(head.next == null) return 1;
        int max = 0;
        ListNode prev = null, prevCopy, nextNode, nextCopy;
        while (head != null){
            nextNode = nextCopy = head.next;
            prevCopy = prev;
            max = Integer.max(max, derivePalindromicLengthOdd(prevCopy, nextCopy));
            max = Integer.max(max, derivePalindromicLengthEven(prevCopy, nextCopy, head));
            head.next = prev;
            prev = head;
            head = nextNode;
        }
        return max;
    }

    private int derivePalindromicLengthOdd(ListNode prevNode, ListNode nextNode) {
        int oddLenPal = 0;
        while (prevNode!=null && nextNode!=null && prevNode.val == nextNode.val){
            oddLenPal++;
            prevNode = prevNode.next;
            nextNode = nextNode.next;
        }
        oddLenPal = 2 * oddLenPal + 1;
        return oddLenPal;
    }
    private int derivePalindromicLengthEven(ListNode prevNode, ListNode nextNode, ListNode head) {
        int evenLenPal = 0;
        if(nextNode == null){ return evenLenPal; }
        if(head.val == nextNode.val){
            evenLenPal += 2;
        }
        nextNode = nextNode.next;
        while (prevNode!=null && nextNode!=null && evenLenPal > 0 && prevNode.val == nextNode.val){
            evenLenPal += 2;
            prevNode = prevNode.next;
            nextNode = nextNode.next;
        }
        return evenLenPal;
    }

    // O(N^2)
    public int longestPalindromeLength_Another(ListNode A) {

        ListNode dummy = new ListNode(-1,null);
        ListNode cur = A, prev = dummy;
        int ans = 0;

        while(cur != null)
        {
            //Case 1: cur is a center node of of palindrome of odd length

            ListNode prevItr = prev, nextItr = cur.next;
            int length = 1;
            while(prevItr != null && nextItr != null)
            {
                if(prevItr.val == nextItr.val)
                {
                    prevItr = prevItr.next;
                    nextItr = nextItr.next;
                    length++;
                }
                else break;

            }
            ans = Math.max(ans, length + length-1);

            //Case 2: When palindrome length is even
            length=0;
            prevItr = prev;
            nextItr = cur;

            while(prevItr != null && nextItr != null)
            {
                if(prevItr.val == nextItr.val)
                {
                    prevItr = prevItr.next;
                    nextItr = nextItr.next;
                    length++;
                }
                else break;

            }

            ans = Math.max(2*length, ans);

            ListNode Next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = Next;
        }

        return ans;
    }

}
/**********************************************
Hint:
N^2 solution time complexity can also pass,
So can we just retrieve the numbers from the list and them find longest
palindromic subarray??
*/

/**********************************************
Solution Idea:
N^2 solution time complexity can also pass,
So can we just retrieve the numbers from the list and them find longest palindromic subarray??
We can iterate over the list and store all numbers in another list. Now we can use N^2 brute force solution
to calculate the longest palindromic substring in the given list.
*/
