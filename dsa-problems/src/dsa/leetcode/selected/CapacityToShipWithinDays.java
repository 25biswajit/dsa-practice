package dsa.leetcode.selected;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CapacityToShipWithinDays {

    @Test
    public void test1(){
        int a[] = {1,2,3,4,5,6,7,8,9,10};
        Assertions.assertEquals(15, shipWithinDays(a,5));
    }

    @Test
    public void test2(){
        int a[] = {1,2,3,1,1};
        Assertions.assertEquals(3, shipWithinDays(a,4));
    }

    @Test
    public void test3(){
        int a[] = {3,5,1,7,8,2,5,3,10,1,4,7,5,4,6};
        Assertions.assertEquals(22, shipWithinDays(a,4));
        System.out.println(daysRequired(a,22));
    }

    public int shipWithinDays(int[] weights, int days) {
        int low = 0, high = 0, ans = 0;
        for(int w : weights){
            high += w;
            low = Integer.max(w, low);
        }
        while(low <= high){
            int mid = (low + high) / 2;
            int count = daysRequired(weights, mid);
            if(count <= days){
                ans = mid;
                high = mid -1;
            }else{
                low = mid + 1;
            }
        }
        return ans;
    }

    public int daysRequired(int[] weights, int ans){
        int sum = 0,days=1;
        for(int w : weights){
            sum += w;
            if(sum > ans){
                days++;
                sum = w;
            }
        }
        return days;
    }
}
