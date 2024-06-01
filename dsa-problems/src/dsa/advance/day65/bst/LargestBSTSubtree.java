package dsa.advance.day65.bst;

import dsa.model.TreeNode;
import org.junit.jupiter.api.Test;

public class LargestBSTSubtree {

    @Test
    public void test1(){
        TreeNode root = new TreeNode(15);
        root.left = new TreeNode(12);
    }

    public int largestBSTSubtree(TreeNode root){
        BSTPair largeBSTNode = largestBSTInBT(root);
        return largeBSTNode.size;
    }

    public BSTPair largestBSTInBT(TreeNode node){
        if(node==null) {
            BSTPair bstPair = new BSTPair();
            bstPair.min = Integer.MIN_VALUE;
            bstPair.max = Integer.MAX_VALUE;
            bstPair.isBst = true;
            bstPair.size = 0;
            return bstPair;
        }
        if(node.left==null && node.right==null) {
            BSTPair bstPair = new BSTPair();
            bstPair.min = node.val;
            bstPair.max = node.val;
            bstPair.isBst = true;
            bstPair.size = 1;
            return bstPair;
        }

        BSTPair bstPair = new BSTPair();
        BSTPair bstPairLeft = largestBSTInBT(node.left);
        BSTPair bstPairRight = largestBSTInBT(node.right);

        bstPair.isBst = (bstPairLeft.max < node.val && node.val < bstPairRight.min) && bstPairLeft.isBst && bstPairRight.isBst;
        bstPair.min = Integer.min(bstPairLeft.min, Integer.min(node.val, bstPairRight.min));
        bstPair.max = Integer.min(bstPairLeft.max, Integer.min(node.val, bstPairRight.max));

        if(bstPair.isBst){
            bstPair.bstNode = node;
            bstPair.size = 1 + bstPairLeft.size + bstPairRight.size;
        }
        else if(bstPairLeft.size > bstPairRight.size){
            bstPair.bstNode = bstPairLeft.bstNode;
            bstPair.size = bstPairLeft.size;
        }
        else {
            bstPair.bstNode = bstPairRight.bstNode;
            bstPair.size = bstPairRight.size;
        }
        return bstPair;
    }

}

class BSTPair {
    public int min;
    public int max;
    public int size;
    public boolean isBst;
    public TreeNode bstNode;

}