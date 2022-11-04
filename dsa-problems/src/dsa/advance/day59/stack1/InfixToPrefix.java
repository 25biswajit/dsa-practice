package dsa.advance.day59.stack1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

/*Given string A denoting an infix expression. Convert the infix expression into a postfix expression.
String A consists of ^, /, *, +, -, (, ) and lowercase English alphabets where lowercase English alphabets are operands and ^, /, *, +, - are operators.
Find and return the postfix expression of A.
NOTE:
^ has the highest precedence.
/ and * have equal precedence but greater than + and -.
+ and - have equal precedence and lowest precedence among given operators.*/
public class InfixToPrefix {
    @Test
    public void test1(){
        String infixExp = "a+b-c";
        Assertions.assertEquals("ab+c-", convertInfixToPostfix(infixExp));
    }
    @Test
    public void test2(){
        String infixExp = "a+b*c";
        Assertions.assertEquals("abc*+", convertInfixToPostfix(infixExp));
    }
    @Test
    public void test3(){
        String infixExp = "a^b^c";
        Assertions.assertEquals("ab^c^", convertInfixToPostfix(infixExp));
    }
    @Test
    public void test4(){
        String infixExp = "a+b*(c^d-e)^(f+g*h)-i";
        Assertions.assertEquals("abcd^e-fgh*+^*+i-", convertInfixToPostfix(infixExp));
    }
    @Test
    public void test5(){
        String infixExp = "K + L - M*N + (O^P) * W/U/V * T + Q";
        Assertions.assertEquals("KL+MN*-OP^W*U/V/T*+Q+", convertInfixToPostfix(infixExp));
    }


    public String convertInfixToPostfix(String infixExp){
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < infixExp.length(); i++){
            char c = infixExp.charAt(i);
            if(c==' ') continue;
            if (isAlphaNumeric(c)) {
                sb.append(c);
                continue;
            }
            if(stack.isEmpty() || c == '(') {
                stack.push(c);
            }
            else {
                if(c == ')'){
                    while (!stack.isEmpty() && stack.peek() !='('){
                        sb.append(stack.pop());
                    }
                    if(!stack.isEmpty() && stack.peek() =='('){
                        stack.pop();
                    }
                }
                else {
                    while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())){
                        sb.append(stack.pop());
                    }
                    stack.push(c);
                }
            }
        }
        while (!stack.isEmpty()){
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    private int precedence(char c){
        switch (c){
            case '+':
            case '-': return 1;
            case '*':
            case '/': return 2;
            case '^': return 3;
            default: return -1;
        }
    }

    private boolean isAlphaNumeric(char c){
        return (c >= 'a' && c <='z')||(c >= 'A' && c <='Z')||(c >= '0' && c <='1');
    }
}
