package coding.sample.sharedqueue;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SharedQueueTest {
    public static void main(String[] args) throws Exception {

        SharedQueue queue = new SharedQueue();
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(new Producer(queue) {
        }, 0, 1, TimeUnit.SECONDS);

        Thread c1 = new Thread(new Consumer(queue));
        Thread c2 = new Thread(new Consumer(queue));
        Thread c3 = new Thread(new Consumer(queue));
        Thread c4 = new Thread(new Consumer(queue));
        Thread c5 = new Thread(new Consumer(queue));

        c1.start();
        c2.start();
        c3.start();
        c4.start();
        c5.start();

        c1.join();
        c2.join();
        c3.join();
        c4.join();
        c5.join();

    }
}
