package dsa.advance.day64.tree2;

import dsa.model.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IsBalancedTree {
    @Test
    public void test1(){
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.left.right = new TreeNode(2);
        Assertions.assertEquals(0, isBalanced(root));
    }
    @Test
    public void test2(){
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(2);
        Assertions.assertEquals(1, isBalanced(root));
    }

    public int isBalanced(TreeNode root){
        return checkBalanced(root) == -1 ? 0 : 1;
    }
    public int checkBalanced(TreeNode root) {
        if (root == null) return 0;
        int leftHeight = checkBalanced(root.left);
        if(leftHeight==-1) return -1;
        int rightHeight = checkBalanced(root.right);
        if(rightHeight==-1) return -1;
        if(Math.abs(leftHeight-rightHeight)>1){
            return -1;
        }
        return Math.max(leftHeight,rightHeight)+1;
    }

}
