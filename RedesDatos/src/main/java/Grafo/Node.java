package Grafo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Node {
    String ip;
    String contenido;
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

    public Node getSelf(String iP){
        if(ip.equals(iP)){
            return this;
        }
        return null;
    }
    public boolean getEstado(){
        int randomInt = new Random().nextInt(101);
        if(randomInt > 80){
            return false;
        }
        return true;
    }

}
