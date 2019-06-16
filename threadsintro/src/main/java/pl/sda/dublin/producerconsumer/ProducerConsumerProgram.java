package pl.sda.dublin.producerconsumer;

public class ProducerConsumerProgram {
    public static void main(String[] args) throws InterruptedException {

        // tworzymy nowy bufor
        Buffer buffer = new Buffer();

        // tworzymy nowy wątek producenta
        // i wstrzykujemy bufor przez Konstruktor
        ProducerThread producerThread = new ProducerThread(buffer);
        ConsumerThread consumerThread = new ConsumerThread(buffer);

        producerThread.start();
        consumerThread.start();

        producerThread.join();
        consumerThread.join();
    }
}
