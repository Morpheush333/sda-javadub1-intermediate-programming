package pl.sda.dublin.producerconsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// cykliczny bufor, w którym producent będzie umieszczał dane
// a konsument pobierał dane
public class Buffer {


    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    private int[] buffer = new int[10];
    private int producerIndex = 0;
    private int consumerIndex = 0;
    //counter - zmienna do wykrywania, czy bufor pusty, czy pełny
    private int counter = 0;


    public void insert(int data) {
        lock.lock();
        // sprawdzamy, czy mozna wstawic do bufora
        // petla while po to, aby obudzony wątek jeszcze raz sprawdził początkowy warunek
        while (counter == buffer.length) {
            System.out.println("PRODUCER - Buffer full - going to sleep");
            try {
                // wywołujemy funkcję wait(), która pochodzi z klasy Object
                // i jest mechanizmem pozwalającym na wstrzymanie pracy wątku
                // tak długo, aż inny wątek na tym obiekcie (this) wywoła metodę this.notify() bądź notifyAll();
                condition.await();

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

        condition.signalAll();
        lock.unlock();
    }


    public int getData() {
        lock.lock();
        while (counter == 0) {
            System.out.println("CONSUMER - Buffer empty - going to sleep");
            try {
                condition.await();
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

        condition.signalAll();
        lock.unlock();
        return value;
    }


}
