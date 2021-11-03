# siChiacchera
Pag. 153
- Client appena connesso fa un writeBytes(nomeUtente + '/n')
- Server dopo la connessione fa writeBytes(nomeUtente + ' connsesso' + '/n')
- Client dopo la connessione comunica()
- Server inoltra il messaggio a tutti tranne a quello che l'ha inviato (protocollo messaggio + nomeUtente)
- Server gestisce la situazione con un solo client collegato (impossibile inoltrare il messaggio)
- Client dopo la chiusura della connessione manda un messaggio speciale (nomeUtente + 'left the chat' + '/n')

Server:
-aspetto sulla porta bind(6978) e aspetto la connessione con il client
-appena ci siamo collegati, il server ti chiede il nome utente al client connesso ("dammi il tuo nome" + '/n');
-adesso il server manda a tutti gli altri client connessi ("connessione nuovo utente nome: " + nomeUtente);
-quando mi arriva un messaggio da parte di un client, allora lo invio a tutti gli altri in broadcast (global) == ("messaggio di " + nomeUtente + ": " +  strMessaggio);
-quando mi arriva un stringa che vale "EXIT" allora stacco il socket del client attuale;
-invio un messaggio in brodcast globale che avverte gli altri client che il client ha abbandonato la chat (nomeUtente + " si è disconesso");

Client:
-Il client appena connesso manda il nome utente al server: (nomeUtente + "/n");
-Il client invia un messaggio: (strMessaggio + "/n");
-Il client per disconnettersi deve mandare un strMessaggio = "EXIT" --> (strMessaggio + '/n');

 /n indica la fine del messaggio
