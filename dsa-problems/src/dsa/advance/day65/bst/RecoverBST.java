package dsa.advance.day65.bst;

import dsa.model.TreeNode;
import java.util.ArrayList;

public class RecoverBST {

    public int[] recoverBST(TreeNode root) {
        ArrayList<Integer> path = new ArrayList<>();
        inorder(root,path);
        int firstMismatchIndex = -1;
        int secondMismatchIndex = -1;
        int[] pathArray = path.stream().mapToInt(Integer::intValue).toArray();
        for(int i = 1; i < path.size(); i++){
            if(pathArray[i-1] > pathArray[i] && firstMismatchIndex == -1){
                firstMismatchIndex = i-1;
                secondMismatchIndex = i;
            }
            else if(pathArray[i-1] > pathArray[i]){
                secondMismatchIndex = i;
            }
        }
        int[] result = new int[2];
        if(firstMismatchIndex != -1 && secondMismatchIndex != -1){
            result[1] = pathArray[firstMismatchIndex];
            result[0] = pathArray[secondMismatchIndex];
        }
        return result;
    }

    private void inorder(TreeNode root, ArrayList<Integer> path) {
        if (root == null) return;
        inorder(root.left, path);
        path.add(root.val);
        inorder(root.right, path);
    }

    TreeNode prev = null, firstNode = null, secondNode = null;
    public int[] recoverBST_Optimised(TreeNode root) {
        inorderRecover(root);
        int[] result = new int[2];
        if(firstNode != null && secondNode != null){
            result[1] = secondNode.val;
            result[0] = firstNode.val;
        }
        return result;
    }

    private void inorderRecover(TreeNode root) {
        if (root == null) return;
        inorderRecover(root.left);
        if(prev != null && prev.val > root.val && firstNode == null){
            firstNode = prev;
            secondNode = root;
        }
        else if(prev != null && prev.val > root.val){
            secondNode = root;
        }
        prev = root;
        inorderRecover(root.right);
    }
}
