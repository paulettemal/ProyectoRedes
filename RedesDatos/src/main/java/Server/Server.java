/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import Reader.Reader;
import static ec.edu.espol.redesdatos.RedesDatos.verifyInput;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author icrio
 */
public class Server {
    ArrayList<String> content;
    String name;
    String Address;

    public Server(String name, String Address) {
        this.name = name;
        this.Address = Address;
        this.content = new ArrayList<>();
    
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

    @Override
    public String toString() {
        return "Informacion del Server: \n Nombre: " + name + "\n Direccion: " + Address;
    }
    
    //controlar el null;
    public static void retornarContenido(Scanner sc, ArrayList<String> content){
        System.out.println("Archivos guardados en el servidor:");
        for(int i = 0; i<content.size();i++){
            System.out.println((i+1)+". "+content.get(i));
        }
        System.out.println("Ingrese una opcion:");
        String eleccion = sc.nextLine();
        boolean verify = verifyInput(eleccion,3);
        while(verify != true){
            System.out.println("Opcion no existente.\nIngrese una opcion correcta:");
            String eleccion2= sc.nextLine();
            System.out.println(eleccion2);
            verify = verifyInput(eleccion2,3);
            System.out.println(verify);
            eleccion = eleccion2;
        }
        //generar metodo convertir archivo binario a texto;
        ArrayList<String> archivoLineas = Reader.readTxt(content.get(Integer.parseInt(eleccion)-1));
        for(String s: archivoLineas) System.out.println(Reader.binaryToText(s));
        System.out.println("\n");
    }
}
