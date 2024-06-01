package dsa.model;

import java.util.Comparator;
import java.util.Objects;

public class PairInt {
    int first;
    int second;

    public PairInt(int first, int second){
        this.first = first;
        this.second = second;
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PairInt)) return false;
        PairInt pairInt = (PairInt) o;
        return first == pairInt.first && second == pairInt.second;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public String toString() {
        return "{" + first + "," + second +'}';
    }

    public static Comparator<PairInt> getComparatorFirst(){
        return (o1, o2) -> {
            if(o1.getFirst() < o2.getFirst()){
                return -1;
            }
            else if(o1.getFirst() == o2.getFirst()){
                return 0;
            }
            else {
                return 1;
            }
        };
    }
}


