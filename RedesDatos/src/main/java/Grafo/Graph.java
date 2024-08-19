package Grafo;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    List<Node> nodes;

    public Graph() {
        this.nodes = new ArrayList<>();
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public void addEdge(Node source, Node destination, int weight) {
        Edge edge = new Edge(source, destination, weight);
        source.addNeighbor(edge);
    }

//    // Método para simular el envío de un paquete
//    public void sendPacket(Node source, Node destination, String packetData) {
//        // Aquí iría la lógica de envío, como un algoritmo de búsqueda (DFS, BFS, Dijkstra, etc.)
//        System.out.println("Sending packet from " + source.ip + " to " + destination.ip);
//        // Implementar el recorrido del grafo aquí...
//    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }
}
