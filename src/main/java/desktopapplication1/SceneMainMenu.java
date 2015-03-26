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
import model.Delay;

public class SceneMainMenu implements PopupMenuProvider, ActionListener {

    // add new widget red type
    private static final String ADD_NEW_NODE_ACTION1 = "addNewNodeAction1";
    // add new widget green type
    private static final String ADD_NEW_NODE_ACTION2 = "addNewNodeAction2";
    // add new widget blue type
    private static final String ADD_NEW_NODE_ACTION3 = "addNewNodeAction3";
    private static final String ADD_NEW_NODE_ACTION4 = "addNewNodeAction4";
    private static final String ADD_NEW_NODE_ACTION5 = "addNewNodeAction5";
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
        item = new JMenuItem("Add LED");
        item.setActionCommand(ADD_NEW_NODE_ACTION1);
        item.addActionListener(this);
        menu.add(item); // add menuitem to popup menu
        menu.addSeparator();    // add separator

        // create menuitem for green widget
        item = new JMenuItem("Add Timer");
        item.setActionCommand(ADD_NEW_NODE_ACTION2);
        item.addActionListener(this);
        menu.add(item);
        menu.addSeparator();

        // create menuitem for blue widget
        item = new JMenuItem("Add Button");
        item.setActionCommand(ADD_NEW_NODE_ACTION3);
        item.addActionListener(this);
        menu.add(item);
        menu.addSeparator();

        //

        item = new JMenuItem("Assign value for timer");
        item.setActionCommand(ADD_NEW_NODE_ACTION4);
        item.addActionListener(this);
        menu.add(item);
        menu.addSeparator();
        // add separator

        item = new JMenuItem("Build");
        item.setActionCommand(ADD_NEW_NODE_ACTION5);
        item.addActionListener(this);
        menu.add(item);
        menu.addSeparator();

    }

    public JPopupMenu getPopupMenu(Widget widget, Point point) {
        this.point = point;
        return menu;
    }

    public void actionPerformed(ActionEvent e) {

        if (ADD_NEW_NODE_ACTION1.equals(e.getActionCommand())) {

            // add new widget red type (widget name start with 1)
            String hm = "1#Node" + (nodeCount++);
            Widget newNode = scene.addNode(hm);
            // animate new widget from left top conner to point where click
            scene.getSceneAnimator().animatePreferredLocation(newNode, point);
            scene.validate();
        } else if (ADD_NEW_NODE_ACTION2.equals(e.getActionCommand())) {
            // add new widget green type (widget name start with 2)
            String hm = "2#Node" + (nodeCount++);
            Widget newNode = scene.addNode(hm);
            scene.getSceneAnimator().animatePreferredLocation(newNode, point);
            scene.validate();
        } else if (ADD_NEW_NODE_ACTION3.equals(e.getActionCommand())) {
            // add new widget blue type (widget name start with any letter or number)
            String hm = "3#Node" + (nodeCount++);
            Widget newNode = scene.addNode(hm);
            scene.getSceneAnimator().animatePreferredLocation(newNode, point);
            scene.validate();
        } else if (ADD_NEW_NODE_ACTION4.equals(e.getActionCommand())) {
            double x;
            String input = JOptionPane.showInputDialog("Please Enter The Time");
            x = Double.parseDouble(input);


        }
        if (ADD_NEW_NODE_ACTION5.equals(e.getActionCommand())) {
            // hooon be3mal al Build 
            Builder.build(GraphSceneImpl.listOfCustomWidgets);
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

        Point point2 = new Point(R1, R2);
        GraphSceneImpl.listOfCustomWidgets.add(
                new CustomWidget(newNode, new model.LED(hm, null, null, "B", "1", true)));
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
        GraphSceneImpl.listOfCustomWidgets.add(
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
        GraphSceneImpl.listOfCustomWidgets.add(
                new CustomWidget(newNode, new model.Button(hm, null, null, "D", "0", true)));
        scene.getSceneAnimator().animatePreferredLocation(newNode, point3);
        scene.validate();
    }
}
