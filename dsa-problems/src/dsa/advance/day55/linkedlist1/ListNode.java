package dsa.advance.day55.linkedlist1;

import java.util.Comparator;

public class ListNode implements Comparable<ListNode>{
    public int val;
    public ListNode next;

    public ListNode(int val){
        this(val,null);
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode prepareLinkedList(int[] values){
        ListNode node = new ListNode(values[values.length-1], null);
        for(int i = values.length-2; i >= 0; i--){
            node = new ListNode(values[i], node);
        }
        return node;
    }

    public static boolean compareLinkedList(ListNode node1, ListNode node2){
        while (node1!=null && node2!=null){
            if(node1.val != node2.val) return false;
            else {
                node1 = node1.next;
                node2 = node2.next;
            }
        }
        return node1==null && node2==null;
    }

    public static String print(ListNode node){
        String s = "";
        while (node!= null){
            s += ","+node.val;
            node = node.next;
        }
        return s.substring(1);
    }

    @Override
    public int compareTo(ListNode o) {
        return this.val-o.val;
    }
}
