package dsa.advance.day59.stack1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
Given a string A denoting an expression. It contains the following operators '+', '-', '*', '/'.
Check whether A has redundant braces or not.
NOTE: A will be always a valid expression and will not contain any white spaces.
Input 1: A = "((a+b))" Output : 1
Input 2: A = "(a+(a+b))" Output : 0
*/

/*
Solution:
If we somehow pick out sub-expressions surrounded by ( and ), then if we are left with () as a part of the string, we know we have redundant braces.
Let us take an example:
(a+(a+b))
We keep pushing elements onto the stack till we encounter ')'. When we encounter ')', we start popping elements until we find a matching '('.
If the number of elements popped does not correspond to '()', we are fine, and we can move forward.
Otherwise, voila! Matching braces have been found.
Some Extra Hints:
Try to run your code on test cases like (a*(a))  and (a) ??
*/

import java.util.Stack;

public class RedundantBraces {
    @Test
    public void test1(){
        String A = "((a+b))";
        Assertions.assertEquals(1, braces(A));
    }
    @Test
    public void test2(){
        String A = "(a+(a+b))";
        Assertions.assertEquals(0, braces(A));
    }
    @Test
    public void test3(){
        String A = "(a+(a+(b)))";
        Assertions.assertEquals(1, braces(A));
    }
    @Test
    public void test4(){
        String A = "a+(b+c)";
        Assertions.assertEquals(0, braces(A));
    }
    @Test
    public void test5(){
        String A = "(a+((b*c)+c))";
        Assertions.assertEquals(0, braces(A));
    }

    public int braces(String A) {
        Stack<Character> stack = new Stack<>();
        for(int i=0; i< A.length(); i++){
            char c = A.charAt(i);
            if(!(c == '+' || c == '-' ||c == '*' ||c == '/' ||c == '(' ||c == ')')){
                continue;
            }
            if(stack.isEmpty()) stack.push(c);
            else {
                char top = stack.peek();
                if(top == '(' && c == ')' ){
                    return 1;
                }
                else if( c == '+' || c == '-' ||c == '*' ||c == '/' ||c == '('){
                    stack.push(c);
                }
                else {
                    while (!stack.isEmpty() && stack.pop() != '('){
                        // Keep this while loop running until closing brace doesn't find any opening brace
                    }
                }
            }
        }
        while (!stack.isEmpty() && (stack.peek() == '+' || stack.peek() == '-' ||stack.peek() == '*' ||stack.peek() == '/')){
           stack.pop();
        }
        return stack.isEmpty() ? 0 : 1;
    }
}
