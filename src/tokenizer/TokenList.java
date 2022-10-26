package tokenizer;

import java.util.LinkedList;

/* Composite, but worse */
class TokenList implements TokenReciever {
    private LinkedList<String> tokenList = new LinkedList<>();
    private boolean done = false;

    @Override
    public void recieveToken(String token) {
	tokenList.add(token);
    }

    @Override
    public void recieveEndSignal() {
	done = true;
    }

    public LinkedList<String> getList() {
	if(done)
	    return tokenList;
	return null;
    }
}
