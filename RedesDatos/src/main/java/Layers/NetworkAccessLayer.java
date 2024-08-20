
package Layers;

import Server.IPPackage;
import java.util.Random;

/**
 *
 * @author icrio
 */
public class NetworkAccessLayer {
    // Simulación de una dirección MAC
    private String macAddress;

    // Constructor para inicializar la dirección MAC
    public NetworkAccessLayer(String macAddress) {
        this.macAddress = macAddress;
    }

    // Método para simular la encapsulación de un paquete
    public void encapsulate(IPPackage packet) {
        System.out.println("Encapsulando el paquete en un frame.");
        System.out.println("Añadiendo cabecera de enlace de datos con la dirección MAC: " + macAddress);
        packet.setFrameHeader("Frame Header: MAC " + macAddress);
    }

    // Método para simular la transmisión de un frame a la capa física
    public void transmitFrame(IPPackage packet) {
        // Aquí podría haber lógica adicional para interactuar con la capa física.
        System.out.println("Frame transmitido: " + packet.getFrameHeader() + " | Datos: " + packet.getData());
    }

    // Método para simular la recepción de un frame desde la capa física
    public void receiveFrame(IPPackage packet) {
        // Aquí podría haber lógica adicional para validar la cabecera del frame y procesar los datos.
        System.out.println("Frame recibido con cabecera: " + packet.getFrameHeader());
        decapsulate(packet);
    }

    // Método para simular la decapsulación de un frame
    public void decapsulate(IPPackage packet) {
        System.out.println("Decapsulando el frame.");
        packet.removeFrameHeader();
    }

    // Getters y Setters
    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }
    
    public static String generarMAC() {
        Random random = new Random();
        byte[] macAddr = new byte[6];
        random.nextBytes(macAddr);

        // Formatear bytes en hexadecimal y construir la dirección MAC
        StringBuilder macStr = new StringBuilder(18);
        for (int i = 0; i < macAddr.length; i++) {
            // Convertir cada byte en un valor hexadecimal sin signo
            macStr.append(String.format("%02x", macAddr[i]));
            if (i != macAddr.length - 1) {
                macStr.append(":");
            }
        }
        return macStr.toString();
    }

    
}
