/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import desktopapplication1.GraphSceneImpl;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Admin
 */
public class TempSensor extends Component {

    public static final String GREATER = "Greater than";
    public static final String LESSTHAN = "Less than";
    public static final String EQUALS = "equals";
    private String threshold;
    private String type;
    private String comparetorSelected;
    private String pin = "";
    private boolean isFirstTime = true;
    private int compCount = -1;
    private ArrayList<String> listOfComp = new ArrayList<String>();

    public TempSensor(String name, Component input, Component output, String pin, String value, String type, String comparetorSelected) {
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

    public String getThreshold() {
        return threshold;
    }

    public void setThreshold(String threshold) {
        this.threshold = threshold;
    }

    public int getCompCount() {
        return compCount;
    }

    public void setCompCount(int compCount) {
        this.compCount = compCount;
    }

    public ArrayList<String> getListOfComp() {
        return listOfComp;
    }

    public void setListOfComp(ArrayList<String> listOfComp) {
        this.listOfComp = listOfComp;
    }

    @Override
    public String getComponentsCode() {
        StringBuilder sb = new StringBuilder();
        if (Constants.microcontroller.compareTo("ARDUINO") == 0) {
            if (isFirstTime) {
                isFirstTime = false;
                CodeStructure.globalVarsA.append("int tempC=0;\r\n");
                CodeStructure.functionsA.append("void readTemp(){"
                        + "int reading = analogRead(" + pin + ");  "
                        + "float voltage = reading * 5.0;"
                        + "voltage /= 1024.0;"
                        + "tempC = (voltage - 0.5) * 100 ;"
                        + "delay(100);"
                        + "}");
                sb.append("readTemp();\r\n");
            }
            if (listOfComp.get(compCount).compareTo(LESSTHAN) == 0) {
                sb.append("if (tempC <").append(threshold).append(" ){");
            } else if (listOfComp.get(compCount).compareTo(EQUALS) == 0) {
                sb.append("if (tempC ==").append(threshold).append(" ){");
            } else {
                sb.append("if (tempC >").append(threshold).append(" ){");
            }
            compCount--;
        } else {
            if (isFirstTime) {
                isFirstTime = false;
                CodeStructure.functions.append("void readTemp() {unsigned int ADCResult =0;unsigned int ADCResultMv=0;ConvertADC();"
                        + "while(BusyADC());"
                        + "ADCResult = ReadADC();\r\n"
                        + "ADCResultMv= ADCResult * (5000/1024);"
                        + "    ADCResultMv = ADCResultMv / 10;"
                        + "tempC = 1.2113 * ADCResultMv + 1.2903;"
                        + "}");
                CodeStructure.setup.append("OpenADC(ADC_FOSC_2 & ADC_RIGHT_JUST & ADC_12_TAD,ADC_CH0 & ADC_INT_OFF & ADC_REF_VDD_VSS, ADC_1ANA);");
                CodeStructure.globalVars.append("int tempC=0;\r\n");
                sb.append("readTemp();\r\n");
            }

            if (listOfComp.get(compCount).compareTo(LESSTHAN) == 0) {
                sb.append("if (tempC <").append(threshold).append(" ){");
            } else if (listOfComp.get(compCount).compareTo(EQUALS) == 0) {
                sb.append("if (tempC ==").append(threshold).append(" ){");
            } else {
                sb.append("if (tempC >").append(threshold).append(" ){");
            }
            compCount--;

        }
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
            new JLabel("Threshold (Celsius)"),
            valueTF
        };
        JOptionPane.showMessageDialog(null, inputs, "Configration", JOptionPane.PLAIN_MESSAGE);
        threshold = valueTF.getText();

    }

    @Override
    public String getPrintedValue() {
        return "sprintf(printedStr, \"%d\", tempC);";
    }
}
