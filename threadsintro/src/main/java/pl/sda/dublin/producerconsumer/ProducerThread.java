package pl.sda.dublin.producerconsumer;

public class ProducerThread extends Thread {
    private Buffer buffer;

    public ProducerThread(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        int i =0;
        for (;;) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            buffer.insert(i++);
        }
    }
}
