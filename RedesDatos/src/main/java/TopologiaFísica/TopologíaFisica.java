package TopologiaFísica;

import Grafo.Graph;
import Grafo.Node;
import ec.edu.espol.redesdatos.RedesDatos;

public class TopologíaFisica {
    public static Graph topoFisica = new Graph();

    public TopologíaFisica(){
        //Cliente
        Node cliente = new Node(RedesDatos.clienteIP);
        topoFisica.addNode(cliente);
        //Dispositivo intermediario
        Node intermediario = new Node("192.167.1.1");
        topoFisica.addNode(intermediario);

        topoFisica.addEdge(cliente,intermediario,1);

        Node Server = new Node(RedesDatos.servidor.getAddress());
        topoFisica.addNode(Server);
        topoFisica.addEdge(intermediario,Server, 2);


    }

}
