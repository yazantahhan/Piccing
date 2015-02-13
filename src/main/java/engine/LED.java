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
public class LED {

    public static String getTurnOnTemplate(String pin) {
        String x = pin + "=1;\r\n";
        return x;
    }

    public static String getTurnOffTemplate() {
        String x = "RB0=0;\r\n";
        return x;
    }
}
