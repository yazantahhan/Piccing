/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import jsonModel.ButtonJson;
import jsonModel.ComponentJson;
import jsonModel.LedJson;
import model.Button;
import model.Component;
import model.Constants;
import model.LED;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openide.util.Exceptions;

public class HeaderParser {

    public static void parseHeader(String fileName) throws URISyntaxException {
        JSONParser parser = new JSONParser();
        HashMap<String, ComponentJson> listOfJsonComps = new HashMap<String, ComponentJson>();
        try {
            File f = new File(HeaderParser.class.getResource(fileName).toURI());
            JSONArray headerJson = (JSONArray) parser.parse(new FileReader(f));
            String name;
            String pin;
            String listOfPins;
            JSONObject jsonObj;
            for (int i = 0; i < headerJson.size(); i++) {
                jsonObj = ((JSONObject) headerJson.get(i));
                name = (String) jsonObj.get("name");
                if (name.contains("LED")) {
                    if (!listOfJsonComps.containsKey("LED")) {
                        listOfJsonComps.put("LED", new LedJson("LED", null));
                        Constants.listOfAvailableComponentsStrings.add(name);
                    }
                    String color = (String) jsonObj.get("color");
                    ((LedJson) listOfJsonComps.get("LED")).getColorPinMapping().put(color, (String) jsonObj.get("pin"));
                    ((LedJson) listOfJsonComps.get("LED")).getAvailabeColors().add(color);
                } else if (name.contains("BUTTON")) {
                    listOfPins = (String) jsonObj.get("pin");
                    listOfJsonComps.put("BUTTON", new ButtonJson("BUTTON", new ArrayList<String>(Arrays.asList(listOfPins.split(",")))));
                    Constants.listOfAvailableComponentsStrings.add(name);
                }
            }
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        } catch (ParseException ex) {
            Exceptions.printStackTrace(ex);
        }
        Constants.listOfJsonComponents = listOfJsonComps;
    }
}