package tokenizer;

import java.util.Queue;
import java.util.LinkedList;
import java.lang.String;
import java.lang.Character;

class DefaultExtractingStrategy implements ExtractingStrategy, TokenSender {
    // needs be sorted by length for implementation reasons
    private String[] specialTokens =
    {
	"#\\", "#\'", ",@","#|", "|#",
	"(", ")", "[", "]", "{", "}",
	":", "`", "\'", "\"", ","
    };
    private TokenReciever reciever;

    public DefaultExtractingStrategy(IncrementalTokenizer it) {
	setReciever(it);
    }

    @Override
    public int onTurnStart(Queue<String> sq, int i) {
	return advanceWhileWhite(sq.peek(),i);
    }

    private int advanceWhileWhite(String s, int i) {
	while ((i<s.length()) && Character.isWhitespace(s.charAt(i))) {
	    i++;
	}
	return i;
    }

    @Override
    /* ritorna il nuovo indice di inizio token,
     * e manda i nuovi token estratti, qualora ve ne fossero */
    public int extractToken(Queue<String> sq, int i) {
	String s = sq.peek();
	int end = goPastToken(s,i);
	String token = s.substring(i,end);
	sendToken(token);

	/* #ifdef DEBUGGING
	System.out.println(s);
	System.out.println(i + " : " + end);
	System.out.println(token);
	System.out.println("Ora in index " + i);
	System.out.println("Dopo l'avanzata siamo in " + i);
	try {int fuck = System.in.read();}
	catch(Exception e) {System.out.println("fanculo");}
	#endif */

	i = advanceWhileWhite(s,end);
	if(i == s.length()) {
	    sq.remove();
	    if(!sq.isEmpty())
		i = advanceWhileWhite(s,0);
	}
	return i;
    }

    private int goPastToken(String s,int i) {
	// returns first index past the token to which s.charAt(i) belongs
	if(hasSpecialPrefix(s, i))
	    return goPastSpecialToken(s, i);

	while((i<s.length()) &&
	      (!hasSpecialPrefix(s,i)) &&
	      (!Character.isWhitespace(s.charAt(i)))) {
	    i++;
	}
	return i;
    }

    private boolean hasSpecialPrefix(String s, int index) {
	for(String special : specialTokens) {
	    if(s.startsWith(special, index))
		return true;
	}
	return false;
    }

    private int goPastSpecialToken(String s, int index) {
	for(String special : specialTokens) {
	    if(s.startsWith(special, index))
		return index + special.length();
	}
	return 0;
    }

    @Override
    public void setReciever(TokenReciever tr) {
	reciever = tr;
    }

    @Override
    public void sendToken(String s) {
	reciever.recieveToken(s);
    }
}
