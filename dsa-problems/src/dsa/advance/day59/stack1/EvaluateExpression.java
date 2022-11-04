package dsa.advance.day59.stack1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;
/*
An arithmetic expression is given by a string array A of size N. Evaluate the value of an arithmetic expression in Reverse Polish Notation.
Valid operators are +, -, *, /. Each string may be an integer or an operator.
Input 1:
A =   ["2", "1", "+", "3", "*"]
Explanation 1:
    starting from backside:
    * : () * ()
    3 : () * (3)
    + : (() + ()) * (3)
    1 : (() + (1)) * (3)
    2 : ((2) + (1)) * (3)
    ((2) + (1)) * (3) = 9

Input 2:
    A = ["4", "13", "5", "/", "+"]

Explanation 2:
    + : () + ()
    / : () + (() / ())
    5 : () + (() / (5))
    13 : () + ((13) / (5))
    4 : (4) + ((13) / (5))
    (4) + ((13) / (5)) = 6
*/
public class EvaluateExpression {
    @Test
    public void test1(){
        String[] expression =  {"2", "1", "+", "3", "*"};
        Assertions.assertEquals(9 , evaluateExpression(expression));
    }
    @Test
    public void test2(){
        String[] expression =  {"4", "13", "5", "/", "+"};
        Assertions.assertEquals(6 , evaluateExpression(expression));
    }

    // TC: O(N) , SC: O(N)
    public int evaluateExpression(String[] expression ){
        Stack<String> stack = new Stack<>();
        int res = 0;
        for(int i = 0; i < expression.length; i++){
            String op = expression[i];
            if(op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/")){
                String b = stack.pop();
                String a = stack.pop();
                res = calculate(a,b,op);
                stack.push(Integer.toString(res));
            }else {
                stack.push(op);
            }
        }
        return Integer.parseInt(stack.pop());
    }

    private int calculate(String a, String b, String c) {
        switch (c){
            case "+": return Integer.parseInt(a) + Integer.parseInt(b);
            case "-": return Integer.parseInt(a) - Integer.parseInt(b);
            case "*": return Integer.parseInt(a) * Integer.parseInt(b);
            case "/": return Integer.parseInt(a) / Integer.parseInt(b);
            default: return 0;
        }
    }
}
/*
This is pretty much a simulation problem.
Think using stack.
When you encounter an operator, that is when you need the top 2 numbers on the stack, perform the operation on them, and put them on the stack.*/
