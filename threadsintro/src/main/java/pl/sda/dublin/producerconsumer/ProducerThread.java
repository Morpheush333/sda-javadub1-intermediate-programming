package pl.sda.dublin.producerconsumer;

public class ProducerThread extends Thread {
    private Buffer buffer;

    public ProducerThread(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            buffer.insert(i);
        }
    }
}
