package dsa.advance.day65.bst;

import dsa.model.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

public class TwoSumBST {
    @Test
    public void test1(){
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        Assertions.assertEquals(0, t2SumBST(root,40));
    }

    public int t2SumBST(TreeNode root, int sum) {
        HashSet<Integer> set = new HashSet<>();
        inorder(root,set);
        System.out.println(set);
        if(!set.isEmpty()){
            for(Integer val : set){
                if(set.contains(sum-val) && val!= sum-val){
                    return 1;
                }
            }
        }
        return 0;
    }

    private void inorder(TreeNode root, HashSet<Integer> set) {
        if(root==null) return;
        inorder(root.left,set);
        set.add(root.val);
        inorder(root.right,set);
    }
}
