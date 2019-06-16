package pl.sda.dublin;

import java.io.File;
import java.io.IOException;

public class Program {

    private static final String PATH_TO_FILE
            = "/home/siwipi/sda/sda-javadub1-intermediateprogramming-zajecia/test.txt";

    public static void main(String[] args) {
        File mojPlik = new File(PATH_TO_FILE);

        if (mojPlik.exists()) {
            System.out.println("Plik istnieje");
        }


        File nowyPlik = new File("tekst.txt");

        if (nowyPlik.exists()) {
            System.out.println("Plik juz istnieje");
        } else {

            try {
                nowyPlik.createNewFile();
            } catch (IOException e) {
                System.out.println("Nie udalo sie utworzyc pliku - " + e.getMessage());
            }
        }

        // utworzyc nowy plik o nazwie "tekst.txt"
        // podaj ściezke wzgledna - "tekst.txt"
        // sprawdz czy plik istnieje - jeśli nie to utwórz nowy plik
        // sprawdz gdzie utworzy sie nowy plik


    }
}
