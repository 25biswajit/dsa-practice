package dsa.advance.day63.tree1;

import dsa.utils.MatrixUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class LevelOrderTraversal {

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

        Integer[][] expected = {{1}, {2,3}, {4,5,6}, {7,8}};
        Integer[][] actual = MatrixUtils.convertListToMatrix(levelOrder(root));
        Assertions.assertArrayEquals(expected,actual);
    }

    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int n = queue.size();
            ArrayList<Integer> sublist = new ArrayList<>(n);
            for(int i=1; i<=n; i++){
                TreeNode current = queue.poll();
                sublist.add(current.val);
                if(current.left!=null){ queue.add(current.left); }
                if(current.right!=null){ queue.add(current.right); }
            }
            list.add(sublist);
        }
        return list;
    }
}
