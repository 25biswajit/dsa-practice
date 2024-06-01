package dsa.advance.day64.tree2;

import dsa.model.Pair;
import dsa.model.TreeNode;
import dsa.utils.ArrayUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class TopViewBottomView {

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

        int[] expected = {9,8,6,10};
        ArrayList<Integer> result = topView(root);
        int[] actual = ArrayUtils.convertToIntArray(result);
        Assertions.assertArrayEquals(expected,actual);

        int[] expected_bottomView = {2,2,9,4};
        result = bottomView(root);
        int[] actual_bottomView = ArrayUtils.convertToIntArray(result);
        Assertions.assertArrayEquals(expected_bottomView,actual_bottomView);
    }

    public ArrayList<Integer> topView(TreeNode root) {
        int minLevel = 0, maxLevel = 0;
        ArrayList<Integer> list = new ArrayList<>();
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
            list.add(hashMap.get(i).get(0));
        }
        return list;
    }

    public ArrayList<Integer> bottomView(TreeNode root) {
        int minLevel = 0, maxLevel = 0;
        ArrayList<Integer> list = new ArrayList<>();
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
            List<Integer> sublist = hashMap.get(i);
            list.add(sublist.get(sublist.size()-1));
        }
        return list;
    }
}
