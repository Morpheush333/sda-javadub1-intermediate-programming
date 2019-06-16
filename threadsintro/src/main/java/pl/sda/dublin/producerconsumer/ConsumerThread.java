package pl.sda.dublin.producerconsumer;

public class ConsumerThread extends Thread {
    private Buffer buffer;

    public ConsumerThread(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            int value = buffer.getData();
        }
    }
}
