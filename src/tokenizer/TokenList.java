package tokenizer;

import java.util.LinkedList;

/* Composite, but worse */
class TokenList implements TokenReciever {
    private LinkedList<String> tokenList = new LinkedList<>();

    @Override
    public void recieveToken(String token) {
	tokenList.add(token);
    }


    public LinkedList<String> getList() {
	return tokenList;
    }
}
