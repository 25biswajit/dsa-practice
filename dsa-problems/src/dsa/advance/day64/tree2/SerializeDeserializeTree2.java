package dsa.advance.day64.tree2;

import dsa.model.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

public class SerializeDeserializeTree2 {

    @Test
    public void test1(){
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(2);
        root.right = new TreeNode(9);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(6);
        root.left.right.left = new TreeNode(8);
        root.right.right = new TreeNode(14);
        String data = serializeTree(root);
        String expected = "8,2,9,3,6,-1,14,-1,-1,8,-1,-1,-1,-1,-1";
        Assertions.assertEquals(expected, data);
        TreeNode rootAns = deserializeTree(data);
        Assertions.assertEquals(1, isSameTree(root, rootAns));
    }

    @Test
    public void test2(){
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(12);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(16);
        root.left.left.left.left = new TreeNode(12);
        root.left.left.left.left.left = new TreeNode(12);
        String data = serializeTree(root);
        String expected = "10,12,-1,3,-1,16,-1,12,-1,12,-1,-1,-1";
        Assertions.assertEquals(expected, data);
        TreeNode rootAns = deserializeTree(data);
        Assertions.assertEquals(1, isSameTree(root, rootAns));
    }

    String comma = ",";
    Queue<TreeNode> q = null;

    // Encodes a tree to a single string.
    public static String serializeTree(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            TreeNode top = q.poll();
            int val = top!=null ? top.val : -1;
            sb.append(val).append(",");

            if(top!=null){
                q.add(top.left);
                q.add(top.right);
            }

        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    public static TreeNode deserializeTree(String serialized) {
        String[] input = serialized.split(",");
        int n = input.length;
        TreeNode root = new TreeNode(Integer.parseInt( input[0] ));
        int i = 1;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            TreeNode top = q.poll();
            //if(i < n && top!=null){
                int leftVal = Integer.parseInt (input[i]);
                int rightVal = Integer.parseInt (input[i+1]);
                top.left = leftVal == -1 ? null : new TreeNode( leftVal);
                top.right = rightVal == -1 ? null : new TreeNode( rightVal);
                if(leftVal != - 1) q.add(top.left);
                if(rightVal != - 1) q.add(top.right);
            //}
            i+= 2;
        }
        return root;
    }

    public int isSameTree(TreeNode root1, TreeNode root2) {
        if(root1 == null && root2 == null){ return 1; }
        if(root1 == null || root2 == null){ return 0; }
        if(root1.val != root2.val){ return 0; }
        int num = 1;
        return num & isSameTree(root1.left, root2.left) & isSameTree(root1.right, root2.right);
    }
}
