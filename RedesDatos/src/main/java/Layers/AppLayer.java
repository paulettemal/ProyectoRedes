/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Layers;

import Reader.Reader;
import Server.Server;

import java.util.Scanner;

/**
 * @author icrio
 */
public class AppLayer {
    static String inputUsuario;
    private static String inputUsuarioBinario;
    private static String nombreArchivo;
    static String finalText;

    public AppLayer() {

    }

    public static void start(Scanner sc, Server servidor) {
        System.out.println("Ingresa el texto (presiona enter 2 veces al final para guardar): ");
        StringBuilder textoG = new StringBuilder();
        while (true) {
            String linea = sc.nextLine();
            if (linea.isEmpty()) {
                break;  // Si la línea está vacía, se termina la entrada.
            }
            textoG.append(linea).append("\n");  // Agrega la línea al párrafo con un salto de línea.
        }
        inputUsuario = textoG.toString();
        System.out.println("Ingresa nombre del archivo: ");
        nombreArchivo = sc.nextLine();
        convertoToBinario();
        
        String datosEnBinario = AppLayer.getInputUsuarioBinario();
        TransportLayer.procesarDatosDesdeAplicacion(datosEnBinario);
        servidor.setContent(datosEnBinario);

    }

    //Una vez la capa de transporte termine de unir todo el mensaje y no posea errores se pueda convertir en texto para almacenarlo en el servidor
    public static void convertToText(String textInBinario) {
        finalText = Reader.binaryToText(textInBinario); }

    //formato de conversion de el texto solo es pasar el texto a binario
    public static void convertoToBinario() {
        inputUsuarioBinario = Reader.textToBinary(inputUsuario);
    }

    public static String getInputUsuarioBinario() {
        return inputUsuarioBinario;
    }

    public static String getNombreArchivo() {
        return nombreArchivo;
    }

    public static String getFinalText() {
        return finalText;
    }

    public static void setFinalText(String finalText) {
        AppLayer.finalText = finalText;
    }
}
