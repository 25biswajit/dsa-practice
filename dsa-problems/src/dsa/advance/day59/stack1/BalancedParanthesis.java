package dsa.advance.day59.stack1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

/*
Given an expression string A, examine whether the pairs and the orders of “{“,”}”, ”(“,”)”, ”[“,”]” are correct in A.
A = {([])}
A = ()[]
*/

public class BalancedParanthesis {
    @Test
    public void test1(){
        String expression = "{([])}";
        Assertions.assertEquals(1, isBalanced(expression));
    }
    @Test
    public void test2(){
        String expression = "{([)]}";
        Assertions.assertEquals(0, isBalanced(expression));
    }

    // TC: O(N), SC: O(N)
    public int isBalanced(String expression){
        if(expression == null || expression.trim().length()==0) return 0;
        Stack<Character> stack = new Stack<>();
        for(int i = 0;i<expression.length();i++){
            char c = expression.charAt(i);
            if(stack.empty()){
                stack.push(c);
            }else {
                if(c == '[' || c == '(' ||  c == '{'){
                    stack.push(c);
                }else {
                    char top = stack.peek();
                    if((top == '[' && c == ']') || (top == '{' && c == '}') || (top == '(' && c == ')')) {
                        stack.pop();
                    }
                }
            }
        }
        return stack.isEmpty() ? 1 : 0;
    }

}

/*    Solution Approach
First, let us look at the impossible cases:
1) [() : There is no corresponding closing bracket for an opening bracket.
2) [) : Incorrect closing bracket for an opening bracket.
3) } : No opening bracket for a closed bracket.

Now, we can observe that the earlier a bracket is encountered in the string, the later it is closed.
We can guess that the Stack Data Structure will be used using this observation.

We traverse the given string from the left. If the i-th character is an opening bracket, we push it onto the stack.
If it is a closing bracket, we check for the impossible case 2 and case 3. If they are being violated, then we can simply return 0. Otherwise, we can pop the topmost bracket from the stack.
To check for case 1, if our stack is not empty at the end of our traversal, then we can say that the brackets are not correctly matched.

If all the conditions are fulfilled, then we can return 1.*/
