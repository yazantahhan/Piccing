/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopapplication1;

import model.Component;
import org.netbeans.api.visual.widget.Widget;

/**
 *
 * @author Admin
 */
public class CustomWidget {

    private Component component;
    private Widget widget;

    public CustomWidget(Widget widget, Component component) {
        this.widget = widget;

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
    
}
