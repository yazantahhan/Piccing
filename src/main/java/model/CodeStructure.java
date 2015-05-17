package model;

public class CodeStructure {

    public static StringBuilder defines = new StringBuilder();
    public static StringBuilder includes = new StringBuilder("#include <xc.h>\r\n#include <stdio.h>\r\n#include <string.h>\r\n");
    public static StringBuilder globalVars = new StringBuilder();
    public static StringBuilder prototypes = new StringBuilder("void setup();\r\n");
    public static StringBuilder localVars = new StringBuilder("");
    public static StringBuilder main = new StringBuilder("int main(){\r\nsetup();\r\n");
    public static StringBuilder mainLoop = new StringBuilder("while(1){\r\n");
    public static StringBuilder functions = new StringBuilder();
    public static StringBuilder setup = new StringBuilder("void setup(){\r\n");
    public static StringBuilder configBits = new StringBuilder(getConfigBits());
    public static StringBuilder isr = new StringBuilder("void interrupt isr(void){\r\n");

    private static String getConfigBits() {
        return "#define _XTAL_FREQ 4000000\r\n"
                + "#pragma config FOSC = XTPLL_XT\r\n"
                + "#pragma config WDT = ON\r\n"
                + "#pragma config WDTPS = 32768\r\n"
                + "#pragma config PWRT = OFF\r\n"
                + "#pragma config BOR = ON\r\n"
                + "#pragma config LVP = ON\r\n"
                + "#pragma config CPD = OFF\r\n"
                + "#pragma config MCLRE = ON\r\n"
                + "#pragma config WRT0 = OFF\r\n"
                + "#pragma config WRT1 = OFF\r\n"
                + "#pragma config WRT2 = OFF\r\n"
                + "#pragma config WRT3 = OFF\r\n";
    }
}
