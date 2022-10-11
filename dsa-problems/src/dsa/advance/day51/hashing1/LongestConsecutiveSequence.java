package dsa.advance.day51.hashing1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

/*Given an unsorted integer array A of size N.
Find the length of the longest set of consecutive elements from array A.
A = [100, 4, 200, 1, 3, 2]
The set of consecutive elements will be [1, 2, 3, 4]. Ans = 4
*/
public class LongestConsecutiveSequence {
    @Test
    public void test1(){
        int[] array = {100, 4, 200, 1, 3, 2};
        Assertions.assertEquals(4, longestConsecutiveSubSeqLength(array));
    }

    // TC: O(N) , SC: O(N)
    public int longestConsecutiveSubSeqLength(final int[] array) {
        int maxLength = 0;
        HashSet<Integer> set = new HashSet<>( Arrays.stream(array).boxed().collect(Collectors.toList()) );
        for(int i = 0; i < array.length; i++){
            int element = array[i];
            int count = 0;
            if(!set.contains(element - 1)){
                count++;
                element = element + 1;
                while (set.contains(element)){
                    count++;
                    element = element + 1;
                }
                maxLength = Integer.max(count, maxLength);
            }
        }
        return maxLength;
    }

}
