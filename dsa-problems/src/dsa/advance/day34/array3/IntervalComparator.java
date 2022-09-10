package dsa.advance.day34.array3;

import java.util.Comparator;

public class IntervalComparator implements Comparator<Interval> {

    @Override
    public int compare(Interval o1, Interval o2) {
        if (o1.start > o2.start) {
            return 1;
        } else if (o1.start == o2.start) {
            return 0;
        } else {
            return -1;
        }
    }
}
