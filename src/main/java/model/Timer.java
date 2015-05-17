/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Admin
 */
public class Timer extends Component {

    //256-((delay*freq)/(4*ps))
    private int delay;
    private int minDelay = 0;
    private ArrayList<String> softwareTmrs = new ArrayList<String>();
    private int numOfInts = 0;

    public Timer(String name, Component input, Component output, int delay) {
        super(name, input, output);
        this.delay = delay;
    }

    public Timer(String name, Component input, Component output) {
        super(name, input, output);
        delay = 100;
    }

    public int getMinDelay() {
        return minDelay;
    }

    public void setMinDelay(int minDelay) {
        this.minDelay = minDelay;
    }

    private void initTimer0() {
        CodeStructure.setup.append("OPTION_REGbits.T0CS = 0; \r\n" + "OPTION_REGbits.PSA = 0;\r\n"
                + "INTCONbits.T0IE = 1; \r\n" + "INTCONbits. GIE = 1;\r\n"
                + "OPTION_REGbits.PS0 =1;\r\n" + "OPTION_REGbits.PS1 =0;\r\n"
                + "OPTION_REGbits.PS2 =0;\r\n TMR0=6\r\n");
    }

    @Override
    public String getComponentsCode() {
        initTimer0();
        numOfInts++;
        softwareTmrs.add(Variables.getNewVariable());
        String currentInt = softwareTmrs.get(softwareTmrs.size() - 1);
        CodeStructure.globalVars.append(currentInt).append("=0;\r\n");
        CodeStructure.isr.append("if (INTCONbits.T0IF && INTCONbits.T0IE){");
        CodeStructure.isr.append("T0IF_bit = 0\r\n TMR0=6\r\n").append(currentInt).append("++;\r\n" + "if (").append(currentInt).append("==").append(delay).append("){");
        CodeStructure.isr.append("PORTB ^= 0xFF");
        return "";
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
        delayTF.setText(String.valueOf(delay));
        final JComponent[] inputs = new JComponent[]{
            new JLabel("set delay in millisecond"),
            delayTF
        };
        JOptionPane.showMessageDialog(null, inputs, "Configration", JOptionPane.PLAIN_MESSAGE);
        delay = Integer.valueOf(delayTF.getText());
    }
    //TODO mapping 
    
//    private HashMap<String, String> getPrescalerAndCount(int delay) {
//        InitTMR0 = 256 - ( DELAY * Frequency ) / ( 4* Prescaler)
//        HashMap<String, String> map = new HashMap<String, String>();
//        if (delay > 65536) {
//            map.put("isSoftwareInt", "1");
//            map.put("count", "0");
//            map.put("prescaler", "256");
//        }else if(delay > 32768){
//            map.put("isSoftwareInt", "0");
//            map.put("count", "0");
//            map.put("prescaler", "128"); 
//        }else if(delay > 16384){
//            map.put("isSoftwareInt", "0");
//            map.put("count", "0");
//            map.put("prescaler", "64"); 
//        }else if(delay > 8192){
//            map.put("isSoftwareInt", "0");
//            map.put("count", "0");
//            map.put("prescaler", "32"); 
//        }else if(delay > 4096){
//            map.put("isSoftwareInt", "0");
//            map.put("count", "0");
//            map.put("prescaler", "16"); 
//        }else if(delay > 2048){
//            map.put("isSoftwareInt", "0");
//            map.put("count", "0");
//            map.put("prescaler", "8"); 
//        }else if(delay > 1024){
//            map.put("isSoftwareInt", "0");
//            map.put("count", "0");
//            map.put("prescaler", "4"); 
//        }else if(delay > 512){
//            map.put("isSoftwareInt", "0");
//            map.put("count", "0");
//            map.put("prescaler", "2"); 
//        }else if(delay > 256){
//            map.put("isSoftwareInt", "0");
//            map.put("count", "0");
//            map.put("prescaler", "1"); 
//        }
//        return map;
//    }

    @Override
    public String getPrintedValue() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
