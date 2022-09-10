package dsa.basic.practice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TripletCountDay16 {

    @Test
    public void test(){
        int[] array = {4,1,2,6,9,7};
        Assertions.assertEquals(9,  getCountTriplet(array));
    }


    public int getCountTriplet(int[] arr){
        int ans = 0;
        for(int i=1;i<arr.length-1;i++){
            int l = getLeftCountLessThanMiddle(arr, i, arr[i]);
            int r = getRightCountLessThanMiddle(arr, i, arr[i]);
            ans = ans + l*r;
        }
        return ans;
    }

    private int getRightCountLessThanMiddle(int[] arr, int i, int middle) {
        int c = 0;
        i--;
        while(i>=0){
            if(arr[i] < middle){
                c++;
            }
            i--;
        }
        return c;
    }

    private int getLeftCountLessThanMiddle(int[] arr, int i, int middle) {
        int c = 0;
        i++;
        while(i<arr.length){
            if(arr[i] > middle){
                c++;
            }
            i++;
        }
        return c;
    }

}
