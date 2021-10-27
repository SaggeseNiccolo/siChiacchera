package com.itismeucci;

import java.net.*;
import java.util.*;
import java.io.*;

public class ServerLista extends Thread {
    //creato vettore dove mi salvo tutti i socket dei client
    Vector <Socket> lista = new Vector<Socket>();


    BufferedReader inDalClient;
    DataOutputStream outVersoClient;

    String nomeUtente;

    // costruttore
    public ServerLista() {

    }

    //runnable dove vengono chiusi tutti i client dentro il vector
    public void run() {
        try {
            for (Socket client : lista) { //faccio un foreach dove vado a mandare il messaggio a tutti i client
                outVersoClient = new DataOutputStream(client.getOutputStream());
                outVersoClient.writeBytes(nomeUtente + "join the chat");
            }
        } catch (Exception e) {
            //in caso di errore
            System.out.println("Nessun elemento dentro il vettore, istanza prima un client.");
        }
        
    }

    //aggiungo un client al vettore lista
    public void addClient(Socket x){
        lista.add(x);
    }
}