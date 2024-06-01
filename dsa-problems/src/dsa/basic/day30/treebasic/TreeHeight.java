package dsa.basic.day30.treebasic;

import dsa.model.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TreeHeight {
    @Test
    public void test(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.left.left.left = new TreeNode(8);
        Assertions.assertEquals(4, heightTree(root));
    }
    public int heightTree(TreeNode root){
        if (root == null) return 0;
        int leftSubTreeHeight = heightTree(root.left);
        int rightSubTreeHeight = heightTree(root.right);
        return Integer.max(leftSubTreeHeight,rightSubTreeHeight) + 1;
    }

}
