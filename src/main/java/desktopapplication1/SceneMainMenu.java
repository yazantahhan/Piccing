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
import jsonModel.SensorJson;
import model.Constants;
import model.Delay;
import model.Sensor;

public class SceneMainMenu implements PopupMenuProvider, ActionListener {

    // add new widget red type
    private static final String BUILD = "buildProject";
    private static GraphScene scene;
    public static GraphScene currentScene;
    private JPopupMenu menu;
    private Point point;
    private int LedCount = 1; // number to name new widget
    private int BtnCount = 1; // number to name new widget
    private int SensorCount = 1; // number to name new widget
    private int TimerCount = 1; // number to name new widget
    private int DelayCount = 1; // number to name new widget
    private int LCDCount = 1; // number to name new widget
    private int TempCount = 1;
    private int MotorCount = 1;
    private int AdcCount = 1;
    private int KeypadCount = 1;

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
            Builder.build(Constants.listOfCustomWidgets, scene);
        }
//        else{
//            JOptionPane.showInputDialog("Please Enter The Time");
//        }
    }

    public void addLed() {
        String hm = "LED" + LedCount++;
        Widget newNode = scene.addNode(hm);

        Random r = new Random();
//        int Low = 100;
//        int High = 300;
//        int R1 = r.nextInt(High - Low) + Low;
//        int R2 = r.nextInt(High - Low) + Low;
        String defaultColor = ((LedJson) Constants.listOfJsonComponents.get("LED")).getAvailabeColors().get(0);
        String defaultPin = ((LedJson) Constants.listOfJsonComponents.get("LED")).getColorPinMapping().get(defaultColor);
        Point point2 = new Point(0, 0);
        CustomWidget cw = new CustomWidget(newNode, new model.LED(hm, null, null, defaultPin, defaultColor, true), hm);
        Constants.listOfCustomWidgets.add(cw);
        Constants.hashOfCustomWidgets.put(newNode, cw);
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
        Point point3 = new Point(0, 0);
//        String hm = "2#Timer" + (nodeCount++);
        String hm = "Timer" + TimerCount++;
        Widget newNode = scene.addNode(hm);
        CustomWidget cw = new CustomWidget(newNode, new Delay(hm, null, null, 500), hm);
        Constants.listOfCustomWidgets.add(cw);
        Constants.hashOfCustomWidgets.put(newNode, cw);
        scene.getSceneAnimator().animatePreferredLocation(newNode, point3);
        scene.validate();
    }

    public void addButton() {
        Random r = new Random();
        int Low = 100;
        int High = 300;
        int R1 = r.nextInt(High - Low) + Low;
        int R2 = r.nextInt(High - Low) + Low;
        Point point2 = new Point(0, 0);
//        String hm = "Push Button" + (nodeCount++);
        String hm = "Button" + BtnCount++;
        Widget newNode = scene.addNode(hm);
        String defaultPin = Constants.listOfJsonComponents.get("BUTTON").getPins().get(0);
        CustomWidget cw = new CustomWidget(newNode, new model.Button(hm, null, null, defaultPin, true), hm);
        Constants.listOfCustomWidgets.add(cw);
        Constants.hashOfCustomWidgets.put(newNode, cw);
        scene.getSceneAnimator().animatePreferredLocation(newNode, point2);
        scene.validate();
        newNode.getActions();
    }

    public void addSensor(String selectedType) {
        Random r = new Random();
        int Low = 100;
        int High = 300;
        int R1 = r.nextInt(High - Low) + Low;
        int R2 = r.nextInt(High - Low) + Low;
        Point point2 = new Point(0, 0);
//        String hm = "Push Button" + (nodeCount++);
        String hm = selectedType + " Sensor" + SensorCount++;
        Widget newNode = scene.addNode(hm);
        String defaultPin = ((SensorJson) (Constants.listOfJsonComponents.get("SENSOR"))).getTypePinMapping().get(selectedType);
        CustomWidget cw = new CustomWidget(newNode, new model.Sensor(hm, null, null, defaultPin, "0", selectedType, Sensor.EQUALS), hm);
        Constants.listOfCustomWidgets.add(cw);
        Constants.hashOfCustomWidgets.put(newNode, cw);
        scene.getSceneAnimator().animatePreferredLocation(newNode, point2);
        scene.validate();
        newNode.getActions();
    }

    public void addLcd() {

        Point point2 = new Point(0, 0);
//        String hm = "Push Button" + (nodeCount++);
        String hm = "LCD" + LCDCount++;
        Widget newNode = scene.addNode(hm);
        String defaultPin = Constants.listOfJsonComponents.get("BUTTON").getPins().get(0);
        CustomWidget cw = new CustomWidget(newNode, new model.Button(hm, null, null, defaultPin, true), hm);
        Constants.listOfCustomWidgets.add(cw);
        Constants.hashOfCustomWidgets.put(newNode, cw);
        scene.getSceneAnimator().animatePreferredLocation(newNode, point2);
        scene.validate();
        newNode.getActions();
    }

    public void addTemp() {
        Random r = new Random();
        int Low = 100;
        int High = 300;
        int R1 = r.nextInt(High - Low) + Low;
        int R2 = r.nextInt(High - Low) + Low;
        Point point2 = new Point(0, 0);
//        String hm = "Push Button" + (nodeCount++);
        String hm = "Temp Sensor" + TempCount++;
        Widget newNode = scene.addNode(hm);
        String defaultPin = Constants.listOfJsonComponents.get("BUTTON").getPins().get(0);
        //TODO
        CustomWidget cw = new CustomWidget(newNode, new model.Button(hm, null, null, defaultPin, true), hm);
        Constants.listOfCustomWidgets.add(cw);
        Constants.hashOfCustomWidgets.put(newNode, cw);
        scene.getSceneAnimator().animatePreferredLocation(newNode, point2);
        scene.validate();
        newNode.getActions();
    }

    public void addDelay() {

        Point point2 = new Point(0, 0);
//        String hm = "Push Button" + (nodeCount++);
        String hm = "Delay" + DelayCount++;
        Widget newNode = scene.addNode(hm);
        CustomWidget cw = new CustomWidget(newNode, new model.Delay(hm, null, null, 1), hm);
        Constants.listOfCustomWidgets.add(cw);
        Constants.hashOfCustomWidgets.put(newNode, cw);
        scene.getSceneAnimator().animatePreferredLocation(newNode, point2);
        scene.validate();
        newNode.getActions();
    }

    void addMotor() {

        Point point2 = new Point(0, 0);
//        String hm = "Push Button" + (nodeCount++);
        String hm = "Motor" + MotorCount++;
        Widget newNode = scene.addNode(hm);
        //TODO
        CustomWidget cw = new CustomWidget(newNode, new model.Delay(hm, null, null, 1), hm);
        Constants.listOfCustomWidgets.add(cw);
        Constants.hashOfCustomWidgets.put(newNode, cw);
        scene.getSceneAnimator().animatePreferredLocation(newNode, point2);
        scene.validate();
        newNode.getActions();
    }

    void addAdc() {
        Point point2 = new Point(0, 0);
//        String hm = "Push Button" + (nodeCount++);
        String hm = "ADC" + AdcCount++;
        Widget newNode = scene.addNode(hm);
        //TODO
        CustomWidget cw = new CustomWidget(newNode, new model.Delay(hm, null, null, 1), hm);
        Constants.listOfCustomWidgets.add(cw);
        Constants.hashOfCustomWidgets.put(newNode, cw);
        scene.getSceneAnimator().animatePreferredLocation(newNode, point2);
        scene.validate();
        newNode.getActions();
    }

    void addStart() {
        Point point3 = new Point(0, 250);
//        String hm = "2#Timer" + (nodeCount++);
        String ls = "Start";
        Widget newNode = scene.addNode(ls);
        Constants.startWidget = new CustomWidget(newNode, null, ls);
        Constants.listOfCustomWidgets.add(Constants.startWidget);
        Constants.hashOfCustomWidgets.put(newNode, Constants.startWidget);
        scene.getSceneAnimator().animatePreferredLocation(newNode, point3);
        scene.validate();
    }

    public void addEnd() {
        Point point4 = new Point(800, 250);
//        String hm = "2#Timer" + (nodeCount++);
        String hm = "End";
        Widget newNode = scene.addNode(hm);
        Constants.endWidget = new CustomWidget(newNode, null, hm);
        Constants.listOfCustomWidgets.add(Constants.endWidget);
        Constants.hashOfCustomWidgets.put(newNode, Constants.endWidget);
        scene.getSceneAnimator().animatePreferredLocation(newNode, point4);
        scene.validate();
    }

    void addKeypad() {
        Point point2 = new Point(0, 0);
//        String hm = "Push Button" + (nodeCount++);
        String hm = "Keypad" + KeypadCount++;
        Widget newNode = scene.addNode(hm);
        //TODO
        CustomWidget cw = new CustomWidget(newNode, new model.Delay(hm, null, null, 1), hm);
        Constants.listOfCustomWidgets.add(cw);
        Constants.hashOfCustomWidgets.put(newNode, cw);
        scene.getSceneAnimator().animatePreferredLocation(newNode, point2);
        scene.validate();
        newNode.getActions();
    }
}
