/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Admin
 */
public class Motor extends Component{

    private String pin1;
    private String pin2;

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
        return " SetDCPWM1(1024);";
    }

    @Override
    public void showInputPinsDialog() {
    }

    @Override
    public void showOutputPinsDialog() {
    }

    @Override
    public void showConfigDialog() {
    }

    @Override
    public String getPrintedValue() {
        return "";
    }
    
}
