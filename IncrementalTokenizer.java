import java.lang.String;
import java.lang.Character;
import java.util.Queue;
import java.util.LinkedList;

public class IncrementalTokenizer implements TokenSender {
    private TokenReciever reciever;
    private int indexInString = 0;
    private Queue<String> stringQueue = new LinkedList<>();

    // needs be sorted by length for implementation reasons
    private String[] specialTokens =
    {
	"#\\", "#\'", ",@","#|", "|#",
	"(", ")", "[", "]", "{", "}",
	":", "`", "\'", "\""
    };

    private String currString() {
	return stringQueue.peek();
    }

    private boolean hasSpecialPrefix(String s, int index) {
	for(String special : specialTokens) {
	    if(s.startsWith(special, index))
		return true;
	}
	return false;
    }

    private boolean hasSpecialPrefix(String s) {
	return hasSpecialPrefix(s,indexInString);
    }

    public void feedString(String s) {
	stringQueue.add(s);
	if(stringQueue.size() == 1)
	    primeForNewString();
    }

    private void primeForNewString() {
	// preparativi per iniziare a estrarre da stringa in cima se è cambiata
	indexInString = advanceWhileWhite(0);
    }

    private int advanceWhileWhite(int i) {
	String s = currString();
	while ((i<s.length()) && Character.isWhitespace(s.charAt(i))) {
	    i++;
	}
	return i;
    }

    public void exhaustTokens() {
	while(!stringQueue.isEmpty())
	    extractToken();
    }

    private void extractToken() {
	int endIndex = goOutsideToken(indexInString);
	sendToken(currString().substring(indexInString,endIndex));

	// #ifdef DEBUGGING
	// System.out.println(currString());
	// System.out.println(indexInString + " : " + endIndex);
	// System.out.println(currString().substring(indexInString,endIndex));
	// indexInString = advanceWhileWhite(endIndex);
	// System.out.println("Ora in index " + indexInString);
	// #endif

	if(indexInString == currString().length()) {
	    stringQueue.remove();
	    if(!stringQueue.isEmpty())
		primeForNewString();
	}
    }

    private int goOutsideToken(int i) {
	// return last i in the token to which currString().charAt(i) belongs
	String s = currString();

	if(hasSpecialPrefix(s, i))
	    return goOutsideSpecialToken(s, i);

	while((i<s.length()) &&
	      (!hasSpecialPrefix(s,i)) &&
	      (!Character.isWhitespace(s.charAt(i)))) {
	    i++;
	}
	return i;
    }

    private int goOutsideSpecialToken(String s, int index) {
	for(String special : specialTokens) {
	    if(s.startsWith(special, index))
		return index + special.length();
	}
	return 0;
    }

    @Override
    public void sendToken(String s) {
	reciever.recieveToken(s);
    }

    @Override
    public void sendEndSignal() {
	reciever.recieveEndSignal();
    }

    @Override
    public void setReciever(TokenReciever tr) {
	this.reciever = tr;
    }
}
