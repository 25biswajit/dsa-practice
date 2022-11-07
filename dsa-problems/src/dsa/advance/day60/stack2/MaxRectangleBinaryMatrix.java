package dsa.advance.day60.stack2;

import dsa.utils.MatrixUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

//https://www.algotree.org/algorithms/stack_based/maximum_size_rectangle_in_a_binary_matrix/
public class MaxRectangleBinaryMatrix {
    @Test
    public void test1(){
        int[][] matrix = {{1,0,1,0,0},{1,0,1,1,1},{1,1,1,1,1},{1,0,0,1,0}};
        Assertions.assertEquals(6, maxRectangle(matrix));
    }
    @Test
    public void test2(){
        int[][] matrix =
                {{0, 1, 1},
                 {1, 0, 0},
                 {1, 0, 0},
                 {1, 0, 0},
                 {1, 0, 0},
                 {1, 1, 1},
                 {0, 1, 0}};
        Assertions.assertEquals(5, maxRectangle(matrix));
    }

    @Test
    public void test3(){
        int[][] matrix =
                        {{0, 1, 1, 0},
                         {1, 1, 1, 1},
                         {1, 1, 1, 1},
                         {1, 1, 0, 0}};
        Assertions.assertEquals(8, maxRectangle(matrix));
    }




    public int maxRectangle(int[][] matrix){
        int maxArea = largestRectangleArea(matrix[0]);
        System.out.println("Max 0:" + maxArea);
        for(int i = 1; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                if(matrix[i][j] != 0){
                    matrix[i][j] = matrix[i-1][j] + matrix[i][j];
                }

            }
            maxArea = Integer.max( maxArea, largestRectangleArea(matrix[i]));
            System.out.println("Max " + i + ":"+ maxArea);
        }
        MatrixUtils.printMatrix(matrix);
        return maxArea;
    }

    public int largestRectangleArea(int[] array) {
        int maxArea = 0;
        int[] left = prevSmallerIndex(array);
        int[] right = nextSmallerIndex(array);
        for(int i=0; i < array.length; i++){
            int l = left[i];
            int r = right[i];
            int area = (r-l-1) * array[i];
            maxArea = Integer.max(maxArea, area);
        }
        return maxArea;
    }

    public int[] prevSmallerIndex(int[] array) {
        int[] ansArray = new int[array.length];
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        ansArray[0]=-1;
        for(int i = 1; i < array.length; i++){
            if(!stack.isEmpty() && array[stack.peek()] < array[i]){
                ansArray[i] = stack.peek();
            }else {
                while (!stack.isEmpty() && array[stack.peek()] >= array[i]){
                    stack.pop();
                }
                if(!stack.isEmpty()){
                    ansArray[i] = stack.peek();
                }else {
                    ansArray[i] = -1;
                }
            }
            stack.push(i);
        }
        return ansArray;
    }

    public int[] nextSmallerIndex(int[] array) {
        int n = array.length;
        int[] ansArray = new int[array.length];
        Stack<Integer> stack = new Stack<>();
        stack.push(n-1);
        ansArray[n-1]=n;
        for(int i = n-2; i >=0; i--){
            if(!stack.isEmpty() && array[stack.peek()] < array[i]){
                ansArray[i] = stack.peek();
            }else {
                while (!stack.isEmpty() && array[stack.peek()] >= array[i]){
                    stack.pop();
                }
                if(!stack.isEmpty()){
                    ansArray[i] = stack.peek();
                }else {
                    ansArray[i] = n;
                }
            }
            stack.push(i);
        }
        return ansArray;
    }

}
