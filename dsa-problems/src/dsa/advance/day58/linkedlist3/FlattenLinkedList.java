package dsa.advance.day58.linkedlist3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
Given a linked list where every node represents a linked list and contains two pointers of its type:
Pointer to next node in the main list (right pointer)
Pointer to a linked list where this node is head (down pointer). All linked lists are sorted.
You are asked to flatten the linked list into a single list. Use down pointer to link nodes of the flattened list.
The flattened linked list should also be sorted.
Input:
3 -> 4 -> 20 -> 20 ->30
|    |    |     |    |
7    11   22    20   31
|               |    |
7               28   39
|               |
8               39

Output:
3 -> 4 -> 7 -> 7 -> 8 -> 11 -> 20 -> 20 -> 20 -> 22 -> 28 -> 30 -> 31 -> 39 -> 39
*/

public class FlattenLinkedList {
    @Test
    public void test(){
        ListNode_DLL node3 = new ListNode_DLL(3);
        ListNode_DLL node4 = new ListNode_DLL(4);
        ListNode_DLL node7_1 = new ListNode_DLL(7);
        ListNode_DLL node7_2 = new ListNode_DLL(7);
        ListNode_DLL node8 = new ListNode_DLL(8);
        ListNode_DLL node11 = new ListNode_DLL(11);
        ListNode_DLL node20_1 = new ListNode_DLL(20);
        ListNode_DLL node20_2 = new ListNode_DLL(20);
        ListNode_DLL node20_3 = new ListNode_DLL(20);
        ListNode_DLL node22 = new ListNode_DLL(22);
        ListNode_DLL node28 = new ListNode_DLL(28);
        ListNode_DLL node30 = new ListNode_DLL(30);
        ListNode_DLL node31 = new ListNode_DLL(31);
        ListNode_DLL node39_1 = new ListNode_DLL(39);
        ListNode_DLL node39_2 = new ListNode_DLL(39);
        node3.right=node4;
        node3.right.right=node20_1;
        node3.right.right.right=node20_2;
        node3.right.right.right.right=node30;
        node3.down=node7_1;
        node3.down.down=node7_2;
        node3.down.down.down=node8;
        node4.down = node11;
        node20_1.down = node22;
        node20_2.down=node20_3;
        node20_2.down.down=node28;
        node20_2.down.down.down=node39_1;
        node30.down=node31;
        node30.down.down=node39_2;

        ListNode_DLL result_node = flattenIterative(node3);
        Assertions.assertEquals(3, result_node.val);
        Assertions.assertEquals(4, result_node.down.val);
        Assertions.assertEquals(7, result_node.down.down.val);
        Assertions.assertEquals(7, result_node.down.down.down.val);
        Assertions.assertEquals(8, result_node.down.down.down.down.val);
        Assertions.assertEquals(11, result_node.down.down.down.down.down.val);
        Assertions.assertEquals(20, result_node.down.down.down.down.down.down.val);
        Assertions.assertEquals(20, result_node.down.down.down.down.down.down.down.val);
        Assertions.assertEquals(20, result_node.down.down.down.down.down.down.down.down.val);
        Assertions.assertEquals(22, result_node.down.down.down.down.down.down.down.down.down.val);
        Assertions.assertEquals(28, result_node.down.down.down.down.down.down.down.down.down.down.val);
        Assertions.assertEquals(30, result_node.down.down.down.down.down.down.down.down.down.down.down.val);
        Assertions.assertEquals(31, result_node.down.down.down.down.down.down.down.down.down.down.down.down.val);
        Assertions.assertEquals(39, result_node.down.down.down.down.down.down.down.down.down.down.down.down.down.val);
        Assertions.assertEquals(39, result_node.down.down.down.down.down.down.down.down.down.down.down.down.down.down.val);
    }
    @Test
    public void test_recursion(){
        ListNode_DLL node3 = new ListNode_DLL(3);
        ListNode_DLL node4 = new ListNode_DLL(4);
        ListNode_DLL node7_1 = new ListNode_DLL(7);
        ListNode_DLL node7_2 = new ListNode_DLL(7);
        ListNode_DLL node8 = new ListNode_DLL(8);
        ListNode_DLL node11 = new ListNode_DLL(11);
        ListNode_DLL node20_1 = new ListNode_DLL(20);
        ListNode_DLL node20_2 = new ListNode_DLL(20);
        ListNode_DLL node20_3 = new ListNode_DLL(20);
        ListNode_DLL node22 = new ListNode_DLL(22);
        ListNode_DLL node28 = new ListNode_DLL(28);
        ListNode_DLL node30 = new ListNode_DLL(30);
        ListNode_DLL node31 = new ListNode_DLL(31);
        ListNode_DLL node39_1 = new ListNode_DLL(39);
        ListNode_DLL node39_2 = new ListNode_DLL(39);
        node3.right=node4;
        node3.right.right=node20_1;
        node3.right.right.right=node20_2;
        node3.right.right.right.right=node30;
        node3.down=node7_1;
        node3.down.down=node7_2;
        node3.down.down.down=node8;
        node4.down = node11;
        node20_1.down = node22;
        node20_2.down=node20_3;
        node20_2.down.down=node28;
        node20_2.down.down.down=node39_1;
        node30.down=node31;
        node30.down.down=node39_2;

        ListNode_DLL result_node = flattenRecursion(node3);
        Assertions.assertEquals(3, result_node.val);
        Assertions.assertEquals(4, result_node.down.val);
        Assertions.assertEquals(7, result_node.down.down.val);
        Assertions.assertEquals(7, result_node.down.down.down.val);
        Assertions.assertEquals(8, result_node.down.down.down.down.val);
        Assertions.assertEquals(11, result_node.down.down.down.down.down.val);
        Assertions.assertEquals(20, result_node.down.down.down.down.down.down.val);
        Assertions.assertEquals(20, result_node.down.down.down.down.down.down.down.val);
        Assertions.assertEquals(20, result_node.down.down.down.down.down.down.down.down.val);
        Assertions.assertEquals(22, result_node.down.down.down.down.down.down.down.down.down.val);
        Assertions.assertEquals(28, result_node.down.down.down.down.down.down.down.down.down.down.val);
        Assertions.assertEquals(30, result_node.down.down.down.down.down.down.down.down.down.down.down.val);
        Assertions.assertEquals(31, result_node.down.down.down.down.down.down.down.down.down.down.down.down.val);
        Assertions.assertEquals(39, result_node.down.down.down.down.down.down.down.down.down.down.down.down.down.val);
        Assertions.assertEquals(39, result_node.down.down.down.down.down.down.down.down.down.down.down.down.down.down.val);
    }

