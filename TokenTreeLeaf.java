class TokenTreeLeaf extends TokenTreeNode {

    @Override
    public TokenTreeLeaf setName(String name) {
	super.setName(name);
	return this;
    }

    @Override
    public void visit() {
	System.out.println("Foglia dal nome ==" + name + "==");
    }

    public static void main(String args[]) {
	TokenTreeLeaf tl = new TokenTreeLeaf();
	tl.setName("shit");
	System.out.println(tl.getName());
    } 
}
