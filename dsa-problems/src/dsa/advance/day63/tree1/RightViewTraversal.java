package dsa.advance.day63.tree1;

import dsa.utils.ArrayUtils;
import dsa.utils.MatrixUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class RightViewTraversal {

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

        int[] expected = {1,3,6,8};
        int[] actual = ArrayUtils.convertToIntArray(rightView(root));
        Assertions.assertArrayEquals(expected,actual);
    }

    public ArrayList<Integer> rightView(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int n = queue.size();
            for(int i=1; i<=n; i++){
                TreeNode current = queue.poll();
                if (i==n) { list.add(current.val); }
                if(current.left!=null){ queue.add(current.left); }
                if(current.right!=null){ queue.add(current.right); }
            }
        }
        return list;
    }
}
