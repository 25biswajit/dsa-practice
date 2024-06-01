package dsa.leetcode.selected;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class LargestRectangleHistogram {
    @Test
    public void test1(){
        List<Integer> list = Arrays.asList(2,1,5,6,2,3);
        Assertions.assertEquals(10 , largestRectangleArea(list));
    }

    public static int largestRectangleArea(List< Integer > heights) {
        int[] nse = nextSmallerElem(heights);
        int[] pse = prevSmallerElem(heights);
        int maxArea = -1;
        for(int i = 0; i < heights.size(); i++){
            int left = pse[i];
            int right = nse[i];
            int area = (right - left -1) * heights.get(i);
            maxArea = Integer.max(maxArea, area);
        }
        return maxArea;
    }

    private static int[] nextSmallerElem(List < Integer > heights){
        int[] list = new int[heights.size()];
        int n = heights.size();
        int i = n-1;
        Stack<PairObjec> stack = new Stack<>();
        while(i >= 0){
            int cur = heights.get(i);

            if(!stack.isEmpty()){
                if(stack.peek().val < cur ){
                    list[i] = stack.peek().idx;
                    stack.push(new PairObjec(i,cur));
                    i--;
                }
                else{
                    while(!stack.isEmpty() && stack.peek().val >= cur){
                        stack.pop();
                    }
                }
            }else{
                list[i] = n;
                stack.push(new PairObjec(i,cur));
                i--;
            }
        }
        return list;
    }

    private static int[] prevSmallerElem(List < Integer > heights){
        int[] list = new int[heights.size()];
        int n = heights.size();
        int i = 0;
        Stack<PairObjec> stack = new Stack<>();
        while(i < n){
            int cur = heights.get(i);

            if(!stack.isEmpty()){
                if(stack.peek().val < cur ){
                    list[i] = stack.peek().idx;
                    stack.push(new PairObjec(i,cur));
                    i++;
                }
                else{
                    while(!stack.isEmpty() && stack.peek().val >= cur){
                        stack.pop();
                    }
                }
            }else{
                list[i] = -1;
                stack.push(new PairObjec(i,cur));
                i++;
            }
        }
        return list;
    }

}

class PairObjec{
    int idx;
    int val;

    PairObjec(int i, int v){
        this.idx = i;
        this.val = v;
    }
}