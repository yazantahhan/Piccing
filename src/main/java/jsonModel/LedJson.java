/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonModel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Admin
 */
public class LedJson extends ComponentJson {

    private HashMap<String, String> colorPinMapping;
    private ArrayList<String> availabeColors;

    public LedJson(String name, ArrayList<String> pins) {
        super(name, pins);
        colorPinMapping = new HashMap<String, String>();
        availabeColors = new ArrayList<String>();
    }

    public HashMap<String, String> getColorPinMapping() {
        return colorPinMapping;
    }

    public ArrayList<String> getAvailabeColors() {
        return availabeColors;
    }
}
