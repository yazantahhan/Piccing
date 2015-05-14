/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
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
public class Sensor extends Component {
    
    public static final String GREATER = "greater";
    public static final String LESSTHAN = "less";
    public static final String EQUALS = "equals";
    private String value;
    private String type;
    private String comparetorSelected;
    private String pin = "";
    
    public Sensor(String name, Component input, Component output, String pin, String value, String type, String comparetorSelected) {
        super(name, input, output);
        this.value = value;
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
        return value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }

    //TODO code for sensors
    @Override
    public String getComponentsCode() {
        return "";
    }
    
    @Override
    public void showInputPinsDialog() {
    }
    
    @Override
    public void showOutputPinsDialog() {
        String comparators[]= {"Greater than","equals","Less than"};
        JComboBox<String> combo = new JComboBox(comparators);
        
        final JComponent[] inputs = new JComponent[]{
            new JLabel("Comparator"),
            combo            
        };
        JOptionPane.showMessageDialog(null, inputs, "Select output pin", JOptionPane.PLAIN_MESSAGE);
        String comparator  = (String) combo.getSelectedItem();
        
    }
    
    @Override
    public void showConfigDialog() {
        JTextField valueTF = new JTextField();
        valueTF.setText(value);
        final JComponent[] inputs = new JComponent[]{
            new JLabel("Threshold"),
            valueTF
        };
        JOptionPane.showMessageDialog(null, inputs, "Configration", JOptionPane.PLAIN_MESSAGE);
        value = valueTF.getText();
    }
}
