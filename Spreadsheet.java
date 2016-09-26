import java.io.BufferedInputStream;
import java.util.Scanner;

public class Spreadsheet{

    public static void testCellConstructor() {
        Cell a = new Cell("2 3 4 5 6");
        Cell b = new Cell("2");
        Cell c = new Cell("A2");
        Cell d = new Cell("D5");

        System.out.print(a.getLiteral());
        System.out.print(b.getLiteral());
        System.out.print(c.getLiteral());
        System.out.println(d.getLiteral());
    }


    public static boolean validRowCol(int rows, int cols) {
        if (rows <= -1)
            return false;
        if (cols <= -1 || cols > 26)
            return false;
        return true;
    }

    public static void readInput() {

        
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
        // in.nextLine(); // deal with the extra enter

        for (int i=0; i < input.length; i++) {
            input[i] = in.nextLine();
        }

        System.out.printf("\nYour input:\n");
        for (String s : input) {
            System.out.println(s);
        }

    }


    public static void main(String[] args) {
        System.out.println("Hello World");
        testCellConstructor();
        readInput();
    }

}
