package dsa.arrays;


/*Problem :- Check if the array is balanced array or not.
        Description:- A balanced array is defined to be an array where for every value n in the array, -n also is in the array.

Example 1:- {-2, 3, 2, -3} is a balanced array.
Example 2:- {1,1,-1,-1} is a balanced array.
Example 3:- {1,1,-1} is a NOT balanced array.
        Example 4:- {-2, 3, 2, -3, 0, 5,-5} is a balanced array.
Example 5:- {1, 2, -3} is NOT a balanced array.
        Example 6:- {-3,-2, -3, -2, 4, 1, 4, 1 , 3, 2, -4, -1} is NOT a balanced array

Note:-
Zeroes can be ignored.

[ 0 , 0 , 0] - X

There can be duplicates in the array. Every duplicate n needs to have -n
*/

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class Coding_Test {
    @Test
    public void test1(){
        int[] a1 = {2,-2,3,-3};
        int[] a2 = {-2, 3, 2, -3, 0, 5,-5};
        Assertions.assertTrue(isBalanced(a1));
        Assertions.assertTrue(isBalanced(a2));
    }
    @Test
    public void test2(){
        int[] a1 = {1,1,-1};
        int[] a2 = {-3,-2, -3, -2, 4, 1, 4, 1 , 3, 2, -4, -1};
        Assertions.assertFalse(isBalanced(a1));
        Assertions.assertFalse(isBalanced(a2));
    }
    @Test
    public void test3(){
        int[] a = {1,1,-1,-1};
        Assertions.assertTrue(isBalanced(a));
    }

    public boolean isBalanced(int[] a){
        Map<Integer, Integer> map = new HashMap<>();

        for(int i : a){
            if(i < 0){
                if(map.containsKey(i)){
                    map.put(i, map.get(i)+1);
                }else{
                    map.put(i, 1);
                }
            }
        }

        for(int i : a){
            if(i > 0){
                int key = -i;
                if(map.containsKey(key)){
                    int value = map.get(key);
                    map.put(key, value-1);
                    if(map.get(key) <= 0){
                        map.remove(key);
                    }
                }else{
                    return false;
                }
            }
        }

        return true;
    }


}
