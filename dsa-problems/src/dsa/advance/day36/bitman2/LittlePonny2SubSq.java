package dsa.advance.day36.bitman2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
For a given WORD find out the lexicographically minimum subsequence from it of size >= 2.
*/
public class LittlePonny2SubSq {
    @Test
    public void test(){
        Assertions.assertEquals("da" , solve("ksdjha"));
        Assertions.assertEquals("aa" , solve("abcdsfhjagj"));
    }

    // TC: O(N), SC: O(1)
    public String solve(String word) {
        String[] chars = word.split("");
        String min = chars[0];
        int min_index = 0;
        for(int i = 1; i < chars.length-1; i++){
            if(min.compareTo(chars[i]) > 0){
                min = chars[i];
                min_index = i;
            }
        }
        String second_char = chars[min_index + 1];
        for(int i = min_index + 2; i < chars.length ; i++){
            if(second_char.compareTo(chars[i]) > 0){
                second_char = chars[i];
            }
        }
        return min+second_char;
    }
}


/*
Solution :
The last character of WORD cannot be a part of the first character of the answer, so we don’t consider it.
We can pick up the first occurrence of the smallest character in the rest of the string.
Now, all of the string lying before the occurrence of the first character is useless.
So, we can exclude the whole string behind it and only consider the string after it.
We can pick the smallest character in the leftover substring.
*/
