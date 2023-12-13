package coding.sample.pingpong;

import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);
          //  ClientThread clientThread = new ClientThread(socket);
           // clientThread.start();

            ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
            exec.scheduleAtFixedRate(new ClientThread(socket) {
            }, 0, 5, TimeUnit.SECONDS);


        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(0);
        }
    }
}
