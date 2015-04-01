/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Admin
 */
public class Timer extends Component {

    //256-((delay*freq)/(4*ps))
    private int delay;

    public Timer(String name, Component input, Component output, int delay) {
        super(name, input, output);
        this.delay = delay;
    }

    public Timer(String name, Component input, Component output) {
        super(name, input, output);
        delay = 100;
    }

    private void initTimer0(String preScaler) {
        CodeStructure.setup.append("OPTION_REG &=").append(preScaler).append(";\r\nT0IE = 1;\r\nGIE = 1;\r\n");
    }

    @Override
    public String getComponentsCode() {
        return "__delay_ms(" + delay + ");\r\n";
    }
}
