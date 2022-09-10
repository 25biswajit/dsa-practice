package dsa.basic.day30.treebasic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Stack;

public class TreeTraversalWithoutRecursion {
    @Test
    public void test(){
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);

        node1.left = node2;
        node2.left = node4;
        node2.right = node5;
        node1.right = node3;
        node3.left = node6;
        node3.right = node7;

        Integer[] expectedPre = {1,2,4,5,3,6,7};
        Integer[] actualPre = preorderTraversal(node1).toArray(new Integer[0]);
        Assertions.assertArrayEquals(expectedPre,actualPre);

        Integer[] expectedIn = {4,2,5,1,6,3,7};
        Integer[] actualIn = inorderTraversal(node1).toArray(new Integer[0]);
        Assertions.assertArrayEquals(expectedIn,actualIn);

//        Integer[] expectedPost = {4,5,2,6,7,3,1};
//        Integer[] actualPost = postorderTraversal(node1).toArray(new Integer[0]);
//        Assertions.assertArrayEquals(expectedPost,actualPost);
    }



    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode current = stack.pop();
            list.add(current.val);
            if(current.right != null){
                stack.push(current.right);
            }
            if(current.left != null){
                stack.push(current.left);
            }
        }
        return list;
    }

    public ArrayList<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        while (current!=null || !stack.isEmpty()){

            while (current!=null){
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            list.add(current.val);
            current = current.right;
        }
        return list;
    }

    private ArrayList<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        while (current!=null || !stack.isEmpty()){

            while (current!=null){
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            list.add(current.val);
            current = current.right;
        }
        return list;
    }

}
