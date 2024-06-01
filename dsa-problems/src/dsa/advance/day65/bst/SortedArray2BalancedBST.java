package dsa.advance.day65.bst;

import dsa.model.TreeNode;
import dsa.utils.ArrayUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class SortedArray2BalancedBST {

    @Test
    public void test1(){
        int[] array = {1, 2, 3, 5, 10};
        TreeNode root = sortedArrayToBST(array, 0 , array.length-1);
        List<Integer> pathInorder = new ArrayList<>();
        inorder(root,pathInorder);
        int[] path = ArrayUtils.convertToIntArray(pathInorder);
        Assertions.assertArrayEquals(array,path);
    }
    @Test
    public void test2(){
        int[] array = {90, 228, 245, 290, 397, 471, 572, 649, 688, 710, 823, 829, 830, 859, 932, 939, 962};
        TreeNode root = sortedArrayToBST(array, 0 , array.length-1);
        List<Integer> pathInorder = new ArrayList<>();
        inorder(root,pathInorder);
        int[] path = ArrayUtils.convertToIntArray(pathInorder);
        Assertions.assertArrayEquals(array,path);
    }

    public TreeNode sortedArrayToBST(int[] array, int start, int end) {
        if (start>end) return null;
        int mid = (end + start) / 2;
        TreeNode root = new TreeNode(array[mid]);
        root.left = sortedArrayToBST(array, start, mid-1);
        root.right = sortedArrayToBST(array, mid+1, end);
        return root;
    }

    private void inorder(TreeNode root, List<Integer> list) {
        if(root==null) return;
        inorder(root.left,list);
        list.add(root.val);
        inorder(root.right,list);
    }

}
