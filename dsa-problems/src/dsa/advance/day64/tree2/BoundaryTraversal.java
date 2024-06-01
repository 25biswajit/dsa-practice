package dsa.advance.day64.tree2;

import dsa.model.TreeNode;
import dsa.utils.ArrayUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class BoundaryTraversal {

    @Test
    public void test_BottomView(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        int[] expected = {4, 5, 6, 7};
        ArrayList<Integer> result = new ArrayList<>();
        leafNodesInorder(root, result);
        int[] actual = ArrayUtils.convertToIntArray(result);
        Assertions.assertArrayEquals(expected,actual);
    }

    @Test
    public void test0(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        int[] expected = {1, 2, 4, 5, 6, 7, 3};
        ArrayList<Integer> result = boundaryTraversal(root);
        int[] actual = ArrayUtils.convertToIntArray(result);
        Assertions.assertArrayEquals(expected,actual);
    }

    @Test
    public void test1(){
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(9);
        root.right = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(2);
        root.left.right.right = new TreeNode(9);
        root.left.right.right.right = new TreeNode(4);
        root.right.right = new TreeNode(10);

        int[] expected = {8,9,2,2,4,10,6};
        ArrayList<Integer> result = boundaryTraversal(root);
        int[] actual = ArrayUtils.convertToIntArray(result);
        Assertions.assertArrayEquals(expected,actual);
    }
    @Test
    public void test2(){
        TreeNode root = new TreeNode(51);
        root.left = new TreeNode(17);
        root.left.left = new TreeNode(45);
        root.left.right = new TreeNode(58);
        root.left.left.left = new TreeNode(4);
        root.left.left.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);

        root.right = new TreeNode(6);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(30);

        int[] expected = {51,17,45,4,8,7,5,30,6};
        ArrayList<Integer> result = boundaryTraversal(root);
        int[] actual = ArrayUtils.convertToIntArray(result);
        Assertions.assertArrayEquals(expected,actual);
    }

    public ArrayList<Integer> boundaryTraversal(TreeNode root) {
        if (root == null) return new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(root.val);
        leftBoundary(root,list);
        list.remove(list.size()-1);
        leafNodesInorder(root,list);
        list.remove(list.size()-1);
        reverseRightBoundary(root,list);
        System.out.println(list);
        return list;
    }

    private void leftBoundary(TreeNode root,ArrayList<Integer> list) {
        if(root == null) return;
        if (root.left!=null) {
            list.add(root.left.val);
            leftBoundary(root.left, list);
        }
        else if (root.right!=null) {
            list.add(root.right.val);
            leftBoundary(root.right, list);
        }
    }

    private void reverseRightBoundary(TreeNode root,ArrayList<Integer> list) {
        if(root == null) return;
        if (root.right!=null) {
            reverseRightBoundary(root.right, list);
            list.add(root.right.val);
        }
        else if (root.left!=null) {
            reverseRightBoundary(root.left, list);
            list.add(root.left.val);
        }
    }

    private void leafNodesInorder(TreeNode root, ArrayList<Integer> list) {
        if(root == null) return;
        leafNodesInorder(root.left,list);
        if(root.left == null && root.right == null){
            list.add(root.val);
        }
        leafNodesInorder(root.right,list);
    }
}
/*
Idea:
Find the Leftmost boundary path:
This path can be found in top down manner and store this in temp array.
Find all the leaves from left to right:
Find all leaf nodes using inorder traversal and store them at the end of temp array.
Find the Rightmost boundary path:
This path can be found in bottom up manner and store this at the end of temp array.
Now traverse temp array and remove duplicates.
*/
