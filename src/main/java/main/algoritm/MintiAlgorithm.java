package main.algoritm;

import main.constants.Constants;
import main.dto.Root;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.IntStream;

public class MintiAlgorithm {

//    // Number of vertices in the graph
//    private static final int V = 7;
//
//    // A utility function to find the vertex with minimum
//    // key value, from the set of vertices not yet included
//    // in MST
//    int minKey(int key[], Boolean mstSet[])
//    {
//        // Initialize min value
//        int min = Integer.MAX_VALUE, min_index = -1;
//
//        for (int v = 0; v < V; v++)
//            if (mstSet[v] == false && key[v] < min) {
//                min = key[v];
//                min_index = v;
//            }
//
//        return min_index;
//    }
//
//    // A utility function to print the constructed MST
//    // stored in parent[]
//    void printMST(int parent[], int graph[][])
//    {
//        System.out.println("Edge \tWeight");
//        for (int i = 1; i < V; i++)
//            System.out.println(parent[i] + " - " + i + "\t"
//                    + graph[i][parent[i]]);
//    }
//
//    // Function to construct and print MST for a graph
//    // represented using adjacency matrix representation
//    void primMST(int graph[][])
//    {
//        // Array to store constructed MST
//        int parent[] = new int[V];
//
//        // Key values used to pick minimum weight edge in
//        // cut
//        int key[] = new int[V];
//
//        // To represent set of vertices included in MST
//        Boolean mstSet[] = new Boolean[V];
//
//        // Initialize all keys as INFINITE
//        for (int i = 0; i < V; i++) {
//            key[i] = Integer.MAX_VALUE;
//            mstSet[i] = false;
//        }
//
//        // Always include first 1st vertex in MST.
//        // Make key 0 so that this vertex is
//        // picked as first vertex
//        key[0] = 0;
//
//        // First node is always root of MST
//        parent[0] = -1;
//
//        // The MST will have V vertices
//        for (int count = 0; count < V - 1; count++) {
//
//            // Pick the minimum key vertex from the set of
//            // vertices not yet included in MST
//            int u = minKey(key, mstSet);
//
//            // Add the picked vertex to the MST Set
//            mstSet[u] = true;
//
//            // Update key value and parent index of the
//            // adjacent vertices of the picked vertex.
//            // Consider only those vertices which are not
//            // yet included in MST
//            for (int v = 0; v < V; v++)
//
//                // graph[u][v] is non zero only for adjacent
//                // vertices of m mstSet[v] is false for
//                // vertices not yet included in MST Update
//                // the key only if graph[u][v] is smaller
//                // than key[v]
//                if (graph[u][v] != 0 && mstSet[v] == false
//                        && graph[u][v] < key[v]) {
//                    parent[v] = u;
//                    key[v] = graph[u][v];
//                }
//        }
//
//        // Print the constructed MST
//        printMST(parent, graph);
//    }
//
//    public static int[][] createGraphFromRoots(List<Root> roots) {
//        int n = 0;
//        for (Root root : roots) {
//            n = Math.max(n, Math.max(root.getStartNode(), root.getTargetNode()) + 1);
//        }
//        int[][] graph = new int[n][n];
//        for (Root root : roots) {
//            graph[root.getStartNode()][root.getTargetNode()] = root.getLength();
//        }
////        int[][] newArray = new int[graph.length-1][graph.length-1];
////        for(int i=1, k=0; i<graph.length; i++,k++){
////            for(int j=1, m=0; j< graph.length; j++, m++){
////                newArray[k][m] = graph[i][j];
////            }
////        }
//        return removeRowAndColumn(Constants.MAX_NODE_NUMBER-1, 0, removeRowAndColumn(0,0,graph));
////        return removeRowAndColumn(0,0,graph);
//    }
//
//    public static int[][] removeRowAndColumn(int row, int column, int[][] array){
//        int[][] output = new int[array.length-1][array.length-1];
//        int p = 0;
//        for( int i = 0; i < array.length; ++i)
//        {
//            if ( i == row)
//                continue;
//            int q = 0;
//            for( int j = 0; j < array.length; ++j)
//            {
//                if ( j == column)
//                    continue;
//
//                output[p][q] = array[i][j];
//                ++q;
//            }
//            ++p;
//        }
//        return output;
//    }
//
//
//
////    public static int[][] createGraphFromRoots(List<Root> roots) {
////        int[][] graph = new int[roots.size()][roots.size()];
////        for(Root root : roots){
////            graph[root.getStartNode()][root.getTargetNode()] = root.getLength();
////        }
////        return graph;
////    }

    public void getMintiAlgorithm(List<Root> roots){
//        int[][] graph = createGraphFromRoots(roots);
//        primMST(graph);


    }
}
