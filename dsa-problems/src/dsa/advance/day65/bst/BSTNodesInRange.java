package dsa.advance.day65.bst;

import dsa.model.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
Given a binary search tree of integers. You are given a range B and C.
Return the count of the number of nodes that lie in the given range.
*/
public class BSTNodesInRange {
    @Test
    public void test1(){
        TreeNode root = new TreeNode(15);
        root.left = new TreeNode(12);
        root.left.left = new TreeNode(10);
        root.left.right = new TreeNode(14);
        root.left.left.left = new TreeNode(8);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(16);
        root.right.right = new TreeNode(27);
        Assertions.assertEquals(5,rangeBST(root,12,20));
        Assertions.assertEquals(5,solve(root,12,20));
    }

    public int rangeBST(TreeNode root, int B, int C) {
        if(root == null){ return 0;}
        int leftCount = rangeBST(root.left,B,C);
        int rightCount = rangeBST(root.right,B,C);
        int count = leftCount + rightCount;
        if( B <= root.val && root.val <= C ){
            count = count+1;
        }
        return count;
    }

    static int ans = 0;
    public int solve(TreeNode A, int B, int C) {
        ans = 0;
        traverse(A, B, C);
        return ans;
    }
    public static void traverse(TreeNode a, int B, int C) {
        if (a == null)
            return;
        if (a.val >= B && a.val <= C)
            ans++;
        traverse(a.left, B, C);
        traverse(a.right, B, C);
    }
}
