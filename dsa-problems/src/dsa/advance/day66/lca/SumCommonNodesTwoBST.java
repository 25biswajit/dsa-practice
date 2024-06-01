package dsa.advance.day66.lca;

import dsa.model.TreeNode;
import dsa.utils.Constants;

import java.util.HashMap;

public class SumCommonNodesTwoBST {

    public int solve(TreeNode bstOneRoot, TreeNode bstTwoRoot) {
        HashMap<Integer,Integer> map = new HashMap<>();
        inorder(bstOneRoot, map, 1);
        inorder(bstOneRoot, map, -1);
        long sum = 0L;
        for (Integer key : map.keySet()){
            if(map.get(key)==0){
                sum += key;
            }
        }
        return (int) (sum % 1000000007);
    }

    public void inorder(TreeNode root, HashMap<Integer,Integer> map, int factor){
        if (root == null) return;
        inorder(root.left, map, factor);
        map.put(root.val, map.getOrDefault(root.val, 0)+factor);
        inorder(root.right, map, factor);
    }
}
