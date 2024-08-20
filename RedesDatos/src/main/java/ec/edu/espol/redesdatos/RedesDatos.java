package ec.edu.espol.redesdatos;

import Layers.AppLayer;
import Layers.InternetLayer;
import Layers.NetworkAccessLayer;
import Layers.TransportLayer;
import Reader.Reader;
import Server.IPPackage;
import Server.Server;
import TopologiaFísica.TopologíaFisica;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static Layers.TransportLayer.establecerConexionToServer;

/**
 *
 * @author icrio
 */
public class RedesDatos {

    public static String clienteIP = "192.185.10.1";
    public static Server servidor = new Server("servidor","192.168.1.1");
    public static int opcion;

    public static void main(String[] args) {
        new TopologíaFisica();
        menuOpciones();
        Scanner sc= new Scanner(System.in,"UTF-8");
        sc.useDelimiter("\n");
        opcion = Integer.parseInt(sc.nextLine());
        verifyUntilTrue(opcion,sc);

        while(opcion != 4){
            switch (opcion) {

            //funcion para ingresar texto por consola
                case 1:
                    AppLayer.start(sc,servidor);
                    establecerConexionToServer();
                {
                    try {
                        simulateProgress(100, 3);
                    } catch (Exception ex) {
                        Random random = new Random();
                        int chance = random.nextInt(3);
                        Server.errorServidor(chance);
                    }
                }
                    menuBucle(opcion,sc);
                    break;

            //funcion que ingresar archivo .txt
                case 2:
                    System.out.println("Ingresa el archivo .txt a guardar: ");
                    String nombreArchivo2 = Reader.guardarTxt(sc);
                    servidor.setContent(nombreArchivo2);
                    IPPackage pack = InternetLayer.create_Package(clienteIP,servidor.getAddress(),nombreArchivo2);
                    NetworkAccessLayer nAL = new NetworkAccessLayer(NetworkAccessLayer.generarMAC());
                    nAL.encapsulate(pack);
                    nAL.transmitFrame(pack);


                    menuBucle(opcion,sc);
                    break;

                //funcion para obtener información del servidor
                case 3:
                    System.out.println("La informacion del servidor es: ");
                    System.out.println(servidor.toString());
                    String nombreArch = Server.retornarContenido(sc,servidor.getContent());
                    
                    IPPackage pack2 = InternetLayer.create_Package(clienteIP,servidor.getAddress(),nombreArch);
                    NetworkAccessLayer nAL2 = new NetworkAccessLayer(NetworkAccessLayer.generarMAC());
                    nAL2.decapsulate(pack2);
                    nAL2.receiveFrame(pack2);
                    
                    
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
                                                           1. Ingresar texto al servidor
                                                           2. Ingresar archivo .txt al servidor
                                                           3. Obtener informacion del Servidor
                                                           4. Salir""");
        System.out.println("Ingrese una opcion:");
    }
    public static void verifyUntilTrue(int eleccion, Scanner sc){
        boolean verify = verifyInput(eleccion,5);
        while(verify != true){
            System.out.println("Opcion no existente.\nIngrese una opcion correcta:");
            int eleccion2= Integer.parseInt(sc.nextLine());

            verify = verifyInput(eleccion2,6);

            eleccion = eleccion2;
        }
        opcion= eleccion;
    }
    
    // Método que simula el progreso con posibilidad de error
    public static void simulateProgress(int max, int delay) throws Exception {
        System.out.print("Progress: ");

        for (int i = 0; i <= max; i++) {
            // Chequear si ocurre un error
            checkForError(i);

            printProgressBar(i, max);
            try {
                Thread.sleep(delay); // Pausa para simular el tiempo de procesamiento
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\nAccion completada!");
    }


    // Método que imprime la barra de progreso
    private static void printProgressBar(int current, int total) {
        int progress = (current * 50) / total;
        System.out.print("\r[");

        for (int i = 0; i < 50; i++) {
            if (i < progress) {
                System.out.print("|");
            } else {
                System.out.print(" ");
            }
        }
        System.out.print("] " + (current * 100 / total) + "%");
    }
    
    private static void checkForError(int currentStep) throws Exception {
        Random random = new Random();
        int chance = random.nextInt(100); // Número aleatorio entre 0 y 99

        // Simular un error con un 10% de probabilidad
        if (chance < 10) {
            throw new Exception("Se produjo un error en el paso " + currentStep);
        }
    }    
    
}

