package com.itismeucci;

import java.net.*;

public class Server {
    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(6789);
            System.out.println("il server è partito");

            for (;;) {
                Socket socket = serverSocket.accept();
                ServerListener listener1 = new ServerListener(socket, serverSocket);
                ServerThread serverThread = new ServerThread(socket, serverSocket, listener1);
                serverThread.start();
                listener1.start();

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server !");
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        Server tcpServer = new Server();
        tcpServer.start();
    }
}
