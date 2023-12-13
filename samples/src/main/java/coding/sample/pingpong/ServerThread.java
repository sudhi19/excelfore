package coding.sample.pingpong;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;


public class ServerThread extends Thread {
    private Socket socket;
    private PrintWriter output;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                String clientMessage = input.readLine();
                System.out.println("Received message: " + clientMessage);
                output.println("pong from: " + Thread.currentThread().getName());
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

}
