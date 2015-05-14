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
public class SensorJson extends ComponentJson{

    private HashMap<String, String> typePinMapping;
    private ArrayList<String> availabeTypes;
    
    public SensorJson(String name, ArrayList<String> pins) {
        super(name, pins);
        typePinMapping = new HashMap<String, String>();
        availabeTypes = new ArrayList<String>();
    }

    public ArrayList<String> getAvailabeTypes() {
        return availabeTypes;
    }

    public void setAvailabeTypes(ArrayList<String> availabeTypes) {
        this.availabeTypes = availabeTypes;
    }

    public HashMap<String, String> getTypePinMapping() {
        return typePinMapping;
    }

    public void setTypePinMapping(HashMap<String, String> typePinMapping) {
        this.typePinMapping = typePinMapping;
    }
}
