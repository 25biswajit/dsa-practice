package dsa.advance.day53.patternMatch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
You are given a string A of length N consisting of lowercase alphabets. Find the period of the string.
Period of the string is the minimum value of k (k >= 1), that satisfies A[i] = A[i % k] for all valid i.
A = abababab => Period = 2, Period of the string will be 2: Since, for all i, A[i] = A[i%2].
A = abcabcab => Period = 3
*/

public class PeriodOfString {
    @Test
    public void test1(){
        String text = "ababababab";
        Assertions.assertEquals(2, countPeriodString(text));
        Assertions.assertEquals(2, countPeriodObservationBased(text));
    }
    @Test
    public void test2(){
        String text = "abbaabba";
        Assertions.assertEquals(4, countPeriodString(text));
        Assertions.assertEquals(4, countPeriodObservationBased(text));
    }
    @Test
    public void test3(){
        String text = "abaaba";
        Assertions.assertEquals(3, countPeriodString(text));
        Assertions.assertEquals(3, countPeriodObservationBased(text));
    }
    @Test
    public void test4(){
        String text = "abcd";
        Assertions.assertEquals(4, countPeriodString(text));
        Assertions.assertEquals(4, countPeriodObservationBased(text));
    }
    @Test
    public void test5(){
        String text = "abcabcabca";
        Assertions.assertEquals(3, countPeriodString(text));
        Assertions.assertEquals(3, countPeriodObservationBased(text));
    }

    // TC: O(N), SC:O(N)
    public int countPeriodString(String text){
        int[] LPS = deriveLPSArray(text);
        int lps = LPS[LPS.length-1];
        return text.length() - lps;
    }

    // TC: O(N+N), SC:O(N)
    public int countPeriodObservationBased(String text){
        int[] LPS = deriveLPSArray(text);
        int elem = LPS[LPS.length-1];
        int count = 0;
        int i = LPS.length - 1;
        while (elem > 1){
            i--;
            if(i >= 0){
                elem = LPS[i];
            }else {
                break;
            }
        }
        if(i-1 >= 0 && (LPS[i] == LPS[i-1] && LPS[i]==1) || (LPS[i] == 1 && LPS[i-1] == 0)){
            i--;
        }
        while (i >= 0){
            count++;
            i--;
        }
        return count;
    }

    public static int[] deriveLPSArray(String text){
        char[] word = text.toCharArray();
        int n = word.length;
        int LPS[] = new int[n];
        LPS[0] = 0;
        for(int i=1;i < n;i++){
            int x = LPS[i-1];
            while (word[i]!=word[x]){
                if(x==0){ x=-1;break;}
                x = LPS[x-1];
            }
            LPS[i]=x+1;
        }
        return LPS;
    }
}
