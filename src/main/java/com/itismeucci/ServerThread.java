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
    ServerLista lista;

    public ServerThread(Socket socket, ServerSocket server, ServerLista lista) {
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

        for (;;) {
            stringaRicevuta = inDalClient.readLine();
            if (stringaRicevuta == null || stringaRicevuta.equals("FINE")) {
                outVersoClient.writeBytes(stringaRicevuta + "(=>server in chiusura ...)" + '\n');
                System.out.println("Echo sul server in chiusura :" + stringaRicevuta);
                break;
            } else if(stringaRicevuta.equals("STOP")) { //controllo sulla stringa STOP
                outVersoClient.writeBytes(stringaRicevuta + "(=>server in chiusura ...)" + '\n');
                System.out.println("Echo sul server in chiusura :" + stringaRicevuta);
                break;
            } else {
                outVersoClient.writeBytes(stringaRicevuta + " (ricevuta e ritrasmessa)" + '\n');
                System.out.println("Echo sul server :" + stringaRicevuta);
            }
        }
        outVersoClient.close();
        inDalClient.close();
        System.out.println("Chiusura socket" + client);
        client.close();

        // if(stringaRicevuta.equals("STOP")) {
        //     System.out.println("Chiusura SERVER: " +  server);
        //     server.close();
        // }

    }
}
