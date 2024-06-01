package dsa.advance.day64.tree2;

import dsa.model.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

//https://leetcode.com/problems/binary-tree-maximum-path-sum/
public class MaxPathSumTree {

    @Test
    public void test(){
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        Assertions.assertEquals(42, maxPathSum(root));
    }

    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxPathSumTree(root);
        return max;
    }
    //TC : N, SC : H
    private int maxPathSumTree(TreeNode root){
        if(root == null) return 0;
        int leftSum = Integer.max( 0 , maxPathSumTree(root.left));
        int rightSum = Integer.max( 0 , maxPathSumTree(root.right));
        int sum = root.val + leftSum + rightSum;
        max = Integer.max(max, sum);
        return root.val + Integer.max( rightSum, leftSum );
    }
}
