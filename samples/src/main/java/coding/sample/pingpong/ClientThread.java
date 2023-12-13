package coding.sample.pingpong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {

    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    public ClientThread(Socket s) throws IOException {
        this.socket = s;
        this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.output = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void run() {

        try {
            output.println("ping from: " + Thread.currentThread().getName());
            System.out.println("Sent message to server.");
            String response = input.readLine();
            System.out.println("Server message received: " + response);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(0);
        }
    }
}

