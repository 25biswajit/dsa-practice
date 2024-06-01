package dsa.leetcode.selected;

import dsa.advance.day55.linkedlist1.ListNode;
import dsa.utils.ArrayUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class MergeKSortedLinkedList {

    @Test
    public void testLinkedList(){
        ListNode head1 = ListNode.prepareLinkedList(new int[]{1,3,7,10});
        ListNode head2 = ListNode.prepareLinkedList(new int[]{2,5,8});
        ListNode head3 = ListNode.prepareLinkedList(new int[]{4,6,9});
        ListNode result = mergeKSortedLL(new ListNode[]{head1, head2, head3});
        ListNode expected = ListNode.prepareLinkedList(new int[]{1,2,3,4,5,6,7,8,9,10});
        Assertions.assertTrue(ListNode.compareLinkedList(expected, result));
        System.out.println(ListNode.print(result));
    }

    @Test
    public void testArray(){
        List<Integer> list1 = Arrays.asList(1,3,7,10);
        List<Integer> list2 = Arrays.asList(2,5,8);
        List<Integer> list3 = Arrays.asList(4,6,9);
        List<Integer> result = mergeKSortedArrays(Arrays.asList(list1,list2,list3));
        int[] expected = {1,2,3,4,5,6,7,8,9,10};
        Assertions.assertArrayEquals(expected,ArrayUtils.convertToIntArray(result));
    }

    private ListNode mergeKSortedLL(ListNode[] lists) {
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>((a,b) -> a.val - b.val);
        ListNode head = new ListNode(-1);
        ListNode current = head;
        for(ListNode node : lists){
            if(node!=null){
                priorityQueue.add(node);
            }
        }
        while (!priorityQueue.isEmpty()){
            ListNode nodeMin = priorityQueue.remove();
            current.next = nodeMin;
            current = nodeMin;
            if(nodeMin.next != null){
                priorityQueue.add(nodeMin.next);
            }
        }
        return head.next;
    }

    private List<Integer> mergeKSortedArrays(List<List<Integer>> lists) {
        List<Integer> result = new ArrayList<>();
        PriorityQueue<Pair> priorityQueue = new PriorityQueue<>((a,b) -> a.value - b.value);
        int index;
        for(int i = 0; i < lists.size(); i++){
            if(lists.get(i)!=null && !lists.get(i).isEmpty()){
                Pair pair = new Pair(lists.get(i).get(0), 0, i);
                priorityQueue.add(pair);
            }
        }
        while (!priorityQueue.isEmpty()){
            Pair minPair = priorityQueue.remove();
            result.add(minPair.value);
            index = minPair.index;
            if(++index < lists.get(minPair.listIndex).size()){
                Pair pair = new Pair(lists.get(minPair.listIndex).get(index), index, minPair.listIndex);
                priorityQueue.add(pair);
            }
        }
        return result;
    }
}

class Pair{
    int value;
    int index;
    int listIndex;

    public Pair(int value, int index, int listIndex) {
        this.value = value;
        this.index = index;
        this.listIndex = listIndex;
    }
}
