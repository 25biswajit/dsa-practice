package dsa.advance.day36.bitman2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OddEvenSubseq {
    @Test
    public void test(){
        int[] array = {1,2,4,6,8,9,11,12};
        int maxLengthActual = getMaxLengthSubSeqOddEvenAlternatingSubSeq(array);
        Assertions.assertEquals(4, maxLengthActual);
        Assertions.assertEquals(4, solve_optimised(array));
        Assertions.assertEquals(4, solve_optimised_simple(array));
    }

    public int getMaxLengthSubSeqOddEvenAlternatingSubSeq(int[] array){
        boolean needEven = array[0] % 2 != 0;
        int length = 1;
        for(int i = 1; i < array.length ; i++){
            if(needEven && array[i]%2==0){
                needEven = false;
                length++;
            }
            else if(!needEven && array[i]%2 != 0){
                needEven = true;
                length++;
            }
        }
        return length;
    }

    public int solve_optimised(int[] array){
        int length1 = 0, length2 = 0;
        int x = 1, y = 0;
        for(int i = 0; i < array.length ; i++){
            int current = array[i] & 1;
            if(current == x){
                length1++;
                x = x ^ 1;
            }
            if(current == y){
                length2++;
                y = y ^ 1;
            }
        }
        return Math.max(length1,length2);
    }

    public int solve_optimised_simple(int[] array){
        int length1 = 0, length2 = 0;
        boolean needOdd = true, needEven = false;
        for(int i = 0; i < array.length ; i++){
            boolean isOdd = array[i] % 2 != 0;
            if(isOdd == needOdd){
                length1++;
                needOdd = !needOdd;
            }
            if(isOdd == needEven){
                length2++;
                needEven = !needEven;
            }
        }
        return Math.max(length1,length2);
    }
}

/*
If the current number of the subsequence is even, then for maximum length, it is best to choose the closest odd number as the next number.
The same can be done if the current number is odd; take the next closest even number.

getMaxLengthSubSeqOddEvenAlternatingSubSeq :
Idea :
if A[0] is Even, then SubSeq will start with Even, next element of subsequence would be Odd, so needEven = false
if A[0] is Odd, then SubSeq will start with Odd, next element of subsequence would be Even, so needEven = true
Iterating over the loop till last, Apply same logic ( needEven true/false ) and increment the length.

solve_optimised :
Idea :
Find two odd-even subsequences, first whose first element is odd and the other whose first element is even, return the maximum of these two subsequences.
*/
