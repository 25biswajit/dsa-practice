package dsa.advance.day63.tree1;

import dsa.utils.MatrixUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class ZigZagTraversal {

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

        Integer[][] expected = {{1}, {3,2}, {4,5,6}, {8,7}};
        Integer[][] actual = MatrixUtils.convertListToMatrix(traverseZigZag(root));
        Assertions.assertArrayEquals(expected,actual);
    }

    public ArrayList<ArrayList<Integer>> traverseZigZag_(TreeNode root) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean isLeftToRight = true;
        while (!queue.isEmpty()){
            int n = queue.size();
            ArrayList<Integer> sublist = new ArrayList<>(n);
            for(int i=1; i<=n; i++){
                TreeNode current = queue.poll();
                sublist.add(current.val);
                if(current.left!=null){ queue.add(current.left); }
                if(current.right!=null){ queue.add(current.right); }
            }
            if(!isLeftToRight){ Collections.reverse(sublist); }
            list.add(sublist);
            isLeftToRight = !isLeftToRight;
        }
        return list;
    }

    public ArrayList<ArrayList<Integer>> traverseZigZag(TreeNode root) {
        boolean leftToRight = true;
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            ArrayList<Integer> list = new ArrayList<>();
            int n = q.size();
            for(int i = 0; i < n; i++){
                TreeNode node = q.poll();
                list.add(node.val);
                if(node.left != null) q.add(node.left);
                if(node.right != null) q.add(node.right);
            }
            if(!leftToRight){
                Collections.reverse(list);
            }
            ans.add(list);
            leftToRight = !leftToRight;
        }
        return ans;
    }
}
