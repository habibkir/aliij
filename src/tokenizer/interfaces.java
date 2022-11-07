package tokenizer;

import java.lang.RuntimeException;
import java.util.LinkedList;

/* Observer, ma peggio, e di parecchio */
/* TODO, reimplementalo con un observer più tale */
interface TokenSender {
    public void sendToken(String s);
    public void setReciever(TokenReciever tr);
}

/* Observer, but worse */
interface TokenReciever {
    public void recieveToken(String s);
}

/* Composite, but worse */
abstract class TokenTreeNode {
    protected String name = "";
    public TokenTreeNode setName(String name) {
	this.name = name;
	return this;
    }
    public String getName() {
	return name;
    }
    public TokenTreeNode appendChild(TokenTreeNode ttd) {
	throw new RuntimeException
	    ("Cannot add a child, the node is of type leaf");
    }
    public LinkedList<TokenTreeNode> getChildren() {
	throw new RuntimeException
	    ("Tried accessing children of a leaf, are you stupid?");
    }
    abstract public void visit();
}
