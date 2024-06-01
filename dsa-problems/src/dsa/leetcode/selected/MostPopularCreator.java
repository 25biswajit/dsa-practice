package dsa.leetcode.selected;

import org.junit.jupiter.api.Test;

import java.util.*;

public class MostPopularCreator {

    @Test
    public void test1(){
        String[] c = {"a","a"};
        String[] ids = {"aa", "a"};
        int[] vw = {5,5};
        System.out.println( mostPopularCreator(c,ids,vw) );
    }

    @Test
    public void test2(){
        String[] c = {"a","a"};
        String[] ids = {"aa", "a"};
        int[] vw = {Integer.MAX_VALUE,Integer.MAX_VALUE};
        System.out.println( mostPopularCreator(c,ids,vw) );
    }

    public List<List<String>> mostPopularCreator(String[] creators, String[] ids, int[] views) {
        List<List<String>> list = new ArrayList<>();
        int n = creators.length;
        long max = 0L;

        Map<String , Creator> map = new HashMap<>();

        for(int i = 0; i < n; i++){
            String key = creators[i];
            Creator e;
            if(map.containsKey(creators[i])){
                e = map.get(key);
                e.total += views[i];
                if(views[i] > e.vw || (views[i] == e.vw && ids[i].compareTo(e.id) < 0)){
                    e.vw = views[i];
                    e.id = ids[i];
                }
            }
            else{
                e = new Creator(ids[i], views[i], views[i]);
                map.put(key, e);
            }
            max = Math.max(max, e.total);
        }

        for(String k : map.keySet()){
            Creator e = map.get(k);
            if(e.total == max){
                List<String> ans = new ArrayList<>();
                ans.add(k);
                ans.add(e.id);
                list.add(ans);
            }
        }

        return list;
    }
}

class Creator{
    String id;
    int vw;
    long total;

    Creator(String id,int vw,int total){
        this.id = id;
        this.vw = vw;
        this.total = total;
    }

    @Override
    public String toString() {
        return "Creator{" +
                "id='" + id + '\'' +
                ", vw=" + vw +
                ", total=" + total +
                '}';
    }
}