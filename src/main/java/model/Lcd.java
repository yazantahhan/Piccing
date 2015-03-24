/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Admin
 */
public class Lcd {

    public static String getInclude() {
        return "#include \"../../Libraries/lcd.h\"\r\n";
    }

    public static String getLcdInitTemplate() {
        return "Lcd_Init();";
    }

    public static String getLcdClearTemplate() {
        return "Lcd_Clear();";
    }

    public static String getLcdSetCursorTemplate(String row, String column) {
        return "Lcd_Set_Cursor(" + row + ", " + column + ");";
    }

    public static String getLcdWriteStringTemplate(String stringToWrite) {
        return "Lcd_Write_String(\"" + stringToWrite + "\");";
    }
}
