package dsa.leetcode.selected;

import dsa.model.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BurningTree {
    @Test
    public void test(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(5);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(9);
        root.left.right.right = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(6);
        int res = amountOfTime(root, 3);
        System.out.println("Ans:" + res);
    }

    public int amountOfTime(TreeNode root, int start) {
        //child - parent map
        HashMap<Integer, TreeNode> map = new HashMap<>();
        //lookup
        HashMap<Integer, TreeNode> lookup = new HashMap<>();

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            TreeNode top = q.poll();
            lookup.put(top.val, top);
            if(top.left!=null){
                q.add(top.left);
                map.put(top.left.val, top);
            }
            if(top.right!=null){
                q.add(top.right);
                map.put(top.right.val, top);
            }
        }

        boolean flag = false;
        int time = -1;
        int n = 0;
        HashSet<Integer> visited = new HashSet<>();
        q.clear();
        TreeNode hot = lookup.get(start);
        q.add(hot);
        while(!q.isEmpty()){
            printQ(q);
            n = q.size();

            while(n > 0){
                hot = q.poll();
                if(!visited.contains(hot.val)){
                    visited.add(hot.val);
                }
                else{
                    continue;
                }

                if(hot.left!=null && !visited.contains(hot.left.val)) {
                    q.add(hot.left);
                    flag = true;
                }
                if(hot.right!=null && !visited.contains(hot.right.val)) {
                    q.add(hot.right);
                    flag = true;
                }
                if(map.containsKey(hot.val)){
                    int key = map.get(hot.val).val;
                    if(!visited.contains(key)){
                        q.add(map.get(hot.val));
                        flag = true;
                    }
                }
                n--;
            }

            if(flag) time++;
        }

        return time;

    }

    private void printQ(Queue<TreeNode> q) {
        StringBuilder stringBuilder = new StringBuilder();
        q.forEach(x -> stringBuilder.append(x.val).append(","));
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        System.out.println(stringBuilder);
    }

}
