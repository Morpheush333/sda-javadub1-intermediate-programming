package pl.sda.dublin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class WordCounterApplication {
    public static void main(String[] args) {

        // wyswietlic na konsoli wszyskie linie z pliku

        List<String> strings = null;
        try {
            strings = Files.readAllLines(Paths.get("loremasdsad-ipsum.txt"));


            // rozbic listę linii na listę słów

            List<String> words = new ArrayList<>();
            for (String line : strings) {
                String[] lineSplit = line.split("\\s+");
                List<String> lst = Arrays.asList(lineSplit).stream()
                        .map(s -> s.toLowerCase())
                        .map(s -> s.replace(",", ""))
                        .map(s -> s.replace(".", ""))
                        .map(s -> s.replace(";", ""))
                        .collect(Collectors.toList());


                words.addAll(lst);
            }
            // z listy słów zliczać słowa do mapy
            Map<String, Integer> wordMap = new TreeMap<>();

            for (String word : words) {
                // jesli dane slowo wystepuje w slowniku
                // to pobieramy jego aktualna wartosc
                // i zwiekszamy o 1
                if (wordMap.containsKey(word)) {
                    // pobierz aktualna liczbe wystapien
                    Integer currentCount = wordMap.get(word);
                    // wstaw do mapy nowa wartosc
                    wordMap.put(word, ++currentCount);
                } else {
                    wordMap.put(word, 1);
                }
            }

            // wypisz zawartosc slownika na konsole
            // "slowo" -> 5

            System.out.println("Word counter");
            for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
                System.out.println(" [ " + entry.getKey() + " ] " + "  ==>  " + entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
