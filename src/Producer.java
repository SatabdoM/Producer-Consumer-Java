public class Producer implements Runnable {
    private final Buffer buffer;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                buffer.produce(new Resource("Item_" + i, i * 10));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
