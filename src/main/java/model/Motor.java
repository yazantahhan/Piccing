/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class Motor extends Component {

    private String pin1;
    private String pin2;
    private boolean willTurnOn = true;

    public Motor(String name, Component input, Component output, String pin1, String pin2) {
        super(name, input, output);
        this.pin1 = pin1;
        this.pin2 = pin2;
    }

    public String getPin1() {
        return pin1;
    }

    public void setPin1(String pin1) {
        this.pin1 = pin1;
    }

    public String getPin2() {
        return pin2;
    }

    public void setPin2(String pin2) {
        this.pin2 = pin2;
    }

    @Override
    public String getComponentsCode() {
        CodeStructure.setup.append(" OpenTimer2(T2_PS_1_16);OpenPWM1(0xF9);");
        if (willTurnOn) {
            return " SetDCPWM1(900);";
        } else {
            return " SetDCPWM1(0);";
        }
    }

    @Override
    public void showInputPinsDialog() {
    }

    @Override
    public void showOutputPinsDialog() {
    }

    @Override
    public void showConfigDialog() {
        String combo2Str[] = {"Turn on", "Turn off"};
        JComboBox<String> combo2 = new JComboBox(combo2Str);
        final JComponent[] inputs = new JComponent[]{
            new JLabel("When signal comes"),
            combo2
        };
        JOptionPane.showMessageDialog(null, inputs, "Configration", JOptionPane.PLAIN_MESSAGE);
        if (((String) combo2.getSelectedItem()).compareTo(combo2Str[0]) == 0) {
            willTurnOn = true;
        } else {
            willTurnOn = false;
        }
    }

    @Override
    public String getPrintedValue() {
        return "";
    }
}
