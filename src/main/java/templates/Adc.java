/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package templates;

/**
 *
 * @author Admin
 */
public class Adc {

    public static String getAdcInitTemplate() {
        return "OpenADC(ADC_FOSC_2 & ADC_RIGHT_JUST & ADC_8_TAD, ADC_CH0 & ADC_INT_OFF &"
                + " ADC_REF_VDD_VSS, ADC_0ANA);\r\n";
    }

    public static String getConvertAdcTemplate() {
        return "ConvertADC();\r\n"
                + "while (ADCON0bits.nDONE) continue;\r\n"
                + "ADCValue = ReadADC();\r\n";
    }
}
