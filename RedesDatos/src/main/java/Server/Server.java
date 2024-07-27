/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import java.util.Scanner;

/**
 *
 * @author icrio
 */
public class Server {
    String Content;
    String name;
    String Address;

    public Server(String name, String Address) {
        this.name = name;
        this.Address = Address;
        this.Content = null;
    
    }
    
    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
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
    
}
