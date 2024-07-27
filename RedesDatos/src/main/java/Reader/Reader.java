/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author icrio
 */
public class Reader {
    
    public static List<String> readTxt(String nfilev){ //direccion del archivo
        
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
}
