package redblack.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

public class GraphTest {

    private static final Integer MATRIX_SIZE = 5;

    @Test
    void simpleGraphSuccessTest() {
        Graph graph = new Graph(true);
        IntStream.range(1, MATRIX_SIZE + 1).forEach(graph::addVertex);

        graph.addEdge(1, 2, Weight.B);
        graph.addEdge(2, 3, Weight.R);
        graph.addEdge(3, 4, Weight.R);
        graph.addEdge(4, 5, Weight.B);
        Assertions.assertEquals(graph.getAllGraphPaths(), 4);
    }

    @Test
    void emptyGraphSuccessTest() {
        Graph graph = new Graph(true);
        IntStream.range(1, MATRIX_SIZE + 1).forEach(graph::addVertex);
        Assertions.assertEquals(graph.getAllGraphPaths(), 0);
    }

    @Test
    void allBlackGraphSuccessTest() {
        Graph graph = new Graph(true);
        IntStream.range(1, MATRIX_SIZE + 1).forEach(graph::addVertex);

        graph.addEdge(1, 2, Weight.B);
        graph.addEdge(2, 3, Weight.B);
        graph.addEdge(3, 4, Weight.B);
        graph.addEdge(4, 5, Weight.B);
        Assertions.assertEquals(graph.getAllGraphPaths(), 0);
    }

    @Test
    void complexGraphSuccessTest() {
        Graph graph = new Graph(true);
        IntStream.range(1, MATRIX_SIZE + 1).forEach(graph::addVertex);

        graph.addEdge(1, 2, Weight.B);
        graph.addEdge(1, 3, Weight.R);
        graph.addEdge(2, 3, Weight.R);
        graph.addEdge(2, 4, Weight.B);
        graph.addEdge(3, 4, Weight.R);
        graph.addEdge(4, 5, Weight.B);
        Assertions.assertEquals(graph.getAllGraphPaths(), 6);
    }

}
