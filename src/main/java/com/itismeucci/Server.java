package com.itismeucci;
import java.net.*;

public class Server {
    //public static HashMap<String, Socket> HANDLER = new HashMap<String, Socket>(); in caso mi servisse che l'hashmap sia in variabile globale
    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(6789);
            System.out.println("il server è partito");

            for (;;) {
                Socket socket = serverSocket.accept();
                ServerListener writer1 = new ServerListener(socket, serverSocket);
                ServerThread serverListener = new ServerThread(socket, serverSocket, writer1);
                serverListener.start();
                writer1.start();

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server!");
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
}
