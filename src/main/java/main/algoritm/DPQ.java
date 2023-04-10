package main.algoritm;

import main.constants.Constants;
import main.dto.Root;

import java.util.*;
import java.util.stream.IntStream;

public class DPQ {
    private int dist[];
    private Set<Integer> settled;
    private PriorityQueue<Node> pq;
    private int V; // Number of vertices
    List<List<Node>> adj;

    public DPQ(int V)
    {
        this.V = V;
        dist = new int[V];
        settled = new HashSet<Integer>();
        pq = new PriorityQueue<Node>(V, new Node());
    }

    // Function for Dijkstra's Algorithm
    public void dijkstra(List<List<Node> > adj, int src)
    {
        this.adj = adj;

        for (int i = 0; i < V; i++)
            dist[i] = Integer.MAX_VALUE;

        // Add source node to the priority queue
        pq.add(new Node(src, 0));

        // Distance to the source is 0
        dist[src] = 0;
        while (settled.size() != V) {
            //when the priority queue is empty, return
            if(pq.isEmpty())
                return ;
            // remove the minimum distance node
            // from the priority queue
            int u = pq.remove().node;

            // adding the node whose distance is
            // finalized
            settled.add(u);

            e_Neighbours(u);
        }
    }

    // Function to process all the neighbours
    // of the passed node
    private void e_Neighbours(int u)
    {
        int edgeDistance = -1;
        int newDistance = -1;
        int settledDistance = -1;

        // All the neighbors of v
        for (int i = 0; i < adj.get(u).size(); i++) {
            Node v = adj.get(u).get(i);

            System.out.println("To node:" + v.node + " Cost " + v.cost);

            // If current node hasn't already been processed
            if (!settled.contains(v.node)) {
                edgeDistance = v.cost;
                newDistance = dist[u] + edgeDistance;

                // If new distance is cheaper in cost
                if (newDistance < dist[v.node])
                    dist[v.node] = newDistance;


                // Add the current node to the queue
                pq.add(new Node(v.node, dist[v.node]));
            }
        }

        System.out.println("New distance: " + edgeDistance);
        System.out.println();
    }

    // Driver code
    public static void runMinti(ArrayList<Root> root)
    {
//        int V = 8;
        int source = 0;


//        List<List<Node> > adj = new ArrayList<List<Node> >();
//
//        // Initialize list for every node
//        for (int i = 0; i < V; i++) {
//            List<Node> item = new ArrayList<Node>();
//            adj.add(item);
//        }
//
//        // Inputs for the DPQ graph
//        adj.get(0).add(new Node(1, 4));
//        adj.get(0).add(new Node(4, 18));
//        adj.get(1).add(new Node(2, 7));
//        adj.get(1).add(new Node(3, 11));
//
//        adj.get(2).add(new Node(3, 4));
//        adj.get(2).add(new Node(7, 37));
//
//        adj.get(3).add(new Node(4, 9));
//        adj.get(3).add(new Node(5, 3));
//        adj.get(4).add(new Node(5, 8));
//        adj.get(5).add(new Node(6, 6));
//        adj.get(5).add(new Node(7, 14));
//        adj.get(6).add(new Node(7, 10));
//        adj.get(6).add(new Node(1, 5));

        List<List<Node> > adj = fromRootListToNodeList(root);


        // Calculate the single source shortest path
        DPQ dpq = new DPQ(Constants.MAX_NODE_NUMBER);
        dpq.dijkstra(adj, source);


        // Print the shortest path to all the nodes
        // from the source node
        System.out.println("The shorted path from node :");
        for (int i = 0; i < dpq.dist.length; i++)
            System.out.println(source + " to " + i + " is "
                    + dpq.dist[i]);
    }

    public static List<List<Node>> fromRootListToNodeList(ArrayList<Root> root){
        // Adjacency list representation of the
        // connected edges
        List<List<Node>> adj = new ArrayList<>();
        // Initialize list for every node
        for (int i = 0; i < Constants.MAX_NODE_NUMBER; i++) {
            List<Node> item = new ArrayList<Node>();
            adj.add(item);
        }

        for(Root value : root){
            adj.get(value.getStartNode() - 1).add(new Node(value.getTargetNode() - 1, value.getLength()));
        }
        return adj;
    }


}

// Class to represent a node in the graph
class Node implements Comparator<Node> {
    public int node;
    public int cost;

    public Node()
    {
    }

    public Node(int node, int cost)
    {
        this.node = node;
        this.cost = cost;
    }

    @Override
    public int compare(Node node1, Node node2)
    {
        if (node1.cost < node2.cost)
            return -1;
        if (node1.cost > node2.cost)
            return 1;
        return 0;
    }
}
