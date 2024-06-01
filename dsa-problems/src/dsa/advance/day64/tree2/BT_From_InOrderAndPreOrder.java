package dsa.advance.day64.tree2;

import dsa.model.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BT_From_InOrderAndPreOrder {
    @Test
    public void test(){
        int[] preorder = {8,6,2,11,10,16,12,4,14,9,18,15};
        int[] inorder = {11,2,6,16,10,12,8,14,4,18,9,15};
        TreeNode root = buildTree(preorder,inorder);
        Assertions.assertEquals(root.val, 8);
        Assertions.assertEquals(root.left.val, 6);
        Assertions.assertEquals(root.left.left.val, 2);
        Assertions.assertEquals(root.left.left.left.val, 11);
        Assertions.assertEquals(root.left.right.val, 10);
        Assertions.assertEquals(root.left.right.left.val, 16);
        Assertions.assertEquals(root.left.right.right.val, 12);
        Assertions.assertEquals(root.right.val, 4);
        Assertions.assertEquals(root.right.left.val, 14);
        Assertions.assertEquals(root.right.right.val, 9);
        Assertions.assertEquals(root.right.right.left.val, 18);
        Assertions.assertEquals(root.right.right.right.val, 15);
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, 0 , preorder.length-1, inorder, 0, inorder.length-1);
    }

    public TreeNode buildTree(int[] preorder, int start_pre, int end_pre, int[] inorder, int start_in, int end_in) {
        if(start_in > end_in) return null;
        TreeNode root = new TreeNode(preorder[start_pre]);
        int index = -1;
        for(int i = start_in; i <= end_in; i++){
            if(inorder[i]==root.val) {
                index = i;
                break;
            }
        }
        int len = index - start_in;
        root.left = buildTree(preorder,start_pre+1,start_pre+len,inorder,start_in,index-1);
        root.right = buildTree(preorder,start_pre+len+1,end_pre,inorder,index+1,end_in);
        return root;
    }
}
