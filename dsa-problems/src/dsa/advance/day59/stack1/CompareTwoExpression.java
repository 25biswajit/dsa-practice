package dsa.advance.day59.stack1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
Problem Description
Given two strings A and B. Each string represents an expression consisting of lowercase English alphabets, '+', '-', '(' and ')'.
The task is to compare them and check if they are similar. If they are identical, return 1 else, return 0.
NOTE: It may be assumed that there are at most 26 operands from ‘a’ to ‘z’, and every operand appears only once.
Input 1:
A = "-(a+b+c)"
B = "-a-b-c"
Input 2:
A = "a-b-(c-d)"
B = "a-b-c-d"
*/

public class CompareTwoExpression {
    @Test
    public void test1(){
        String A = "-(a+b+c)";
        String B = "-a-b-c";
        Assertions.assertEquals(1, compareExpression(A,B));
    }

    @Test
    public void test2(){
        String A = "-b+(-(a+b-(-a+b-(-a-b)+b)-b))";
        String B = "-b-a-b-a+b+a+b+b+b";
        Assertions.assertEquals(1, compareExpression(A,B));
    }

    public int compareExpression(String exp1, String exp2){
        HashMap<Character, Integer> map = new HashMap<>();
        evaluateExpression(exp1, true, map);
        System.out.println(map);
        evaluateExpression(exp2, false, map);
        System.out.println(map);
        for(Map.Entry<Character,Integer> entry : map.entrySet()){
            if(entry.getValue()!=0){
                return 0; // false
            }
        }
        return 1; // true
    }

    private HashMap<Character, Integer> evaluateExpression(String expression, boolean flag, HashMap<Character, Integer> map){
        Stack<Boolean> stack = new Stack<>();
        stack.push(true); // True Stands for Positive
        for(int i = 0;i < expression.length(); i++){
            char c = expression.charAt(i);
            if(c == '+' || c == '-'){ /* Skipped */ }
            // If Found "-(" change the effective sign , derive with help of prev effective sign ( stack peek ) and current local sign
            else if( c == '('){ stack.push(stack.peek() == isPositive(expression, i)); }
            // ")" stands for end of current effective sign , go back to previous effective sign , so pop the current effective sign
            else if( c == ')'){ stack.pop(); }
            else {
                int freq = map.getOrDefault(c,0);
                if(flag){
                    freq += (isPositive(expression, i) == stack.peek()) ? 1:-1;
                }else {
                    freq += (isPositive(expression, i) == stack.peek()) ? -1:1;
                }
                map.put(c, freq);
            }
        }
        return map;
    }

    // True Stands for + i.e. Positive
    // False Stands for - i.e. Negative
    // [ + - = - ] or [ - + = - ] => Opposite Sign gives False i.e Negative
    // [ + + = + ] or [ - - = + ] => Same Sign gives True i.e. Positive
    private boolean isPositive(String expression, int index){
        if(index == 0) return true;
        if(expression.charAt(index-1)=='-') return false;
        return true;
    }
}
/*
https://www.tutorialcup.com/interview/stack/check-if-two-expressions-with-brackets-are-same.htm
Algorithm to Check if Two Expressions With Brackets are Same
Initialize two strings s1 and s2 representing_expressions containing addition operator, subtraction_operator, lowercase alphabets, and parenthesis.
Create a vector and initialize all the values of the vector as 0.
After that create a stack of boolean type and push true in it.
Traverse through the string 1 i.e. s1 and check if the character at the current index in the string is equal to ‘+’ or ‘-‘, go to the next iteration.
Else if the character at the current index in the string is equal to an opening parenthesis, check if the character at the previous index in the string is not equal to ‘-‘, push the value at top of the stack in the stack itself else push the not of the value at top of the stack in the stack itself.
If 5 step did not check then Else if the character at the current index in the string is equal to a closing parenthesis, pop the element at the top the stack.
Else check if the stack has top, update the vector as v[s1[i]-‘a’]  +=(adjSign(s1,i) ? add ? 1 : -1 : add ? -1 : 1) else update the vector as v[s1[i]-‘a’]  +=(adjSign(s1,i) ? add ? -1 : 1 : add ? 1 : -1).
Similarly, repeat the same steps with string 2 i.e. s2.
After that, traverse from 0 to 25 and check if the value at the current index in the vector if not equal to 0, print “No” else print “Yes”.
*/
