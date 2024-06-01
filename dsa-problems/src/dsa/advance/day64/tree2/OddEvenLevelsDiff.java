package dsa.advance.day64.tree2;

import dsa.model.Pair;
import dsa.model.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

public class OddEvenLevelsDiff {
    @Test
    public void test(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.left.left.left = new TreeNode(8);
        Assertions.assertEquals(10, differenceOddEvenLevel(root));
    }

    public int differenceOddEvenLevel(TreeNode root){
        Queue<Pair<TreeNode,Boolean>> queue = new LinkedList<>();
        Pair<TreeNode,Boolean> pair = new Pair<>(root,true);
        queue.add(pair);
        int sum = 0;
        while (!queue.isEmpty()){
            int n = queue.size();
            for(int i = 1; i<= n;i++){
                Pair<TreeNode,Boolean> tempPair = queue.poll();
                Boolean flag = tempPair.second;
                TreeNode current = tempPair.first;
                sum = sum + (flag ? current.val : -current.val);
                if(current.left!=null){
                    queue.add(new Pair<>(current.left,!flag));
                }
                if(current.right!=null){
                    queue.add(new Pair<>(current.right,!flag));
                }
            }
        }
        return sum;
    }

}
