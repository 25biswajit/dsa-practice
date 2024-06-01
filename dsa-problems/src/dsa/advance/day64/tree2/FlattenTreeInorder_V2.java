package dsa.advance.day64.tree2;

import dsa.model.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//Binary tree to Circular Doubly Linked List
/*
Given a binary tree convert it into circular doubly linked list based on the following rules:
The left and right pointers in nodes are to be used as previous and next pointers respectively in converted Circular Linked List.
The order of nodes in List must be same as Inorder of the given Binary Tree.
The first node of Inorder traversal must be the head node of the Circular List.
NOTE: You are expected to convert the binary tree into Doubly linked list in place.
*/

// Think of recursively concatenating the left and right subtree of the tree into a circular double linked list.
public class FlattenTreeInorder_V2 {
    @Test
    public void test(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        TreeNode head = bTreeToCList(root);
        TreeNode temp = head;
        Assertions.assertEquals(head.val, 4);
        temp = temp.right; Assertions.assertEquals(temp.val, 2);
        temp = temp.right; Assertions.assertEquals(temp.val, 5);
        temp = temp.right; Assertions.assertEquals(temp.val, 1);
        temp = temp.right; Assertions.assertEquals(temp.val, 6);
        temp = temp.right; Assertions.assertEquals(temp.val, 3);
        temp = temp.right; Assertions.assertEquals(temp.val, 7);
        temp = temp.right; Assertions.assertEquals(temp.val, 4);
        temp = temp.left; Assertions.assertEquals(temp.val, 7);
        temp = temp.left; Assertions.assertEquals(temp.val, 3);
        temp = temp.left; Assertions.assertEquals(temp.val, 6);
        temp = temp.left; Assertions.assertEquals(temp.val, 1);
        temp = temp.left; Assertions.assertEquals(temp.val, 5);
        temp = temp.left; Assertions.assertEquals(temp.val, 2);
        temp = temp.left; Assertions.assertEquals(temp.val, 4);
    }

    // Another Solution
    TreeNode concatenate(TreeNode leftList, TreeNode rightList)
    {
        // If either of the list is empty, then return the other list
        if (leftList == null)
            return rightList;
        if (rightList == null)
            return leftList;

        // Store the last Node of left List
        TreeNode leftLast = leftList.left;

        // Store the last Node of right List
        TreeNode rightLast = rightList.left;

        // Connect the last node of Left List with the first Node of the right List
        if(leftLast!=null)
            leftLast.right = rightList;
        rightList.left = leftLast;

        // left of first node refers to the last node in the list
        leftList.left = rightLast;

        // Right of last node refers to the first node of the List
        if(rightLast!=null)
            rightLast.right = leftList;

        // Return the Head of the List
        return leftList;
    }

    // Method converts a tree to a circular Link List and then returns the head of the Link List
    TreeNode bTreeToCList(TreeNode root)
    {
        if (root == null)
            return null;

        // Recursively convert left and right subtrees
        TreeNode left = bTreeToCList(root.left);
        TreeNode right = bTreeToCList(root.right);

        // Make a circular linked list of single node (or root). To do so, make the right and left pointers of this node point to itself
        root.left = root.right = root;

        // Step 1 (concatenate the left list with the list with single node, i.e., current node)
        // Step 2 (concatenate the returned list with the right List)
        return concatenate(concatenate(left, root), right);
    }
}
/*
Given the root of the tree, we will try to form the circular double linked list for each left and right subtree seperately and then concatenate each circular double linked list.

We can do this recursively, first for left subtree and then for right subtree.

Now traverse the given tree:

-> Recursively convert left subtree to a circular DLL. Let the converted list be leftlist.
-> Recursively convert right subtree to a circular DLL. Let the converted list be rightlist.
-> Make a circular linked list of root of the tree, make left and right of root to point to itself.
-> Concatenate leftlist with list of single root node.
-> Concatenate the list produced in step above with rightList.

To Concatenate two circular DLLs, we will follow below steps:

-> Get the last node of the leftlist. Retrieving the last node is an O(1) operation, since the prev pointer of the head points to the last node of the list.
-> Connect it with the first node of the right list
-> Get the last node of the second list
-> Connect it with the head of the list.*/


