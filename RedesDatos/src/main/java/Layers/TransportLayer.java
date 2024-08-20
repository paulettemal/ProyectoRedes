 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Layers;

import Grafo.Node;
import Server.Server;
import TopologiaFísica.TopologíaFisica;
import ec.edu.espol.redesdatos.RedesDatos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author icrio
 */
public class TransportLayer{
    private static int congestionVentana = 1;
    private static int numeroSecuencia = 0;
    private static Map<Integer, String> mapaSegmentos = new HashMap<>();
    
    // Divide datos en particiones de igual longitud
    public static String[] segmentacionPalabra(String palabraBinaria, int segSize){
        int numTotalSeg = (int) Math.ceil((double) palabraBinaria.length()/segSize);
        String[] segmentos = new String[numTotalSeg];
        
        //System.out.println("Longitud de palabra: " + palabra.length());
        //System.out.println("Número total de segmentos: " + numTotalSeg);
        
        for (int i=0; i<numTotalSeg; i++){
            int comienzo = i*segSize;
            int fin = Math.min(comienzo+segSize, palabraBinaria.length());
            segmentos[i] = palabraBinaria.substring(comienzo, fin);
        }
        return segmentos;
    }
    public static boolean establecerConexionToServer(){
        Node nodoCliente = null;
        Node nodoServidor = null;
        for(Node nodo :TopologíaFisica.topoFisica.getNodes()){
            Node nodoC= nodo.getSelf(RedesDatos.clienteIP);
            Node nodoS= nodo.getSelf(RedesDatos.servidor.getAddress());
            if(nodoC != null){
                nodoCliente = nodoC;
            }
            if(nodoS != null){
                nodoServidor = nodoS;
            }
        }
        Node intermedio = nodoCliente.getNeighbors().getFirst().getDestination();
        while (true){
            if(intermedio.getEstado()){
                Node server =  intermedio.getNeighbors().getFirst().getDestination();
                System.out.println("Conexión establecida con: " + server.getIp());
                break;
            }
            System.out.println("Conexión fallida, intentando de nuevo...");
        }
        return true;
    }


    // Calcula el num y suma
    public static int calcularChecksum(String segmento){
        int checksum = 0;
        for (char c:segmento.toCharArray()){
            if (c == '1') {
                checksum += 1;
            } else {
                checksum += 0;
            }
        }
        return checksum;
    }
    
    public static boolean puedeEnviar(int segmentoT){
        return segmentoT < congestionVentana;
    }
    
    public static void incrementarVentana(){
        int doble = congestionVentana * 2;
        congestionVentana = Math.min(doble, 64);
    }
    
    public static void disminuirVentana(){
        int div = congestionVentana / 2;
        congestionVentana = Math.max(div, 1);
    }
    
    public static int getCongestionVentana(){
        return congestionVentana;
    }
    
    public static void añadirSegmento(int numeroSecuencia, String segmento){
        mapaSegmentos.put(numeroSecuencia, segmento);
    }
    
    public static int getSiguienteNumeroSecuencia(){
        return numeroSecuencia++;
    }
    
    public static String reensamblarPalabra(int totalSegmentos){
        StringBuilder todo = new StringBuilder();
        for (int i=0; i<totalSegmentos; i++) {
            todo.append(mapaSegmentos.get(i));
        }
        return todo.toString();
    }
    
    public static void procesarDatosDesdeAplicacion(String datosEnBits) {
        //Segmentación
        String[] segmentos = segmentacionPalabra(datosEnBits, 10);
        System.out.println("Número de segmentos generados: " + segmentos.length);
        
        // Control de flujo y congestión
        int enviarSegments = 0;
        while (enviarSegments < segmentos.length) {
            if (puedeEnviar(enviarSegments)) {
                String segmento = segmentos[enviarSegments];
                int sumeroSec = getSiguienteNumeroSecuencia();
                int checksum = calcularChecksum(segmento);
                System.out.println("Segmento " + (enviarSegments + 1) + " | Checksum: " + checksum);
                añadirSegmento(sumeroSec, segmento);
                enviarSegments++;
                incrementarVentana();
            }
        }

        // Reensamblaje
        String todo = reensamblarPalabra(segmentos.length);
        System.out.println("Reensamblado en bits: " + todo);
    }
}