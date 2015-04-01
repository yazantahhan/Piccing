/*
 * This class is for adding new widget to GraphScene.
 * Right click GraphScene to show add widget menu.
 */
package desktopapplication1;

/**
 *
 * @author aaa
 */
import engine.Builder;
import org.netbeans.api.visual.action.PopupMenuProvider;
import org.netbeans.api.visual.graph.GraphScene;
import org.netbeans.api.visual.widget.Widget;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import jsonModel.LedJson;
import model.Constants;
import model.Delay;
import org.netbeans.api.visual.action.WidgetAction;

public class SceneMainMenu implements PopupMenuProvider, ActionListener {

    // add new widget red type
    private static final String BUILD = "buildProject";
    private static GraphScene scene;
    public static GraphScene currentScene;
    private JPopupMenu menu;
    private Point point;
    private static int nodeCount = 0; // number to name new widget

    public SceneMainMenu(GraphScene scene) {
        this.scene = scene; // get reference GraphScene from caller class
        currentScene = scene;
        menu = new JPopupMenu("Scene Menu");    // create popup menu
        JMenuItem item;
        // create menuitem for red widget

        item = new JMenuItem("Build");
        item.setActionCommand(BUILD);
        item.addActionListener(this);
        menu.add(item);
        menu.addSeparator();
        
    }
    
    public JPopupMenu getPopupMenu(Widget widget, Point point) {
        this.point = point;
        return menu;
    }
    
    public void actionPerformed(ActionEvent e) {
        if (BUILD.equals(e.getActionCommand())) {
            // hooon be3mal al Build 
            Builder.build(Constants.listOfCustomWidgets);
        }
    }
    
    public void addLed() {
        String hm = "1#LED" + (nodeCount++);
        Widget newNode = scene.addNode(hm);
        
        Random r = new Random();
        int Low = 100;
        int High = 300;
        int R1 = r.nextInt(High - Low) + Low;
        int R2 = r.nextInt(High - Low) + Low;
        String defaultColor = ((LedJson) Constants.listOfJsonComponents.get("LED")).getAvailabeColors().get(0);
        String defaultPin = ((LedJson) Constants.listOfJsonComponents.get("LED")).getColorPinMapping().get(defaultColor);
        Point point2 = new Point(R1, R2);
        Constants.listOfCustomWidgets.add(
                new CustomWidget(newNode, new model.LED(hm, null, null, defaultPin, defaultColor, true)));
        // animate new widget from left top conner to point where click
        scene.getSceneAnimator().animatePreferredLocation(newNode, point2);
        scene.validate();
    }
    
    public void addTimer() {
        Random r = new Random();
        int Low = 100;
        int High = 300;
        int R1 = r.nextInt(High - Low) + Low;
        int R2 = r.nextInt(High - Low) + Low;
        Point point3 = new Point(R1, R2);
        String hm = "2#Timer" + (nodeCount++);
        Widget newNode = scene.addNode(hm);
        Constants.listOfCustomWidgets.add(
                new CustomWidget(newNode, new Delay(hm, null, null, 500)));
        scene.getSceneAnimator().animatePreferredLocation(newNode, point3);
        scene.validate();
    }
    
    public void addButton() {
        Random r = new Random();
        int Low = 100;
        int High = 300;
        int R1 = r.nextInt(High - Low) + Low;
        int R2 = r.nextInt(High - Low) + Low;
        Point point3 = new Point(R1, R2);
        String hm = "3#Button" + (nodeCount++);
        Widget newNode = scene.addNode(hm);
        String defaultPin = Constants.listOfJsonComponents.get("BUTTON").getPins().get(0);
        Constants.listOfCustomWidgets.add(
                new CustomWidget(newNode, new model.Button(hm, null, null, defaultPin, true)));
        scene.getSceneAnimator().animatePreferredLocation(newNode, point3);
        scene.validate();
    }
}
