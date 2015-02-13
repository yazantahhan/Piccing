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
public class Button {
        public static String getButtonStartTemplate(String pin) {
        String x = "if("+pin+"){\r\n";
        return x;
    }

    public static String getButtonEndTemplate() {
        String x = "}\r\n";
        return x;
    }
}
