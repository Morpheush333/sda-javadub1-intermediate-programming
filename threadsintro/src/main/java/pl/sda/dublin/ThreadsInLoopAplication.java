package pl.sda.dublin;

public class ThreadsInLoopAplication {

    static int i = 0;

    public static void main(String[] args) {
        // tworzenie 10 watkow
        // kazdy watek ma za zadanie wypisac numer iteracji
        // wersja z poprawnym rozwiazaniem
        // utworzenie zmiennej lokalej j w petli
        // usun zmienna j i porownaj efekty

        // wersja 1: (błędna) - data race
        /*for (i = 0; i < 10; i++) {

            Thread watek = new Thread(() -> {
                printMessage(i);
            });
            watek.setName("Watek - " + i);
            watek.start();

        }*/

        // wersja 2: rozwiazanie
        for (i = 0; i < 10; i++) {
            int j = i;
            Thread watek = new Thread(() -> {
                printMessage(j);
            });
            watek.setName("Watek - " + j);
            watek.start();

        }
    }

    static void printMessage(int i) {
        System.out.println("Jestem wątek: " + Thread.currentThread().getName() + " Moj numer to: " + i);
    }
}
