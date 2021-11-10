package com.itismeucci;

import java.io.*;
import java.net.Socket;

public class ClientListener extends Thread {

    Client client = new Client();
    BufferedReader inDalServer;
    String stringaRicevutaDalServer;
    Socket mioSocket;

    // costruttore
    public ClientListener(Socket socket) {
        mioSocket = socket;
    }

    public void ascolta() throws IOException {
        inDalServer = new BufferedReader(new InputStreamReader(mioSocket.getInputStream()));

        for (;;) {
            stringaRicevutaDalServer = inDalServer.readLine();
            System.out.println("\n" + "Stringa ricevuta: " + stringaRicevutaDalServer);
        }
    }

    public void run() {
        try {
            ascolta();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
