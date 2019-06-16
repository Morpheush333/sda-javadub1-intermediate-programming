package pl.sda.dublin.producerconsumer;

public class ConsumerThread extends Thread {
    private Buffer buffer;

    public ConsumerThread(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        int i = 0;
        for (; ; ) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int value = buffer.getData();
        }
    }
}
