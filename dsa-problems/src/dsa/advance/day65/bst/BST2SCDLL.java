package dsa.advance.day65.bst;

import dsa.model.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
Given a binary search tree to Sorted Doubly Circular LinkedList
 */
public class BST2SCDLL {
    @Test
    public void test1(){
        TreeNode root = new TreeNode(20);
        root.left = new TreeNode(10);
        root.left.right = new TreeNode(15);
        root.left.left = new TreeNode(5);

        root.right = new TreeNode(30);
        root.right.right = new TreeNode(35);
        root.right.left = new TreeNode(25);

        TreeNode ans = bst2scll(root);
        System.out.println(ans);
    }
    public TreeNode bst2scll(TreeNode node){
        if(node == null) return null;
        TreeNode left = bst2scll(node.left);
        TreeNode right = bst2scll(node.right);
        // Make a circular linked list of single node (or root). To do so, make the right and left pointers of this node point to itself
        node.left = node.right = node;
        return combine( combine(left,node), right);
    }

    private TreeNode combine(TreeNode head1, TreeNode head2) {
        if(head1 == null) return head2;
        if(head2 == null) return head1;
        TreeNode tail1 = head1.left;
        TreeNode tail2 = head2.right;
        tail1.right = head2;
        head2.left = tail1;
        tail2.right = head1;
        head1.left = tail2;
        return head1;
    }
}
