package dsa.advance.day65.bst;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CheckBSTWithOneChild {
    @Test
    public void test1(){
        int[] array = {4, 10, 5, 8};
        Assertions.assertEquals("YES", checkBST(array));
    }
    @Test
    public void test2(){
        int[] array = {1, 5, 6, 4};
        Assertions.assertEquals("NO", checkBST(array));
    }

    public String checkBST(int[] array){
        if(array == null || array.length == 0) return "NO";
        int low = Integer.MIN_VALUE, high = Integer.MAX_VALUE;
        if(!(low < array[0] && array[0] < high)){
            return "NO";
        }
        for(int i = 1; i < array.length; i++){
            int parent_data = array[i-1];
            int child_data = array[i];
            // right subtree
            if(parent_data+1 <= child_data && child_data <= high){
                low = parent_data+1;
            }
            else if(low <= child_data && child_data <= parent_data-1){
                high = parent_data-1;
            }
            else {
                return "NO";
            }
        }
        return "YES";
    }
}
