/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import com.google.common.base.CharMatcher;
import desktopapplication1.CustomWidget;
import java.util.Collection;
import org.netbeans.api.visual.graph.GraphScene;
import org.openide.util.Exceptions;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.CodeStructure;
import model.Constants;
import model.TempSensor;
import org.netbeans.api.visual.widget.Widget;

/**
 *
 * @author Admin
 */
public class Builder {

    static String PATH = "Projects\\test\\";
    static int x = 100;
    static boolean errFlag = false;

    public static void build(ArrayList<CustomWidget> listOfComponents, GraphScene scene) {

        Object nodesArray[] = scene.getNodes().toArray();
        for (int i = 0; i < nodesArray.length; i++) {
            Widget wn = scene.findWidget(nodesArray[i]);
            CustomWidget cwn = Constants.hashOfCustomWidgets.get(wn);
            if (!cwn.isInFlag() || !cwn.isOutFlag()) {

                errFlag = true;
                break;

            }
        }
        if (errFlag) {
            JOptionPane.showMessageDialog(null, "Error there are non connected widgets");

        } else {
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
            CustomWidget lastBranchedWidget = null;

            while (true) {
                if (currentCustomWidget.getName().compareTo("End") == 0 && edgeCount == -1) {
                    break;
                } else if (currentCustomWidget.getName().compareTo("End") == 0) {
                    String tmpwidgetstr = (String) scene.getEdgeTarget(edges.get(edgeCount--));
                    tmpwidget = scene.findWidget(tmpwidgetstr);
                    currentCustomWidget = Constants.hashOfCustomWidgets.get(tmpwidget);
                    if (CharMatcher.is('{').countIn(CodeStructure.mainLoop) > 1) {
                        CodeStructure.mainLoop.append("}");
                    }
                    if (lastBranchedWidget.getName().contains("TEMP Sensor")) {
                        CodeStructure.mainLoop.append(lastBranchedWidget.getComponent().getComponentsCode());
                    }
                } else {
                    tmpEdges = scene.findNodeEdges(currentCustomWidget.getName(), true, false);
                    if (tmpEdges.size() > 1) {
                        edges.addAll(tmpEdges);
                        edgeCount = tmpEdges.size() - 1;
                        String z = (String) scene.getEdgeTarget(edges.get(edgeCount--));
                        tmpwidget = scene.findWidget(z);
                        lastBranchedWidget = currentCustomWidget;
                        currentCustomWidget = Constants.hashOfCustomWidgets.get(tmpwidget);
                    } else {

                        Object[] y = tmpEdges.toArray();
                        String z = (String) scene.getEdgeTarget(y[0]);
                        tmpwidget = scene.findWidget(z);
//                    tmpwidget = (Widget) scene.getEdgeTarget(scene.findNodeEdges(currentCustomWidget.getName(), true, false).toArray()[0]);
                        currentCustomWidget = Constants.hashOfCustomWidgets.get(tmpwidget);
                    }
                }

                if (currentCustomWidget.getName().compareTo("End") != 0) {
//                    if (currentCustomWidget.getName().contains("LCD")) {
//                        CodeStructure.mainLoop.append(lastBranchedWidget.getComponent().getPrintedValue());
//                    }
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
                writer.print(CodeStructure.functions);
                writer.print(CodeStructure.isr);
                writer.print("}");
                writer.print(CodeStructure.main);
                writer.print(CodeStructure.localVars);
                writer.print(CodeStructure.mainLoop);
                writer.print("}");
                writer.print("}");
                writer.print("return 0;\r\n}");
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

    

    public void endOfTheProgram() {
        JOptionPane.showMessageDialog(null, "Please switch to user mode  by chamging the switch staement");
    }
}
