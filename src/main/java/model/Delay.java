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
public class Delay extends Component{
    
    private int delayMs;

    public Delay(String name, Component input, Component output, int delayMs) {
        super(name, input, output);
        this.delayMs = delayMs;
    }
    
    @Override
    public String getComponentsCode() {
        return "__delay_ms(" + delayMs + ");\r\n";
    }
}
