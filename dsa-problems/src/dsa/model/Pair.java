package dsa.model;

import java.util.Comparator;
import java.util.Objects;

public class Pair{
    int first;
    int second;

    public Pair(int first,int second){
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
        if (!(o instanceof Pair)) return false;
        Pair pair = (Pair) o;
        return first == pair.first && second == pair.second;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public String toString() {
        return "{" + first + "," + second +'}';
    }

    public static Comparator<Pair> getComparatorFirst(){
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


