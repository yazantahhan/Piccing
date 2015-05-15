/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopapplication1;

import java.util.ArrayList;
import model.Component;
import org.netbeans.api.visual.widget.Widget;

/**
 *
 * @author Admin
 */
public class CustomWidget {

    private Component component;
    private Widget widget;
    private ArrayList<CustomWidget> input = new ArrayList<CustomWidget>();
    private ArrayList<CustomWidget> output = new ArrayList<CustomWidget>();
    private String name;

    public CustomWidget(Widget widget, Component component, String name) {
        this.widget = widget;
        this.name=name;
        this.component = component;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public Widget getWidget() {
        return widget;
    }

    public void setWidget(Widget widget) {
        this.widget = widget;
    }

    public ArrayList<CustomWidget> getInput() {
        return input;
    }

    public void setInput(ArrayList<CustomWidget> input) {
        this.input = input;
    }

    public ArrayList<CustomWidget> getOutput() {
        return output;
    }

    public void setOutput(ArrayList<CustomWidget> output) {
        this.output = output;
    }

    public void addOutputToList(CustomWidget component) {
        output.add(component);
    }

    public void addInputToList(CustomWidget component) {
        input.add(component);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
