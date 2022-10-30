package dsa.advance.day46.binarySearch2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SpecialInteger {
    @Test
    public void test1(){
        int[] array = {5, 17, 100, 11};int B = 130;
        Assertions.assertEquals(3, maxLengthSubArraySumGreaterThanB(array,B));
    }
    @Test
    public void test2(){
        int[] array = {3,2,5,4,6,3,7,2};int B = 20;
        Assertions.assertEquals(4, maxLengthSubArraySumGreaterThanB(array,B));
    }

    public int maxLengthSubArraySumGreaterThanB(int[] array, int B){
        int low = 0, high = array.length;
        int maxK = 0;
        while (low <= high){
            int mid = (low + high)/2;
            if( isSubArrayPresentGreaterThanB(array, mid, B) ){
                high = mid - 1;
            }else {
                maxK = mid;
                low = mid + 1;
            }
        }
        return maxK;
    }

    private boolean isSubArrayPresentGreaterThanB(int[] array, int mid, int B) {
        long sum = 0;
        for(int i = 0; i< mid; i++){
            sum += array[i];
        }
        if(sum > B){
            return true;
        }
        int p1 = 1, p2 = mid;
        while (p2 < array.length){
            sum += array[p2] - array[p1-1];
            if(sum > B){
                return true;
            }
            p1++;
            p2++;
        }
        return false;
    }
}
