/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Admin
 */
public class Lcd extends Component {

    private ArrayList<String> pins;
    private String stringToWrite = null;
    private StringBuilder returnedString = new StringBuilder();
    private String startingString = "";
    private String endingString = "";

    public Lcd(String name, Component input, Component output, ArrayList<String> pins) {
        super(name, input, output);
        this.pins = pins;
    }

    public String getStringToWrite() {
        return stringToWrite;
    }

    public void setStringToWrite(String stringToWrite) {
        this.stringToWrite = stringToWrite;
    }

    public ArrayList<String> getPins() {
        return pins;
    }

    public void setPins(ArrayList<String> pins) {
        this.pins = pins;
    }

    @Override
    public String getComponentsCode() {
        CodeStructure.includes.append("#include \"../../Libraries/lcd.h\"\r\n");
        CodeStructure.setup.append("Lcd_Init();\r\n");
        CodeStructure.globalVars.append("char *printedStr;\r\nchar *buff;\r\n");
        CodeStructure.functions.append(getItoaFunc());
        returnedString.append("Lcd_Clear();");
        returnedString.append("Lcd_Set_Cursor(0,0);\r\n");
        returnedString.append("strcpy(printedStr,itoa1(buff, tempC));\r\n");
        returnedString.append("strcat(printedStr,\"").append(startingString).append("\");");
        returnedString.append("Lcd_Write_String(printedStr);");
        return returnedString.toString();
    }

    @Override
    public void showInputPinsDialog() {
    }

    @Override
    public void showOutputPinsDialog() {
    }

    @Override
    public void showConfigDialog() {
        JTextField startingStringTF = new JTextField();
        JTextField endingStringTF = new JTextField();
        startingStringTF.setText(startingString);
        endingStringTF.setText(endingString);
        final JComponent[] inputs = new JComponent[]{
            new JLabel("Enter the post text"),
            startingStringTF,};
        JOptionPane.showMessageDialog(null, inputs, "Configration", JOptionPane.PLAIN_MESSAGE);
        startingString = startingStringTF.getText();
    }

    @Override
    public String getPrintedValue() {
        return "";
    }

    private String getItoaFunc() {
        return "char *itoa1(char *buffer, int i) {"
                + "   unsigned int n;"
                + "   unsigned int negate = 0;"
                + "   int c = 6;"
                + "   if (i < 0) {"
                + "       negate = 1;"
                + "       n = -i;"
                + "   } else if (i == 0) {"
                + "       buffer[0] = '0';"
                + "       buffer[1] = 0;"
                + "       return buffer;"
                + "   } else {"
                + "       n = i;"
                + "   }"
                + "   buffer[c--] = 0;"
                + "   do {"
                + "       buffer[c--] = (n % 10) + '0';"
                + "       n = n / 10;"
                + "   } while (n);"
                + "   if (negate) {"
                + "       buffer[c--] = '-';"
                + "   }"
                + "   return &buffer[c + 1];"
                + "}";
    }
}
