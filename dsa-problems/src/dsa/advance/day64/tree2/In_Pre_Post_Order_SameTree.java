package dsa.advance.day64.tree2;

import dsa.model.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class In_Pre_Post_Order_SameTree {
    @Test
    public void test1(){
        int[] preorder = {1, 2, 4, 5, 3};
        int[] inorder = {4, 2, 5, 1, 3};
        int[] postorder = {4, 5, 2, 3, 1};
        Assertions.assertEquals(1, isSameTree_GeeksForGeeks(preorder,inorder,postorder));
        Assertions.assertEquals(1, isSameTree_Own(preorder,inorder,postorder));
    }
    @Test
    public void test2(){
        int[] preorder = {1, 5, 4, 2, 3};
        int[] inorder = {4, 2, 5, 1, 3};
        int[] postorder = {4, 1, 2, 3, 5};
        Assertions.assertEquals(0, isSameTree_GeeksForGeeks(preorder,inorder,postorder));
        Assertions.assertEquals(0, isSameTree_Own(preorder,inorder,postorder));
    }
    @Test
    public void test3(){
        int[] preorder = {3, 18, 26, 16, 5};
        int[] inorder = {16, 5, 18, 3, 26};
        int[] postorder = {3, 16, 5, 18, 26};
        Assertions.assertEquals(0, isSameTree_GeeksForGeeks(preorder,inorder,postorder));
        Assertions.assertEquals(0, isSameTree_Own(preorder,inorder,postorder));
    }
    @Test
    public void test4(){
        int[] preorder = {17, 8, 7, 31, 22, 32};
        int[] inorder = {7, 8, 31, 17, 32, 22};
        int[] postorder = {7, 31, 8, 32, 22, 17};
        Assertions.assertEquals(1, isSameTree_GeeksForGeeks(preorder,inorder,postorder));
        Assertions.assertEquals(1, isSameTree_Own(preorder,inorder,postorder));
    }

    public int isSameTree_Own(int[] preorder, int[] inorder, int[] postorder) {
        return buildTree(preorder, 0 , preorder.length-1, inorder, 0, inorder.length-1, postorder, 0, postorder.length-1);
    }
    public int isSameTree_GeeksForGeeks(int[] preorder, int[] inorder, int[] postorder) {
        return checkTree(preorder, 0, inorder, 0, postorder, 0, preorder.length) ? 1 : 0;
    }

    public int buildTree(int[] preorder, int start_pre, int end_pre, int[] inorder, int start_in, int end_in, int[] postorder, int start_post, int end_post) {
        // if the array lengths are 0, then all of them are obviously equal
        if(start_in > end_in || start_pre > end_pre || start_post > end_post) return 1;

        // check as preorder & postorder root elements are same or not
        TreeNode root_pre = new TreeNode(preorder[start_pre]);
        TreeNode root_post = new TreeNode(postorder[end_post]);
        if(root_post.val != root_pre.val){
            return 0;
        }
        // if array lengths are 1, then check if all of them are equal
        if(end_pre-start_pre+1 == end_in-start_in+1 && end_pre-start_pre+1 == end_post-start_post+1 && end_in-start_in+1 == 1){
            if(preorder[start_pre] == inorder [start_in] && inorder [start_in] == postorder[start_post]){
                return 1;
            }else {
                return 0;
            }
        }
        // Find index of root in inorder used in splitting
        int index = -1;
        for(int i = start_in; i <= end_in; i++){
            if(inorder[i]==root_post.val) {
                index = i;
                break;
            }
        }
        int len = index - start_in;
        int leftSubTree = buildTree(preorder,start_pre+1,start_pre+len,inorder,start_in,index-1,postorder, start_post, start_post+len-1);
        int rightSubTree = buildTree(preorder,start_pre+len+1,end_pre,inorder,index+1,end_in,postorder, start_post+len, end_post-1);
        return leftSubTree & rightSubTree;
    }

    // Geeks for Geeks Solution
    private boolean checkTree(int preorder[], int s, int inorder[], int s1, int postorder[], int s2, int len){
        // if the array lengths are 0, then all of them are obviously equal
        if (len == 0) return true;

        // if array lengths are 1, then check if all of them are equal
        if (len == 1) { return ((preorder[s] == inorder[s1]) && (inorder[s1] == postorder[s2])); }

        // search for first element of preorder in inorder array
        int idx = -1;
        for (int i = s1; i < s1 + len; ++i)
            if (inorder[i] == preorder[s]) {
                idx = i;
                break;
            }

        if (idx == -1) return false;

        idx = idx - s1;

        // check for the left subtree
        boolean ret1 = checkTree(preorder, s + 1, inorder, s1, postorder, s2, idx);

        // check for the right subtree
        boolean ret2 = checkTree( preorder, s + idx + 1, inorder, s1 + idx + 1, postorder, s2 + idx, len - idx - 1);

        // return 1 only if both of them are correct else 0
        return (ret1 && ret2);
    }
}
