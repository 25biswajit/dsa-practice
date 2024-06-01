package dsa.advance.day64.tree2;

import dsa.model.Pair;
import dsa.model.TreeNode;
import dsa.utils.ArrayUtils;
import dsa.utils.MatrixUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class DiagonalTraversal {
    
    @Test
    public void test1(){
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(9);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(2);
        root.left.right.right = new TreeNode(9);
        root.left.right.right.right = new TreeNode(4);
        root.right = new TreeNode(6);
        root.right.right = new TreeNode(10);

        int[] expected = {8,6,10,9,2,9,4,2};

        ArrayList<Integer> result = diagonalTraversal_LeftToRight(root);
        int[] actual = ArrayUtils.convertToIntArray(result);
        Assertions.assertArrayEquals(expected,actual);

        ArrayList<Integer> result_ = diagonalTraversal(root);
        int[] actual_ = ArrayUtils.convertToIntArray(result_);
        Assertions.assertArrayEquals(expected,actual_);

        // Level by Level
        Integer[][] expected_level = {{8,6,10}, {9,2,9,4}, {2}};
        ArrayList<ArrayList<Integer>> list = diagonalTraversal_LeftToRight_levels(root);
        Integer[][] actual_level = MatrixUtils.convertListToMatrix(list);
        Assertions.assertArrayEquals(expected_level,actual_level);
    }

    @Test
    public void test2(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(5);
        root.left.right.left = new TreeNode(6);
        root.left.right.right = new TreeNode(7);
        root.left.right.left.right = new TreeNode(8);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);

        int[] expected = {1,3,2,5,7,4,6,8};

        ArrayList<Integer> result = diagonalTraversal_LeftToRight(root);
        int[] actual = ArrayUtils.convertToIntArray(result);
        Assertions.assertArrayEquals(expected,actual);

        // Level by Level
        Integer[][] expected_level = {{1,3}, {2,5,7,4}, {6,8}};
        ArrayList<ArrayList<Integer>> list = diagonalTraversal_LeftToRight_levels_new(root);
        Integer[][] actual_level = MatrixUtils.convertListToMatrix(list);
        Assertions.assertArrayEquals(expected_level,actual_level);

        // This is failing for overlapping Nodes
        /*
        ArrayList<Integer> result_ = diagonalTraversal(root);
        int[] actual_ = ArrayUtils.convertToIntArray(result_);
        Assertions.assertArrayEquals(expected,actual_);
        */
    }

    // Easiest Solution
    // Traverse towards right and Print Right Nodes , Push left Node to Queue
    public ArrayList<Integer> diagonalTraversal_LeftToRight(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode temp = queue.poll();
            while (temp!=null){
                if(temp.left!=null){ queue.add(temp.left); }
                list.add(temp.val);
                temp = temp.right;
            }
        }
        //System.out.println(list);
        return list;
    }

    public ArrayList<ArrayList<Integer>> diagonalTraversal_LeftToRight_levels(TreeNode root) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        ArrayList<Integer> subList = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        while (!queue.isEmpty()){
            TreeNode temp = queue.poll();
            if(temp == null && !subList.isEmpty()){
                list.add(new ArrayList<>(subList));
                System.out.println(subList);
                subList.clear();
                queue.add(null);
            }
            while (temp!=null){
                if(temp.left!=null){ queue.add(temp.left); }
                subList.add(temp.val);
                temp = temp.right;
            }
        }

        return list;
    }

    public ArrayList<ArrayList<Integer>> diagonalTraversal_LeftToRight_levels_new(TreeNode root) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        while (!queue.isEmpty()){
            int n = queue.size();
            ArrayList<Integer> subList = new ArrayList<>();
            while (n > 0){
                TreeNode temp = queue.poll();
                while (temp!=null){
                    if(temp.left!=null){ queue.add(temp.left); }
                    subList.add(temp.val);
                    temp = temp.right;
                }
                n--;
            }
            System.out.println(subList);
            list.add(subList);
        }
        return list;
    }

    //Not working for all situations
    public ArrayList<Integer> diagonalTraversal(TreeNode root) {
        int maxLevel = 0;
        ArrayList<Integer> list = new ArrayList<>();
        HashMap<Integer, List<Integer>> hashMap = new HashMap<>();
        Queue<Pair<TreeNode,Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(root,0));
        while (!queue.isEmpty()){
            Pair<TreeNode,Integer> pair = queue.poll();
            TreeNode temp = pair.first;
            Integer tempLevel = pair.second;
            if(temp.left!=null){
                queue.add(new Pair<>(temp.left,tempLevel+1));
            }
            if(temp.right!=null){
                queue.add(new Pair<>(temp.right,tempLevel));
            }
            if(hashMap.containsKey(tempLevel)){
                hashMap.get(tempLevel).add(temp.val);
            }else {
                ArrayList<Integer> sublist = new ArrayList<>();
                sublist.add(temp.val);
                hashMap.put(tempLevel, sublist);
            }
            maxLevel = Integer.max(maxLevel, tempLevel);
        }
        for(int i = 0; i<= maxLevel; i++){
            list.addAll(hashMap.get(i));
        }
        //System.out.println(list);
        return list;
    }
}

/*Method 1:
The idea is to use map. We use different slope distances and use them as key in map. Value in map is vector (or dynamic array) of nodes. We traverse the tree to store values in map. Once map is built, we print contents of it.

Suppose slope at current node is x then:
a) The slope of it’s right child remains same i.e x
b) The slope of it’s left child becomes x + 1

As the order in the output matters so we have to move in the pre-order traversal format in the tree A i.e
1)Process the root push root to map[slope(root)].push(root)
2)Go to left child
3)Go to right child
Keep repeating the above steps until the whole tree is traversed.

Time Complexity : O(N)
Space Complexity : O(N)

Method 2: Using Queue

Every node will help to generate the next diagonal. We will push the element in the queue only when its left is available. We will process the node and move to its right.

Time Complexity: O(N), because we are visiting nodes once.
Space Complexity: O(N), because we are using queue.*/
