/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import Layers.TransportLayer;
import Reader.Reader;
import static ec.edu.espol.redesdatos.RedesDatos.verifyInput;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author icrio
 */
public class Server {
    ArrayList<String> content;
    String name;
    String Address;
    String Layer;

    public Server(String name, String Address) {
        this.name = name;
        this.Address = Address;
        this.content = new ArrayList<>();
        this.Layer = null;
    
    }
    
    public ArrayList<String> getContent() {
        return content;
    }

    public void setContent(String Content) {
        this.content.add(Content);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }
    
    
    public static Server createServer(){
        
        System.out.println("\n Ingrese un nombre para su servidor: ");
        Scanner sc= new Scanner(System.in);
        sc.useDelimiter("\n");
        String nombre= sc.nextLine();
        System.out.println("Ingrese una direccion ip valida para su servidor: ///.///.///.///");
        String direccion= sc.nextLine();
        boolean verify = verifyAddress(direccion);
        while(verify != true){
            System.out.println("Direccion de iP ingresada no valida. \n Ingrese una direccion correcta:");
            String direccion2= sc.nextLine();
            verify = verifyAddress(direccion2);
            direccion = direccion2;
        }
        Server servidor= new Server(nombre, direccion);
        return servidor;
    }
    
    public static boolean verifyAddress(String dir){
        String[] adres = dir.split("\\.");
        if(adres.length <4 || adres.length>4) return false;
        if(adres.length==4){
            for(String s: adres){
                try {
                    int numero = Integer.parseInt(s);
                    if(numero>= 255 || numero<0) return false;
                    
                } catch (NumberFormatException e) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    public String getLayer() {
        return Layer;
    }

    public void setLayer(String Layer) {
        this.Layer = Layer;
    }


    @Override
    public String toString() {
        return "Informacion del Server: \n Nombre: " + name + "\n Direccion: " + Address;
    }
    
    //controlar el null;
    public static String retornarContenido(Scanner sc, ArrayList<String> content){
        System.out.println("Archivos guardados en el servidor:");
        for(int i = 0; i<content.size();i++){
            System.out.println((i+1)+". "+content.get(i));
        }
        System.out.println("Ingrese una opcion:");
        int eleccion = Integer.parseInt(sc.nextLine());
        boolean verify = verifyInput(eleccion,3);
        while(verify != true){
            System.out.println("Opcion no existente.\nIngrese una opcion correcta:");
            int eleccion2= Integer.parseInt(sc.nextLine());
            System.out.println(eleccion2);
            verify = verifyInput(eleccion2,3);
            System.out.println(verify);
            eleccion = eleccion2;
        }
        //generar metodo convertir archivo binario a texto;
        ArrayList<String> archivoLineas = Reader.readTxt(content.get(eleccion-1));
        for(String s: archivoLineas) System.out.println(Reader.binaryToText(s));
        System.out.println("\n");
        return content.get(eleccion-1);
    }
    
   public static void errorServidor(Scanner sc, int numero, ArrayList<String> content, String checkSum, IPPackage pack){
        switch(numero){
                case 1:
                    simulateErrorEnvio(checkSum);
                    break;
                
               case 2:
                   simulateErrorLostPackets(pack);
                    break;
                    
               case 3:
                   simulateErrorMessage(sc, content);
                   break;
        }
    }
    
    public static void simulateErrorEnvio(String layer){
        System.out.println("Valor: " + TransportLayer.calcularChecksum(layer)*10 + " es Incorrecto! Index de paquete fuera de orden.");
    }
    
    public static void simulateErrorLostPackets(IPPackage pack){
        String frame = pack.getFrameHeader();
        Random random = new Random();
        System.out.println("Paquete #" + (random.nextInt(0, 50)+ frame));
    }
    
    public static void simulateErrorMessage(Scanner sc,ArrayList<String> content){
        String textoAlt = retornarContenido(sc, content);
        String binario = Reader.textToBinary(textoAlt);
        Random random = new Random();
        StringBuilder md = new StringBuilder(textoAlt  );
        char[] specialChars = {'0', '1'};
        int numberOfModifications = random.nextInt(0,textoAlt.length()) + 1;

        for (int i = 0; i < numberOfModifications; i++) {
            // Posición aleatoria en el string
            int position = random.nextInt(md.length());

            // Carácter especial aleatorio
            char specialChar = specialChars[random.nextInt(specialChars.length)];
            
            // Insertar el carácter especial en la posición aleatoria
            md.insert(position, specialChar);
        }
        
        System.out.println("Archivo corrupto, elementos mal procesados |Mostrando errores|");
        System.out.println(md.toString());
    }
}
