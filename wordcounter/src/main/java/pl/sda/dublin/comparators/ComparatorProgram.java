package pl.sda.dublin.comparators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ComparatorProgram {

    public static void main(String[] args) {


        List<String> lst = Arrays.asList("Ala");

        Comparator<String> cmp = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return 0;
            }
        };


        List<String> imiona = Arrays.asList("ala", "janek", "tomasz", "adam", "amadeusz");

        // sortowanie alfabetyczne (słownikowe)
//        Collections.sort(imiona);

        // sortowanie imion po długosci

        Collections.sort(imiona, (o1, o2) -> 0);


        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);


        Collections.sort(integers, (o1, o2) -> o1.compareTo(o2));


        // impreratywnie

        List<String> imionaA = new ArrayList<>();
        for (String imie : imiona) {
            if (imie.startsWith("a")) {
                imionaA.add(imie);
            }
        }

        System.out.println("Imiona.... ");


        // funkcyjnie - lambda


        List<String> imionaNaA = imiona.stream()
                .filter(imie -> imie.startsWith("a"))
                .collect(Collectors.toList());

        Predicate<String> imionaDluzeNiz4 = s1 -> s1.length() > 4;

        System.out.println(imionaDluzeNiz4.test("Amadeusz"));


//        Arrays.asList("ala", "janek", "tomasz", "adam", "amadeusz");

        List<String> mojaListaWynikowa = imiona.stream()
                .filter(s1 -> s1.length() > 4) // boolean test(T t);
                .map(imie -> imie.toUpperCase()) // R apply(T t);
                .collect(Collectors.toList());





    }


    boolean test(String alamakota) {
        return alamakota.startsWith("a");
    }
}
