/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package templates;

/**
 *
 * @author Admin
 */
public class LED {

    public static String getTurnOnTemplate(String port, String pin) {
        String x = "PORT" + port + "bits." + pin + "=1;\r\n";
        return x;
    }

    public static String getTurnOffTemplate(String port, String pin) {
        String x = "PORT" + port + "bits." + pin + "=0;\r\n";
        return x;
    }
}
