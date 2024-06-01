package dsa.advance.day64.tree2;

import dsa.model.Pair;
import dsa.model.TreeNode;
import dsa.utils.ArrayUtils;
import dsa.utils.MatrixUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class VerticalOrderTraversal {
    
    @Test
    public void test(){
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(9);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(2);
        root.left.right.right = new TreeNode(9);
        root.left.right.right.right = new TreeNode(4);
        root.right = new TreeNode(6);
        root.right.right = new TreeNode(10);

        Integer[][] expected = {{9,2},{8,2},{6,9},{10,4}};
        ArrayList<ArrayList<Integer>> result = verticalOrderTraversal(root);
        Integer[][] actual = MatrixUtils.convertListToMatrix(result);
        Assertions.assertArrayEquals(expected,actual);
    }

    public ArrayList<ArrayList<Integer>> verticalOrderTraversal(TreeNode root) {
        int minLevel = 0, maxLevel = 0;
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        HashMap<Integer, List<Integer>> hashMap = new HashMap<>();
        Queue<Pair<TreeNode,Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(root,0));
        while (!queue.isEmpty()){
            Pair<TreeNode,Integer> pair = queue.poll();
            TreeNode temp = pair.first;
            Integer tempLevel = pair.second;
            if(temp.left!=null){
                queue.add(new Pair<>(temp.left,tempLevel-1));
            }
            if(temp.right!=null){
                queue.add(new Pair<>(temp.right,tempLevel+1));
            }
            if(hashMap.containsKey(tempLevel)){
                hashMap.get(tempLevel).add(temp.val);
            }else {
                ArrayList<Integer> sublist = new ArrayList<>();
                sublist.add(temp.val);
                hashMap.put(tempLevel, sublist);
            }
            minLevel = Integer.min(minLevel, tempLevel);
            maxLevel = Integer.max(maxLevel, tempLevel);
        }

        for(int i = minLevel; i<= maxLevel; i++){
            list.add(new ArrayList<>(hashMap.get(i)));
        }
        return list;
    }

}
