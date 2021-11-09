package com.itismeucci;

import java.io.*;
import java.net.*;

public class ServerThread extends Thread {
    ServerSocket server = null;
    ServerListener listener2;
    Socket client = null;
    String stringaRicevuta = null;
    String stringaModificata = null;
    BufferedReader inDalClient;
    // DataOutputStream outVersoClient;

    String nomeUtente = null;

    int conta = 0;

    public ServerThread(Socket socket, ServerSocket server, ServerListener listener1) {
        this.client = socket;
        this.server = server;
        this.listener2 = listener1;
    }

    public void run() {
        try {
            comunica();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public void comunica() throws Exception {

        inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
        // outVersoClient = new DataOutputStream(client.getOutputStream());


        for (;;) {
            stringaRicevuta = inDalClient.readLine();

            if (stringaRicevuta.equals("EXIT")) { // IN QUESTO CASO DOBBIAMO FAR USCIRE IL CLIENT

                // outVersoClient.writeBytes(stringaRicevuta + "(=>server in chiusura ...)" + '\n');
                // System.out.println("Echo sul server in chiusura :" + stringaRicevuta);
                break;

            } else if (conta == 0) {
                nomeUtente = stringaRicevuta;
                listener2.aggiungiSocket(nomeUtente, client);
                System.out.println(nomeUtente + " si è connesso");
                // lista.addNome(nomeUtente);
                conta++;

            } else {
                // outVersoClient.writeBytes(stringaRicevuta + " (ricevuta e ritrasmessa)" + '\n');
                System.out.println(nomeUtente + ": " + stringaRicevuta);
            }
        }
        // outVersoClient.close();

        // inDalClient.close(); //da errore perchè per ora non ho messo un breakk

        // System.out.println("Chiusura socket " + client);
        System.out.println(nomeUtente + " si è disconnesso");
        client.close();

    }
}
