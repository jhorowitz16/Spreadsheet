import java.util.*;

public class Cell {

    private boolean literal; // literal means just a number
    // figure out a good definition of literal
    // literal as in 3, or literal as in 4/3
    // make sure variables set corretly with constructor TODO
    private String strExpr;
    private double val;
    private ArrayList<String> dependencies; // we want easy resizing of this

    public Cell(String strExpr) {

        this.strExpr = strExpr;
        this.literal = true;
        this.dependencies = new ArrayList<String>();
        String[] words = strExpr.split(" ");

        if (words.length == 1) { 
            // just a number
            this.val = Double.parseDouble(words[0]); 
            return;
        }

        for (int i=0; i<words.length; i++) {
            char c = words[i].charAt(0);
            if ( c >= (int)'A' && c <= (int)'Z' ) {
                // there's a dependency!
                dependencies.add(words[i]);
                this.literal = false;
            }
            else if (isOperator(c))
                this.literal = false; // not just a number then...
        }
    }


    // accessors
    public boolean isLiteral() {
        return this.literal;
    }
    public String getStrExpr() {
        return this.strExpr;
    }
    public double getVal() {
        return this.val;
    }
    public ArrayList<String> getDependencies() {
        return this.dependencies;
    }


    // mutators
    public void setLiteral(boolean newBool) {
        this.literal = newBool; 
    }
    public void setStrExpr(String newStr) {
        this.strExpr = newStr;
    }
    public void setVal(double newVal) {
        this.val = newVal;
    }


    // I/O
    public String toString() {
        return "{ Cell " + this.strExpr + " }";
    }
    public String getOutput() {
        if (literal)
            return String.format("%.5f", this.val);
        return strExpr; // TODO should always return the string
    }

    // utils
    public static boolean isOperator(char s) {
        if (s == '+' || s == '-' || s == '*' || s == '/')
            return true;    
        return false;
    }
}
