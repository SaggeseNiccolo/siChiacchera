package com.itismeucci;

import java.io.*;
import java.net.Socket;

public class ClientListener extends Thread {

    Client client = new Client();
    BufferedReader inDalServer;
    String stringaRicevutaDalServer;
    Socket mioSocket = client.getSocket();

    public ClientListener() {
    }

    public void ascolta() throws IOException {
        inDalServer = new BufferedReader(new InputStreamReader(mioSocket.getInputStream()));

        stringaRicevutaDalServer = inDalServer.readLine();

        System.out.println(stringaRicevutaDalServer);

        // for (;;) {}
    }

    public void run() {
        try {
            this.ascolta();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
