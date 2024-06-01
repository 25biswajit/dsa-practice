package dsa.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

class Main{
    LRUCache_LC lru = null;
    @Test
    public void test1(){
        Integer[] expected = {null,null,null,1,null,-1,null,-1,3,4};
        List<String> instructions = Arrays.asList("LRUCache","put","put","get","put","get","put","get","get","get");
        List<String> values = Arrays.asList("2","1-1","2-2","1","3-3","2","4-4","1","3","4");
        Integer[] result = executeLRUCache(instructions, values);
        Assertions.assertArrayEquals(expected, result);
    }
    @Test
    public void test2(){
        Integer[] expected = {null,-1,null,-1,null,null,2,6};
        List<String> instructions = Arrays.asList("LRUCache","get","put","get","put","put","get","get");
        List<String> values = Arrays.asList("2","2","2-6","1","1-5","1-2","1","2");
        Integer[] result = executeLRUCache(instructions, values);
        Assertions.assertArrayEquals(expected, result);
    }

    private Integer[] executeLRUCache(List<String> instructions, List<String> values) {
        Integer[] result = new Integer[instructions.size()];
        for(int i = 0; i < instructions.size(); i++){
            String instruction = instructions.get(i);
            String value = values.get(i);
            if(instruction.equals("LRUCache")){
                lru = new LRUCache_LC(Integer.parseInt(value));
            }
            else if(instruction.equals("put")){
                int k = Integer.parseInt(value.split("-")[0]);
                int v = Integer.parseInt(value.split("-")[1]);
                lru.put(k,v);
                System.out.println(i + "::::" +lru);
            }else {
                result[i] = lru.get(Integer.parseInt(value));
                System.out.println(i + "::::" +lru);
            }
        }
        return result;
    }
}

public class LRUCache_LC {

    @Override
    public String toString() {
        return "{" +
                ", front=" + (front.next != null ? front.next.data.v +" @"+front.next.hashCode() : -1) +
                ", back=" + (back.prev != null ? back.prev.data.v+" @"+back.prev.hashCode() : -1) +
                '}';
    }

    Map<Integer,Node> map;
    Node front;
    Node back;
    int capacity;

    public LRUCache_LC(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        front = new Node(-1, -1);
        back = new Node(-1, -1);
        front.next = back;
        back.prev = front;
    }

    public int get(int key) {
        int val = -1;
        if(map.containsKey(key)){
            Node node = map.get(key);
            deleteNode(node);
            addAtBack(back, node);
            val = node.data.v;
        }
        return val;

    }

    private void addAtBack(Node back, Node nodeToBeAdded) {
        nodeToBeAdded.prev = back.prev;
        nodeToBeAdded.next = back;
        back.prev.next = nodeToBeAdded;
        back.prev = nodeToBeAdded;
        map.put(nodeToBeAdded.data.k, nodeToBeAdded);
    }

    private void deleteNode(Node node) {
        map.remove(node.data.k);
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            deleteNode(node);
        }
        if (map.size() == capacity) {
            deleteNode(front.next);
        }
        Node newEntry = new Node(key, value);
        addAtBack(back, newEntry);
    }
}

class Data{
    int k;
    int v;
    Data(int k, int v){
        this.k = k;
        this.v = v;
    }
}

class Node {
    Node prev;
    Node next;
    Data data;

    Node(int k, int v){
        this.data = new Data(k,v);
        this.prev = null;
        this.next = null;
    }
}
