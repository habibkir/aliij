
public class Driver {
    public static void testMonoCode() {
	IncrementalTokenizer it = new IncrementalTokenizer();
	TreeBuilder tb = new TreeBuilder();
	it.setReciever(tb);
	//String codiceMono = "(defun square (n) (* n n))";
	String codiceMono = "(defun mapc (lst fn)";
	it.feedString(codiceMono);
	it.exhaustStrings();
	it.sendEndSignal();
	tb.getTree().visit();
    }

    public static void testTwoStrings() {
	IncrementalTokenizer it = new IncrementalTokenizer();
	TreeBuilder other = new TreeBuilder();
	it.setReciever(other);

	it.feedString("(defun mapc(lst fn)");
	it.feedString("(do ((ll lst (cdr lst))");

	it.exhaustStrings();
	it.sendEndSignal();
	other.getTree().visit();
    }

    public static void testPolyCode() {
	IncrementalTokenizer it = new IncrementalTokenizer();
	String codicePoli [] =
	    { "(defun mapc (lst fn)",
	      "(do ((ll lst (cdr lst))",
	      "     (test tast tiddy diddler))",
	      "(funcall fn lst)))"};
	TreeBuilder other = new TreeBuilder();
	it.setReciever(other);
	for(String s : codicePoli) {
	    it.feedString(s);
	}
	it.exhaustStrings();
	it.sendEndSignal();
	other.getTree().visit();
    }
    public static void main(String args[]) {
	testPolyCode();
	System.out.println("Fine");
    }
}
