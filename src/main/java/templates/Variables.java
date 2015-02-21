/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package templates;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class Variables {
    
    private static ArrayList<String> arrayOfVars = new ArrayList();
    
    public static String getNewVariable() {
        String var = "";
        if (arrayOfVars.isEmpty()) {
            var = "a";
            arrayOfVars.add(var);
        } else {
            var = arrayOfVars.get(arrayOfVars.size() - 1);
            int len = var.length() - 1;
            if (var.charAt(len) == 'z') {
                var = var.substring(0, len) + 'a' + 'a';
                arrayOfVars.add(var);
            } else {
                String part = var.substring(0, len);
                var = part + new Character((char) (var.charAt(len) + 1));
                arrayOfVars.add(var);
            }
        }
        return var;
    }
}