    // TC: O(N*M) // My Solution : Merge Adjacent Sorted Linked List
    ListNode_DLL flattenIterative(ListNode_DLL root) {
        if(root==null || root.right == null){
            return root;
        }
        ListNode_DLL current = root, current_next = current.right, current_next_next;
        while (current_next != null && current!=null){
            current_next_next = current_next.right;
            current = mergeSortedList(current,current_next);
            current_next = current_next_next;
        }
        return current;
    }

    private ListNode_DLL mergeSortedList(ListNode_DLL node1, ListNode_DLL node2) {
        if(node1 == null){ return node2;}
        if(node2 == null){ return node1;}
        ListNode_DLL start, tail;
        if(node1.val < node2.val){
            start = tail = node1;
            node1 = node1.down;
         }
        //if(node2.val >= node1.val)
        else{
            start = tail = node2;
            node2 = node2.down;
        }
        while (node1!=null && node2!=null){
            if(node1.val < node2.val){
                tail.down = node1;
                tail = node1;
                node1 = node1.down;
            }
            //node1.val >= node2.val)
            else{
                tail.down = node2;
                tail = node2;
                node2 = node2.down;
            }
        }
        if(node1 == null && node2!=null){ tail.down = node2; }
        else{ tail.down = node1; }
        return start;
    }

    /*
    What if we were given only two lists how we would have merged them?
    The idea is to extend the same on multiple lists, select any two list and merge them to make a single list.
    Now we have (total - 1) lists to merge, again repeat the above process untill we have only one list left.
    */
    ListNode_DLL flattenRecursion(ListNode_DLL root) {
        if (root == null || root.right == null)
            return root;
        return merge(root, flattenRecursion(root.right));
    }
    ListNode_DLL merge(ListNode_DLL a, ListNode_DLL b) {
        if (a == null) return b;
        if (b == null) return a;
        ListNode_DLL result;
        if (a.val < b.val) {
            result = a;
            result.down = merge(a.down, b);
        } else {
            result = b;
            result.down = merge(a, b.down);
        }
        return result;
    }
}


class ListNode_DLL {
    int val;
    ListNode_DLL right, down;
    ListNode_DLL(int x) {
        val = x;
        right = down = null;
    }
}
