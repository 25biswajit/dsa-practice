package dsa.advance.day50.TwoPointer;


import dsa.utils.ArrayUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/*
An array with N objects colored red, white, or blue, sort them so that objects of the same color are adjacent,
with the colors in the order red, white, and blue.
We will use the integers 0, 1, and 2 to represent red, white, and blue, respectively.
Note: Using the library sort function is not allowed.

//https://www.youtube.com/watch?v=9pdkbqGwUhs - Practical Visualisation with Cards
//https://www.youtube.com/watch?v=MbV26HWqxrs - Pepcoding
*/

public class DutchFlag {
    @Test
    public void test(){
        int[] colors = {0,1,2,2,0,1,0,2,1,0,1,2,0,1,2};
        //0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2
        int[] colors_bruteForce_result = sortColors_bruteForce(colors);
        int[] colors_3pointer_result = sortColors_3pointer(colors);
        Assertions.assertArrayEquals(colors_bruteForce_result, colors_3pointer_result);
    }
    //TC: O(N), SC:O(N)
    public int[] sortColors_bruteForce(int[] colors){
        int zeroCount = 0, oneCount = 0, twoCount = 0;
        for(int i = 0;i< colors.length;i++){
            if(colors[i]==0){ zeroCount++;}
            if(colors[i]==1){ oneCount++;}
            if(colors[i]==2){ twoCount++;}
        }
        List<Integer> list = new ArrayList<>();
        while (zeroCount > 0){ list.add(0); zeroCount--;}
        while (oneCount > 0){ list.add(1); oneCount--; }
        while (twoCount > 0){ list.add(2);twoCount--; }
        System.out.println(list);
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    //TC: O(N), SC:O(1)
    public int[] sortColors_3pointer(int[] colors){
        int low = 0;
        int unknown = 0; // unknown
        int high = colors.length-1;
        while (unknown <= high){
            if(colors[unknown] == 0){
                swap(colors, unknown, low);
                low++;
                unknown++;
            }
            else if(colors[unknown] == 1){
                unknown++;
            }
            else if(colors[unknown] == 2){
                swap(colors, unknown, high);
                high--;
            }
        }
        ArrayUtils.printArray(colors);
        return colors;
    }

    private void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
}

/*There are multiple approaches possible here. We need to make sure we do not allocate extra memory.
Approach 1:
Count the number of red, white, and blue balls.
Then, in another pass, set the initial redCount number of cells as 0, next whiteCount cell as 1, and next blueCount cells as 2.
Requires two passes of the array.

Approach 2:
Swap the 0s to the start of the array maintaining a pointer, and 2s to the end of the array.
1s will automatically be in their right position.

0,0,0,1,1,X,X,X,X,X,X,2,2
Zero's Area is defined i.e. [ 0, low-1 ]
One's Area is defined i.e. [ low, unknown-1 ]
Unknown Area is defined i.e. [ unknown, high ]
Two's Area is defined i.e. [ high+1, N ]

0,0,0,1[low],1, X[Unknown] ,X,X,X,X, X[High] ,2,2

Array[unknown] == 0 => swap(low,unknown)
Array[unknown] == 2 => swap(high,unknown)
Array[unknown] == 1 => Do Nothing, unknown++
*/
