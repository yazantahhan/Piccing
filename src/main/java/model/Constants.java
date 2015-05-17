/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import desktopapplication1.CustomWidget;
import java.util.ArrayList;
import java.util.HashMap;
import jsonModel.ComponentJson;
import org.netbeans.api.visual.widget.Widget;

/**
 *
 * @author Admin
 */
public class Constants {

    public static ArrayList<CustomWidget> listOfCustomWidgets = new ArrayList<CustomWidget>();
    public static HashMap<Widget, CustomWidget> hashOfCustomWidgets = new HashMap<Widget, CustomWidget>();
    public static HashMap<String, ComponentJson> listOfJsonComponents = new HashMap<String, ComponentJson>();
    public static ArrayList<String> listOfAvailableComponentsStrings = new ArrayList<String>();
    public static CustomWidget startWidget;
    public static CustomWidget endWidget;
    public static String microcontroller = "";
}
