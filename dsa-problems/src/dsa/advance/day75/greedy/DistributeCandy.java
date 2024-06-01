package dsa.advance.day75.greedy;

import dsa.utils.ArrayUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DistributeCandy {
    @Test
    public void test(){
        int[] ratings = {2,6,3,1,10,12,20,5,2};
        Assertions.assertEquals(19, candy(ratings));
    }

    public int candy(int[] ratings) {
        int left[] = new int[ratings.length];
        int right[] = new int[ratings.length];
        left[0] = 1;
        right[ratings.length-1]=1;
        for (int i = 1; i < ratings.length; i++){
            left[i] = ratings[i] > ratings[i-1] ? left[i-1]+1 : 1;
        }
        for (int i = ratings.length-2; i >= 0; i--){
            right[i] = ratings[i] > ratings[i+1] ? right[i+1]+1 : 1;
        }
        int result = 0;
        for (int i = 0; i < ratings.length; i++){
            result += Integer.max(left[i],right[i]);
        }
        return result;
    }
}
