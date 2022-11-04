package dsa.advance.day59.stack1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

/*
You are given a string A.
An operation on the string is defined as follows:
Remove the first occurrence of the same consecutive characters. eg for a string "abbcd", the first occurrence of same consecutive characters is "bb".
Therefore the string after this operation will be "acd".
Keep performing this operation on the string until there are no more occurrences of the same consecutive characters and return the final string.
A = abccbc
Explanation :
Remove the first occurrence of same consecutive characters. eg for a string "abbc",
the first occurrence of same consecutive characters is "bb".
Therefore the string after this operation will be "ac".
*/

public class DoubleCharacterTrouble {
    @Test
    public void test1(){
        Assertions.assertEquals("ac", removeAdjacentSameChar("abccbc"));
        Assertions.assertEquals("", removeAdjacentSameChar("babbabbb"));
        Assertions.assertEquals("ay", removeAdjacentSameChar("azxxzy"));
    }

    // TC: O(N), SC: O(N)
    public String removeAdjacentSameChar(String word){
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for(int i = 0;i<word.length();i++) {
            char c = word.charAt(i);
            if(stack.empty()){
                stack.push(c);
            }else {
                char top = stack.peek();
                if(c == top){
                    stack.pop();
                }else {
                    stack.push(c);
                }
            }
        }
        while (!stack.isEmpty()){
            result.append(stack.pop());
        }
        return result.reverse().toString();
    }
}
/*Consider an example string abba.
When we remove the “bb”, the remaining string is “aa” which has to be removed as well.
So we need to keep track of the characters before the first occurrence of similar consecutive characters.
We can do this using a stack.
We keep pushing the characters in a stack, if the current character is equal to the top of the stack,
we pop from the stack since they represent
a pair of similar characters.
Finally, we print the stack in reverse.*/
