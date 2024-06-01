package dsa.arrays;

/*
k th
ind 2nd highest integer number from unsorted array.
i.e., int[] arr = {7, 10, 4, 3, 20, 15, 20}
Output: 15

N log n - sorting - 20,15,.....

O(N):
max1 : 20
max2 : 15

Quick Select :
7, 10, 4, 3, 20, 15, 20
Avg = 0(n)

P  l    l  l   h/l   h    h
20, 10, 20, 15, 7, 3, 4

*/

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class FindKthHighest {

    @Test
    public void test(){
        int[] a = {7, 10, 4, 3, 20, 15};

        Assertions.assertEquals(15, findKthHighest(a,2));
    }

    private int findKthHighest(int[] a, int k){
        int index = -1;
        int n = a.length;
        int l = 0, h = n - 1;
        while(l <= h){
            index = quickSelect(a, l, h);
            if(index == k -1) break;
            else if( index > k - 1) h = index - 1;
            else l = index + 1;
        }

        return a[index];
    }

    private int quickSelect(int[] a, int l, int h) {
        int p = l;
        l++;
        while(l <= h){
            if(a[p] > a[l] && a[p] < a[h]){
                swap_(a,l,h);
                l++;
                h--;
            }
            if(a[p] <= a[l]){
                l++;
            }
            if(a[p] >= a[h]){
                h--;
            }
        }
        swap_(a, p, h);
        return h;
    }

    private void swap_(int[] a, int l, int h) {
        int  temp = a[l];
        a[l] = a[h];
        a[h] = temp;
    }


}
/*
Write a SQL query to calculate the average salary of employees for each job title.

Employee Table: EMPLOYEE_ID, FIRST_NAME, LAST_NAME, JOB_ID, SALARY
1 , B, S, 1, 1000

Job table : JOB_ID, JOB_TITLE
1 - J1
2 - J2
3 - J3

select avg(e.salary), J.JOB_TITLE
from emp E right join job J
on E.jobid = J.jobid
groupby (J.JOB_TITLE)

*/















