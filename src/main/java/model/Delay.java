/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Admin
 */
public class Delay {

    public static String getStartTemplate(int delayMs) {
        
        return "Delay10KTCYx(" + delayMs/10 + ");\r\n";
    }
}
