package dsa.advance.day81.dp4;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/combination-sum-iii/description/
public class CombinationSum3 {

    @Test
    public void test1(){
        System.out.println( combinationSum3(9,45));
    }
    @Test
    public void test2(){
        System.out.println( combinationSum3(3,9));
    }

    /*Input: k = 3, n = 9
    Output: [[1,2,6],[1,3,5],[2,3,4]]
    Explanation:
            1 + 2 + 6 = 9
            1 + 3 + 5 = 9
            2 + 3 + 4 = 9
    There are no other valid combinations.*/

    List<List<Integer>> result = null;
    public List<List<Integer>> combinationSum3(int k, int n) {
        result = new ArrayList<>();
        solve(k, 1, n, new ArrayList<>());
        return result;
    }

    private void solve(int count, int i, int target, List<Integer> temp){
        if(target == 0 && count == 0){
            result.add(new ArrayList<>(temp));
            return;
        }
        if(i > 9) return;

        // include
        if(target >= i){
            temp.add(i);
            solve(count-1, i+1, target-i, temp);
            temp.remove(temp.size()-1);
        }

        // exclude
        solve(count, i+1, target, temp);
    }
}
