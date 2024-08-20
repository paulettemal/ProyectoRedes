package Layers;

import java.util.Arrays;
import Server.IPPackage;
/**
 *
 * @author icrio
 */
public class InternetLayer {
    
    static IPPackage ippackage;
    
    public InternetLayer() {
    }
    
    // Método para crear el paquete
    public IPPackage create_Package(String sourceIP, String destinationIP, byte[] data) {
        return new IPPackage(sourceIP, destinationIP, data);
    }

    // Simular protocolo ICMP (sending echo request and receiving echo reply)
    public void sendICMPRequest(String destinationIP) {
        System.out.println("Sending ICMP Echo Request to " + destinationIP);
        // Simulate receiving a reply
        System.out.println("Received ICMP Echo Reply from " + destinationIP);
    }

    // Simular protocolo IPsec (encrypting and authenticating IP packets)
    public IPPackage applyIPsec(IPPackage packet, String encryptionKey) {
        System.out.println("Applying IPsec to packet...");
        byte[] encryptedData = Arrays.copyOf(packet.getData(), packet.getData().length); // For simplicity, just copying the data
        
        return new IPPackage(packet.getSourceIP(), packet.getDestinationIP(), encryptedData);
    }

    // Simular protocolo IGMP(joining a multicast group)
    public void joinMulticastGroup(String multicastGroupIP) {
        System.out.println("Joining multicast group: " + multicastGroupIP);
    }

    // Enrutar paquete al siguiente host
    public void routePacket(IPPackage packet) {
        String nextHop = determineNextHop(packet.getDestinationIP());
        sendPacketToNextHop(packet, nextHop);
    }

    // Encontrar/Guardar direccion del siguiente host
    private String determineNextHop(String destinationIP) {
        return destinationIP; 
    }

    // Enviar paquete al siguiente host
    private void sendPacketToNextHop(IPPackage packet, String nextHop) {
        System.out.println("Packet sent to " + nextHop);
    }
    
    // Procesar datos desde la AppLayer
    public void processDataFromAppLayer( String sourceIP, String destinationIP, String appData) {
        System.out.println("Processing data from AppLayer...");

        // Convertir datos de aplicación en bytes
        byte[] dataBytes = appData.getBytes();

        // Crear un paquete IP con los datos recibidos
        IPPackage IPPackage = create_Package(sourceIP, destinationIP, dataBytes);
        
        System.out.println("Data processed from AppLayer.");
    }

    // Procesar datos desde la capa de transporte
    public void processDataFromTransportLayer(IPPackage ipPacket) {
        System.out.println("Processing data from TransportLayer...");
        
        //Enrutar el paquete
        routePacket(ipPacket);

        System.out.println("Data processed and routed to the next host.");
    }
}
