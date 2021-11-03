package com.itismeucci;

import java.io.*;
import java.net.*;
import java.util.Vector;

public class ServerListener extends Thread {
    // creato vettore dove mi salvo tutti i socket dei client
    Vector<Socket> sockets = new Vector<Socket>();

    Vector<String> nomi = new Vector<String>();

    DataOutputStream outVersoClient;

    int i = 0;

    // costruttore
    public ServerListener() {
    }

    // runnable dove vengono chiusi tutti i client dentro il vector
    public void run() {
        try {
            for (Socket client : sockets) { // faccio un foreach dove vado a chiudere tutti i socket dei client

                outVersoClient = new DataOutputStream(client.getOutputStream());

                outVersoClient.writeBytes("connessione nuovo utente: " + nomi.get(0) + '\n');

                System.out.println("Esco dal for");
            }
        } catch (Exception e) {
            // in caso di errore
            System.out.println("Nessun elemento dentro il vettore, istanza prima un client.");
        }

    }

    // aggiungo un client al vettore sockets
    public void addClient(Socket x) {
        sockets.add(x);
    }

    // aggiungo il nome al vettore nomi
    public void addNome(String x) {
        nomi.add(x);
    }


}
