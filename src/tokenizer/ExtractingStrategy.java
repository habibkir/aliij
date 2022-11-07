package tokenizer;

import java.util.Queue;

/* commenti, magari multiline, e letterali di stringa richiedono tutt'un altro mondo
 * quindi faremo con tutt'un'altra strategy
 *
 * l'estrazione avviene mandando la stringa al tokenizer tramite sendToken e recieveToken
 * il valore di ritorno è invece l'indice a cui spostare indexInString nel tokenizer.
 *
 * onTurnStart viene chiamato quando si passa da uno stato in cui la strategy non è
 * utilizzata o utilizzabile a uno stato in cui viene usata.
 * Serve un metodo a parte per quando si cambia da una strategy all'altra, in quanto settare
 * roba nel costruttore valrebbe al massimo per il primo utilizzo dopo la costruzoine.
 * onTurnStart ritorna un int in caso serva aggiornare l'indice interno del tokenizer
 * durante l'inizializzazoine e/o setup.(Integer mi fa schifo).
 *
 * (vale a dire, lo stato di un tokenizer lo descrivi con queue e index, se prendi la
 * reference di una queue e l'index che ritorni viene assegnato di sopra, ecco che
 * puoi modificare più o meno a piacere lo stato del tokenizer durante l'inizializzazione)
 * (vedi IncrementalTokenizer.feedString() et al per quando viene usato)
 *
 * le strategy che useremo sono: 
 *  - DefaultExtractingStrategy :
 *    si aspetta una lista di token, senza troppi problemi, e gli estrae normalmente
 *    va alla strategy comment se trova ';' o '#|', va alla strategy stringa se trova '"'
 *    
 *  - CommentExtractingStrategy :
 *    attivata per commenti inline (; ... \n) o multiline (#| ... |#)
 *    (meglio se ne fai due diverse)
 *    ignora tutto fino all'arrivo di fine commento, e poi torna alla strategy normale
 *    
 *  - StringExtractingStrategy :
 *    per leggere correttamente letterali di stringa,
 *    al momento non è previsto supporto per escape sequence
 *    legge la stringa fino alla virgoletta finale, ci fa una stringa, e torna al default */

interface ExtractingStrategy {
    public int extractToken(Queue<String> sq, int i);
    public int onTurnStart(Queue<String> sq, int i);
}
