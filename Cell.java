public class Cell {

    private boolean literal;
    private String strExpr;

    public Cell(String strExpr) {

        this.strExpr = strExpr;
        this.literal = true;
        for (int i=0; i<strExpr.length(); i++) {
            char c = strExpr.charAt(i);
            if ( c >= (int)'A' && c <= (int)'Z' ) {
                // there's a dependency!
                this.literal = false;
                break;
            }
        }
    }

    public boolean getLiteral() {
        return this.literal;
    }

    public String toString() {
        return "{ Cell " + this.strExpr + " }";
    }

}
