package dsa.advance.day63.tree1;

import dsa.utils.ArrayUtils;
import dsa.utils.MatrixUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ReverseLevelOrder {

    @Test
    public void test(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.right.left.left = new TreeNode(7);
        root.right.left.right = new TreeNode(8);

        int[] expected = {7,8,4,5,6,2,3,1};
        int[] actual = ArrayUtils.convertToIntArray(reverseLevelOrder(root));
        Assertions.assertArrayEquals(expected,actual);
    }

    public ArrayList<Integer> reverseLevelOrder(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode current = queue.poll();
            stack.push(current);
            if(current.right!=null){ queue.add(current.right); }
            if(current.left!=null){ queue.add(current.left); }
        }
        while (!stack.isEmpty()){
            list.add(stack.pop().val);
        }
        return list;
    }

}
