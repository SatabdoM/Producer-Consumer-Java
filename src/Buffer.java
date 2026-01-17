import java.util.LinkedList;
import java.util.Queue;

public class Buffer {
    private final Queue<Resource> buffer = new LinkedList<>();
    private final int capacity;

    public Buffer(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void produce(Resource item) throws InterruptedException {
        try {
            while (buffer.size() == capacity) {
                wait();
            }
            buffer.add(item);
            System.out.println("\n Produced: " + item.getKey() + " - " + item.getValue());
//            Thread.sleep(2000); // Simulate time taken to produce
            notifyAll(); // Will notify consumers that there is data available
        } catch (InterruptedException e) {
            System.out.println("Put operation interrupted");
            Thread.currentThread().interrupt();
        }
    }

    public synchronized Resource consume() throws InterruptedException {
        Resource item = null;
        try {
            while (buffer.size() == 0) {
                wait();
            }
            item = buffer.poll();
            System.out.println("\n         Consumed: " + item.getKey() + " - " + item.getValue());
            Thread.sleep(5000); // Simulate processing time
            notifyAll(); // Will notify producers that there is space available
        } catch (InterruptedException e) {
            System.out.println("Get operation interrupted");
            Thread.currentThread().interrupt();
        }
        return item;
    }
}
