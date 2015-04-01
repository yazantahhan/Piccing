/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonModel;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class ComponentJson {

    private String name;
    private ArrayList<String> pins;

    public ComponentJson(String name, ArrayList<String> pins) {
        this.name = name;
        this.pins = pins;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getPins() {
        return pins;
    }

    public void setPins(ArrayList<String> pins) {
        this.pins = pins;
    }
}
