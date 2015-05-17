/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.TextField;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Admin
 */
public class Delay extends Component {

    private int delayMs;

    public Delay(String name, Component input, Component output, int delayMs) {
        super(name, input, output);
        this.delayMs = delayMs;
    }

    @Override
    public String getComponentsCode() {
        return "__delay_ms(" + delayMs + ");\r\n";
    }

    @Override
    public void showInputPinsDialog() {
    }

    @Override
    public void showOutputPinsDialog() {
    }

    @Override
    public void showConfigDialog() {
        JTextField delayTF = new JTextField();
        delayTF.setText(String.valueOf(delayMs));
        final JComponent[] inputs = new JComponent[]{
            new JLabel("set delay in millisecond"),
            delayTF            
        };
        JOptionPane.showMessageDialog(null, inputs, "Configration", JOptionPane.PLAIN_MESSAGE);
        System.out.println(delayTF.getText());
        delayMs = Integer.valueOf(delayTF.getText());
        
    }

    @Override
    public String getPrintedValue() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
