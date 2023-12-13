package coding.sample.sharedqueue;

public class Consumer implements Runnable {
    private final SharedQueue queue;

    Consumer(SharedQueue queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            System.out.println("Started consumer " + Thread.currentThread().getName());
            while (true) {
                String value = queue.take();
                System.out.println(Thread.currentThread().getName() + ": " + value);
            }

        } catch (Exception e) {
            System.err.println(Thread.currentThread().getName() + " " + e.getMessage());
        }
    }
}