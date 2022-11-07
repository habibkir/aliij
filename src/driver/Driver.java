package driver;

import tokenizer.IncrementalTokenizer;
import tokenizer.TreeBuilder;

public class Driver {
    public static void testMonoCode() {
	IncrementalTokenizer it = new IncrementalTokenizer();
	TreeBuilder tb = new TreeBuilder();
	it.setReciever(tb);
	//String codiceMono = "(defun square (n) (* n n))";
	String codiceMono = "(defun mapc (lst fn)";
	it.feedString(codiceMono);
	it.exhaustStrings();
	tb.getTree().visit();
    }

    public static void testTwoStrings() {
	IncrementalTokenizer it = new IncrementalTokenizer();
	TreeBuilder other = new TreeBuilder();
	it.setReciever(other);

	it.feedString("(defun mapc(lst fn)");
	it.feedString("(do ((ll lst (cdr lst))");

	it.exhaustStrings();
	other.getTree().visit();
    }

    public static void testPolyCode() {
	IncrementalTokenizer it = new IncrementalTokenizer();
	TreeBuilder other = new TreeBuilder();
	String codicePoli [] =
	    { "(defun mapc (lst fn)",
	      "(do ((ll lst (cdr lst))",
	      "     (test tast tiddy diddler))",
	      "(funcall fn lst)))"};

	System.out.println("Dichiarato");
	System.out.println("Costruito");
	it.setReciever(other);
	System.out.println("Settato");
	for(String s : codicePoli) {
	    it.feedString(s);
	}
	System.out.println("Mangiato");

	it.exhaustStrings();
	System.out.println("Esaurito");
	System.out.println("Mandato");
	other.getTree().visit();
	System.out.println("Visitato");
    }
    public static void main(String args[]) {
	System.out.println("Inizio");
	testPolyCode();
	System.out.println("Fine");
    }
}
