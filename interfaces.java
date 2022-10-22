import java.lang.RuntimeException;

/* Observer, ma peggio, e di parecchio */
/* TODO, reimplementalo con un observer più tale */
interface TokenSender {
    public void sendToken(String s);
    public void sendEndSignal(); //notify recievers, stronzo
    public void setReciever(TokenReciever tr);
}

/* Observer, but worse */
interface TokenReciever {
    public void recieveToken(String s);
    public void recieveEndSignal();
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
	    ("Cannot add a child to this node of the token tree");
    }
    abstract public void visit();
}
