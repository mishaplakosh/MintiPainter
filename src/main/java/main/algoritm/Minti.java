package main.algoritm;

import main.constants.Constants;
import main.dto.MintyResult;
import main.dto.Root;
import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.util.*;

public class Minti {
    public static List<String> shortestPathString;
    public static List<String> runMinty(List<Root> roots) {
//        SimpleWeightedGraph<Integer, DefaultWeightedEdge> graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
//
//        // Додамо вершини
//        for (int i = 1; i <= 8; i++) {
//            graph.addVertex(i);
//        }
//
//        // Додамо ребра та їх ваги
//        graph.setEdgeWeight(graph.addEdge(1, 2), 4);
//        graph.setEdgeWeight(graph.addEdge(1, 5), 18);
//        graph.setEdgeWeight(graph.addEdge(2, 3), 7);
//        graph.setEdgeWeight(graph.addEdge(3, 4), 5);
//        graph.setEdgeWeight(graph.addEdge(3, 8), 37);
//        graph.setEdgeWeight(graph.addEdge(4, 5), 9);
//        graph.setEdgeWeight(graph.addEdge(4, 6), 3);
//        graph.setEdgeWeight(graph.addEdge(5, 6), 8);
//        graph.setEdgeWeight(graph.addEdge(6, 7), 6);
//        graph.setEdgeWeight(graph.addEdge(6, 8), 14);
//        graph.setEdgeWeight(graph.addEdge(7, 8), 10);
//        graph.setEdgeWeight(graph.addEdge(7, 2), 5);

        SimpleWeightedGraph<Integer, DefaultWeightedEdge> graph = fromRootsToGraph(roots);
        // Застосуємо алгоритм Мінті та виведемо результат
        DijkstraShortestPath<Integer, DefaultWeightedEdge> shortestPath = new DijkstraShortestPath<>(graph);
        shortestPathString = new ArrayList<>();
        for (Integer v : graph.vertexSet()) {
            shortestPathString.add("Найкоротший шлях від 1 до " + v + ": " + shortestPath.getPath(1, v) + " з довжиною " + shortestPath.getPathWeight(1, v));
            System.out.println("Shortest path from 1 to " + v + " is " + shortestPath.getPath(1, v) + " with weight " + shortestPath.getPathWeight(1, v));
        }
        return shortestPathString;
    }

    public static List<MintyResult> getMintyShortestPath(List<Root> roots){
        SimpleWeightedGraph<Integer, DefaultWeightedEdge> graph = fromRootsToGraph(roots);
        // Застосуємо алгоритм Мінті та виведемо результат
        DijkstraShortestPath<Integer, DefaultWeightedEdge> shortestPath = new DijkstraShortestPath<>(graph);
        ArrayList<MintyResult> mintyResults = new ArrayList<>();
        for (Integer v : graph.vertexSet()) {
            mintyResults.add(new MintyResult(v, shortestPath.getPath(1, v)));
        }
        return mintyResults;
    }

    public static SimpleWeightedGraph<Integer, DefaultWeightedEdge> fromRootsToGraph(List<Root> roots){
        SimpleWeightedGraph<Integer, DefaultWeightedEdge> graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
        // Додамо вершини
        for (int i = 1; i <= Constants.NODE_NUMBER; i++) {
            graph.addVertex(i);
        }
        for(Root root : roots){
            graph.setEdgeWeight(graph.addEdge(root.getStartNode(), root.getTargetNode()), root.getLength());
        }
        return graph;
    }

    public static Map<Integer, Integer> mintyAlgorithm(Graph<Integer, DefaultWeightedEdge> g, int s) {
        Map<Integer, Integer> dist = new HashMap<>();
        for (Integer v : g.vertexSet()) {
            dist.put(v, Integer.MAX_VALUE);

        }
        dist.put(s, 0);
        for (int i = 1; i <= g.vertexSet().size() - 1; i++) {
            System.out.println("Iteration " + i + ": ");
            for (DefaultWeightedEdge e : g.edgeSet()) {
                Integer u = g.getEdgeSource(e);
                Integer v = g.getEdgeTarget(e);
                int w = (int) g.getEdgeWeight(e);
                if (dist.get(u) != Integer.MAX_VALUE && dist.get(u) + w < dist.get(v)) {
                    dist.put(v, dist.get(u) + w);
                    System.out.println("\tRelaxed edge: " + u + " -> " + v + ", new weight: " + dist.get(v));
                }
            }
        }
        return dist;
    }
}