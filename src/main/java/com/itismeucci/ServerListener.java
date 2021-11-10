package com.itismeucci;
import java.io.*;
import java.net.*;
import java.util.*;

public class ServerListener extends Thread {
    // creato vettore dove mi salvo tutti i socket dei client
    Vector<Socket> sockets = new Vector<Socket>();
    HashMap<String, Socket> handler = new HashMap<String, Socket>();
    String nomeUtente = null;
    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    String stringaModificata = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;

    // costruttore
    public ServerListener(Socket socket, ServerSocket server) {
        this.client = socket;
        this.server = server;
        sockets.add(client);
    }

    public void run(){
    }

    public void aggiungiSocket(String nomeUtente, Socket Client) throws Exception {
        handler.put(nomeUtente, Client);

        for (Socket socket : handler.values()) {
            outVersoClient = new DataOutputStream(socket.getOutputStream());
            outVersoClient.writeBytes(nomeUtente + " è entrato nella chat." + '\n');
        }
    }

    public void sendAll(String messaggio, String mittente) throws Exception {
        for (Socket socket : handler.values()) {
            outVersoClient = new DataOutputStream(socket.getOutputStream());
            outVersoClient.writeBytes(mittente + " ha scritto:" + messaggio + '\n');
        }
    }

    public void sendOne(String messaggio, String mittente, String destinatario) throws Exception {
        for (String x : handler.keySet()) {
            if (x == destinatario) {
                outVersoClient = new DataOutputStream(handler.get(x).getOutputStream());
                outVersoClient.writeBytes(mittente + " ha scritto (in privato):" + messaggio + '\n');
            }
        }
    }

    public void remove(String nome) throws Exception{
        handler.remove(nome);

        for (Socket socket : handler.values()) {
            outVersoClient = new DataOutputStream(socket.getOutputStream());
            outVersoClient.writeBytes(nomeUtente + " è uscito dalla chat." + '\n');
        }
    }

    public boolean verify(String nome, Socket client1) throws Exception{
        for (String x : handler.keySet()) {
            if (x == nome) {
                outVersoClient = new DataOutputStream(client1.getOutputStream());
                outVersoClient.writeBytes("Errore: il nome è gia stato inserito. Sceglierne un altro." + '\n');
                return false;
            }
        }
        return true;
    }

}
