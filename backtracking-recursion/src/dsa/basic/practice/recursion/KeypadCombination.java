package dsa.basic.practice.recursion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class KeypadCombination {
    @Test
    public void test(){
        Map<String, List<String>> dictionary = new HashMap<>();
        dictionary.put("1", new ArrayList<>(Arrays.asList("a","b","c")));
        dictionary.put("2", new ArrayList<>(Arrays.asList("d","e","f")));
        dictionary.put("3", new ArrayList<>(Arrays.asList("g","h","i")));
        dictionary.put("4", new ArrayList<>(Arrays.asList("j","k","l")));
        dictionary.put("5", new ArrayList<>(Arrays.asList("m","n","o")));
        dictionary.put("6", new ArrayList<>(Arrays.asList("p","q","r")));
        dictionary.put("7", new ArrayList<>(Arrays.asList("s","t","u")));
        dictionary.put("8", new ArrayList<>(Arrays.asList("v","w","x")));
        dictionary.put("9", new ArrayList<>(Arrays.asList("y","z")));

        KeypadCombinationAlgorithm algorithm = new KeypadCombinationAlgorithm(dictionary);
        List<String> list = algorithm.main("573");
        Assertions.assertEquals(27, list.size());

        list = algorithm.main("1294");
        Assertions.assertEquals(54, list.size());
    }
}

class KeypadCombinationAlgorithm{
    Map<String, List<String>> dictionary;

    KeypadCombinationAlgorithm(Map<String, List<String>> dictionary){
        this.dictionary = dictionary;
    }

    List<String> main(String input){
        return keypadCombinationAlgorithm(input);
    }

    private List<String> keypadCombinationAlgorithm(String input) {
        if(input.length() == 1){
            return dictionary.get(input);
        }
        String chatAtZero = String.valueOf(input.charAt(0));
        String restString = input.substring(1);
        List<String> derivedCombinations = keypadCombinationAlgorithm(restString);
        List<String> combinationOfZerothChar = dictionary.get(chatAtZero);
        List<String> newDerivedCombination = new ArrayList<>();
        for(String ds : derivedCombinations){
            for (String s : combinationOfZerothChar) {
                newDerivedCombination.add(s + ds);
            }
        }
        return newDerivedCombination;
    }

}
