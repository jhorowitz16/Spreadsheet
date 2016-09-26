public class Cell {

    private boolean literal;

    public Cell(String strExpr) {

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
}
