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
    private StringBuilder stringToWrite = null;
    private StringBuilder returnedString = new StringBuilder();
    private String startingString = "";
    private String endingString = "";

    public Lcd(String name, Component input, Component output, ArrayList<String> pins) {
        super(name, input, output);
        this.pins = pins;
    }

    public StringBuilder getStringToWrite() {
        return stringToWrite;
    }

    public void setStringToWrite(StringBuilder stringToWrite) {
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
        returnedString.append("Lcd_Clear();");
        if (stringToWrite.length() > 16) {
            returnedString.append("Lcd_Set_Cursor(0,0);\r\n");
            returnedString.append("Lcd_Write_String(\"").append(stringToWrite.substring(0, 16)).append("\");\r\n");
            returnedString.append("Lcd_Set_Cursor(1,0);\r\n");
            returnedString.append("Lcd_Write_String(\"").append(stringToWrite.substring(16)).append("\");\r\n");
        } else {
            returnedString.append("Lcd_Write_String(\"").append(stringToWrite).append("\");");
        }
        return returnedString.toString();
    }

    @Override
    public void showInputPinsDialog() {
    }

    @Override
    public void showOutputPinsDialog() {
//        JTextField firstName = new JTextField();
//        JTextField lastName = new JTextField();
//        JPasswordField password = new JPasswordField();
//        final JComponent[] inputs = new JComponent[]{
//            new JLabel("First"),
//            firstName,
//            new JLabel("Last"),
//            lastName,
//            new JLabel("Password"),
//            password
//        };
//        JOptionPane.showMessageDialog(null, inputs, "My custom dialog", JOptionPane.PLAIN_MESSAGE);
//        System.out.println("You entered "
//                + firstName.getText() + ", "
//                + lastName.getText() + ", "
//                + password.getText());
    }

    @Override
    public void showConfigDialog() {
        JTextField startingStringTF = new JTextField();
        JTextField endingStringTF = new JTextField();
        startingStringTF.setText(startingString);
        endingStringTF.setText(endingString);
        final JComponent[] inputs = new JComponent[]{
            new JLabel("Enter the pre text"),
            startingStringTF,
            new JLabel("Enter the post text"),
            endingStringTF            
        };
        JOptionPane.showMessageDialog(null, inputs, "Configration", JOptionPane.PLAIN_MESSAGE);
        startingString = startingStringTF.getText();
        endingString = endingStringTF.getText();
    }
}
