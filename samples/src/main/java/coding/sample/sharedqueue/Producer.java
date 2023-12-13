package coding.sample.sharedqueue;

public class Producer implements Runnable {
    private final SharedQueue queue;

    Producer(SharedQueue queue) {
        this.queue = queue;
    }

    public void run() {
        System.out.println("Adding messages to the queue");
        try {
            for (int i = 0; i < 5; i++) {
                queue.put("message " + i);
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}