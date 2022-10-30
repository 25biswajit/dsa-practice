package dsa.advance.day50.TwoPointer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContainerWithMostWater {
    @Test
    public void test1(){
        int[] array = {3,5,4,7,3,6,5,4,1,2};
        Assertions.assertEquals(25, maxAreaWater(array));
    }

    public int maxAreaWater(int[] array) {
        int left = 0, right = array.length - 1, water = 0;
        while(left < right){
            int wallLeft = array[left];
            int wallRight = array[right];
            int height = Integer.min(wallLeft, wallRight);
            int width = right - left;
            water = Integer.max( water , height * width);
            if(wallLeft < wallRight){
                left++;
            }else {
                right--;
            }
        }
        return water;
    }
}
