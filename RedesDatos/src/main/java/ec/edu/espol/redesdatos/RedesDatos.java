/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ec.edu.espol.redesdatos;

import Server.Server;
import java.util.Scanner;

/**
 *
 * @author icrio
 */
public class RedesDatos {

    public static void main(String[] args) {
        System.out.println("""
                           Menú de Opciones:
                           1. Crear servidor
                           2. Ingresar información al servidor
                           3. Obtener información del servidor
                           4. Salir""");
        System.out.println("Ingrese una opcion:");
        Scanner sc= new Scanner(System.in);
        sc.useDelimiter("\n");
        String eleccion = sc.nextLine();
        boolean verify = verifyInput(eleccion);
        while(verify != true){
            System.out.println("Opcion no existente.\nIngrese una opcion correcta:");
            String eleccion2= sc.nextLine();
            System.out.println(eleccion2);
            verify = verifyInput(eleccion2);
            System.out.println(verify);
            eleccion = eleccion2;
        }
        int opcion= Integer.parseInt(eleccion);
        while(opcion != 4){
            switch (opcion) {
                case 1:
                    Server.createServer();
                    System.out.println("Servidor creado. \n");
                    System.out.println("""
                                                           Menú de Opciones:
                                                           1. Crear servidor
                                                           2. Ingresar información al servidor
                                                           3. Obtener información del servidor""");
                    System.out.println("Ingrese una opcion:");
                    eleccion = sc.nextLine();
                    verify = verifyInput(eleccion);
                    while(verify != true){
                        System.out.println("Opcion no existente.\nIngrese una opcion correcta:");
                        String eleccion2= sc.nextLine();
                        verify = verifyInput(eleccion2);
                        eleccion = eleccion2;
                    }
                    opcion= Integer.parseInt(eleccion);
                    break;
            //funcion para ingresar .txt
                case 2:
                    break;
            //funcion que devuelva el txt al usuario
                case 3:
                    break;
                default:
                    break;
            }
            }
      
    }
    
    public static boolean verifyInput(String input){
        try {
            int numero = Integer.parseInt(input);
            if(numero>= 5 || numero<0){return false; }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}

