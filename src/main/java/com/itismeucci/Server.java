package com.itismeucci;

import java.net.*;

public class Server {
    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(6789);

            for (;;) {
                System.out.println("Server in attesa ...");
                Socket socket = serverSocket.accept();
                System.out.println("Server socket " + socket);
                ServerThread serverThread = new ServerThread(socket, serverSocket);
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
