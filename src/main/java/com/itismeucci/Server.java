package com.itismeucci;

import java.net.*;

public class Server {
    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(6789);
            System.out.println("il server è partito");

            for (;;) {
                Socket socket = serverSocket.accept();
                ServerListener listener = new ServerListener(); //
                ServerThread serverThread = new ServerThread(socket, serverSocket, listener);
                
                serverThread.start();

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
