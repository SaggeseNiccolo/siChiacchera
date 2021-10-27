package com.itismeucci;

import java.net.*;

public class Server {
    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(6789);

            for (;;) {
                Socket socket = serverSocket.accept();
                ServerLista listener = new ServerLista(); //
                ServerThread serverThread = new ServerThread(socket, serverSocket, listener);
                
                serverThread.start();
                System.out.println(Client.nomeUtente + " connected");
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
