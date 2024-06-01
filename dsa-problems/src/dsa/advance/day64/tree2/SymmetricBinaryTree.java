package dsa.advance.day64.tree2;

import dsa.model.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SymmetricBinaryTree {
    @Test
    public void test1(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);
        Assertions.assertEquals(1, isSymmetric(root));
    }
    @Test
    public void test2(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);
        Assertions.assertEquals(0, isSymmetric(root));
    }

    @Test
    public void test3(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);
        Assertions.assertEquals(1, isSymmetric_Using_OR(root));
        Assertions.assertEquals(1, isSymmetric_Using_AND(root));
    }
    @Test
    public void test4(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);
        Assertions.assertEquals(0, isSymmetric_Using_OR(root));
        Assertions.assertEquals(0, isSymmetric_Using_AND(root));
    }

    public int isSymmetric(TreeNode root) {
        TreeNode leftSubTree = root.left;
        TreeNode rightSubTree = root.right;
        invertTree(leftSubTree);
        return isIdenticalTree(leftSubTree,rightSubTree);
    }

    private int isIdenticalTree(TreeNode leftSubTree, TreeNode rightSubTree) {
        if(leftSubTree == null && rightSubTree == null) return 1;
        if(leftSubTree == null || rightSubTree == null) return 0;
        if(leftSubTree.val != rightSubTree.val) return 0;
        return 1 & isIdenticalTree(leftSubTree.left, rightSubTree.left) & isIdenticalTree(leftSubTree.right, rightSubTree.right);
    }

    private void invertTree(TreeNode root) {
        if(root == null) return;
        invertTree(root.left);
        invertTree(root.right);
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }

    public int isSymmetric_Using_AND(TreeNode root) {
        return isSymmetricRecursionAnd(root.left,root.right) ? 1 : 0;
    }
    public int isSymmetric_Using_OR(TreeNode root) {
        return isSymmetricRecursionOr(root.left,root.right) ? 1 : 0;
    }
    private boolean isSymmetricRecursionAnd(TreeNode leftNode, TreeNode rightNode) {
        if(leftNode == null || rightNode == null) return leftNode == rightNode;
        if(leftNode.val != rightNode.val ) return false;
        return  isSymmetricRecursionAnd(leftNode.right,rightNode.left) & isSymmetricRecursionAnd(leftNode.left,rightNode.right);
    }

    private boolean isSymmetricRecursionOr(TreeNode leftNode, TreeNode rightNode) {
        if(leftNode == null || rightNode == null) return leftNode == rightNode;
        if(leftNode.val != rightNode.val ) return false;
        return  isSymmetricRecursionOr(leftNode.right,rightNode.left) | isSymmetricRecursionOr(leftNode.left,rightNode.right);
    }
}
