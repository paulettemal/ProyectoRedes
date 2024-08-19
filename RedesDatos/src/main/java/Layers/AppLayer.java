/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Layers;

import Reader.Reader;

import java.util.Scanner;

/**
 *
 * @author icrio
 */
public class AppLayer {
    String inputUsuario;
    String inputUsuarioBinario;
    String nombreArchivo;

    public AppLayer(Scanner sc) {
        start(sc);
        convertoToBinario();
    }
    public void start(Scanner sc){
        System.out.println("Ingresa el texto a guardar: ");
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
        nombreArchivo= sc.nextLine();

    }
    public void convertoToBinario(){
        inputUsuarioBinario = Reader.textToBinary(inputUsuario);
    }
    public String getInputUsuarioBinario(){
        return inputUsuarioBinario;
    }
    public String getNombreArchivo(){
        return nombreArchivo;
    }

    
}
