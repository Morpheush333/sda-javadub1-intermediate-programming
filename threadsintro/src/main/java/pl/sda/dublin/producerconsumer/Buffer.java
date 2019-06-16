package pl.sda.dublin.producerconsumer;

// cykliczny bufor, w którym producent będzie umieszczał dane
// a konsument pobierał dane
public class Buffer {

    private int[] buffer = new int[10];
    private int producerIndex = 0;
    private int consumerIndex = 0;
    //counter - zmienna do wykrywania, czy bufor pusty, czy pełny
    private int counter = 0;

    // obiekt wykorzystany jako zamek
    private Object lock = new Object();

    public synchronized void insert(int data) {
        while (counter == buffer.length) {
            System.out.println("PRODUCER - Buffer full - going to sleep");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("PRODCUER - Inserting value : "
                + data
                + "at index: "
                + producerIndex
                + ", counter value: "
                + counter);

        buffer[producerIndex] = data;
        producerIndex = (producerIndex + 1) % buffer.length;
        counter++;
        this.notifyAll();
    }


    public synchronized int getData() {
        while (counter == 0) {
            System.out.println("CONSUMER - Buffer empty - going to sleep");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int value = buffer[consumerIndex];


        // alternatywny zapis:
        consumerIndex = (consumerIndex + 1) % buffer.length;
//        if (consumerIndex == buffer.length) {
//            consumerIndex = 0;
//        } else {
//            consumerIndex++;
//        }
        counter--;

        System.out.println("CONSUMER - Consuming value : "
                + value
                + "at index: "
                + consumerIndex
                + ", counter value: "
                + counter);

        this.notifyAll();
        return value;
    }


}
