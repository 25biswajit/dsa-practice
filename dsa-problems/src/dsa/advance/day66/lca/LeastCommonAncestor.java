package dsa.advance.day66.lca;

import dsa.model.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class LeastCommonAncestor {

    @Test
    public void test_lca_recursion(){
        TreeNode root = new TreeNode(15);
        root.left = new TreeNode(12);
        root.left.left = new TreeNode(10);
        root.left.right = new TreeNode(14);
        root.left.left.left = new TreeNode(8);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(16);
        root.right.right = new TreeNode(27);

        Assertions.assertEquals(12, lca_recursion(root,8,14));
        Assertions.assertEquals(15, lca_recursion(root,8,27));
        Assertions.assertEquals(-1, lca_recursion(root,8,30));

        Assertions.assertEquals(12, lca(root,8,14));
        Assertions.assertEquals(15, lca(root,8,27));
        Assertions.assertEquals(-1, lca(root,8,30));
    }

    public int lca(TreeNode root, int B, int C) {
        List<TreeNode> path_B = new ArrayList<>();
        List<TreeNode> path_C = new ArrayList<>();

        derivePathFromRoot(root,B,path_B);
        derivePathFromRoot(root,C,path_C);

        if(path_B.isEmpty() || path_C.isEmpty()) return -1;
        int pnt_B = path_B.size()-1;
        int pnt_C = path_C.size()-1;

        int lca = -1;
        while (pnt_B >= 0 && pnt_C >=0){
            if(path_B.get(pnt_B) != path_C.get(pnt_C)){
                break;
            }else {
                lca = path_B.get(pnt_B).val;
            }
            pnt_B--;
            pnt_C--;
        }
        return lca;
    }

    private boolean derivePathFromRoot(TreeNode node, int data, List<TreeNode> path) {
        if(node == null) return false;
        if(node.val == data) {
            path.add(node);
            return true;
        }
        if(derivePathFromRoot(node.left, data, path) || derivePathFromRoot(node.right, data, path)){
            path.add(node);
            return true;
        }
        return false;
    }


    public int lca_recursion(TreeNode root, int B, int C) {
        if(IsNodePresent(root,B) && IsNodePresent(root, C)){
            TreeNode lcaNode = findLCA(root,B,C);
            return (lcaNode == null) ? -1 : lcaNode.val;
        }
        return -1;
    }

    // If given Either B,C both present or B,C both absent
    private TreeNode findLCA(TreeNode root, int B, int C){
        if(root == null){
            return null;
        }
        if(root.val == B || root.val == C) {
            return root;
        }
        TreeNode leftLCA = findLCA(root.left, B, C);
        TreeNode rightLCA = findLCA(root.right, B, C);
        if(leftLCA != null && rightLCA != null){
            return root;
        }
        return leftLCA != null ? leftLCA : rightLCA;
    }

    private boolean IsNodePresent(TreeNode node, int data) {
        if(node == null) return false;
        if(node.val == data) {
            return true;
        }
        return IsNodePresent(node.left, data) || IsNodePresent(node.right, data);
    }

}
