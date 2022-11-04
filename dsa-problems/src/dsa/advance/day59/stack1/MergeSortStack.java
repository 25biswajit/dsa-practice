package dsa.advance.day59.stack1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

/*Sort stack using another stack
Given a stack of integers A, sort it using another stack.
Return the array of integers after sorting the stack using another stack.*/

public class MergeSortStack {
    @Test
    public void test(){
        int[] array = {5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5};
        Assertions.assertArrayEquals(expected, solve(array));
    }
    // N log N
    public int[] solve(int[] array){
        Stack<Integer> stack = new Stack<>();
        for(int x: array){
            stack.push(x);
        }
        stack = sortStack(stack);
        for(int i=0;i<array.length; i++){
            array[i] = stack.pop();
        }
        return array;
    }


    public Stack<Integer> sortStack(Stack<Integer> stack){
        if(stack.size()<=1) return stack;
        Stack<Integer> stackNew = new Stack<>();
        int size = stack.size();
        for(int i = 0; i< size/2 ; i++){
            stackNew.push(stack.pop());
        }
        return mergeStack(sortStack(stack), sortStack(stackNew));
    }

    private Stack<Integer> mergeStack(Stack<Integer> stack1, Stack<Integer> stack2) {
        Stack<Integer> mergedStack = new Stack<>();
        Stack<Integer> sortedStack = new Stack<>();
        while (!stack1.isEmpty() && !stack2.isEmpty()){
            if(stack1.peek() < stack2.peek()){
                mergedStack.push(stack1.pop());
            }
            else {
                mergedStack.push(stack2.pop());
            }
        }
        while (!stack1.isEmpty()){
            mergedStack.push(stack1.pop());
        }
        while (!stack2.isEmpty()){
            mergedStack.push(stack2.pop());
        }
        while (!mergedStack.isEmpty()){
            sortedStack.push(mergedStack.pop());
        }
        return sortedStack;
    }

}