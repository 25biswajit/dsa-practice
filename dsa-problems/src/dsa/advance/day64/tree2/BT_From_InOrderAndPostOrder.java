package dsa.advance.day64.tree2;

import dsa.model.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BT_From_InOrderAndPostOrder {
    @Test
    public void test(){
        int[] postorder = {4,9,12,15,1,19,9,10,6,5};
        int[] inorder = {12,4,9,5,15,1,6,9,19,10};
        TreeNode root = buildTree(inorder,postorder);
        Assertions.assertEquals(root.val, 5);
    }

    public TreeNode buildTree(int[] inorder,int[] postorder) {
        return buildTree(postorder, 0 , postorder.length-1, inorder, 0, inorder.length-1);
    }

    public TreeNode buildTree(int[] postorder, int start_post, int end_post, int[] inorder, int start_in, int end_in) {
        if(start_post > end_post) return null;
        TreeNode root = new TreeNode(postorder[end_post]);
        int index = -1;
        for(int i = start_in; i <= end_in; i++){
            if(inorder[i]==root.val) {
                index = i;
                break;
            }
        }
        int len = end_in - index;
        root.left = buildTree(postorder,start_post,end_post-len-1,inorder,start_in,index-1);
        root.right = buildTree(postorder,end_post-len,end_post-1,inorder,index+1,end_in);
        return root;
    }
}
