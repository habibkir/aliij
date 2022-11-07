package tokenizer;

import java.util.Queue;
import java.util.LinkedList;

public class IncrementalTokenizer implements TokenSender, TokenReciever {
    private TokenReciever reciever;
    private int indexInString = 0;
    private Queue<String> stringQueue = new LinkedList<>();
    private ExtractingStrategy strat = new DefaultExtractingStrategy(this);

    public void feedString(String s) {
	stringQueue.add(s);
	/* TODO, questa cosa fa schifo, rivedi come non farle fare schifo
	 * magari un metodo extractingStrategy.onQueueNotEmpty().
	 * oppure un extractingStrategy.onChosen(), o onTurnStart(),
	 * visto che comunque dovranno tutte prepararsi in qualche modo
	 * quando inizia il loro turno */
	if(stringQueue.size() == 1) {
	    indexInString = strat.onTurnStart(stringQueue, indexInString);
	}
    }

    public void exhaustStrings() {
	while(!stringQueue.isEmpty())
	    extractToken();
    }

    private void extractToken() {
	indexInString = strat.extractToken(stringQueue, indexInString);
    }

    @Override
    public void sendToken(String s) {
	reciever.recieveToken(s);
    }

    @Override
    public void setReciever(TokenReciever tr) {
	this.reciever = tr;
    }

    @Override
    public void recieveToken(String s) {
	sendToken(s);
    }
}
