//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    Buffer buffer = new Buffer(3);
    Producer producer = new Producer(buffer);
    Consumer consumer = new Consumer(buffer);
    Thread producerThread = new Thread(producer);
    Thread consumerThread = new Thread(consumer);
    producerThread.start();
    consumerThread.start();
}
