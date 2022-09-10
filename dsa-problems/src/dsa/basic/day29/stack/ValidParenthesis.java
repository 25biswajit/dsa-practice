package dsa.basic.day29.stack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

public class ValidParenthesis {
    @Test
    public void test1(){
        String inputs = "[{()}]"; Assertions.assertEquals(1, solve(inputs));
        inputs = "[]{()}[]"; Assertions.assertEquals(1, solve(inputs));
    }
    @Test
    public void test2(){
        String inputs = "[{([)}]]"; Assertions.assertEquals(0, solve(inputs));
        inputs = "]{()}["; Assertions.assertEquals(0, solve(inputs));
        inputs = "]]]])))"; Assertions.assertEquals(0, solve(inputs));
        inputs = "{}("; Assertions.assertEquals(0, solve(inputs));
    }


    public int solve(String phrase) {
        String[] arr = phrase.split("");
        Stack<String> stack = new Stack<>();

        for(String s : arr){
            // Found Closing bracket but no opeing bracket
            if(!isOpen(s) && stack.isEmpty()){
                return 0;
            }
            // Found Opening bracket push into stack
            else if(isOpen(s)){
                stack.push(s);
            }
            // Found Closing bracket, then evaluate with top element of stack
            else if(!isOpen(s) && !stack.isEmpty()){
                String top = stack.peek();
                if(isValidPair(top,s)){
                    stack.pop();
                }else {
                    return 0;
                }
            }
        }
        // After evaluating all bracket should be nullified if Valid
        if (stack.isEmpty()) return 1;
        else return 0;
    }

    private boolean isValidPair(String top, String s) {
        return  (top.equals("[") && s.equals("]")) || (top.equals("{") && s.equals("}")) || (top.equals("(") && s.equals(")"));
    }

    private boolean isOpen(String s){
        return s.equals("[") || s.equals("{") || s.equals("(");
    }
}
