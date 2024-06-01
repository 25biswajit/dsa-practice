package dsa.advance.day64.tree2;

import dsa.model.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class IdenticalBinaryTrees {

    @Test
    public void test1(){
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.left.right = new TreeNode(5);
        root1.right.left = new TreeNode(6);
        root1.right.right = new TreeNode(7);
        root1.left.left.left = new TreeNode(8);

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.left = new TreeNode(4);
        root2.left.right = new TreeNode(5);
        root2.right.left = new TreeNode(6);
        root2.right.right = new TreeNode(7);
        root2.left.left.left = new TreeNode(8);
        Assertions.assertEquals(1, isSameTree_UsingInorder(root1,root2));
        Assertions.assertEquals(1, isSameTree(root1,root2));
    }
    @Test
    public void test2(){
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.left.right = new TreeNode(5);
        root1.right.left = new TreeNode(6);
        root1.right.right = new TreeNode(7);
        root1.left.left.left = new TreeNode(8);

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.left = new TreeNode(4);
        root2.left.right = new TreeNode(5);
        root2.right.left = new TreeNode(6);
        root2.right.right = new TreeNode(7);
        root2.left.left.left = new TreeNode(9);
        Assertions.assertEquals(0, isSameTree_UsingInorder(root1,root2));
        Assertions.assertEquals(0, isSameTree(root1,root2));
    }

    public int isSameTree_UsingInorder(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        inorder(root1,list1);
        List<Integer> list2 = new ArrayList<>();
        inorder(root2,list2);
        return list1.equals(list2) ? 1 : 0;
     }

    private void inorder(TreeNode root, List<Integer> list) {
        if(root == null) return;
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right,list);
    }

    public int isSameTree(TreeNode root1, TreeNode root2) {
        if(root1 == null && root2 == null){ return 1; }
        if(root1 == null || root2 == null){ return 0; }
        if(root1.val != root2.val){ return 0; }
        int num = 1;
        return num & isSameTree(root1.left, root2.left) & isSameTree(root1.right, root2.right);
    }
}
