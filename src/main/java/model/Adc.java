/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Admin
 */
public class Adc extends Component {

    private String pin = "";
    private int threshold;
    private String direction;

    public Adc(String name, Component input, Component output, String pin, int threshold, String direction) {
        super(name, input, output);
        this.pin = pin;
        this.direction = direction;
        this.threshold = threshold;
    }

    private static String getAdcInitTemplate() {
        return "ADCON0bits.ADCS = 0; //Selecting the clk division factor = FOSC/2\r\n"
                + "ADCON1bits.ADCS2 = 0; //Selecting the clk division factor = FOSC/2\r\n"
                + "ADCON1bits.ADFM = 1; //Result right justified\r\n"
                + "ADCON1bits.PCFG = 0; //Setting all the analog ports as Analog inputs\r\n"
                + "ADCON0bits.ADON = 1; //Turns on ADC module\r\n"
                + "ADCON0bits.CHS = 0; //Selects channel 0 ( AN0 )\r\n";
    }

    private static String getConvertAdcTemplate(String pin, int threshold, String direction) {
        StringBuilder code = new StringBuilder("__delay_us(25);\r\n"
                + " ADCON0bits.GO = 1;\r\n"
                + "while (ADCON" + pin + "bits.nDONE) continue;\r\n"
                + "ADCValue = (ADRESH<<8) + ADRESL ;\r\n");
        if (direction.equals("more than")) {
            code.append("if(ADCValue > ").append(threshold).append(") ");
        } else if (direction.equals("less than")) {
            code.append("if(ADCValue < ").append(threshold).append(") ");
        } else if (direction.equals("equals")) {
            code.append("if(ADCValue == ").append(threshold).append(") ");
        } else {
            code.append("if(ADCValue != ").append(threshold).append(") ");
        }
        return code.toString();
    }

    @Override
    public String getComponentsCode() {
        CodeStructure.setup.append(getAdcInitTemplate());
        CodeStructure.globalVars.append("int ADCValue=0;\r\n");
        return getConvertAdcTemplate(pin, threshold, direction);
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
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
}
