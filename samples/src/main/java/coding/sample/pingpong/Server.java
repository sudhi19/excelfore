package coding.sample.pingpong;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {

        try {
            ServerSocket serversocket = new ServerSocket(5000);
            while (true) {
                Socket socket = serversocket.accept();
                ServerThread serverThread = new ServerThread(socket);
                serverThread.start();

            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
