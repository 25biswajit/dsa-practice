package dsa.advance.day63.tree1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Stack;

public class TreeTraversal {

    @Test
    public void testInorder(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.right.left.left = new TreeNode(7);
        root.right.left.right = new TreeNode(8);

        // Inorder
        int[] expected_inorder = {4,2,1,7,5,8,3,6};
        int[] actual_inorder = inorderTraversalIterative(root);
        Assertions.assertArrayEquals(expected_inorder,actual_inorder);

        // Preorder
        int[] expected_preorder = {1,2,4,3,5,7,8,6};
        int[] actual_preorder = preorderTraversalIterative(root);
        Assertions.assertArrayEquals(expected_preorder,actual_preorder);

        // Postorder
        int[] expected_postorder = {4,2,7,8,5,6,3,1};
        int[] actual_postorder = postorderTraversalIterative(root);
        Assertions.assertArrayEquals(expected_postorder,actual_postorder);
    }

    public int[] inorderTraversalIterative(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        TreeNode current = root;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || current != null){
            while (current != null){
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            list.add(current.val);
            current = current.right;
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public int[] preorderTraversalIterative(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        TreeNode current = root;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(current);
        while (!stack.isEmpty()){
            current = stack.pop();
            list.add(current.val);
            if(current.right!=null){ stack.push(current.right); }
            if(current.left!=null){ stack.push(current.left); }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public int[] postorderTraversalIterative(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        TreeNode current = root;
        Stack<TreeNode> stack_aux = new Stack<>();
        Stack<TreeNode> stack_out = new Stack<>();
        stack_aux.push(current);
        while (!stack_aux.isEmpty()){
            current = stack_aux.pop();
            stack_out.push(current);
            if(current.left!=null){ stack_aux.push(current.left); }
            if(current.right!=null){ stack_aux.push(current.right); }
        }
        while (!stack_out.isEmpty()){
            list.add(stack_out.pop().val);
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
