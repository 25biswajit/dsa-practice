package dsa.advance.day66.lca;

import dsa.model.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/*
Given a binary search tree.
Return the distance between two nodes with given two keys B and C. It may be assumed that both keys exist in BST.
NOTE: Distance between two nodes is number of edges between them.
*/

public class DistanceNodesBST {

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

        Assertions.assertEquals(5, distanceBetweenNodes(root,8,27));
        Assertions.assertEquals(5, distanceBetweenNodes_Optimised(root,8,27));
    }

    public int distanceBetweenNodes(TreeNode root, int B, int C){
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
        if(lca == -1) return -1;
        List<TreeNode> path_LCA = new ArrayList<>();
        derivePathFromRoot(root,lca,path_LCA);

        return path_B.size() + path_C.size() - ( 2 * path_LCA.size());
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

    // Recursive Approach
    public int distanceBetweenNodes_Optimised(TreeNode root, int a, int b) {
        return distanceBetween2(root, Integer.min(a,b), Integer.max(a,b));
    }
    public static int distanceFromRoot(TreeNode root, int x) {
        if (root.val == x)
            return 0;
        else if (root.val > x)
            return 1 + distanceFromRoot(root.left, x);
        return 1 + distanceFromRoot(root.right, x);
    }
    public static int distanceBetween2(TreeNode root, int a, int b) {
        if (root == null)
            return 0;
        if (root.val > a && root.val > b)
            return distanceBetween2(root.left, a, b);
        if (root.val < a && root.val < b)
            return distanceBetween2(root.right, a, b);
        if (root.val >= a && root.val <= b)
            return distanceFromRoot(root, a) + distanceFromRoot(root, b);
        return 0;
    }
}

/*
An efficient way to solve the above problem:

We start from the root and for every node, we do following.

If both keys are greater than the current node, we move to the right child of the current node.
If both keys are smaller than current node, we move to left child of current node.
If one keys is smaller and other key is greater, current node is Lowest Common Ancestor (LCA) of two nodes. We find distances of current node from two keys and return sum of the distances.

Time Complexity : O(h) (height of Tree)
*/
