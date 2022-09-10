package dsa.advance.day36.bitman2;

import dsa.utils.ArrayUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

//Maximum Satisfaction
public class MaximumAND {
    @Test
    public void test(){
        int[] arr = {26,13,23,28,27,7,25};
        Assertions.assertEquals(26, getMaxAndOfPair(arr,2));
    }

    public int getMaxAndOfPair(int[] array, int pairNumber) {
        int answer = 0;
        for(int i = 31; i >= 0; i--){
            ArrayUtils.printArray(array);
            int countSetBit = getCountOfSetBitAtIth(array, i);
            if(countSetBit >= pairNumber){
                answer = answer + (1<<i);
                array = removeElementsIthBitZero(array,i);
            }
        }
        return answer;
    }

    private int[] removeElementsIthBitZero(int[] array, int i) {
        return Arrays.stream(array).filter(n -> isIthBitSet(n,i)).toArray();
    }

    private int getCountOfSetBitAtIth(int[] array, int i) {
        return (int) Arrays.stream(array).filter(n -> isIthBitSet(n,i)).count();
    }

    private boolean isIthBitSet(int num, int i) {
        return ((num >> i) & 1 ) == 1;
    }
}
