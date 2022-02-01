package redblack.graph;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Slf4j
public class Graph {

    private final Map<Integer, List<Edge>> adjMatrix;

    private final boolean bidirectional;

    public Graph(final boolean bidirectional) {
        this.adjMatrix = new HashMap<>();
        this.bidirectional = bidirectional;
    }

    /**
     * Add a Vertex
     * @param source
     */
    public void addVertex(Integer source) {
        adjMatrix.put(source, new LinkedList<>());
    }

    /**
     * Add a Edge to Matrix
     * @param source Vertex Integer
     * @param destination Vertex Integer
     * @param weight Red or Black (r|b)
     */
    public void addEdge(Integer source, Integer destination, Weight weight) {

        if (!adjMatrix.containsKey(source)) {
            addVertex(source);
        }

        if (!adjMatrix.containsKey(destination)) {
            addVertex(destination);
        }

        adjMatrix.get(source).add(new Edge(source, destination, weight));
        if (bidirectional) {
            adjMatrix.get(destination).add(new Edge(destination, source, weight));
        }
    }

    /**
     * Get the adjacency list
     * @param source Integer
     * @return List of Edges
     */
    private List<Edge> getAdjVertices(Integer source) {
        return adjMatrix.get(source);
    }

    /**
     * get all Graph Paths with more than two REDs as Weight
     * @return Integer
     */
    public Integer getAllGraphPaths() {
        Integer pathCount = 0;
        Integer destination = adjMatrix.size();
        for (Integer source = 1; destination - source + 1 > 2; source++) {
            for(Integer currDest = destination; currDest - source + 1 > 2 ; currDest--) {
                pathCount += getAllPaths(source , currDest);
            }
        }
        return pathCount;
    }

    public Integer getAllPaths(Integer source, Integer destination) {
        boolean[] isVisited = new boolean[adjMatrix.size()];
        List<Integer> pathList = new ArrayList<>();

        pathList.add(source);

        return getAllPathsRecursive(source, destination, isVisited, pathList, 0);
    }

    /**
     * Recursive.
     * Calculate paths between source and destination
     * @param source Integer
     * @param destination Integer
     * @param isVisited boolean[]
     * @param localPathList List
     * @param weight Integer
     * @return Integer - count of valid paths.
     */
    private Integer getAllPathsRecursive(Integer source, Integer destination, boolean[] isVisited,
                                   List<Integer> localPathList, Integer weight) {
        Integer allPaths = 0;
        if (source.equals(destination)) { // Found a Path with more than 2 RED
            return weight >= 2 ? 1 : 0;
        }

        isVisited[source-1] = true;

        for (Edge i : getAdjVertices(source)) {

            if (!isVisited[i.getDestination()-1] && i.getDestination() > i.getSource()) {

                localPathList.add(i.getDestination());
                if (Weight.R.equals(i.getWeight())) {
                    weight++;
                }
                allPaths += getAllPathsRecursive(i.getDestination(), destination, isVisited,
                        localPathList, weight);

                localPathList.remove(i.getDestination());
            }
        }

        isVisited[source-1] = false;
        return allPaths;
    }

}
