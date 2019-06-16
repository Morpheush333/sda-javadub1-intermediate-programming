package pl.sda.dublin.comparators;

// comparator do porównywania długości stringa
// im dłuższy napis tym większy

import java.util.Comparator;

public class StringLengthComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        // o1 -> "ala" -> 3 litery
        // o2 -> "tomasz" -> 6 liter

        // 3 - 6 = -3
        return o1.length() - o2.length();
    }
}
