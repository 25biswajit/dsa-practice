package dsa.basic.day20.modarithmatic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExcelColumnNumber {
    @Test
    public void test(){
        Assertions.assertEquals(27, titleToNumber("AA"));
    }

    public int titleToNumber(String word) {
        char [] arr = word.toCharArray();
        int sum = 0;
        for(int i = 0; i< arr.length; i++){
            sum = sum*26 + (arr[i] - 'A')+1;
            System.out.println(sum);
        }
        return sum;
    }
}
