package Grafo;

import java.util.ArrayList;
import java.util.List;

public class Node {
    String ip;
    List<Edge> neighbors;

    public Node(String ip) {
        this.ip = ip;
        this.neighbors = new ArrayList<>();
    }

    public void addNeighbor(Edge edge) {
        neighbors.add(edge);
    }
    public String getIp(){
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public List<Edge> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(List<Edge> neighbors) {
        this.neighbors = neighbors;
    }
}
