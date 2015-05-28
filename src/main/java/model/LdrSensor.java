/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import desktopapplication1.GraphSceneImpl;
import java.util.ArrayList;
import javax.naming.spi.DirStateFactory.Result;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author Admin
 */
public class LdrSensor extends Component {

    public static final String GREATER = "Greater than";
    public static final String LESSTHAN = "Less than";
    public static final String EQUALS = "equals";
    private String threshold;
    private String type;
    private String comparetorSelected;
    private String pin = "";
    private boolean isFirstTime = true;
    private ArrayList<String> listOfComp = new ArrayList<String>();
    private int compCount = -1;

    public LdrSensor(String name, Component input, Component output, String pin, String value, String type, String comparetorSelected) {
        super(name, input, output);
        this.threshold = value;
        this.type = type;
        this.comparetorSelected = comparetorSelected;
        this.pin = pin;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getComparetorSelected() {
        return comparetorSelected;
    }

    public void setComparetorSelected(String comparetorSelected) {
        this.comparetorSelected = comparetorSelected;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return threshold;
    }

    public void setValue(String value) {
        this.threshold = value;
    }

    //TODO code for sensors
    @Override
    public String getComponentsCode() {
        StringBuilder sb = new StringBuilder();
        if (Constants.microcontroller.compareTo("ARDUINO") == 0) {
            if (isFirstTime) {
                isFirstTime = false;
                CodeStructure.globalVarsA.append("int light=0;\r\n");
                sb.append("light = analogRead("+ pin + ")/4;\r\nlight = map(light, 0, 260, 0, 9);" + "delay(100);\r\n");
            }
            if (listOfComp.get(compCount).compareTo(LESSTHAN) == 0) {
                sb.append("if (light <").append(threshold).append(" ){");
            } else if (listOfComp.get(compCount).compareTo(EQUALS) == 0) {
                sb.append("if (light ==").append(threshold).append(" ){");
            } else {
                sb.append("if (light >").append(threshold).append(" ){");
            }
        }
        compCount--;
        return sb.toString();
    }

    @Override
    public void showInputPinsDialog() {
    }

    @Override
    public void showOutputPinsDialog() {
        String comparators[] = {"Greater than", "equals", "Less than"};
        JComboBox<String> combo = new JComboBox(comparators);

        final JComponent[] inputs = new JComponent[]{
            new JLabel("Comparator"),
            combo
        };
        JOptionPane.showMessageDialog(null, inputs, "Select output pin", JOptionPane.PLAIN_MESSAGE);
        String comparator = (String) combo.getSelectedItem();
        GraphSceneImpl.resultedStr = comparator;
        listOfComp.add(comparator);
         compCount++;
    }

    @Override
    public void showConfigDialog() {
        JTextField valueTF = new JTextField();
        valueTF.setText(threshold);
        final JComponent[] inputs = new JComponent[]{
            new JLabel("Threshold (From 0-9) "),
            valueTF
        };
        JOptionPane.showMessageDialog(null, inputs, "Configration", JOptionPane.PLAIN_MESSAGE);
        threshold = valueTF.getText();

    }

    @Override
    public String getPrintedValue() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
