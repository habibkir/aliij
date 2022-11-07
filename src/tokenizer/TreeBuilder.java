package tokenizer;

import java.lang.String;
import java.lang.Character;

import java.util.LinkedList;
import java.util.Stack;

public class TreeBuilder implements TokenReciever {
    private TokenTree internalTree;
    private Stack<TokenTreeNode> nodePath = new Stack<>();

    int depth = 0;
    private String genTreeName() {
	return "Depth : " + depth;
    }

    public TreeBuilder() {
	internalTree = (new TokenTree()).setName("Satchmo");
	nodePath.push(internalTree);
    }

    @Override
    public void recieveToken(String token) {
	if(token.equals(")")) {
	    nodePath.pop();
	    depth --;
	}
	else if(token.equals("(")) {
	    TokenTree newHead = (new TokenTree()).setName(genTreeName());
	    nodePath.peek().appendChild(newHead); // beginning of subtree
	    nodePath.push(newHead);
	    depth ++;
	}
	/* poi vedi la quote, vedi i caratteri, che ne so */
	else {
	    nodePath.peek().appendChild((new TokenTreeLeaf()).setName(token));
	}
	return;
    }

    public TokenTree getTree() {
	return internalTree;
    }
}
