package dsa.advance.day64.tree2;

import dsa.model.TreeNode;
import dsa.utils.ArrayUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class SerializeDeserializeTree {

    @Test
    public void test1(){
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(2);
        root.right = new TreeNode(9);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(6);
        root.left.right.left = new TreeNode(8);
        root.right.right = new TreeNode(14);
        String data = serialize(root);
        String expected = "8,2,9,3,6,null,14,null,null,8,null,null,null,null,null";
        Assertions.assertEquals(expected, data);
        TreeNode rootAns = deserialize(data);
        Assertions.assertEquals(1, isSameTree(root, rootAns));
    }


    String comma = ",";
    Queue<TreeNode> q = null;

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) return "";
        StringBuilder sb = new StringBuilder();
        q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            TreeNode node = q.poll();
            String val = node!=null ? node.val+"" : null;
            sb.append(val).append(comma);
            if(node!=null) { q.add(node.left); }
            if(node!=null) { q.add(node.right); }
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null || data.isEmpty()) return null;
        String[] arr = data.split(comma);
        int i = 0;
        TreeNode root = new TreeNode(Integer.parseInt( arr[i]));
        q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            TreeNode node = q.poll();
            if(node != null) {
                String left = arr[i+1];
                String right = arr[i+2];
                if(!left.equals("null")){
                    node.left = new TreeNode(Integer.parseInt(left));
                    q.add(node.left);
                }
                if(!right.equals("null")){
                    node.right = new TreeNode(Integer.parseInt(right));
                    q.add(node.right);
                }
            }
            i += 2;
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
