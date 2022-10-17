package dsa.advance.day53.patternMatch;

import dsa.utils.ArrayUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PeriodOfString {
    @Test
    public void test1(){
        String text = "ababababab";
        Assertions.assertEquals(2, countPeriodString(text));
    }
    @Test
    public void test2(){
        String text = "abbaabba";
        Assertions.assertEquals(4, countPeriodString(text));
    }
    @Test
    public void test3(){
        String text = "abaaba";
        Assertions.assertEquals(3, countPeriodString(text));
    }
    @Test
    public void test4(){
        String text = "abcd";
        Assertions.assertEquals(4, countPeriodString(text));
    }

    public int countPeriodString(String text){
        int[] LPS = HiddenPattern.deriveLPSArray(text);
        ArrayUtils.printArray(LPS);
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
}
