package dsa.advance.day63.tree1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InvertTree {
    @Test
    public void test(){
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.right = new TreeNode(10);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);
        root.right.right.left = new TreeNode(3);

        root = invertTree(root);
        Assertions.assertEquals(root.val, 8);
        Assertions.assertEquals(root.left.val, 10);
        Assertions.assertEquals(root.right.val, 2);
        Assertions.assertEquals(root.right.right.val, 3);
        Assertions.assertEquals(root.left.left.val, 9);
        Assertions.assertEquals(root.left.right.val, 6);
        Assertions.assertEquals(root.left.left.right.val, 3);
    }

    public TreeNode invertTree(TreeNode root) {
        if(root==null) return null;
        invertTree(root.left);
        invertTree(root.right);
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        return root;
    }
}
