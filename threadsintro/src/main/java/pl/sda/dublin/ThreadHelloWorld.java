package pl.sda.dublin;

public class ThreadHelloWorld {
    public static void main(String[] args) throws InterruptedException {


        System.out.println("Uruchamiam nowy watek");
        Thread myThread = new Thread(() -> {
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {

            }
            System.out.println("Hello from another thread");
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {

            }
        });


        myThread.start();
        myThread.join();

        System.out.println("Hello from main thread");


    }
}
