package com.itismeucci;

import java.io.*;
import java.net.*;

public class ServerThread extends Thread {
    ServerSocket server = null;
    ServerListener writer2;
    Socket client = null;
    String stringaRicevuta = null;
    String stringaModificata = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;
    String nomeUtente = null;
    int conta = 0;
    String destinatario;

    public ServerThread(Socket socket, ServerSocket server, ServerListener writer1) {
        this.client = socket;
        this.server = server;
        this.writer2 = writer1;
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
        outVersoClient = new DataOutputStream(client.getOutputStream());

        for (;;) {
            // leggo la stringa inviata dal client
            stringaRicevuta = inDalClient.readLine();

            // se è la prima allora è perforza il nome dell'utente poichè questo viene
            // richiesto all'inizio dell'esecuzione del socket
            if (conta == 0) {

                // se il controllo va a buon fine allora il client entra nella chat, senno darà
                // errore e richiederà l'inserimento dei dati
                if (writer2.verify(nomeUtente, client)) {
                    nomeUtente = stringaRicevuta;
                    writer2.aggiungiSocket(nomeUtente, client);
                    conta++;
                    System.out.println("Aggiunto utente: " + nomeUtente);
                }

                // TIPS: LE "text" VENGONO USATE PER LA STRINGA MENTRE 'text' PER LE CHAR
            } else if (stringaRicevuta.charAt(0) == '$' && stringaRicevuta.charAt(1) == 'b') {
                // confermo la selezione del PUBBLIC e chiedo al client il messaggio da inviare
                // a tutti
                outVersoClient.writeBytes(stringaRicevuta
                        + "Selezionato messaggio Pubblico, dimmi il messaggio che vuoi inviare." + '\n');
                // aspetto l'invio del messaggio
                stringaRicevuta = inDalClient.readLine();
                // funzione del thread writer che esegue l'invio del messaggio a tutti i client
                // connessi
                writer2.sendAll(stringaRicevuta, nomeUtente);
                outVersoClient.writeBytes("Messaggio inviato correttamente." + '\n');
                System.out.println("SERVER DICE: HO APPENA INVIATO A TUTTI UN MESSAGGIO");

            } else if (stringaRicevuta.charAt(0) == '$' && stringaRicevuta.charAt(1) == 'v') {
                // confermo la selezione del PRIVATE e chiedo al client il destinatario
                outVersoClient.writeBytes(
                        stringaRicevuta + "Selezionato messaggio Privato, dimmi il destinatario del messaggio." + '\n');
                // aspetto l'invio del nome del destinatario
                stringaRicevuta = inDalClient.readLine();
                // salvo il nome del destinatario in una variabile
                destinatario = stringaRicevuta;
                // adesso richiedo il messaggio da inviare al destinatario
                outVersoClient.writeBytes(stringaRicevuta + "Selezionato destinatario " + destinatario
                        + ", dimmi il messaggio che vuoi inviare." + '\n');
                // aspetto l'invio del messaggio
                stringaRicevuta = inDalClient.readLine();
                writer2.sendOne(stringaRicevuta, nomeUtente, destinatario);
                outVersoClient.writeBytes("Messaggio inviato correttamente." + '\n');
                System.out.println("SERVER DICE: HO APPENA INVIATO A " + destinatario + " UN MESSAGGIO");

            } else if (stringaRicevuta.charAt(0) == '$' && stringaRicevuta.charAt(1) == 'e') {
                // faccio uscire dalla chat l'utente
                outVersoClient.writeBytes("$e" + '\n');
                writer2.remove(nomeUtente);
                break;
            }
        }
        outVersoClient.close();
        inDalClient.close();
        System.out.println("Chiusura socket: " + client);
        client.close();
    }
}
