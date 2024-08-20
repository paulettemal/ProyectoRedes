/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author icrio
 */
public class Reader {
    
    public static ArrayList<String> readTxt(String nfilev){ //direccion del archivo
        
         ArrayList<String> lineastxt = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nfilev))){
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                lineastxt.add(line);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return lineastxt;
    }
    
    public static int binaryToDecimal(String binary) {
    // A este número le vamos a sumar cada valor binario
    int decimal = 0;
    int position = 0;
    // Recorrer la cadena...
    for (int x = binary.length() - 1; x >= 0; x--) {
        // Saber si es 1 o 0; primero asumimos que es 1 y abajo comprobamos
        short digit = 1;
        if (binary.charAt(x) == '0') {
            digit = 0;
        }

  /*
      Se multiplica el dígito por 2 elevado a la potencia
      según la posición; comenzando en 0, luego 1 y así
      sucesivamente
   */
        double multiplier = Math.pow(2, position);
        decimal += digit * multiplier;
        position++;
        }
     return decimal;
    }

    public static String decimalToBinary(int decimal) {
        if (decimal <= 0) {
            return "0";
        }
        String binary = "";
        while (decimal > 0) {
            short remainder = (short) (decimal % 2);
            decimal = decimal / 2;
            // Insertar el dígito al inicio de la cadena
            binary = String.valueOf(remainder) + binary;
        }
        return binary;
    }
        
    public static String textToBinary(String originalText) {
    String binaryText = "";
    for (int i = 0; i < originalText.length(); i++) {
        // Primero obtenemos la letra o carácter actual
        char currentChar = originalText.charAt(i);
        // Necesitamos obtener su representación entera ASCII
        int ascii = (int) currentChar;
        // Una vez que ya tenemos el entero, lo convertimos a binario
        String binary = decimalToBinary(ascii);
        // Lo agregamos a la cadena resultante agregando además un espacio
        binaryText += binary + " ";
        }
    // Finalmente regresamos el texto
        return binaryText;
    }
    
    public static String binaryToText(String binaryText) {
    // Necesitamos separar cada número binario por espacio. Usamos split
    String[] binaryNumbers = binaryText.split(" ");
    String text = "";

    // Los recorremos. En cada paso tenemos al número binario
    for (String currentBinary : binaryNumbers) {
        // Ahora convertimos ese binario a decimal
        int decimal = binaryToDecimal(currentBinary);
        // Obtenemos la letra que le corresponde a ese valor ASCII
        char letra = (char) decimal;
        text += letra;
        }
        return text;
    }
    
    
    //generar archivo vacio de error
    public static String guardarTxt(Scanner sc){
    // Solicitar la ruta del archivo al usuario
        System.out.print("Por favor, ingresa la ruta completa del archivo .txt: ");
        String rutaArchivo = sc.nextLine();
        
        // Verificar que el archivo existe y es un archivo .txt
        File archivo = new File(rutaArchivo);
        
        // Obtener el nombre del archivo
        int lastIndex = rutaArchivo.lastIndexOf('\\');
         String nombreArchivo;
        if (lastIndex != -1) {
            nombreArchivo = rutaArchivo.substring(lastIndex + 1);
        } else {
            nombreArchivo = rutaArchivo; // Si no hay '/', se devuelve todo el String
        }
        System.out.println(nombreArchivo);
        // Determinar la ruta de destino en la carpeta del proyecto
        Path rutaDestino = Paths.get(System.getProperty("user.dir"), nombreArchivo);
        
        // Copiar el archivo a la carpeta del proyecto
        try {
            Files.copy(archivo.toPath(), rutaDestino);
            System.out.println("El archivo " + nombreArchivo + " ha sido guardado en la carpeta del proyecto.");
            ArrayList<String> lineas  = readTxt(rutaArchivo);
            ArrayList<String> binarios = new ArrayList<>();
            for(String s: lineas){
                System.out.println(textToBinary(s));
                binarios.add(textToBinary(s));
            }
            crearArchivoBinario(binarios, nombreArchivo);

            return nombreArchivo.replace(".txt", "") +"-BIN.txt";
        
            
        } catch (IOException e) {
            System.out.println("Error al copiar el archivo: " + e.getMessage());
            return null;
        }
        
        
    }
    
    public static  String guardarTexto(String rutaArchivo, String texto){      
        System.out.println("Hola usando guardarTexto");
        ArrayList<String> lineas = ingresarTexto(texto);
        ArrayList<String> binarios = new ArrayList<>();
            for(String s: lineas){
                System.out.println(textToBinary(s));
                binarios.add(textToBinary(s));
            }
        crearArchivoBinario(binarios, rutaArchivo);
        return rutaArchivo.replace(".txt", "") +"BIN.txt";
    }
    
    public static ArrayList<String> ingresarTexto(String texto){
         ArrayList<String> lineas = new ArrayList<>(Arrays.asList(texto.split("\n")));
        
        // Imprimir las líneas guardadas en el ArrayList
        System.out.println("Las líneas ingresadas son:");
        for (String linea : lineas) {
            System.out.println(linea);
        }
        return lineas;
    }
    
    public static boolean crearArchivoBinario(ArrayList<String> lineas, String nombre){
        String nombreArchivo = nombre.replace(".txt", "") + "BIN.txt";
        
        // Ruta donde se guardará el archivo (en la carpeta del proyecto)
        String rutaArchivo = System.getProperty("user.dir") + "/" + nombreArchivo;
        
        try (BufferedWriter escritor = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(rutaArchivo), StandardCharsets.UTF_8))) {
            // Escribir cada línea en el archivo
            for (String linea : lineas) {
                escritor.write(linea + System.lineSeparator());
            }
            System.out.println("El archivo ha sido creado en: " + rutaArchivo);
            return true;
        } catch (IOException e) {
            System.out.println("Ocurrió un error al crear el archivo: " + e.getMessage());
            return false;
        }
    }
    
}    
