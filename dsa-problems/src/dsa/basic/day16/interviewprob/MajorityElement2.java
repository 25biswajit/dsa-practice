package dsa.basic.day16.interviewprob;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MajorityElement2 {

    @Test
    public void test(){
        int[] a = {1,3,3,1,3,1};
        //System.out.println( majorityElement(a) );
        int[] b = {3,7,4,7,4,5,7,4};
        System.out.println( majorityElement(b) );
    }

    public List<Integer> majorityElement(int[] v) {
        int n = v.length; //size of the array

        int cnt1 = 0, cnt2 = 0; // counts
        int el1 = Integer.MIN_VALUE; // element 1
        int el2 = Integer.MIN_VALUE; // element 2

        // applying the Extended Boyer Moore's Voting Algorithm:
        for (int i = 0; i < n; i++) {
            if (v[i] == el1) cnt1++;
            else if (v[i] == el2) cnt2++;
            else if (cnt1 == 0) {
                cnt1 = 1;
                el1 = v[i];
            }
            else if (cnt2 == 0) {
                cnt2 = 1;
                el2 = v[i];
            }
            else {
                cnt1--; cnt2--;
            }
        }

        List<Integer> ls = new ArrayList<>(); // list of answers

        // Manually check if the stored elements in
        // el1 and el2 are the majority elements:
        cnt1 = 0; cnt2 = 0;
        for (int i = 0; i < n; i++) {
            if (v[i] == el1) cnt1++;
            if (v[i] == el2) cnt2++;
        }

        int mini = (int)(n / 3) + 1;
        if (cnt1 >= mini) ls.add(el1);
        if (cnt2 >= mini) ls.add(el2);

        // Uncomment the following line
        // if it is told to sort the answer array:
        //Collections.sort(ls); //TC --> O(2*log2) ~ O(1);

        return ls;
    }
}
