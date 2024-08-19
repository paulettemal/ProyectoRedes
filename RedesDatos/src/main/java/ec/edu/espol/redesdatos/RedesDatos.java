/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ec.edu.espol.redesdatos;

import Layers.AppLayer;
import Reader.Reader;
import Server.Server;
import java.util.HashSet;
import java.util.Scanner;



/**
 *
 * @author icrio
 */
public class RedesDatos {
    public static AppLayer appLayer;
    public static Server servidor;

    public static void main(String[] args) {
        
        
        System.out.println("""
                                        Menú de Opciones:
                                        1. Crear servidor
                                        2. Ingresar texto al servidor
                                        3. Ingresar archivo .txt al servidor
                                        4. Obtener informacion del Servidor
                                        5. Salir""");
        System.out.println("Ingrese una opcion:");
        Scanner sc= new Scanner(System.in,"UTF-8");
        sc.useDelimiter("\n");
        String eleccion = sc.nextLine();
        boolean verify = verifyInput(eleccion,6);
        while(verify != true){
            System.out.println("Opcion no existente.\nIngrese una opcion correcta:");
            String eleccion2= sc.nextLine();
            System.out.println(eleccion2);
            verify = verifyInput(eleccion2,6);
            System.out.println(verify);
            eleccion = eleccion2;
        }
        int opcion= Integer.parseInt(eleccion);
        while(opcion != 5){
            switch (opcion) {
                case 1:
                    servidor = Server.createServer();
                    System.out.println("Servidor creado. \n");
                    System.out.println("""
                                                           Menú de Opciones:
                                                           1. Crear servidor
                                                           2. Ingresar texto al servidor
                                                           3. Ingresar archivo .txt al servidor
                                                           4. Obtener informacion del Servidor
                                                           5. Salir""");
                    System.out.println("Ingrese una opcion:");
                    eleccion = sc.nextLine();
                    verify = verifyInput(eleccion,6);
                    while(verify != true){
                        System.out.println("Opcion no existente.\nIngrese una opcion correcta:");
                        String eleccion2= sc.nextLine();
                        verify = verifyInput(eleccion2,6);
                        eleccion = eleccion2;
                    }
                    opcion= Integer.parseInt(eleccion);
                    break;
                    
                    
            //funcion para ingresar .txt
                case 2:
                    appLayer = new AppLayer(sc);

//                    System.out.println("Ingresa el texto a guardar: ");
//                    StringBuilder textoG = new StringBuilder();
//                    while (true) {
//                    String linea = sc.nextLine();
//                    if (linea.isEmpty()) {
//                        break;  // Si la línea está vacía, se termina la entrada.
//                    }
//                        textoG.append(linea).append("\n");  // Agrega la línea al párrafo con un salto de línea.
//                    }
//
//                    System.out.println("Ingresa nombre del archivo: ");
//                    String rutaArchivo = sc.nextLine();


//                    String nombreArchivo = Reader.guardarTexto(rutaArchivo, textoG.toString());
//
//                    servidor.setContent(nombreArchivo);
//                    System.out.println("Texto guardado \n");

                    System.out.println("""
                                                    Menú de Opciones:
                                                    1. Crear servidor
                                                    2. Ingresar texto al servidor
                                                    3. Ingresar archivo .txt al servidor
                                                    4. Obtener informacion del Servidor
                                                    5. Salir""");
                    System.out.println("Ingrese una opcion:");
                    eleccion = sc.nextLine();
                    verify = verifyInput(eleccion,6);
                    while(verify != true){
                        System.out.println("Opcion no existente.\nIngrese una opcion correcta:");
                        String eleccion2= sc.nextLine();
                        verify = verifyInput(eleccion2,6);
                        eleccion = eleccion2;
                    }
                    opcion= Integer.parseInt(eleccion);
                    break;
            //funcion que devuelva el txt al usuario
                case 3:
                    System.out.println("Ingresa el archivo .txt a guardar: ");
                    String nombreArchivo2 = Reader.guardarTxt(sc);
                    servidor.setContent(nombreArchivo2);
                    
                    
                    System.out.println("""
                                                           Menú de Opciones:
                                                           1. Crear servidor
                                                           2. Ingresar texto al servidor
                                                           3. Ingresar archivo .txt al servidor
                                                           4. Obtener informacion del Servidor
                                                           5. Salir""");
                    System.out.println("Ingrese una opcion:");
                    eleccion = sc.nextLine();
                    verify = verifyInput(eleccion,6);
                    while(verify != true){
                        System.out.println("Opcion no existente.\nIngrese una opcion correcta:");
                        String eleccion2= sc.nextLine();
                        verify = verifyInput(eleccion2,6);
                        eleccion = eleccion2;
                    }
                    opcion= Integer.parseInt(eleccion);
                    break;
                    
                case 4:
                    System.out.println("La informacion del servidor es: ");
                    System.out.println(servidor.toString());
                    Server.retornarContenido(sc,servidor.getContent());
                    System.out.println("""
                                                           Menú de Opciones:
                                                           1. Crear servidor
                                                           2. Ingresar texto al servidor
                                                           3. Ingresar archivo .txt al servidor
                                                           4. Obtener informacion del Servidor
                                                           5. Salir""");
                    System.out.println("Ingrese una opcion:");
                    eleccion = sc.nextLine();
                    verify = verifyInput(eleccion,6);
                    while(verify != true){
                        System.out.println("Opcion no existente.\nIngrese una opcion correcta:");
                        String eleccion2= sc.nextLine();
                        verify = verifyInput(eleccion2,6);
                        eleccion = eleccion2;
                    }
                    opcion= Integer.parseInt(eleccion);
                    break;
                    
                default:
                    break;
                }
            }
      
    }
    
    public static boolean verifyInput(String input, int limite){
        try {
            int numero = Integer.parseInt(input);
            if(numero>= limite || numero<=0){return false; }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}

