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

//https://www.youtube.com/watch?v=FsxTX7-yhOw
public class FlattenTreeInorder_DLL {
    @Test
    public void test(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        TreeNode head = flattenTree(root);
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

    TreeNode head = null, prev = null;
    public TreeNode flattenTree(TreeNode root){
        head = null;
        prev = null;
        inorder(root);
        head.left = prev;
        prev.right = head;
        return head;
    }

    public void inorder(TreeNode root){
        if (root == null) return;
        inorder(root.left);
        if(prev == null) head = root;
        else {
            root.left = prev;
            prev.right = root;
        }
        prev = root;
        inorder(root.right);
    }
}


