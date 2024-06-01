package dsa.basic.day30.treebasic;

import dsa.model.TreeNode;
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

        node1.setLeft(node2);
        node2.setLeft(node4);
        node2.setRight(node5);
        node1.setRight(node3);
        node3.setLeft(node6);
        node3.setRight(node7);

        Integer[] expectedPre = {1,2,4,5,3,6,7};
        Integer[] actualPre = preorderTraversal(node1).toArray(new Integer[0]);
        Assertions.assertArrayEquals(expectedPre,actualPre);

        Integer[] expectedIn = {4,2,5,1,6,3,7};
        Integer[] actualIn = inorderTraversal(node1).toArray(new Integer[0]);
        Assertions.assertArrayEquals(expectedIn,actualIn);
    }



    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode current = stack.pop();
            list.add(current.getVal());
            if(current.getRight() != null){
                stack.push(current.getRight());
            }
            if(current.getLeft() != null){
                stack.push(current.getLeft());
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
                current = current.getLeft();
            }

            current = stack.pop();
            list.add(current.getVal());
            current = current.getRight();
        }
        return list;
    }

}
