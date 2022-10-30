package dsa.advance.day55.linkedlist1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
Reverse a linked list A from position B to C.
NOTE: Do it in-place and in one-pass.
A = 1 -> 2 -> 3 -> 4 -> 5
B = 2
C = 4
Output: 1 -> 4 -> 3 -> 2 -> 5
Explanation :
In the first example, we want to reverse the highlighted part of the given linked list : 1 -> 2 -> 3 -> 4 -> 5
Thus, the output is 1 -> 4 -> 3 -> 2 -> 5
*/

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
    @Test
    public void test_Another(){ // 1, n
        ListNode head = ListNode.prepareLinkedList(new int[]{1,2,3,4,5});
        ListNode expected = ListNode.prepareLinkedList(new int[]{5,4,3,2,1});
        Assertions.assertTrue(ListNode.compareLinkedList( expected, reverseBetween_Another(head,1,5)) );
    }

    // My Solution - TC: O(N)
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
/*
Hint1:
Let’s first look at the problem of reversing the linked list.
a -> b -> c should become
a <- b <- c
Obviously, at every instant, you need to know about the previous of the current node so that you can link it to the next of the node.
Can you think along the lines of maintaining a previousNode, curNode, and nextNode ?
Now, once you know how to reverse a linked list, how can you apply it to the current problem?

Solution Explanation :
-----------------------------
If you are still stuck at reversing the full linked list problem, then would maintain curNode, nextNode and a tmpNode help?
Maybe at every step, you do something like this :
tmp = nextNode->next;
nextNode->next = cur;
cur = nextNode;
nextNode = tmp;
Now, let’s say you did solve the problem of reversing the linked list and are stuck at applying it to the current problem.
What if your function reverses the linked list and returns the endNode of the list.
You can simply do
prevNodeOfFirstNode->next = reverseLinkedList(curNode, n - m + 1);

We can also find the two pointers between which the list needs to be reversed and only reverse that portion.
We will also have to make two new connections, one from the node just before the first node in the original portion to the node at the starting of the reversed portion, another from the first node of the original portion to the node after the last node in the original portion.
*/
    public ListNode reverseBetween_Another(ListNode A, int m, int n) {
        int i;
        ListNode node = A;
        ListNode prev = null;
        m--;
        n--;
        for (i = 0; i < m; i++) {
            prev = node;
            node = node.next;
        }
        if (prev != null)
            prev.next = reverseList(node, n - m + 1);
        else
            A = reverseList(node, n - m + 1);
        return A;
    }
    public ListNode reverseList(ListNode A, int count) {
        ListNode node, prev, temp, last;
        node = A;
        last = A;
        if (node == null)
            return null;
        prev = null;
        while (node != null && count > 0) {
            temp = node.next;
            node.next = prev;
            prev = node;
            node = temp;
            count--;
        }
        last.next = node;
        return prev;
    }

}
