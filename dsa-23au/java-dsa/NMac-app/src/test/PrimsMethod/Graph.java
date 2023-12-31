// Prim’s method to find the minimum-spanning tree of a graph.
// Step 1. Put the entire graph (all nodes and edges) in a bag.
// Step 2. Pull any one node out of the bag; the edges that are incident on this node
// are now crossing the boundary of the bag.
// Step 3. Among all edges that cross the boundary of the bag, pick the one with the
// minimum weight. Add this edge to the MST.
// Step 4. Follow this edge to the node inside the bag, and pull that node out of the
// bag. In doing so, if some edge has both its end nodes outside the bag, then
// this edge must no longer cross the boundary of the bag.
// Step 5. Repeat steps 3 and 4 until the bag is empty.

package graphEdgeVertex;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Graph extends MST_Graph<String, Integer> {

    public Map<String, Vertex> verticesMap = new HashMap<>();

    public class Edge {
        public int weight;
        public boolean isIncluded;

        public Edge(int weight) {
            this.weight = weight;
            this.isIncluded = false;
        }
    }

    public class Vertex {
        public String label = null;
        public Map<Vertex, Edge> edges = new HashMap<>();
        public boolean isVisited = false;

        public Vertex(String label) {
            this.label = label;
            this.edges = new HashMap<>();
            this.isVisited = false;
        }
    }

    @Override
    public boolean addEdge(String nodeKey1, String nodeKey2, Integer edge) {
        return false;
    }

    public void addNode(String label) {
        verticesMap.put(label, new Vertex(label));
    }

    @Override
    public MST_Graph.Edge<Integer> getEdge(String nodeKey1, String nodeKey2) {
        return null;
    }

    @Override
    public MST_Graph.Edge<Integer> getMinEdgeToUnvisitedNode(String sourceNodeKey) {
        return null;
    }

    @Override
    public void printMermaidDiagram() {

    }

    public void addEdge(String srcNode, String destNode, int weight) {
        Vertex node1 = verticesMap.get(srcNode);
        Vertex node2 = verticesMap.get(destNode);

        Edge edge = new Edge(weight);

        node1.edges.put(node2, edge);
        node2.edges.put(node1, edge);
    }

    public Edge getMinEdgeToUnvisitedNode(Vertex source) {
        int minWeightSoFar = Integer.MAX_VALUE;
        Edge minWeightEdgeSoFar = null;

        for (Entry<Vertex, Edge> entry : source.edges.entrySet()) {
            Edge e = entry.getValue();
            Vertex dest = entry.getKey();
            if (dest.isVisited) {
                continue;
            }
            if (e.weight < minWeightSoFar) {
                minWeightSoFar = e.weight;
                minWeightEdgeSoFar = e;
            }
        }
        return minWeightEdgeSoFar;
    }

    // Helper method to check if there are still unvisited nodes
    private boolean hasUnvisitedNodes() {
        for (Vertex v : verticesMap.values()) {
            if (!v.isVisited) {
                return true;
            }
        }
        return false;
    }

    // Method to execute Prim's algorithm
    public void executePrimsAlgorithm(String startNodeLabel) {
        Vertex startNode = verticesMap.get(startNodeLabel);
        startNode.isVisited = true;

        while (hasUnvisitedNodes()) {
            Edge minEdge = null;
            Vertex nextNode = null;

            for (Vertex v : verticesMap.values()) {
                if (!v.isVisited) continue;

                Edge tempEdge = getMinEdgeToUnvisitedNode(v);
                if (tempEdge != null) {
                    Vertex dest = getUnvisitedNodeConnectedToEdge(v, tempEdge);
                    if (dest != null && (minEdge == null || tempEdge.weight < minEdge.weight)) {
                        minEdge = tempEdge;
                        nextNode = dest;
                    }
                }
            }

            if (minEdge != null) {
                minEdge.isIncluded = true;
                nextNode.isVisited = true;
            }
        }
    }

    // Helper method to get the unvisited node connected to an edge
    private Vertex getUnvisitedNodeConnectedToEdge(Vertex source, Edge edge) {
        for (Entry<Vertex, Edge> entry : source.edges.entrySet()) {
            if (entry.getValue() == edge && !entry.getKey().isVisited) {
                return entry.getKey();
            }
        }
        return null;
    }

    // Method to execute Prim's method algorithm
    public static void main(String[] args) {
        Graph graph = new Graph();

        // Add nodes
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");

        // Add edges
        graph.addEdge("A", "B", 1);
        graph.addEdge("B", "C", 3);
        graph.addEdge("C", "D", 4);
        graph.addEdge("A", "D", 5);
        graph.addEdge("B", "D", 2);

        // Execute Prims algorithm starting from node "A"
        graph.executePrimsAlgorithm("A");

        // Print the results
        graph.printMST();
    }

    // Method to print the Minimum Spanning Tree
    public void printMST() {
        System.out.println("Minimum Spanning Tree:");
        for (Vertex v : verticesMap.values()) {
            for (Entry<Vertex, Edge> entry : v.edges.entrySet()) {
                if (entry.getValue().isIncluded) {
                    System.out.println(v.label + " - " + entry.getKey().label + " : " + entry.getValue().weight);
                }
            }
        }
    }
}

