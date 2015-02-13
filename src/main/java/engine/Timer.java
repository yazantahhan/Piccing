/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

/**
 *
 * @author Admin
 */
public class Timer {

    public static String initTimer0(String preScaler) {
        return "OPTION_REG &=" + preScaler + ";\r\nT0IE = 1;\r\nGIE = 1;\r\n";
    }

    public static String initInt() {
        return "if(T0IF){\r\nT0IF = 0;\r\n";
    }

    public static String getClosedBrace() {
        return "}\r\n";
    }
}
