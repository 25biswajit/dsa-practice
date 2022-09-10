package dsa.basic.practice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LinkedListDeleteInset {
    ListNode head = null;

    @Test
    public void test1() {
        List<List<Integer>> inputs = new ArrayList<>();
        inputs.add(Arrays.asList(1, 13, -1)); // 13
        inputs.add(Arrays.asList(3, 0, -1)); // None
        inputs.add(Arrays.asList(3, 1, -1)); // None
        inputs.add(Arrays.asList(2, 15, 0)); // 15
        inputs.add(Arrays.asList(3, 0, -1)); // None
        inputs.add(Arrays.asList(1, 12, -1)); // 12
        inputs.add(Arrays.asList(3, 0, -1)); // None
        inputs.add(Arrays.asList(1, 19, -1)); // 19
        inputs.add(Arrays.asList(1, 13, -1)); // 19 13
        inputs.add(Arrays.asList(3, 0, -1)); // 13
        inputs.add(Arrays.asList(0, 12, -1)); // 12 13
        inputs.add(Arrays.asList(1, 13, -1)); // 12 13 13
        inputs.add(Arrays.asList(3, 2, -1)); // 12 13
        solve(inputs);
        Assertions.assertEquals("12,13", print_ll());
    }

    @Test
    public void test2() {
        List<List<Integer>> inputs = new ArrayList<>();
        inputs.add(Arrays.asList(2, 18, 0));//18
        inputs.add(Arrays.asList(2, 5, 1));//18,5
        inputs.add(Arrays.asList(2, 8, 0));//8,18,5
        inputs.add(Arrays.asList(1, 7, -1));//8,18,5,7
        inputs.add(Arrays.asList(1, 5, -1));//8,18,5,7,5
        solve(inputs);//8 -> 18 -> 5 -> 7 -> 5
        Assertions.assertEquals("8,18,5,7,5", print_ll());
    }

    @Test
    public void test3() {
        List<List<Integer>> inputs = new ArrayList<>();
        inputs.add(Arrays.asList(2, 30, 0));
        inputs.add(Arrays.asList(1, 40, -1));
        inputs.add(Arrays.asList(2, 20, 0));
        inputs.add(Arrays.asList(0, 10, -1));
        inputs.add(Arrays.asList(2, 25, 2));
        inputs.add(Arrays.asList(3, 5, -1));
        inputs.add(Arrays.asList(3, 2, -1));
        inputs.add(Arrays.asList(2, 5, 0));
        inputs.add(Arrays.asList(2, 27, 3));
        solve(inputs);
        Assertions.assertEquals("5,10,20,27,30,40", print_ll());
    }

    public ListNode solve(List<List<Integer>> A) {
        head = null;
        for (List<Integer> list : A) {
            int choice = list.get(0);
            switch (choice) {
                case 0:  head = insertAtFirst(list.get(1)); break;
                case 1:  head = insertAtLast(list.get(1)); break;
                case 2:  head = insert(list.get(1), list.get(2)); break;
                case 3:  head = delete(list.get(1)); break;
            }
        }
        return head;
    }

    public ListNode insertAtFirst(int value) {
        ListNode newNode = new ListNode(value);
        newNode.next = head;
        head = newNode;
        return head;
    }

    public ListNode deleteFirst() {
        if (head == null) return null;
        head = head.next;
        return head;
    }

    public ListNode delete(int index) {
        if (index == 0) return deleteFirst();
        ListNode temp = head;
        for (int i = 0; i < index-1 && temp != null; i++) {
            temp = temp.next;
        }
        // While traversing we reached at the end, so nothing to delete
        if (temp == null || temp.next == null) return head;
        ListNode last = temp.next.next;
        temp.next = last;
        return head;
    }

    public ListNode insertAtLast(int value) {
        return insert(value, Integer.MAX_VALUE);
    }

    public ListNode insert(int value, int index) {
        if (head == null || index == 0) return insertAtFirst(value);
        ListNode newNode = new ListNode(value);
        ListNode temp = head;
        for (int i = 0; i < index-1 && temp.next != null; i++) {
            temp = temp.next;
        }
        // While traversing we reached at the end
        if (temp.next == null) {
            temp.next = newNode;
        }else{
            newNode.next = temp.next;
            temp.next = newNode;
        }
        return head;
    }

    public String print_ll() {
        ListNode temp = head;
        String result = "";
        while (temp != null) {
            result += "," + temp.val;
            temp = temp.next;
        }
        if(result.length() > 1)
            result = result.substring(1);
        else
            result = "None";

        return result;
    }

}

class ListNode {
    public int val;
    public ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

