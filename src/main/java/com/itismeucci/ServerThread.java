package com.itismeucci;

import java.io.*;
import java.net.*;

public class ServerThread extends Thread {
    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    String stringaModificata = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;
    ServerListener lista;

    String nomeUtente = null;
    int conta = 0;

    public ServerThread(Socket socket, ServerSocket server, ServerListener lista) {
        this.client = socket;
        this.server = server;
        this.lista = lista;

        lista.addClient(client);
    }

    public void run() {
        try {
            this.lista.run();
            comunica();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public void comunica() throws Exception {

        inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
        outVersoClient = new DataOutputStream(client.getOutputStream());

        lista.addClient(client);

        for (;;) {
            stringaRicevuta = inDalClient.readLine();

            if (stringaRicevuta.equals("EXIT")) { // IN QUESTO CASO DOBBIAMO FAR USCIRE IL CLIENT

                outVersoClient.writeBytes(stringaRicevuta + "(=>server in chiusura ...)" + '\n');
                System.out.println("Echo sul server in chiusura :" + stringaRicevuta);
                break;

            } else if (conta == 0) {
                nomeUtente = stringaRicevuta;
                System.out.println(nomeUtente + " è entrato nella chat");

                lista.addNome(nomeUtente);

                lista.start();

                conta++;

            } else {
                outVersoClient.writeBytes(stringaRicevuta + " (ricevuta e ritrasmessa)" + '\n');
                System.out.println(nomeUtente + ": " + stringaRicevuta);
            }
        }
        outVersoClient.close();
        inDalClient.close();
        System.out.println("Chiusura socket" + client);
        client.close();

    }
}
