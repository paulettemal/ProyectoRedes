/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ec.edu.espol.redesdatos;

import Layers.AppLayer;
import Layers.TransportLayer;
import Reader.Reader;
import Server.Server;
import TopologiaFísica.TopologíaFisica;

import java.util.HashSet;
import java.util.Scanner;



/**
 *
 * @author icrio
 */
public class RedesDatos {

    public static String clienteIP = "192.185.10.1";
    public static Server servidor;
    public static int opcion;

    public static void main(String[] args) {
        new TopologíaFisica();
        menuOpciones();
        Scanner sc= new Scanner(System.in,"UTF-8");
        sc.useDelimiter("\n");
        opcion = Integer.parseInt(sc.nextLine());
        verifyUntilTrue(opcion,sc);

        while(opcion != 5){
            switch (opcion) {
                case 1:
                    servidor = Server.createServer();
                    System.out.println("Servidor creado. \n");

                    menuBucle(opcion,sc);
                    break;
                    
                    
            //funcion para ingresar texto por consola
                case 2:
                    AppLayer.start(sc);

                    // Debería ir 
                    /*String datosEnBinario = appLayer.getInputUsuarioBinario();
                    TransportLayer.procesarDatosDesdeAplicacion(datosEnBinario);
                    servidor.setContent(datosEnBinario);
                    System.out.println("Datos procesados y almacenados");
                    break;
                    */

                    menuBucle(opcion,sc);
                    break;
            //funcion que ingresar archivo .txt
                case 3:
                    System.out.println("Ingresa el archivo .txt a guardar: ");
                    String nombreArchivo2 = Reader.guardarTxt(sc);
                    servidor.setContent(nombreArchivo2);


                    menuBucle(opcion,sc);
                    break;

                //funcion para obtener información del servidor
                case 4:
                    System.out.println("La informacion del servidor es: ");
                    System.out.println(servidor.toString());
                    Server.retornarContenido(sc,servidor.getContent());

                    menuBucle(opcion, sc);
                    break;
                    
                default:
                    break;
                }
            }
      
    }
    //---------------------------------------------------------------------------------------------------------------------------------------------------
    public static void menuBucle(int eleccion, Scanner sc){
        menuOpciones();
        eleccion = Integer.parseInt(sc.nextLine());
        verifyUntilTrue(eleccion,sc);
    }
    public static boolean verifyInput(int input, int limite){
        try {
            int numero = input;
            if(numero>= limite || numero<=0) {return false; }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    public static void menuOpciones(){
        System.out.println("""
                                                           Menú de Opciones:
                                                           1. Crear servidor
                                                           2. Ingresar texto al servidor
                                                           3. Ingresar archivo .txt al servidor
                                                           4. Obtener informacion del Servidor
                                                           5. Salir""");
        System.out.println("Ingrese una opcion:");
    }
    public static void verifyUntilTrue(int eleccion, Scanner sc){
        boolean verify = verifyInput(eleccion,6);
        while(verify != true){
            System.out.println("Opcion no existente.\nIngrese una opcion correcta:");
            int eleccion2= Integer.parseInt(sc.nextLine());

            verify = verifyInput(eleccion2,6);

            eleccion = eleccion2;
        }
        opcion= eleccion;
    }
}

