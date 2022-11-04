package dsa.advance.day58.linkedlist3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/*
A linked list of length n is given such that each node contains an additional random pointer, which could point to any node in the list, or null.
Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes, where each new node has its value set to the value of its corresponding original node. Both the next and random pointer of the new nodes should point to new nodes in the copied list such that the pointers in the original list and copied list represent the same list state. None of the pointers in the new list should point to nodes in the original list.
For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding two nodes x and y in the copied list, x.random --> y.
Return the head of the copied linked list.
The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:
val: an integer representing Node.val
random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does not point to any node.
Your code will only be given the head of the original linked list.
*/



public class CloneLinkedList {
    @Test
    public void test1(){
        RandomListNode node0 = new RandomListNode(30);
        RandomListNode node1 = new RandomListNode(10);
        RandomListNode node2 = new RandomListNode(20);
        node0.next = node1;node1.next = node2;
        node1.random = node0;
        node2.random = node0;
        node0.random = node0;

        RandomListNode node0Copy = clone_naive(node0);
        RandomListNode node1Copy = node0Copy.next;
        RandomListNode node2Copy = node0Copy.next.next;
        Assertions.assertEquals(node0.val, node0Copy.val);
        Assertions.assertEquals(node1.val, node1Copy.val);
        Assertions.assertEquals(node2.val, node2Copy.val);
        Assertions.assertEquals(node0.next.val, node0Copy.next.val);
        Assertions.assertEquals(node2.next, node2Copy.next);
        Assertions.assertEquals(node1.next.val, node1Copy.next.val);
        Assertions.assertEquals(node0.random.val, node0Copy.random.val);
        Assertions.assertEquals(node1.random.val, node1Copy.random.val);
        Assertions.assertEquals(node2.random.val, node2Copy.random.val);

        node0Copy = clone_optimised(node0);
        node1Copy = node0Copy.next;
        node2Copy = node0Copy.next.next;
        Assertions.assertEquals(node0.val, node0Copy.val);
        Assertions.assertEquals(node1.val, node1Copy.val);
        Assertions.assertEquals(node2.val, node2Copy.val);
        Assertions.assertEquals(node0.next.val, node0Copy.next.val);
        Assertions.assertEquals(node2.next, node2Copy.next);
        Assertions.assertEquals(node1.next.val, node1Copy.next.val);
        Assertions.assertEquals(node0.random.val, node0Copy.random.val);
        Assertions.assertEquals(node1.random.val, node1Copy.random.val);
        Assertions.assertEquals(node2.random.val, node2Copy.random.val);

        // Object Reference Equalise
        Assertions.assertEquals(node2.random, node1.random); // *** Notice
        Assertions.assertNotEquals(node2.random, node1Copy.random); // *** Notice
;    }
    @Test
    public void test2(){
        // Next Links: 10 -> 20 -> 30 -> 40
        // Random Links : 10 -> 40 ; 20 -> 20 ; 30 -> 10 ; 40 -> 30
        RandomListNode node1 = new RandomListNode(10);
        RandomListNode node2 = new RandomListNode(20);
        RandomListNode node3 = new RandomListNode(30);
        RandomListNode node4 = new RandomListNode(40);
        node1.next = node2;
        node1.next.next = node3;
        node1.next.next.next = node4;
        node1.random = node4;
        node2.random = node2;
        node3.random = node1;
        node4.random = node3;
        RandomListNode node1Copy = clone_optimised(node1);
        RandomListNode node2Copy = node1Copy.next;
        RandomListNode node3Copy = node2Copy.next;
        RandomListNode node4Copy = node3Copy.next;
        Assertions.assertEquals(node1Copy.next.val, node2Copy.val);
        Assertions.assertEquals(node1Copy.next.next.val, node3Copy.val);
        Assertions.assertEquals(node1Copy.next.next.next, node4Copy);
        Assertions.assertEquals(node1.random.val, node1Copy.random.val);
        Assertions.assertEquals(node2.random.val, node2Copy.random.val);
        Assertions.assertEquals(node3.random.val, node3Copy.random.val);
        Assertions.assertEquals(node4.random.val, node4Copy.random.val);
        Assertions.assertEquals(node1Copy.random, node4Copy);
        Assertions.assertEquals(node2Copy.random, node2Copy);
        Assertions.assertEquals(node3Copy.random, node1Copy);
        Assertions.assertEquals(node4Copy.random, node3Copy);
    }

    //You may want to use extra space to keep old_node ---> new_node mapping to prevent creating multiple copies of the same node.
    // TC: O(N) SC:(N)
    public RandomListNode clone_naive(RandomListNode root){
        Map<RandomListNode,RandomListNode> map = new HashMap<>();
        RandomListNode temp = root;
        while (temp!=null){
            map.put(temp, new RandomListNode(temp.val));
            temp=temp.next;
        }
        temp = root;
        while (temp!=null){
            map.get(temp).next = map.get(temp.next);
            map.get(temp).random = map.get(temp.random);
            temp=temp.next;
        }
        return map.get(root);
    }

/*
Optimised Solution Idea :
We can avoid using extra space for old_node ---> new_node mapping by tweaking the original linked list.
Simply interweave the nodes of the old and copied list. For example: Old List: A --> B --> C --> D
InterWeaved List: A --> A' --> B --> B' --> C --> C' --> D --> D'
Replicate the same Mapping Idea(Naive Approach)
https://www.youtube.com/watch?v=4apaOcK586U
https://www.geeksforgeeks.org/clone-linked-list-next-random-pointer-o1-space/
*/
    //TC:O(N) SC:O(1)
    public RandomListNode clone_optimised(RandomListNode head){
        RandomListNode root = head;
        RandomListNode temp,tempClone,cloneHead=null;
        // Step 1 : insert additional node after every node of original list, insert a clone node after every original node
        // Old List: 10 -> 20 -> 30 -> 40
        // InterWeaved List: 10 -> 10* -> 20 -> 20* -> 30 -> 30* -> 40 -> 40*
        // Random Links : 10 -> 40 ; 20 -> 20 ; 30 -> 10 ; 40 -> 30
        while (head!=null){
            temp = head.next;
            tempClone = new RandomListNode(head.val);
            head.next = tempClone;
            tempClone.next = temp;
            head = temp;
        }
        // Step 2: adjust the random pointers of the newly added nodes ( cloned list )
        // InterWeaved List: 10 -> 10* -> 20 -> 20* -> 30 -> 30* -> 40 -> 40*
        // Random Links : 10 -> 40 ; 20 -> 20 ; 30 -> 10 ; 40 -> 30
        // Random Links : 10* -> 40* ; 20* -> 20* ; 30* -> 10* ; 40* -> 30*
        temp = root;
        while (temp!=null){
            tempClone = temp.next;
            tempClone.random = temp.random.next;
            temp = tempClone.next;
        }
        // Now separate the original list and copied list
        // 2 List Separated : Main List : 10 -> 20 -> 30 -> 40 , Cloned List : 10* -> 20* -> 30* -> 40*
        temp = root;
        while (temp!=null){
            tempClone = temp.next;
            if(cloneHead==null){
                cloneHead = tempClone;
            }
            temp.next = tempClone.next;
            temp = temp.next;
            if(temp!=null){
                tempClone.next = temp.next;
            }
        }
        return cloneHead;
    }

}

class RandomListNode {
    int val;
    RandomListNode next, random;
    RandomListNode(int x) {
        val = x;
        next = random = null;
    }
}




