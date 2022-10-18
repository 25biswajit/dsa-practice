package dsa.advance.day54.patternMatching;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/*
You are given a string A of lowercase English alphabets. Rearrange the characters of the given string A such that there is no boring substring in A.
A boring substring has the following properties:
Its length is 2.
Both the characters are consecutive, for example - "ab", "cd", "dc", "zy" etc.(If the first character is C then the next character can be either (C+1) or (C-1)).
Return 1 if it is possible to rearrange the letters of A such that there are no boring substrings in A else, return 0.
A = "abcd", String A can be rearranged into "cadb" or "bdac" , Ans = 1
A = "aab", No arrangement of string A can make it free of boring substrings., Ans = 0
*/

public class BoringSubString {
    @Test
    public void test1(){
        String s = "abcd";
        Assertions.assertEquals(1, isBoringString(s));
    }
    @Test
    public void test2(){
        String s = "aab";
        Assertions.assertEquals(0, isBoringString(s));
    }

    // TC : N log N, sc : N
    public int isBoringString(String A) {
        char[] letter = A.toCharArray();
        StringBuilder even = new StringBuilder();
        StringBuilder odd = new StringBuilder();
        for (int i = 0; i < A.length(); i++) {
            if (letter[i] % 2 == 0) {
                even.append(letter[i]);
            } else {
                odd.append(letter[i]);
            }
        }
        char[] ev = even.toString().toCharArray();
        char[] od = odd.toString().toCharArray();
        Arrays.sort(ev);
        Arrays.sort(od);
        if (Math.abs(ev[ev.length - 1] - od[0]) == 1 && Math.abs(od[od.length - 1] - ev[0]) == 1) {
            return 0;
        }
        return 1;
    }
}

/*
Think in terms of parity of ASCII values of the characters.
All odd parity needs to stay together.
Similarly, all even parity characters need to stay together.

No specific knowledge is required to solve this question.
You need to observe and find an existing pattern hidden in the parities of ASCII value of characters.

‘a’ could be present near ‘c’ , similarly ‘c’ could be near ‘e’ as we can see odd characters can be put aside each other,
and there will be no boring substring in it.

Like: “acegik…” No boring substring is present in this string.

Similarly for even characters.

Now traverse in the string and form two strings, one containing the odd characters and the other even characters.
Sort both of them and check if placing them together doesn’t make a boring substring at their join point.

For example:
A = “abcdefg”
So ,
odd = “aceg”
even= “bdf”

Check the string s = odd+even or s=even+odd doesn’t contain any boring substring.

Time Complexity: O(	A	)
*/

