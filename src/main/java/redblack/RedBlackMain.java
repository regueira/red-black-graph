package redblack;

import redblack.graph.Graph;
import redblack.graph.Weight;

import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class RedBlackMain {

    private static final Pattern PATTERN = Pattern.compile("^\\d+ \\d+ [rb]", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
    private static final Scanner SCANNER = new Scanner(System.in).useDelimiter(Pattern.compile("[\\r\\n]"));


    public static void main(String[] args) {

        Graph graph = new Graph(true);

        int matrixSize = scanGraphSize();
        System.out.println("Thank you! Got " + matrixSize);

        IntStream.range(1, matrixSize + 1).forEach(graph::addVertex);

        boolean isRunning;
        do {

            final String edge = scanEdge();
            isRunning = !edge.equals("");
            if (isRunning) {
                String[] exec = edge.split(" ");

                int source = Integer.parseInt(exec[0]);
                int destination = Integer.parseInt(exec[1]);
                Weight weight = Weight.valueOf(exec[2].toUpperCase(Locale.ROOT));

                graph.addEdge(source, destination, weight);
            }

        } while (isRunning);

        System.out.println(graph.getAllGraphPaths());

    }

    /**
     * Get Matrix Size
     * @return Integer
     */
    private static int scanGraphSize() {
        System.out.print("Matrix size: ");
        while (!SCANNER.hasNextInt()) {
            System.out.println("That's not a number! Try again.");
            SCANNER.next(); //Remove invalid line from scanner
            System.out.print("Matrix size: ");
        }
        return SCANNER.nextInt();
    }

    /**
     * Scan Edges from input.
     * Valid input `Integer Integer R` or `Integer Integer B`
     * @return Edge String
     */
    private static String scanEdge() {
        System.out.print("Add Edge: ");

        while (!SCANNER.hasNext(PATTERN)) {
            String err = SCANNER.next();
            if (err.equals("")) {
               return err; //EXIT Scan Edge.
            }
            System.out.println("Error Edge format! Try again. " + err);
            System.out.print("Add Edge: ");
        }
        return SCANNER.next();
    }
}
