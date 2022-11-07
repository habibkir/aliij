package tokenizer;

import java.util.LinkedList;

/* Composite, but worse */
public class TokenTree extends TokenTreeNode {
    private LinkedList<TokenTreeNode> children = new LinkedList<>();

    @Override
    public TokenTree setName(String newName) {
	super.setName(newName);
	return this;
    }

    @Override
    public TokenTree appendChild(TokenTreeNode newChild) {
	children.add(newChild);
	return this;
    }

    @Override
    public void visit() {
	System.out.println("Albero dal nome  ==" + name + "== con figli");
	for(TokenTreeNode ttd : children) {
	    ttd.visit();
	}
	System.out.println("Fine dell'albero ==" + name + "==");
    }

    @Override
    public LinkedList<TokenTreeNode> getChildren() {
	return children;
    }
}
