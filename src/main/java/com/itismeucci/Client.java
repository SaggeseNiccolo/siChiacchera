package com.itismeucci;

import java.io.*;
import java.net.*;

public class Client {
    String nomeServer = "localhost";
    int portaServer = 6789;
    Socket miosocket;
    BufferedReader tastiera;
    String stringaUtente;
    String stringaRicevutaDalServer;
    DataOutputStream outVersoServer;
    BufferedReader inDalServer;

    public Socket connetti() {
        System.out.println("Ingresso nella chat");
        try {

            tastiera = new BufferedReader(new InputStreamReader(System.in));

            miosocket = new Socket(nomeServer, portaServer);

            outVersoServer = new DataOutputStream(miosocket.getOutputStream());

            inDalServer = new BufferedReader(new InputStreamReader(miosocket.getInputStream()));

        } catch (UnknownHostException e) {
            System.err.println("Host sconosciuto");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la connessione");
            System.exit(1);
        }
        return miosocket;
    }

    public void comunica() {
        for (;;) {
            try {
                System.out.print("Messaggio:");
                stringaUtente = tastiera.readLine();

                // la spedisco al server
                System.out.println("Invio messaggio...");
                outVersoServer.writeBytes(stringaUtente + '\n');

                // leggo la risposta del server
                stringaRicevutaDalServer = inDalServer.readLine();
                System.out.println("Risposta dal server" + '\n' + stringaRicevutaDalServer);

                if (stringaUtente.equals("FINE") || stringaUtente.equals("STOP")) {
                    System.out.println("CLIENT: termina elaborazione e chiude connessione");
                    miosocket.close();
                    break;
                }

            } catch (Exception e) {
                System.out.println("e.getMessage()");
                System.out.println("Errore durante la comunicazione con il server!");
                System.exit(1);
            }
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.connetti();
        client.comunica();
    }
}
