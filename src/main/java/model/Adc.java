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

    private String name = "";
    private Component input = null;
    private Component output = null;
    private String pin = "";
    private int threshold;
    private String direction;

    public Adc(String name, Component input, Component output, String pin, int threshold, String direction) {
        this.name = name;
        this.input = input;
        this.output = output;
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
                +" ADCON0bits.GO = 1;\r\n"
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

    public Component getInput() {
        return input;
    }

    public void setInput(Component input) {
        this.input = input;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Component getOutput() {
        return output;
    }

    public void setOutput(Component output) {
        this.output = output;
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
}
