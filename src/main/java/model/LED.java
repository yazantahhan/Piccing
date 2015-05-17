package model;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import jsonModel.LedJson;

public class LED extends Component {

    private boolean willTurnOn = false;
    private String pin = "";
    private String color = "";

    public LED(String name, Component input, Component output, String pin, String color, boolean willTurnOn) {
        super(name, input, output);
        this.pin = pin;
        this.color = color;
        this.willTurnOn = willTurnOn;
        
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public boolean isWillTurnOn() {
        return willTurnOn;
    }

    public void setWillTurnOn(boolean willTurnOn) {
        this.willTurnOn = willTurnOn;
    }

    @Override
    public String getComponentsCode() {
        CodeStructure.localVars.append(pin).append("=0;\r\n");
        if (willTurnOn) {
            return getTurnOnTemplate(pin);
        } else {
            return getTurnOffTemplate(pin);
        }
    }

    private String getTurnOnTemplate(String pin) {
        String x = pin + "=1;\r\n";
        return x;
    }

    private String getTurnOffTemplate(String pin) {
        String x = pin + "=0;\r\n";
        return x;
    }

    @Override
    public void showInputPinsDialog() {
    }

    @Override
    public void showOutputPinsDialog() {
    }

    @Override
    public void showConfigDialog() {
        ArrayList<String> listOfColors = ((LedJson)(Constants.listOfJsonComponents.get("LED"))).getAvailabeColors();
        HashMap<String, String> listofPins=((LedJson)(Constants.listOfJsonComponents.get("LED"))).getColorPinMapping();
        ArrayList<String> ready = new ArrayList<String>();
        for(int i=0;i<listofPins.size();i++){
            String currentColor = listOfColors.get(i);
            String x =  currentColor+ "  -----> "+ listofPins.get(currentColor);
            ready.add(x);
            
        }
           
        JComboBox<String> combo = new JComboBox(ready.toArray());
        
        final JComponent[] inputs = new JComponent[]{
            new JLabel("Color"),
            combo            
        };
        JOptionPane.showMessageDialog(null, inputs, "Configration", JOptionPane.PLAIN_MESSAGE);
        color = ((String) combo.getSelectedItem()).split(" ")[0];
        pin=((LedJson)Constants.listOfJsonComponents.get("LED")).getColorPinMapping().get(color);
    }

    @Override
    public String getPrintedValue() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
