package com.dsa.practice.recursion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class GetAllSubSequence {
    @Test
    void test() {
        GetAllSubSequence algo = new GetAllSubSequence();
        List<String> expectedList = Arrays.asList("ABC","BC","AC","C","AB","B","A");
        List<String> actualList = algo.main("ABC");
        Assertions.assertEquals(expectedList, actualList);
    }

    List<String> main(String word){
        List<String> list = getAllSubSequenceList(word);
        Iterator<String> iterator = list.listIterator();
        while(iterator.hasNext()){
            if(iterator.next().equals("")){
                iterator.remove();
                break;
            }
        }
        return list;
    }

    List<String> getAllSubSequenceList(String word){
        if(word.length() == 0){
            List<String> blankList = new ArrayList<>();
            blankList.add("");
            return blankList;
        }
        char ch = word.charAt(0);
        word = word.substring(1);
        List<String> list = getAllSubSequenceList(word);
        List<String> newList = new ArrayList<>();
        for(String s : list){
            newList.add(ch+""+s);
            newList.add(s);
        }
        return newList;
    }
}

