/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class Builder {

    static int x = 7000;

    public static void build() {
        PrintWriter writer = null;
        
            File file = new File("Projects\\test\\example.c");
        try {
            writer = new PrintWriter(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Builder.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        writer.print(MainTemplate.getStartMainTemplate());
        writer.print(Button.getButtonStartTemplate("RD0"));
        writer.print(Delay.getStartTemplate(x));
        writer.print(LED.getTurnOnTemplate("RB0"));
        writer.print(Button.getButtonEndTemplate());
        writer.print(MainTemplate.getEndMainTemplate());
        writer.close();
        Runtime runTime = Runtime.getRuntime();
        try {
            Process p2 = runTime
                    .exec("cmd /c start XC8compileFile.bat example");
            try {
                p2.waitFor();
                Thread.sleep(2000);
                runTime.exec("cmd /c start XC8mapFiles.bat example");
            } catch (InterruptedException e) {
                System.out.println(e.toString());
            }

        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

}
