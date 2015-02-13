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
public class MainTemplate {

    public static String getStartMainTemplate() {
        StringBuilder x = new StringBuilder(
                "#define _LEGACY_HEADERS\r\n #include <pic.h>\r\n #include \"../../Libraries/delay.h\"\r\n ");
        x.append("#pragma config FOSC = XT \r\n#pragma config WDTE = OFF\r\n #pragma config PWRTE = OFF\r\n"
                + " #pragma config BOREN = ON\r\n #pragma config LVP = OFF\r\n #pragma config CPD = OFF\r\n "
                + "#pragma config WRT = OFF\r\n #pragma config CP = OFF \r\n");
        x.append("int main(){\r\nTRISD = 1; TRISB = 0;\r\nRB0=0;\r\nwhile(1){\r\n");
        return x.toString();
    }

    public static String getEndMainTemplate() {
        String x = "}return 0;\r\n}";
        return x;
    }

}
