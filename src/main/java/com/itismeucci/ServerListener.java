package com.itismeucci;

import java.io.*;
import java.net.*;
import java.util.*;

public class ServerListener extends Thread {
    // creato vettore dove mi salvo tutti i socket dei client
    Vector<Socket> sockets = new Vector<Socket>();

    DataOutputStream outVersoClient;

    String nomeUtente = null;

    int i = 0;

    // costruttore
    public ServerListener() {
    }

    public void run() {
        try {
            // devo fare in modo di mandare il messaggio a tutti i client
            for (Socket client : sockets) {

                outVersoClient = new DataOutputStream(client.getOutputStream());

                // outVersoClient.writeBytes(nomeUtente + ": " + strMessaggio + '\n');

            }

            // System.out.println("ho inviato il messaggio a tutti i client.");

        } catch (Exception e) {
            // in caso di errore
            System.out.println("Nessun elemento dentro il vettore, istanza prima un client.");
        }

    }

    // aggiungo un client al vettore sockets
    public void addClient(Socket x) {
        sockets.add(x);
    }

    public void inoltra() { //parte dove invio a tutti i client la connessione di un determinato utente

        // for (Socket client : sockets) { 

        //     outVersoClient = new DataOutputStream(client.getOutputStream());

        //     outVersoClient.writeBytes("connessione nuovo utente: " + nomi.get(i) + '\n');

        //     System.out.println(client);

        //     i++;

        // }
    }

}
