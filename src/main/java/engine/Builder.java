/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;
import desktopapplication1.CustomWidget;
import java.util.Collection;
import org.netbeans.api.visual.graph.GraphScene;
import org.openide.util.Exceptions;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import model.CodeStructure;
import model.Component;
import model.Constants;
import org.netbeans.api.visual.widget.Widget;
import org.netbeans.api.visual.widget.general.IconNodeWidget;

/**
 *
 * @author Admin
 */
public class Builder {

    static String PATH = "Projects\\test\\";
    static int x = 100;

    public static void build(ArrayList<CustomWidget> listOfComponents, GraphScene scene) {
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

        ArrayList<Integer> edges = new ArrayList<Integer>();
        CustomWidget currentCustomWidget = Constants.startWidget;
        Widget tmpwidget;
        Collection tmpEdges;
        int edgeCount = -1;


        while (true) {
            if (currentCustomWidget.getName().compareTo("End") == 0 && edgeCount == -1) {
                break;
            } else if (currentCustomWidget.getName().compareTo("End") == 0) {
                tmpwidget = (Widget) scene.getEdgeTarget(edges.get(edgeCount--));
                currentCustomWidget = Constants.hashOfCustomWidgets.get(tmpwidget);
            } else {
                tmpEdges = scene.findNodeEdges(currentCustomWidget.getName(), true, false);
                if (tmpEdges.size() > 1) {
                    edges.addAll(scene.getEdges());
                    edgeCount = tmpEdges.size() - 1;
                    tmpwidget = (Widget) scene.getEdgeTarget(scene.findNodeEdges(currentCustomWidget.getName(), true, false).toArray()[edgeCount--]);
                    currentCustomWidget = Constants.hashOfCustomWidgets.get(tmpwidget);
                } else {
                    Collection x = scene.findNodeEdges(currentCustomWidget.getName(), true, false);
                    Object[] y =  x.toArray();
                    String z = (String) scene.getEdgeTarget(y[0]);
                    tmpwidget = scene.findWidget(z); 
//                    tmpwidget = (Widget) scene.getEdgeTarget(scene.findNodeEdges(currentCustomWidget.getName(), true, false).toArray()[0]);
                    currentCustomWidget = Constants.hashOfCustomWidgets.get(tmpwidget);
                }
            }

            if (currentCustomWidget.getName().compareTo("End") != 0) {
                CodeStructure.mainLoop.append(currentCustomWidget.getComponent().getComponentsCode());
            }


        }

//        ArrayList<Widget> multiConnWidgetStack = new ArrayList<Widget>();
//        ArrayList<Integer> multiConnNumStack = new ArrayList<Integer>();
//        Widget currentWidget = Constants.startWidget.getWidget();
//        String currentWidgetStr = Constants.startWidget.getName();
//        Collection collectionOfEdges;
//        while (true) {
//            currentWidget = Constants.startWidget.getWidget();
//            currentWidgetStr = Constants.startWidget.getName();
//            collectionOfEdges = scene.findNodeEdges(currentWidgetStr, true, false);
//            if (currentWidgetStr.compareTo("End") == 0 && multiConnWidgetStack.isEmpty()) {
//                break;
//            } else if (collectionOfEdges.size() == 1) {
//                CodeStructure.mainLoop.append(currentWidget.getComponent().getComponentsCode());
//            } else {
//                multiConnWidgetStack.add(currentWidget);
//                multiConnNumStack.add(0);
//            }
//
//    
//
//    
//        else {
//                if (currentWidgetStr.compareTo("End") == 0) {
//            currentWidget = multiConnWidgetStack.get(multiConnWidgetStack.size() - 1);
//        } else {
//        }
//    }

//
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
