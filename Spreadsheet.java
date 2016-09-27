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


    // found on stackoverflow - for converting "row 0" to row A
    private static String getCharForNumber(int i) {
        return i > 0 && i < 27 ? String.valueOf((char)(i + 64)) : null;
    }


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
            rows = Integer.parseInt(params[0]); 
            cols = Integer.parseInt(params[1]); 
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

            for (int j=0; j < cols; j++) {
                String next = in.nextLine();
                input[cols*i+j] = next; 
                cells[i][j] = new Cell(next);
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

    public static void runMain() {
        Cell[][] cells = readInput();
        for (int i=0; i < cells.length; i++)
            for (int j=0; j < cells[0].length; j++)
                break;// eval(cell);
    }

    // main eval function
    // if its a literal - do nothing
    // if its a expression - make a list of its dependencies, and eval those recursively
    // updates a "visited" list of "nodes" (cells) to check for cycles

    public static HashSet visited = new HashSet();

    public static void eval(Cell rootCell) {
        if (rootCell.isLiteral())
            return;
        else {
            // reset the hashset first
            HashSet visited = new HashSet();
            // go through the arraylist of dependencies, and use the helper eval on each
            for (String s: rootCell.getDependencies()) {
                // convert string to actual dependent node
                // then call helper eval on that
                break;
            }
           
        }
         
    }

    public static void main(String[] args) {
        testCellConstructor();
        Cell[][] cells = readInput();
        debugDisplayCells(cells);

        System.out.println(getCharForNumber(0));
    }
}
