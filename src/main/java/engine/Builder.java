/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import org.openide.util.Exceptions;
import templates.LED;
import templates.MainTemplate;
import templates.Button;
import templates.Delay;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import templates.Adc;
import templates.Lcd;

/**
 *
 * @author Admin
 */
public class Builder {

    static String PATH = "Projects\\test\\";
    static int x = 100;

    public static void build() {
        PrintWriter writer = null;
        File file = new File("Projects\\test\\", "example.c");
        if (!file.exists()) {
            new File(PATH).mkdirs();
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
        try {
            writer = new PrintWriter(file);
        } catch (FileNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        }
        writer.print(MainTemplate.getStartMainTemplate());
        writer.print(Lcd.getLcdClearTemplate());
        writer.print(Delay.getStartTemplate(x));
        writer.print(Adc.getConvertAdcTemplate());
        writer.print("if (ADCValue > 512){\r\n");
        writer.print(LED.getTurnOnTemplate("B", "RB0"));
        writer.print(Button.getButtonEndTemplate());
        writer.print("else\r\n");
        writer.print(LED.getTurnOffTemplate("B", "RB0"));
        writer.print("Lcd_Clear();"
                + "Lcd_Set_Cursor(1, 1);"
                + "Lcd_Write_String(\"LCD Library for\");"
                + "Lcd_Set_Cursor(2, 1);"
                + "Lcd_Write_String(\"MPLAB XC8\");");
        writer.print(Delay.getStartTemplate(x));
        writer.print(MainTemplate.getEndMainTemplate());
        writer.close();
        Runtime runTime = Runtime.getRuntime();
        try {
            Process p2 = runTime.exec("Tools\\AStyle.exe Projects\\test\\example.c");
            try {
                p2.waitFor();
                runTime.exec("cmd /c start Tools\\XC8compileFile.bat example");
            } catch (InterruptedException e) {
                System.out.println(e.toString());
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
