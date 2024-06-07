package dsa.advance.day64.tree2;

import dsa.model.TreeNode;
import dsa.utils.ArrayUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BoundaryTraversalLeetCode {

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
        List<Integer> result = boundaryTraversal(root);
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
        List<Integer> result = boundaryTraversal(root);
        int[] actual = ArrayUtils.convertToIntArray(result);
        Assertions.assertArrayEquals(expected,actual);
    }


    public static List<Integer> boundaryTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null) return list;

        list.add(root.val);
        leftView(root, list);
        bottomView(root, list);
        rightView(root, list);

        System.out.println(list);
        return list;
    }

    private static void rightView(TreeNode root, List<Integer> list) {
        List<Integer> temp = new ArrayList<>();
        TreeNode current = root.right;
        while (current!=null){
            if( !isLeaf(current) ) { temp.add(current.val); }
            current = current.right != null ? current.right : current.left;
        }
        Collections.reverse(temp);
        list.addAll(temp);
    }

    private static void bottomView(TreeNode root, List<Integer> list) {
        if(root == null) return;
        bottomView(root.left, list);
        // add to list if it is leaf
        if(isLeaf(root)) { list.add(root.val); }
        bottomView(root.right, list);
    }

    private static void leftView(TreeNode root, List<Integer> list) {
        TreeNode current = root.left;
        while (current!=null){
            if( !isLeaf(current) ) { list.add(current.val); }
            current = current.left != null ? current.left : current.right;
        }
    }

    private static boolean isLeaf(TreeNode root) {
        return root.left == null && root.right == null;
    }
}
