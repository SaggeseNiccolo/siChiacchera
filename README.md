# siChiacchera
Pag. 153
- Client appena connesso fa un writeBytes(nomeUtente + '/n')
- Server dopo la connessione fa writeBytes(nomeUtente + ' connsesso' + '/n')
- Client dopo la connessione comunica()
- Server inoltra il messaggio a tutti tranne a quello che l'ha inviato (protocollo messaggio + nomeUtente)
- Server gestisce la situazione con un solo client collegato (impossibile inoltrare il messaggio)
- Client dopo la chiusura della connessione manda un messaggio speciale (nomeUtente + 'left the chat' + '/n')
