/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import desktopapplication1.CustomWidget;
import org.openide.util.Exceptions;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import model.CodeStructure;
import model.Component;
import model.Constants;
import org.netbeans.api.visual.widget.general.IconNodeWidget;

/**
 *
 * @author Admin
 */
public class Builder {

    static String PATH = "Projects\\test\\";
    static int x = 100;

    public static void build(ArrayList<CustomWidget> listOfComponents) {
        PrintWriter writer = null;
        File file = new File(PATH, "example.c");
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

        CustomWidget currentWidget = Constants.startWidget.getOutput().get(0);
        ArrayList<CustomWidget> listOfOutputs;
        while (currentWidget.getName().compareTo("End") != 0) {
            CodeStructure.mainLoop.append(currentWidget.getComponent().getComponentsCode());
            listOfOutputs = currentWidget.getOutput();
            if (listOfOutputs.size() == 1) {
                currentWidget = currentWidget.getOutput().get(0);
            } else {
                //TODO
                System.out.println("More tahn one");
                currentWidget = currentWidget.getOutput().get(0);
            }
        }
//        for (int i = 0; i < listOfComponents.size(); i++) {
//            CodeStructure.mainLoop.append(listOfComponents.get(i).getComponent().getComponentsCode());
//        }


        writer.print(CodeStructure.includes);
        writer.print(CodeStructure.defines);
        writer.print(CodeStructure.configBits);
        writer.print(CodeStructure.globalVars);
        writer.print(CodeStructure.setup);
        writer.print("}");
        writer.print(CodeStructure.isr);
        writer.print("}");
        writer.print("}");
        writer.print(CodeStructure.main);
        writer.print(CodeStructure.localVars);
        writer.print(CodeStructure.mainLoop);
        writer.print("}");
        writer.print("}");
        writer.print("return 0;\r\n}");

//        writer.print("if (ADCValue > 512){\r\n");
//        writer.print(LED.getTurnOnTemplate("B", "RB0"));
//        writer.print(Button.getButtonEndTemplate());
//        writer.print("else\r\n");
//        writer.print(LED.getTurnOffTemplate("B", "RB0"));
//        writer.print("Lcd_Clear();"
//                + "Lcd_Set_Cursor(1, 1);"
//                + "Lcd_Write_String(\"LCD Library for\");"
//                + "Lcd_Set_Cursor(2, 1);"
//                + "Lcd_Write_String(\"MPLAB XC8\");");
//        writer.print(Delay.getStartTemplate(x));
        writer.close();
        Runtime runTime = Runtime.getRuntime();
        try {
            Process p2 = runTime.exec("Tools\\AStyle.exe Projects\\test\\example.c");
//            try {
//                p2.waitFor();
//                runTime.exec("cmd /c start Tools\\XC8compileFile.bat example");
//            } catch (InterruptedException e) {
//                System.out.println(e.toString());
//            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
