package dsa.basic.day23.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReverseSentenceWordByWord {
    @Test
    public void test(){
        String sentence = "____This____Is____My____Book____";
        System.out.println(sentence.substring(4,8));
        System.out.println(sentence.substring(12,14));
        System.out.println(sentence.substring(18,20));
        System.out.println(sentence.substring(24,28));

        Assertions.assertEquals("Book_My_Is_This" , solve(sentence));

        sentence = "This____Is____My____Book";
        Assertions.assertEquals("Book_My_Is_This" , solve(sentence));

    }

    public String solve(String sentence) {
        String space = "_";
        String[] sentenceArray = sentence.split("");
        String reversedSentence = null;
        int lastIndex = -1;
        int firstIndex = -1;
        for(int i = sentenceArray.length-1; i>=0; i--){
            if(!sentenceArray[i].equals(space)){
                if(lastIndex == -1){
                    lastIndex = i;
                    firstIndex = i;
                }else if(lastIndex > 0){
                    firstIndex = i;
                }
            }else if(sentenceArray[i].equals(space) && firstIndex!= -1){
                //System.out.println(String.format("FirstIndex %d, LastIndex %d, Reversed %s, Substring %s", firstIndex,lastIndex, reversedSentence, sentence.substring(firstIndex, lastIndex+1)));
                if(reversedSentence == null){
                    reversedSentence = sentence.substring(firstIndex, lastIndex+1);
                }else{
                    reversedSentence += space + sentence.substring(firstIndex, lastIndex+1);
                }
                lastIndex = -1;
                firstIndex = -1;
            }
        }

        if (firstIndex != -1){
            reversedSentence += "_" + sentence.substring(firstIndex, lastIndex+1);
        }

        return reversedSentence;
    }
}
