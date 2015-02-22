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
public class MainTemplate {

    public static String getStartMainTemplate() {
        StringBuilder x = new StringBuilder(
                "#include <xc.h>\r\n #include <plib/delays.h>\r\n"
                + "#include <plib/adc.h>\r\n #include \"../../Libraries/lcd.h\"\r\n ");
        x.append(getConfigBits());
        x.append("int ADCValue=0;\r\n");
        x.append("int main(){\r\nTRISA = 1;\r\n TRISB = 0;\r\nTRISD = 0x00;\r\nPORTBbits.RB0=0;\r\n");
        x.append(Adc.getAdcInitTemplate());
        x.append(Lcd.getLcdInitTemplate());
        x.append("while(1){\r\n");
        return x.toString();
    }

    public static String getEndMainTemplate() {
        String x = "}return 0;\r\n}";
        return x;
    }

    private static String getConfigBits() {
        return "#define _XTAL_FREQ 4000000\r\n"
                + "#pragma config PLLDIV = 1\r\n       "
                + "#pragma config CPUDIV = OSC1_PLL2\r\n"
                + "#pragma config USBDIV = 1\r\n       "
                + "#pragma config FOSC = EC_EC \r\n    "
                + "#pragma config FCMEN = OFF \r\n     "
                + "#pragma config IESO = OFF   \r\n    "
                + "#pragma config PWRT = OFF   \r\n    "
                + "#pragma config BOR = OFF    \r\n    "
                + "#pragma config BORV = 3      \r\n   "
                + "#pragma config VREGEN = OFF  \r\n   "
                + "#pragma config WDT = OFF     \r\n   "
                + "#pragma config WDTPS = 32768  \r\n  "
                + "#pragma config CCP2MX = ON    \r\n  "
                + "#pragma config PBADEN = ON    \r\n  "
                + "#pragma config LPT1OSC = OFF  \r\n  "
                + "#pragma config MCLRE = ON    \r\n   "
                + "#pragma config STVREN = ON   \r\n   "
                + "#pragma config LVP = OFF     \r\n   "
                + "#pragma config ICPRT = OFF   \r\n   "
                + "#pragma config XINST = OFF   \r\n   "
                + "#pragma config CP0 = OFF     \r\n   "
                + "#pragma config CP1 = OFF    \r\n    "
                + "#pragma config CP2 = OFF    \r\n    "
                + "#pragma config CP3 = OFF    \r\n    "
                + "#pragma config CPB = OFF      \r\n  "
                + "#pragma config CPD = OFF     \r\n   "
                + "#pragma config WRT0 = OFF    \r\n   "
                + "#pragma config WRT1 = OFF   \r\n    "
                + "#pragma config WRT2 = OFF    \r\n   "
                + "#pragma config WRT3 = OFF   \r\n    "
                + "#pragma config WRTC = OFF   \r\n    "
                + "#pragma config WRTB = OFF    \r\n   "
                + "#pragma config WRTD = OFF    \r\n   "
                + "#pragma config EBTR0 = OFF   \r\n   "
                + "#pragma config EBTR1 = OFF   \r\n   "
                + "#pragma config EBTR2 = OFF   \r\n   "
                + "#pragma config EBTR3 = OFF   \r\n   "
                + "#pragma config EBTRB = OFF\r\n";
    }
}
