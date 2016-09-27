import java.util.*;

public class Spreadsheet{

    public static void testCellConstructor() {
        Cell a = new Cell("2 3 4 5 6");
        Cell b = new Cell("2");
        Cell c = new Cell("A2");
        Cell d = new Cell("D5");
    }

    public static boolean validRowCol(int rows, int cols) {
        if (rows <= -1)
            return false;
        if (cols <= -1 || cols > 26)
            return false;
        return true;
    }

    public static boolean isOperator(String s) {
        if (s == "+" || s == "-" || s == "*" || s == "/")
            return true;    
        return false;
    }


    // using ASCII representation for converting "row 0" to row A
    private static String getStrForNumber(int i) {
        return String.valueOf((char)(i + 65));
    }

    // HashMap for mappings between Cells and their location strings
    public static HashMap<String, Cell> locationMap = new HashMap<String, Cell>();

    // return a 2d array of cells based on the input
    public static Cell[][] readInput() {
        Scanner in = new Scanner(System.in);

        System.out.print("spreadsheet size: ");
        String spreadParams = in.nextLine(); 
        String[] params = spreadParams.split(" ");

        // do error checking here later...
        int rows = -1;
        int cols = -1;
        try {
            cols = Integer.parseInt(params[0]); 
            rows = Integer.parseInt(params[1]); 
        } catch (NumberFormatException e) {
            System.out.println("Error: spreadsheet dimensions must be two integers");
        }

        if (!validRowCol(rows, cols))
            throw new IllegalArgumentException("bad row/col input"); 

        System.out.println("rows: " + rows + ", " + "cols: " + cols);

        String[] input = new String[rows*cols];
        Cell[][] cells = new Cell[rows][cols];

        // use the input to populate the array of Cells
        for (int i=0; i < rows; i++) {
            String rowChar = getStrForNumber(i);
            for (int j=0; j < cols; j++) {
                String next = in.nextLine();
                input[cols*i+j] = next; 
                cells[i][j] = new Cell(next);

                // use cellString to map each Cell to a string for O(1) lookup
                String cellString = rowChar + String.valueOf(j+1);
                // System.out.print(cellString);
                locationMap.put(cellString, cells[i][j]);
            }
        }

        System.out.println("\nYour input:");
        for (String s : input) {
            System.out.println(s);
        }
        return cells;
    }

    public static void debugDisplayCells(Cell[][] cells) {
       for (int i=0; i < cells.length; i++)
           for (int j=0; j < cells[0].length; j++)
               System.out.println(cells[i][j]);
    }

    public static HashSet visited = new HashSet();

    public static void runMain() {
        Cell[][] cells = readInput();
        for (int i=0; i < cells.length; i++)
            for (int j=0; j < cells[0].length; j++) {
                // reset the hashset first
                HashSet visited = new HashSet();
                eval(cells[i][j]);
                System.out.println(String.format("%.5f", cells[i][j].getVal()));
            }
    }

    // main eval function
    // if its a literal - do nothing
    // if its a expression - make a list of its dependencies, and eval those recursively
    // updates a "visited" list of "nodes" (cells) to check for cycles

    // in future versions of this project - could optimize the eval calls with
    // a topological sort of the "graph" of cells so that we minimize repeated evals
    public static void eval(Cell rootCell) {
        if (rootCell.isLiteral())
            return;
            //return cell.getVal();
        else if (rootCell.getDependencies().size() > 0) {
            if (visited.contains(rootCell)) {
                System.out.println("CYCLE FOUND TODO");
            }
            visited.add(rootCell);
            // go through the arraylist of dependencies, and use the helper eval on each
            for (String s: rootCell.getDependencies()) {
                // convert string to actual dependent node
                // then call helper eval on that
                Cell dependent = locationMap.get(s);
                eval(dependent);
            }
        }
        else {
            // evaluate some math expression then
            double result = evalHelper(rootCell.getStrExpr());
            rootCell.setVal(result);
            rootCell.setLiteral(true);
        }
    }

    // this function takes some math expression and "solves it"
    // assume we have the information we need (i.e. no dependencies)
    public static double evalHelper(String expression) {

        // initialize Stack
        // for each element, if its a number, add it to the stack
        // if its an operator? then pop the last two and perform the operator
        // keep going until stack empty and we have reached the end of the expression 
        
        Stack tokenStack = new Stack();
        for (String word : expression.split(" ")) {
            if (isOperator(word)) {
                double op2 = (double)tokenStack.pop(); 
                double op1 = (double)tokenStack.pop();
                double result = Integer.MAX_VALUE; // some default
                if (word == "+")
                    result = op1 + op2;
                else if (word == "-")
                    result = op1 - op2;
                else if (word == "*")
                    result = op1 * op2;
                else if (word == "/")
                    result = op1 / op2;
                tokenStack.push(new Double(result));
            }
            else 
                System.out.println("word here is " + word);
                tokenStack.push(Double.parseDouble(word));
        }
        if (tokenStack.size() == 1) 
            return (double)tokenStack.pop();
        // if we are here, then we had a bad expression...
        System.out.println("stack problem TODO");
        throw new IllegalArgumentException("bad row/col input"); 
    }


    public static void main(String[] args) {
        // testCellConstructor();
        // Cell[][] cells = readInput();
        // debugDisplayCells(cells);
        runMain();
    }
}
